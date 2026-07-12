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

public class DbConnector {
    
    private Connection connection;
    private String connectionString = 
            "jdbc" +
            ":mariadb" +
            "://localhost:3306" +
            "/blackjack" +
            "?user=root&password=";
    
    public DbConnector(){};
    
    public DbConnector(String connectionString){
        this.connectionString = connectionString;
    };
    
    public DbConnector(String driver, String dbType, String host, int port, String dbName, String userName, String password){
        this.connectionString = 
                driver +
                ":" + dbType +
                "://" + host +
                ":" + port +
                "/" + dbName +
                "?" + userName +
                "&" + password;
    };
    
    public void connectToDb(){
        try{
            this.connection = DriverManager.getConnection(connectionString);
            System.out.println("Létrejött a kapcsolat! (" + this.connectionString + ")");
        } catch(SQLException sqlException){
            System.err.println("Hiba az adatbázishoz való kapcsolódáskor!(" + this.connectionString + ")" + sqlException.getMessage());
        }
    };
    
    public void closeConnectionToDb(){
        try{
            this.connection.close();
            System.out.println("Sikeres kapcsolatbontás. (" + this.connectionString + ")");
        } catch(SQLException sqlException){
            System.err.println("Hiba az adatbáziskapcsolat bontásakor!(" + this.connectionString + ")" + sqlException.getMessage());
        }
    };
    
    public ResultSet selectFromDb(String sql){
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
}
