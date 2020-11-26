package com.daar.projet3.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "cv")
@Data
@AllArgsConstructor
public class CV {

    @Id
    private String id;
    private String prenom;
    private String nom;
    private List<String> motsCles;
}
