package com.daar.projet3.utils;

import java.io.File;
import java.io.IOException;

import com.daar.projet3.models.Competence;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class ParsingPDF {

    public static void main(String[] args) {
    //public CV parsePDF(String filename) throws FormatException{
        //String cv = filename;
        String cv = "CV_Veyrack.pdf";//"CV_Maxence.pdf";
        File file = new File ("src/main/java/com/daar/projet3/utils/ressources/"+cv);
        PDDocument doc;

        try {

            doc = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);
            if(text.trim().equals("")) {
                //throw new FormatException;
                System.out.println("VIDE");
            }
            String[] l = text.split("\n");
            for(String ligne : l){
                for(String mot : ligne.split(" |/|-|\\(|\\)|,")){
                    try{
                        //System.out.println(mot);
                        System.out.println(Competence.valueOf(mot.toLowerCase()).toString());
                    }catch (IllegalArgumentException e){
                        System.out.println(e.getMessage());
                        continue;
                    }
                }
                //System.out.println("line: "+s);
            }



            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
