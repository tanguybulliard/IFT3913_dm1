package com.tanguybulliard;

import java.io.File;
import java.util.*;
import java.lang.*;

public class Java_file_finder {

    public static ArrayList<String> folderToVisit = new ArrayList<>();
    public static ArrayList<String> javaFileTovisit = new ArrayList<>();
    public static Character c1 = 'a';
    public static Character c2 = 'v';
    public static Character c3 = 'j';
    public static Character c5 = '.';

    /**
     * java_finder prend un argument un chemin et ajoute dans le tableau "javaFileTovisit" toute les fichiers java se
     * trouvant dans le dossier
     * @param yourPath
     * @return True
     */
    public static boolean java_finder(String yourPath) {
        File folder = new File(yourPath);
        File[] listOfFiles = folder.listFiles();
        folder_explorer(yourPath);


        while (folderToVisit.isEmpty() == false) {//Si il n'y a plus de dossier à visiter, nous avons récolté tous les fichiers
            String myFolder= folderToVisit.get(0);
            folder_explorer(yourPath+"/"+myFolder);
            folderToVisit.remove(0);//on enlève le dossier du tableau lorsqu'on l'a visité
        }
       return true;
    }

    /**
     * Cette fonction est appelé par java_finder, c'est elle qui permet de trier entre les fichiers ou non
     * @param yourPath
     * @return true
     */

    public static boolean folder_explorer(String yourPath) {
        File folder = new File(yourPath);
        File[] listOfFiles = folder.listFiles();

        for(int i=0;i < listOfFiles.length; i++){//boucle sur l'entièreté des documents du dossier

            if (listOfFiles[i].isDirectory()) {
                folderToVisit.add(listOfFiles[i].getName());
                // si on a un dossier on le met dans le tableau, on va regarder ces fichiers par la suite
            }
            else {// si le document est un fichier
                String s1 = listOfFiles[i].getName();
                char[] ch = s1.toCharArray();

                if (ch.length < 5) { // si le nom dufichier strictement moins que 5 lettre alors on estime que ce n'est
                    //pas un fichier java, car |.java|=5
                    ;

                } else if (c1.equals(ch[ch.length-1]) && c2.equals(ch[ch.length-2]) && c1.equals(ch[ch.length-3]) &&
                c3.equals(ch[ch.length-4]) && c5.equals(ch[ch.length-5])){
                    //si l'extension est égale à ".java" alors on ajoute le fichier à javaFileTovisit
                    javaFileTovisit.add(listOfFiles[i].getName());
                }
                else{
                   ;
                }
            }
        }
        return true;
    }
}



