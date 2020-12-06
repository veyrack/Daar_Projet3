package com.daar.projet3.services;

import com.daar.projet3.models.CV;
import com.daar.projet3.repositories.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class CvServiceImpl implements CvService{

    private CvRepository cvRepository;

    @Autowired
    public void setCvRepository(CvRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public CV save(CV cv) {
        return cvRepository.save(cv);
    }

    public void delete(CV cv) {
        cvRepository.delete(cv);
    }

    public Iterable<CV> findAll() {
        return cvRepository.findAll();
    }

    public CV findOne(String id) {
        if (cvRepository.findById(id).isPresent())
            return cvRepository.findById(id).get();
        else return null;
    }

    public List<CV> findByPrenom(String filtre) {
        return cvRepository.findByPrenom(filtre);
    }

    public List<CV> findByNom(String filtre) {
        return cvRepository.findByNom(filtre);
    }

    public List<CV> findByNomAndPrenom(String nom, String prenom) {
        return cvRepository.findByNomAndPrenom(nom,prenom);
    }

    public List<CV> findByMotsCles(String filtre) {
        return cvRepository.findByMotsCles(filtre);
    }

    public List<CV> findByMotsCles(List<String> filtre) {
        return cvRepository.findByMotsCles(filtre);
    }

    public List<CV> findByCompetences(String filtre){
        return cvRepository.findByCompetences(filtre);
    }

    public List<CV>  findByCompetences(List<String> filtres){
        return cvRepository.findByCompetences(filtres);
    }

    /*
    public Page<CV> findByPrenom(String filtre, PageRequest pageRequest) {
        return cvRepository.findByPrenom(filtre,pageRequest);
    }

    public Page<CV> findByNom(String filtre, PageRequest pageRequest) {
        return cvRepository.findByNom(filtre,pageRequest);
    }

    public Page<CV> findByMotsCles(String filtre, PageRequest pageRequest) {
        return cvRepository.findByMotsCles(filtre,pageRequest);
    }

    public Page<CV> findByMotsCles(List<String> filtre, PageRequest pageRequest) {
        return cvRepository.findByMotsCles(filtre,pageRequest);
    }
     */

}
