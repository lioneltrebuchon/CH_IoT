/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author FPMs - MAB1 IG
 */
public class DataBaseManagerMySQL extends DatabaseManager{
    
    public DataBaseManagerMySQL() throws Exception {
        DBConnection();                                                 // we introduce the url parameter to make the code fairly clear and parametrable
    }

   
    
    public static Connection DBConnection() throws Exception {          // connexion à la DB on verifie si on est deja connecter si oui on reprend la connexion precedent sinon une new Connexion
        if (cnx == null) {
            
            
            Class.forName("java.sql.Driver");
            String url = "jdbc:mysql://localhost:3306/spsdb";
            cnx = DriverManager.getConnection(url,"root","toor");
            
            
            
            
        }
        return cnx;
    }
    
    
    
}
