/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package recuento_horas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.postgresql.ds.PGSimpleDataSource;


/**
 *
 * @author javie
 */
public class Conectar_db {
    
    
       /*public static Connection conectDB(){
        
    
        try {
            Connection con = DriverManager.getConnection("jdbc:sqlite:datos.db");
            
            System.out.println("Connection established");
            return con;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                    return null;
        }
        }*/
       
       public static Connection conectDB(){
        
    
        try {
            //Connection con = DriverManager.getConnection("jdbc:postgresql://combat-hermit-6452.8nj.cockroachlabs.cloud:26257/defaultdb?sslmode=verify-full&password=ZXODcaL7fyS9myPnHxoJXQ&user=javiergracia2003");
            //Connection con = DriverManager.getConnection("jdbc:sqlite://192.168.1.35/shared/pruebas_sql/data.db");
            //Connection con = DriverManager.getConnection("jdbc:sqlite://Volumes/shared/pruebas_sql/data.db");
            
           /* PGSimpleDataSource ds = new PGSimpleDataSource();
                ds.setUrl("jdbc:postgresql://uuhcyvf9hrzxpgxhbngu:q2W2xDk8sAMphn4GvfCOArjLhLreYk@bjqmtad9izhpbe9txd9j-postgresql.services.clever-cloud.com:5432/bjqmtad9izhpbe9txd9j");
                ds.setUser("uuhcyvf9hrzxpgxhbngu");
                ds.setPassword("q2W2xDk8sAMphn4GvfCOArjLhLreYk");
            System.out.println("Connection established");
            Connection con = ds.getConnection();*/
            String url = "jdbc:mysql://qahl063.qnavarra.com/qahl063";
            String username = "qahl063";
            String password = "Hner527n7MY";

            Connection con = DriverManager.getConnection(url, username, password);

            return con;
           
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
                    return null;
        }
        }
       
       
       
       
       
       
       
       
}
