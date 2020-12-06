package com.daar.projet3.services;

import com.daar.projet3.models.CV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CvService {

    CV save(CV cv);

    void delete(CV cv);

    Iterable<CV> findAll();

    CV findOne(String id);

    List<CV> findByPrenom(String filtre);

    List<CV> findByNom(String filtre);

    List<CV> findByNomAndPrenom(String nom, String prenom);

    List<CV> findByMotsCles(String filtre);

    List<CV> findByMotsCles(List<String> filtre);

    List<CV> findByCompetences(String filtre);

    List<CV> findByCompetences(List<String> filtre);

    //Page<CV> findByPrenom(String filtre, PageRequest pageRequest);
    //Page<CV> findByNom(String filtre, PageRequest pageRequest);
    //Page<CV> findByMotsCles(String filtre, PageRequest pageRequest);
    //Page<CV> findByMotsCles(List<String> filtre, PageRequest pageRequest);


}
