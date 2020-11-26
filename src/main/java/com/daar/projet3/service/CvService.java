package com.daar.projet3.service;

import com.daar.projet3.model.CV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface CvService {

    CV save(CV cv);

    void delete(CV cv);

    Iterable<CV> findAll();

    CV findOne(String id);

    Page<CV> findByPrenom(String filtre, PageRequest pageRequest);

    List<CV> findByPrenom(String filtre);

    Page<CV> findByNom(String filtre, PageRequest pageRequest);

    List<CV> findByNom(String filtre);

    Page<CV> findByMotsCles(String filtre, PageRequest pageRequest);

    List<CV> findByMotsCles(String filtre);

    Page<CV> findByMotsCles(List<String> filtre, PageRequest pageRequest);

    List<CV> findByMotsCles(List<String> filtre);
}
