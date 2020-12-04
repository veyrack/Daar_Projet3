package com.daar.projet3.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.daar.projet3.models.CV;
import com.daar.projet3.models.Competence;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;


public class ParsingPDF {
    private static int cpt=0;

    public static void main(String[] args) {
        //public CV parsePDF(String filename) throws FormatException{
        //String cv = filename;
        String cv = "CV_Veyrack.pdf";//"Brunet_Maxence_CV.pdf";
        //cv= "CV_MADAFRITE1.pdf";
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
            //ArrayList<String> competences = getCompetences(l);
            //System.out.println("Le prenom : "+getPrenom(l));
            //System.out.println("Le nom : "+getNom(l));
            //for(String s : competences)
            //System.out.println(s);

            //System.out.println(getEmail(l));
            for(int i=0;i<5;i++)
                System.out.println(getAllInfo(l)[i]);


            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public static CV parse(File file){
        PDDocument doc;
        CV moncv=null;
        try {

            doc = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);
            if(text.trim().equals("")) {
                //throw new FormatException;
                System.out.println("VIDE");
            }
            String[] l = text.split("\n");
            //On récupère les infos de l'utilisateur
            String [] infos = getAllInfo(l);

            //On récupère ses compétences
            ArrayList<String> competences = getCompetences(l);

            //On récupère son adresse ? Pour check s'il est a tant de km de l'entreprise

            //On récupère ses centres d'interets

            //Creation du CV
            moncv= new CV(String.valueOf(cpt++),
                    infos[1],
                    infos[0],
                    Integer.parseInt(infos[2]),
                    infos[3],
                    infos[4],
                    competences);


            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return moncv;
    }

    /**
     *
     * @param lignes
     * @return un tableau qui contient (dans cet ordre) -> Nom,Prenom,Age,Mail,Tel
     */
    private static String [] getAllInfo(String [] lignes){
        //All info
        String[] infos= new String[5];
        //Pour le nom et prenom
        String prenom=lignes[0].split(" |/|-|\\(|\\)|,")[0];;
        String nom=lignes[0].split(" |/|-|\\(|\\)|,")[1];;

        //Age
        String age="";

        // Pour l'email
        Pattern patternMail = Pattern.compile("^(.+)@(.+)$");
        String mail = "";

        //Numero de telephone
        Pattern patternTelephone = Pattern.compile("([0-9]{2}(\\.)*){5}");
        String tel="";

        //Competences
        ArrayList<String> competences= new ArrayList<>();

        // La recherche dans le pdf
        for(String ligne : lignes){
            for(String mot : ligne.split(" |/|-|\\(|\\)|,")){
                //System.out.println(mot);
                try{
                    //Get email
                    if(patternMail.matcher(mot).find())
                        mail=mot;
                    if(patternTelephone.matcher(mot).find())
                        tel=mot;
                    else
                        competences.add(mot);

                }catch (IllegalArgumentException e){
                    //System.out.println(e.getMessage());
                    continue;
                }
            }
            //System.out.println("line: "+s);
        }
        infos[0]=nom;
        infos[1]=prenom;
        infos[2]=age;
        infos[3]=mail;
        infos[4]=tel;

        return infos;
    }

    /**
     *
     * @param lignes
     * @return Une arrayList des compétences présentes sur le cv
     */
    private static ArrayList<String> getCompetences(String [] lignes){
        ArrayList<String> competences= new ArrayList<>();
        for(String ligne : lignes){
            for(String mot : ligne.split(" |/|-|\\(|\\)|,")){

                try{
                    Object value = Competence.valueOf(mot.toLowerCase()).toString();
                    if(!competences.contains((String)value))
                        competences.add((String)value);
                }catch (IllegalArgumentException e){
                    //System.out.println(e.getMessage());
                    continue;
                }
            }
            //System.out.println("line: "+s);
        }
        return competences;
    }

}
