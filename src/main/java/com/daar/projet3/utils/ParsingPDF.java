package com.daar.projet3.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.daar.projet3.models.CV;
import com.daar.projet3.models.Competence;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;


public class ParsingPDF {

    static int id = 0;

    public static void main(String[] args) {
        try{
            CV cv = parsePDF("CV_Maxence.pdf","Veyrack","Lin");
            System.out.println(cv.toString());
        } catch (FormatException e) {
            System.out.println("Fichier pas au bon format");
        }

    }

    public static CV parsePDF(String filename,String prenom, String nom) throws FormatException{

        //String f = "CV_Veyrack.pdf";//"CV_Maxence.pdf";
        File file = new File ("src/main/java/com/daar/projet3/utils/ressources/"+filename);
        PDDocument doc;

        try {
            int age = 0;
            String mail = "";
            String numTel = "";
            doc = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);
            if(text.trim().equals("")) {

                doc.close();
                throw new FormatException();
                //System.out.println("VIDE");
            }

            String[] l = text.split("\n");
            List<String> motscles = new ArrayList<>();
            //On prend chaque lignes du fichier
            for(String ligne : l){
                String tmp = "";
                //On prend chaque mots de la ligne
                for(String mot : ligne.split(" |/|-|\\(|\\)|,")){
                    //On recupere l'age
                    if(mot.equals("ans")) age = Integer.parseInt(tmp);
                    else tmp = mot;
                    //On recupere le mail
                    if(mot.matches("\\w*@\\w*.\\w*")) mail = tmp;
                    //On recupere le numero
                    if(mot.matches("\\+?[0-9]{11}|[0-9]{10}")) numTel = tmp;
                    //On ajoute le mot cle dans la liste
                    try{
                        //En brut pour C# et C++ pcq c'est les seuls ou ya des symboles
                        if(mot.equals("C#"))
                            motscles.add(Competence.csharp.name());
                        if(mot.equals("C++"))
                            motscles.add(Competence.cpp.name());
                        else
                            motscles.add(Competence.valueOf(mot.toLowerCase()).toString());
                    }catch (IllegalArgumentException e){
                        //System.out.println(e.getMessage());
                        continue;
                    }
                }
                //System.out.println("line: "+s);
            }

            CV cv = new CV(String.valueOf(id++),
                    prenom,
                    nom,
                    age,
                    mail,
                    numTel,
                    motscles.stream().distinct().collect(Collectors.toList()));
            //System.out.println(cv.toString());
            doc.close();
            return cv;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    public static CV parsePDF(MultipartFile f, String prenom, String nom) throws FormatException{
        PDDocument doc;
        File file = null;

        try {
            f.transferTo(file);
            int age = 0;
            String mail = "";
            String numTel = "";
            doc = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);
            if(text.trim().equals("")) {

                doc.close();
                throw new FormatException();
                //System.out.println("VIDE");
            }

            String[] l = text.split("\n");
            List<String> motscles = new ArrayList<>();
            //On prend chaque lignes du fichier
            for(String ligne : l){
                String tmp = "";
                //On prend chaque mots de la ligne
                for(String mot : ligne.split(" |/|-|\\(|\\)|,")){
                    //On recupere l'age
                    if(mot.equals("ans")) age = Integer.parseInt(tmp);
                    else tmp = mot;
                    //On recupere le mail
                    if(mot.matches("\\w*@\\w*.\\w*")) mail = tmp;
                    //On recupere le numero
                    if(mot.matches("\\+?[0-9]{11}|[0-9]{10}")) numTel = tmp;
                    //On ajoute le mot cle dans la liste
                    try{
                        //En brut pour C# et C++ pcq c'est les seuls ou ya des symboles
                        if(mot.equals("C#"))
                            motscles.add(Competence.csharp.name());
                        if(mot.equals("C++"))
                            motscles.add(Competence.cpp.name());
                        else
                            motscles.add(Competence.valueOf(mot.toLowerCase()).toString());
                    }catch (IllegalArgumentException e){
                        //System.out.println(e.getMessage());
                        continue;
                    }
                }
                //System.out.println("line: "+s);
            }

            CV cv = new CV(String.valueOf(id++),
                    prenom,
                    nom,
                    age,
                    mail,
                    numTel,
                    motscles.stream().distinct().collect(Collectors.toList()));
            //System.out.println(cv.toString());
            doc.close();
            return cv;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

}
