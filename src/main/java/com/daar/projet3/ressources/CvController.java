package com.daar.projet3.ressources;

import com.daar.projet3.models.CV;
import com.daar.projet3.services.CvService;
import com.daar.projet3.utils.ParsingPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/CV")
public class CvController {

    @Autowired
    private CvService cvService;

    // /CV?prenom=Jean
    @GetMapping(params = {"prenom"})
    public ResponseEntity<List<CV>> getCvByPrenom(@RequestParam("prenom") String prenom){
        return ResponseEntity.ok(cvService.findByPrenom(prenom));
    }

    // /CV/prenom/0?prenom=Jean
    @GetMapping("/prenom/{page}")
    public ResponseEntity<Page<CV>> getCvByPrenom(@RequestParam("prenom") String prenom,
                                                  @PathVariable("page") int page){
        return ResponseEntity.ok(cvService.findByPrenom(prenom, PageRequest.of(page,5)));
    }

    // /CV?nom=Lin
    @GetMapping(params = {"nom"})
    public ResponseEntity<List<CV>> getCvByNom(@RequestParam("nom") String nom){
        return ResponseEntity.ok(cvService.findByNom(nom));
    }

    // /CV/nom/0?nom=Lin
    @GetMapping("/nom/{page}")
    public ResponseEntity<Page<CV>> getCvByNom(@RequestParam("nom") String nom,
                                               @PathVariable("page") int page){
        return ResponseEntity.ok(cvService.findByNom(nom, PageRequest.of(page,5)));
    }

    // /CV?filtre=Java
    @GetMapping(params = {"filtre"})
    public ResponseEntity<List<CV>> getCvByMotsCles(@RequestParam("filtre") String filtre){
        return ResponseEntity.ok(cvService.findByMotsCles(filtre));
    }

    // /CV/filtre/0?filtre=Java
    @GetMapping("/filtre/{page}")
    public ResponseEntity<Page<CV>> getCvByMotsCles(@RequestParam("filtre") String filtre,
                                                    @PathVariable("page") int page){
        return ResponseEntity.ok(cvService.findByMotsCles(filtre, PageRequest.of(page,5)));
    }

    // /CV?filtres=Java,C
    @GetMapping(params = {"filtres"})
    public ResponseEntity<List<CV>> getCvByMotsCles(@RequestParam("filtres") List<String> filtres){
        return ResponseEntity.ok(cvService.findByMotsCles(filtres));
    }

    // /CV/filtres/0?filtres=Java,C
    @GetMapping("/filtres/{page}")
    public ResponseEntity<Page<CV>> getCvByMotsCles(@RequestParam("filtres") List<String> filtres,
                                                    @PathVariable("page") int page){
        return ResponseEntity.ok(cvService.findByMotsCles(filtres, PageRequest.of(page,5)));
    }

    // /CV
    @GetMapping
    public ResponseEntity<Iterable<CV>> getCv() {
        return ResponseEntity.ok(cvService.findAll());
    }


    @PostMapping
    public ResponseEntity<CV> create(@RequestBody CV cv) {
        return ResponseEntity.created(URI.create("/CV")).body(cvService.save(cv));
    }

    //Creation du CV à partir d'un formulaire
    //CV/file
    @PostMapping("/file")
    public ResponseEntity<CV> envoiePDF(@RequestPart("file") MultipartFile file,
                                        @RequestPart("nom") String nom,
                                        @RequestPart("prenom") String prenom,
                                        @RequestPart("mail") String mail,
                                        @RequestPart("tel") String tel)
            throws IOException {

        // La conversion d'un multipartfile en file. Mettre dans une methode dans le parsingPDF
        File convFile = new File( file.getOriginalFilename() );
        FileOutputStream fos = new FileOutputStream( convFile );
        fos.write( file.getBytes() );
        fos.close();
        //La creation du CV
        CV cv =ParsingPDF.parse(nom,prenom,mail,tel,convFile);
        //L'envoie vers la BD
        return ResponseEntity.created(URI.create("/CV")).body(cvService.save(cv));
    }
}
