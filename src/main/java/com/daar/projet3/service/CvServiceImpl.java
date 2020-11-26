package com.daar.projet3.service;

import com.daar.projet3.model.CV;
import com.daar.projet3.repository.CvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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

    public Page<CV> findByPrenom(String filtre, PageRequest pageRequest) {
        return cvRepository.findByPrenom(filtre,pageRequest);
    }

    public List<CV> findByPrenom(String filtre) {
        return cvRepository.findByPrenom(filtre);
    }

    public Page<CV> findByNom(String filtre, PageRequest pageRequest) {
        return cvRepository.findByNom(filtre,pageRequest);
    }

    public List<CV> findByNom(String filtre) {
        return cvRepository.findByNom(filtre);
    }

    public Page<CV> findByMotsCles(String filtre, PageRequest pageRequest) {
        return cvRepository.findByMotsCles(filtre,pageRequest);
    }

    public List<CV> findByMotsCles(String filtre) {
        return cvRepository.findByMotsCles(filtre);
    }

    public Page<CV> findByMotsCles(List<String> filtre, PageRequest pageRequest) {
        return cvRepository.findByMotsCles(filtre,pageRequest);
    }

    public List<CV> findByMotsCles(List<String> filtre) {
        return cvRepository.findByMotsCles(filtre);
    }
}
