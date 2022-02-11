
package com.tanguybulliard;
import java.io.*;
import java.util.Scanner;
public class Main {

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {
        String path="/Users/tanguybulliard/Documents/GitHub/Ift3913_Tp1/src/com/tanguybulliard/";//Le chemin à analyser

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
        Analyser.WMC(path);
        Analyser.WCP();
        Analyser.classe_BC(path);
        Analyser.paquet_BC();
        
        //partie 3
        Excel.classFile(path);
        Excel.paquetFile(path);
    }
}
