package com.tanguybulliard;

import java.util.ArrayList;
import java.io.*;

public class Analyser {

    public static ArrayList<Double> numberCommentClass = new ArrayList<>();
    public static ArrayList<Double> densityCommentClass = new ArrayList<>();
    public static ArrayList<Double> densityCommentPaquet= new ArrayList<>();
    public static ArrayList<Double> numberOfLineClass = new ArrayList<>();
    public static double numberOfLinesPaquet;
    public static double numberOfCommentPaquet;
    public static double densityOfCommentPaquet;
    public static ArrayList<Double> weightedMethodsPerClass = new ArrayList<>();
    public static double weightedClassPerPackage;
    public static ArrayList<Double> degreBonCommentClass = new ArrayList<>();
    public static double degreBonCommentPaquet;

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
            double count = 0;
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
            double count = 0;
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
    public static boolean classe_DC() {
        for (int i = 0; i < numberCommentClass.size(); i++) {
            numberOfCommentPaquet += numberCommentClass.get(i);
            double nombreClass=numberCommentClass.get(i);
            double nombreLigne=numberOfLineClass.get(i);
            double density= nombreClass / nombreLigne;
            densityCommentClass.add(density);

        }
        return true;
    }

    /**
     *paquet_DC : densité de commentaires pour un paquet : paquet_DC = paquet_CLOC / paquet_LOC
     */
    public static void paquet_DC(){
        densityOfCommentPaquet =numberOfCommentPaquet/numberOfLinesPaquet;

    }

    /**
     *wmc : somme pondérée des complexités cyclomatiques de McCabe de toutes les méthodes d'une classe
     */
    public static boolean wmc(String yourPath){
        int j=0;
        while (Java_file_finder.javaFileTovisit.size()!= j) {
            // on boucle sur tous les fichiers se trouvant javaFileTovisit
            String myFolder = Java_file_finder.javaFileTovisit.get(j);
            String pathFileToVisit = yourPath + "/" + myFolder;
            String line = "";
            // Commence a 1 car le calcul de la complexité cyclomatique est calculé aves les noeuds prédicat +1
            double count = 1;
            try {
                BufferedReader br = new BufferedReader(new FileReader(pathFileToVisit));
                while ((line = br.readLine()) != null) {
                    // si ligne est un multi comment on continue jusqu'a ce qu'on sorte du bloc
                     if (line.contains("/*")) {
                        //si une ligne contient "*" on passe à la prochaine ligne tant qu'on ne trouve pas "*/"
                        while (!line.contains("*/") && !(line = br.readLine()).contains("*/")){
                            //nextline?
                        }
                    }
                     //Si une ligne qui n'est pas un comment possède une boucle incrémente count de 1
                     else if (!line.contains("//") && (line.contains("while") || line.contains("for"))) {
                        count++;
                    }
                     //Si une lige n'est pas un comment et possède une condition
                     else if(!line.contains("//") && (line.contains("if") || line.contains("case"))){
                         count++;
                     }
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            weightedMethodsPerClass.add(count);
            j++;
        }
        return true;

    }

    /**
     *wcp : somme des WMC de toutes les classes d’un paquet et les WCP de ses sous-paquets.
     */
    public static void wcp(){
        for(int i=0; i<numberOfLineClass.size();i++)
            weightedClassPerPackage+=weightedMethodsPerClass.get(i);
    }

    /**
     *classe_BC : degré selon lequel une classe est bien commentée classe_BC = classe_DC / WMC
     */
    public static void classe_BC(String yourPath){
        for (int i = 0; i < numberCommentClass.size(); i++) {
            double count = densityCommentClass.get(i)/weightedMethodsPerClass.get(i);
            degreBonCommentClass.add(count);
        }
    }
    /**
     *paquet_BC : degré selon lequel un paquet est bien commentée paquet_BC = paquet_DC / WCP
     */
    public static void paquet_BC(){
        degreBonCommentPaquet = densityOfCommentPaquet / weightedClassPerPackage;

    }


}



