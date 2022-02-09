package com.tanguybulliard;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

import java.io.FileOutputStream;
import java.io.IOException;


public class Excel {
    /**
     * formate le fichiers classes.csv
     * @param path
     */
    public static void classFile(String path) throws IOException {
        try (HSSFWorkbook workBook = new HSSFWorkbook();
             FileOutputStream fos = new FileOutputStream("classes.csv")) {

            // création de la page excel
            HSSFSheet Sheet = workBook.createSheet("products");

            // Creation de la premiere ligne
            Row header = Sheet.createRow(0);
            header.createCell(0).setCellValue("chemin");
            header.createCell(1).setCellValue("class");
            header.createCell(2).setCellValue("classe_LOC");
            header.createCell(3).setCellValue("classe_CLOC");
            header.createCell(4).setCellValue("classe_DC");



            for(int i = 0; i < Java_file_finder.javaFileTovisit.size(); i++){
               //On crée autant de colonne que demander
                HSSFRow row = Sheet.createRow((short) i + 1);
                // colonne de Chemin
                row.createCell(0).setCellValue(path+"/"+Java_file_finder.javaFileTovisit.get(i));
                // colonne de class
                row.createCell(1).setCellValue(Java_file_finder.javaFileTovisit.get(i));
                // Colonne de classe_LOC
                row.createCell(2).setCellValue(Analyser.numberOfLineClass.get(i));
                // colonne de Classe_CLOC
                row.createCell(3).setCellValue(Analyser.numberCommentClass.get(i));
                // colonne de Classe_DC
                row.createCell(4).setCellValue(Analyser.densityCommentClass.get(i));
            }
            // écrire nos résultats dans le fichier excels
            workBook.write(fos);
        }

        }
    /**
     * formate le fichiers paquets.csv
     * @param path
     */
    public static void paquetFile(String path) {
        //TODO:(
    }
}

