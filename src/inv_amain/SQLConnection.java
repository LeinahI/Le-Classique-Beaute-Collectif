/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inv_amain;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JSpinner;
/**
 *
 * @author PREDATOR HELIOS 300
 */
public class SQLConnection {
    
    public static Connection getConnection(){
        
        Connection con = null;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/lcbcdatabase"/*database location server*/,"root"/*username*/,""/*no password*/);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return con;
    }
    

}
