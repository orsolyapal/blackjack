/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class DbConnector {
    
    private static Connection connection;
    private static String connectionString = 
            "jdbc" +
            ":mariadb" +
            "://localhost:3306" +
            "/blackjack" +
            "?user=root&password=";
    
    
    public static Connection getConn(){
        return connection;
    }
       
    public static void connectToDb(){
        try{
            connection = DriverManager.getConnection(connectionString);
            System.out.println("Létrejött a kapcsolat! (" + connectionString + ")");
        } catch(SQLException sqlException){
            System.err.println("Hiba az adatbázishoz való kapcsolódáskor!(" + connectionString + ")" + sqlException.getMessage());
        }
    };
    
    public static void closeConnectionToDb(){
        try{
            connection.close();
            System.out.println("Sikeres kapcsolatbontás. (" + connectionString + ")");
        } catch(SQLException sqlException){
            System.err.println("Hiba az adatbáziskapcsolat bontásakor!(" + connectionString + ")" + sqlException.getMessage());
        }
    };
    
    public static ResultSet selectFromDb(String sql){
        ResultSet rs = null;
        try{
            Statement stmt = connection.createStatement();             
            rs = stmt.executeQuery(sql);
            System.out.println("Sikeres lekérdezés: " + rs.toString());
        }catch(SQLException sqlException){
            System.err.println("Hiba az SQL utasítás végrehajtása során!" + sqlException.getMessage());
        }
        return rs;
    }
    
    public static ResultSet selectFromDb(PreparedStatement pstmt){
        ResultSet rs = null;
        try{           
            rs = pstmt.executeQuery();
            System.out.println("Sikeres lekérdezés: " + rs.toString());
        }catch(SQLException sqlException){
            System.err.println("Hiba az SQL utasítás végrehajtása során!" + sqlException.getMessage());
        }
        return rs;
    }
}
