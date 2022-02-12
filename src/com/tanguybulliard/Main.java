
package com.tanguybulliard;
import java.io.*;
import java.util.Scanner;
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String path="";//Le chemin absolu à analyser
        String pathExcelClass="";// chemin absolu vers le fichier excel 1
        String pathExcelPaquet="";// chemin absolu vers le fichier excel 2

        //algorithme qui récolte tous les fichiers se trouvant dans le chemin donner
        Java_file_finder.java_finder(path);
        //partie 1
        Analyser.classe_CLOC(path);
        Analyser.classe_LOC(path);
        Analyser.paquet_LOC();
        Analyser.paquet_CLOC();
        Analyser.classe_DC();
        Analyser.paquet_DC();
        //partie 2
        Analyser.wmc(path);
        Analyser.wcp();
        Analyser.classe_BC(path);
        Analyser.paquet_BC();
        
        //partie 3
        Excel.classFile(path,pathExcelClass);
        Excel.paquetFile(path,pathExcelPaquet);
    }
}
