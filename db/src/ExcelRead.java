import jxl.*;
import jxl.read.biff.BiffException;

import java.io.*;
import java.sql.SQLException;

public class ExcelRead {

    public static void main(String[] args) {

        DatabaseManager databaseManager= new DatabaseManager();
        Workbook workbook = null;
        try {

            workbook = Workbook.getWorkbook(new File("./Exemple1.xls")); // enregistrer le fi+chier en format Excel 97-2003

            Sheet sheet = workbook.getSheet(0); // lire la sheet Tabelle1 du fichier Excel
            Cell cell1 = sheet.getCell(2, 2029);
            Cell cell2 = sheet.getCell(8, 2029);
            Cell cell3 = sheet.getCell(9, 2029);
            Cell cell4 = sheet.getCell(10, 2029);
            Cell cell5 = sheet.getCell(67, 2029);
            System.out.println("Object category:" + ""+cell1.getContents());
            System.out.println("Adress:" + ""+cell2.getContents()+","+cell3.getContents()+""+cell4.getContents());
            System.out.println("Link:" + ""+cell5.getContents());

            String SQL = "INSERT INTO Object_category VALUES(cell1.getContents(),cell2.getContents(),cell3.getContents(),cell4.getContents(),cell5.getContents())";
            databaseManager.insert(SQL);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (workbook != null) {
                workbook.close();
            }

        }
    }
}
