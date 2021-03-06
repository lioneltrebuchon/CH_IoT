package com;

import com.sun.rowset.internal.Row;
import jxl.*;
import jxl.read.biff.BiffException;

import java.io.*;
import java.sql.SQLException;

public class ExcelRead {

    public static void main(String[] args) throws Exception {
        DataBaseManagerMySQL databaseManager= new DataBaseManagerMySQL();
        Workbook workbook = null;
        try {

            workbook = Workbook.getWorkbook(new File("./Exemple1.xls")); // enregistrer le fi+chier en format Excel 97-2003
            Sheet sheet = workbook.getSheet(0); // lire la sheet Tabelle1 du fichier Excel

          for(int i= 4; i< sheet.getRows(); i++) {
                Cell cell1 = sheet.getCell(2, i);
                Cell cell2 = sheet.getCell(8, i);
                Cell cell3 = sheet.getCell(9, i);
                Cell cell4 = sheet.getCell(10, i);
                Cell cell5 = sheet.getCell(67, i);
                System.out.println(cell1.getContents()+"|"+new String(cell2.getContents().getBytes(), "UTF-8")+"|"+cell3.getContents()+"|"+new String(cell4.getContents().getBytes(), "UTF-8")+"|"+cell5.getContents());
                //System.out.println("Adress:" + "" + cell2.getContents() + "," + cell3.getContents() + " " + cell4.getContents());
                //System.out.println("Link:" + "" + cell5.getContents());

               String SQL = "INSERT INTO object_category VALUES("+cell1.getContents()+","+new String(cell2.getContents().getBytes(), "UTF-8")+","+cell3.getContents()+","+new String(cell4.getContents().getBytes(), "UTF-8")+","+cell5.getContents()+")";
              try {
                   databaseManager.update(SQL);
               }catch (Exception e){}
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }

        }
    }
}
