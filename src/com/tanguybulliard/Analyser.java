package com.tanguybulliard;

import java.util.ArrayList;
import java.io.*;

public class Analyser {

    public static ArrayList<Integer> numberCommentClass = new ArrayList<>();
    public static ArrayList<Integer> densityCommentClass = new ArrayList<>();
    public static ArrayList<Integer> densityCommentPaquet= new ArrayList<>();
    public static ArrayList<Integer> numberOfLineClass = new ArrayList<>();
    public static Integer numberOfLinesPaquet;
    public static Integer numberOfCommentPaquet;
    public static Integer densityOfCommentPaquet;

    /**
     *  classe_CLOC : nombre de lignes de code d’une classe qui contiennent des commentaires
     * @param yourPath
     * @return
     */
    public static boolean classe_CLOC(String yourPath){
        int j=0;
        while (Java_file_finder.javaFileTovisit.size()!= j) {
        // on boucle sur tous les fichiers se trouvant javaFileTovisit
            String myFolder = Java_file_finder.javaFileTovisit.get(j);
            String pathFileToVisit = yourPath + "/" + myFolder;
            String line = "";
            int count = 0;
            try {
                BufferedReader br = new BufferedReader(new FileReader(pathFileToVisit));
                while ((line = br.readLine()) != null) {
                    // si une contient "//" on incrémente
                    if (line.contains("/"+"/")) {
                        count++;
                    } else if (line.contains("/*")) {
                        //si une ligne contient "*/" on incremente de 1 par ligne tant qu'on ne trouve pas "*/"
                        while (!line.contains("*/") && !(line = br.readLine()).contains("*/")){
                            count++;
                        }
                    }
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("count=" + count);
            numberCommentClass.add(count);
            j++;
        }
        return true;
    }

    /**
     * classe_LOC : nombre de lignes de code d’une classe
     * @param yourPath
     * @return true
     */
    public static boolean classe_LOC(String yourPath){
        int j=0;
        while (Java_file_finder.javaFileTovisit.size()!= j) {
            String myFolder = Java_file_finder.javaFileTovisit.get(j);
            String pathFileToVisit = yourPath + "/" + myFolder;
            String line = "";
            int count = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathFileToVisit));
            while ((line = br.readLine()) != null) {
                if(line.trim().isEmpty()==false){
                    count++;
                    //si une ligne n'est pas vide on incrémente de 1.
                }
                else{
                    ;
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("count=" + count);
        //le nombree de ligne de code est la différence entre les lignes contant des commentaires et des lignes pas vides
        count=count-numberCommentClass.get(j);
        numberOfLineClass.add(count);
        j++;
    }
        return true;
    }

    /**
     *paquet_LOC : nombre de lignes de code d’un paquet (java package) -- la somme des LOC de ses classes
     */
    public static void paquet_LOC (){
        for(int i=0; i<numberOfLineClass.size();i++)
            numberOfLinesPaquet+=numberOfLineClass.get(i);
    }

    /**
     *paquet_CLOC : nombre de lignes de code d’un paquet qui contiennent des commentaires
     */
    public static void paquet_CLOC(){
        for(int i=0; i<numberCommentClass.size();i++)
            numberOfCommentPaquet+=numberCommentClass.get(i);
    }

    /**
     *classe_DC : densité de commentaires pour une classe : classe_DC = classe_CLOC / classe_LOC
     */
    public static void classe_DC() {
        for (int i = 0; i < numberCommentClass.size(); i++) {
            numberOfCommentPaquet += numberCommentClass.get(i);
            int density =numberCommentClass.get(i)/numberOfLineClass.get(i);
            densityCommentClass.add(density);
        }
    }

    /**
     *paquet_DC : densité de commentaires pour un paquet : paquet_DC = paquet_CLOC / paquet_LOC
     */
    public static void paquet_DC(){
        densityOfCommentPaquet =numberOfCommentPaquet/numberOfLinesPaquet;

    }

}


