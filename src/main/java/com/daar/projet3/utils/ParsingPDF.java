package com.daar.projet3.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.daar.projet3.models.CV;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class ParsingPDF {
    private static int cpt=0;

    /**
     * Main reservé aux test de la classe
     * @param args
     */
    public static void main(String[] args) {
        //public CV parsePDF(String filename) throws FormatException{
        //String cv = filename;
        String cv = "CV_Veyrack.pdf";//"Brunet_Maxence_CV.pdf";
        //cv= "CV_Malek.pdf";
        File file = new File ("src/main/java/com/daar/projet3/utils/ressources/"+cv);
        PDDocument doc;

        try {

            doc = PDDocument.load(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);
            //System.out.println(text);
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
            //System.out.println(getAge(l));
            //getAllInfo(l);
            //System.out.println(CompetenceFileToHashSet().contains("C3"));

            doc.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * Parse un fichier .pdf
     * @param nom
     * @param prenom
     * @param mail
     * @param tel
     * @param file : Le CV
     * @return UN CV
     */
    public static CV parse(String nom, String prenom, String mail, String tel,File file) throws IOException{
        PDDocument doc;
        CV moncv;

        doc = PDDocument.load(file);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(doc);
        if(text.trim().equals("")) {
            throw new IOException("Format non valide");
            //System.out.println("VIDE");
        }
        String[] l = text.split("\n");

        //On récupère tous les mots clées et competences du PDF
        ArrayList<String> competences= getCompetences(l);
        ArrayList<String> allkeyword = getAllKeyWord(l);

        //Creation du CV
        moncv= new CV(String.valueOf(cpt++),
                prenom,
                nom,
                getAge(l),
                mail,
                tel,
                competences,
                allkeyword);

        doc.close();
        return moncv;
    }

    /**
     * parse un fichier .docx
     * @param nom
     * @param prenom
     * @param mail
     * @param tel
     * @param file : Le CV
     * @return UN CV
     */
    public static CV parseDocx(String nom, String prenom, String mail, String tel,File file) throws IOException{
        CV moncv=null;
        try {
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(OPCPackage.open(fis));
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            String text = extractor.getText();
            if(text.trim().equals("")) {
                throw new IOException("Format non valide");
                //System.out.println("VIDE");
            }

            String[] l = text.split("\\s");
            Arrays.stream(l).forEach(System.out::println);
            //On récupère tous les mots clées et competences du PDF
            ArrayList<String> competences= getCompetences(l);
            ArrayList<String> allkeyword = getCompetences(l);

            //Creation du CV
            moncv= new CV(String.valueOf(cpt++),
                    prenom,
                    nom,
                    getAge(l),
                    mail,
                    tel,
                    competences,
                    allkeyword);

            fis.close();

        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return moncv;


    }

    /**
     * recupere tout les mots cles du document
     * @param lignes : Celles du PDF
     * @return Tous les mots du PDF
     */
    private static ArrayList<String> getAllKeyWord(String [] lignes){
        ArrayList<String> res= new ArrayList<>();

        // La recherche dans le pdf
        for(String ligne : lignes){
            for(String mot : ligne.split(" |/|-|\\(|\\)|,")){

                try{
                    if(mot.toLowerCase(Locale.ENGLISH).equals("c#".toLowerCase(Locale.ENGLISH)))
                        res.add("csharp");
                    else
                        if(mot.toLowerCase(Locale.ENGLISH).equals("c++".toLowerCase(Locale.ENGLISH)))
                            res.add("cpp");
                        else
                            if(!mot.toLowerCase(Locale.ENGLISH).matches("| |à|de|je|un|et|une|en|d'un|d'une|le|la|avec|:|\\|"))
                                res.add(mot.toLowerCase(Locale.ENGLISH));
                }catch (IllegalArgumentException e){
                    //System.out.println(e.getMessage());
                    continue;
                }
            }
            //System.out.println("line: "+s);
        }
        return (ArrayList) res.stream().distinct().collect(Collectors.toList());
    }

    /**
     * recupere toute les competences du document
     * Les competences sont celles listees dans le fichier Competences
     * @param lignes : Celles du PDF
     * @return Une arrayList des compétences présentes sur le cv
     */
    private static ArrayList<String> getCompetences(String [] lignes) throws FileNotFoundException {
        HashSet<String> allCompetences= CompetenceFileToHashSet();
        ArrayList<String> competences= new ArrayList<>();
        for(String ligne : lignes){
            for(String mot : ligne.split(" |/|-|\\(|\\)|,")){

                try{
                    if(allCompetences.contains(mot.toLowerCase(Locale.ENGLISH))){
                        if(mot.toLowerCase(Locale.ENGLISH).equals("c#".toLowerCase(Locale.ENGLISH)))
                            competences.add("csharp");
                        else
                            if(mot.toLowerCase(Locale.ENGLISH).equals("c++".toLowerCase(Locale.ENGLISH)))
                                competences.add("cpp");
                            else
                                competences.add(mot.toLowerCase(Locale.ENGLISH));
                    }
                }catch (IllegalArgumentException e){
                    //System.out.println(e.getMessage());
                    continue;
                }
            }
            //System.out.println("line: "+s);
        }
        return (ArrayList) competences.stream().distinct().collect(Collectors.toList());
    }

    /**
     * Recupere l'age dans le document si le format "* ans" est reconnu
     * @param lignes
     * @return L'age
     */
    private static int getAge(String [] lignes){
        Pattern p= Pattern.compile("([0-9]{2}) *ans");
        for(String ligne : lignes){
           // System.out.println(ligne);
            Matcher matcher = p.matcher(ligne);
            if(matcher.find())
                return Integer.parseInt(matcher.group(1));
        }
        return -1; // Dans le cas où la personne n'a pas préciser son age
    }

    /**
     *
     * @return Une hashSet contenant toutes les competences du fichier "Competences"
     * @throws FileNotFoundException
     */
    private static HashSet<String> CompetenceFileToHashSet() throws FileNotFoundException {
        File file = new File("src/main/java/com/daar/projet3/utils/Competences");
        HashSet<String> res= new HashSet<>();

        Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                res.add(line.toLowerCase(Locale.ENGLISH));
            }

        return res;
    }

}
