package com.tanguybulliard;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Excel {
    /**
     * formate le fichiers classes.csv
     * @param path
     */
    public static void classFile(String path) throws IOException {
        try (HSSFWorkbook workBook = new HSSFWorkbook();
             FileOutputStream fos = new FileOutputStream("/Users/tanguybulliard/classes.csv")) {

            // création de la page excel
            HSSFSheet Sheet = workBook.createSheet("classes");

            // Creation de la premiere ligne
            Row header = Sheet.createRow(0);
            header.createCell(0).setCellValue("chemin");
            header.createCell(1).setCellValue("class");
            header.createCell(2).setCellValue("classe_LOC");
            header.createCell(3).setCellValue("classe_CLOC");
            header.createCell(4).setCellValue("classe_DC");
            header.createCell(5).setCellValue("WMC");
            header.createCell(6).setCellValue("classe_BC");
            header.createCell(7).setCellValue("WCP");
            header.createCell(8).setCellValue("paquet_BC");



            for(int i = 0; i < Java_file_finder.javaFileTovisit.size(); i++){
                //On crée autant de colonne que demander
                HSSFRow row = Sheet.createRow((short) i + 1);
                // colonne de Chemin
                row.createCell(0).setCellValue(path+Java_file_finder.javaFileTovisit.get(i));
                // colonne de class
                row.createCell(1).setCellValue(Java_file_finder.javaFileTovisit.get(i));
                // Colonne de classe_LOC
                row.createCell(2).setCellValue(Analyser.numberOfLineClass.get(i));
                // colonne de Classe_CLOC
                row.createCell(3).setCellValue(Analyser.numberCommentClass.get(i));
                // colonne de Classe_DC
                row.createCell(4).setCellValue(Analyser.densityCommentClass.get(i));
                // colonne de wmc
                row.createCell(5).setCellValue(Analyser.weightedMethodsPerClass.get(i));
                // colonne de Classe_BC
                row.createCell(6).setCellValue(Analyser.degreBonCommentClass.get(i));


            }
            HSSFRow row = Sheet.createRow((short) 1);
            // colonne de WCP
            row.createCell(7).setCellValue(Analyser.weightedClassPerPackage);
            // colonne de paquet_BC
            row.createCell(8).setCellValue(Analyser.degreBonCommentPaquet);
            // écrire nos résultats dans le fichier excels
            workBook.write(fos);
        }

        }
    /**
     * formate le fichiers paquets.csv
     * @param path
     */
    public static void paquetFile(String path) {
        try (HSSFWorkbook workBook = new HSSFWorkbook();
             FileOutputStream fos = new FileOutputStream("/Users/tanguybulliard/paquets.csv")) {

            // création de la page excel
            HSSFSheet Sheet = workBook.createSheet("paquets");

            // Creation de la premiere ligne
            Row header = Sheet.createRow(0);
            header.createCell(0).setCellValue("chemin");
            header.createCell(1).setCellValue("paquet");
            header.createCell(2).setCellValue("paquet_LOC");
            header.createCell(3).setCellValue("paquet_CLOC");
            header.createCell(4).setCellValue("paquet_DC");
            header.createCell(5).setCellValue("WMC");
            header.createCell(6).setCellValue("classe_BC");
            header.createCell(7).setCellValue("WCP");
            header.createCell(8).setCellValue("paquet_BC");
            for(int i = 0; i < Java_file_finder.javaFileTovisit.size(); i++){
                HSSFRow row = Sheet.createRow((short) i + 1);
                // colonne de wmc
                row.createCell(5).setCellValue(Analyser.weightedMethodsPerClass.get(i));
                // colonne de Classe_BC
                row.createCell(6).setCellValue(Analyser.degreBonCommentClass.get(i));

            }

            //On crée autant de colonne que demander
            HSSFRow row = Sheet.createRow((short) 1);
            // colonne de Chemin
            row.createCell(0).setCellValue(path+Java_file_finder.javaFileTovisit.get(0));
            // colonne de paquet
            row.createCell(1).setCellValue(6666);
            // Colonne de paquet_LOC
            row.createCell(2).setCellValue(Analyser.numberOfLinesPaquet);
            // colonne de paquet_CLOC
            row.createCell(3).setCellValue(Analyser.numberOfCommentPaquet);
            // colonne de paquet_DC
            row.createCell(4).setCellValue(Analyser.densityOfCommentPaquet);

            // colonne de WCP
            row.createCell(7).setCellValue(Analyser.weightedClassPerPackage);
            // colonne de paquet_BC
            row.createCell(8).setCellValue(Analyser.degreBonCommentPaquet);
            // écrire nos résultats dans le fichier excels
            workBook.write(fos);
            // écrire nos résultats dans le fichier excels
            workBook.write(fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

