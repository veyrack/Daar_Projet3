package com.daar.projet3.models;

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
    private int age;
    private String email;
    private String numTel;
    private List<String> competences;
    private List<String> motsCles;
}
