package com.daar.projet3.ressources;

import com.daar.projet3.models.CV;
import com.daar.projet3.services.CvService;
import com.daar.projet3.utils.FormatException;
import com.daar.projet3.utils.ParsingPDF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    public ResponseEntity<CV> create(@RequestParam("prenom") String prenom,
                                     @RequestParam("nom") String nom,
                                     @RequestPart("file") MultipartFile cv) {
        CV res = null;
        try {
            res = ParsingPDF.parsePDF(cv,prenom,nom);
        } catch (FormatException e) {
            e.printStackTrace();
        }
        return ResponseEntity.created(URI.create("/CV/post")).body(cvService.save(res));
    }
}
