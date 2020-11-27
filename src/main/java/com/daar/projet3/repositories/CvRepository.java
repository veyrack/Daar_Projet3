package com.daar.projet3.repositories;

import com.daar.projet3.models.CV;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface CvRepository extends ElasticsearchRepository<CV,String> {


    Page<CV> findByPrenom(String filtre, Pageable pageable);

    List<CV> findByPrenom(String filtre);

    Page<CV> findByMotsCles(String filtre, Pageable pageable);

    List<CV> findByMotsCles(String filtre);

    Page<CV> findByMotsCles(List<String> filtre, Pageable pageable);

    List<CV> findByMotsCles(List<String> filtre);

    Page<CV> findByNom(String filtre, PageRequest pageRequest);

    List<CV> findByNom(String filtre);
}
