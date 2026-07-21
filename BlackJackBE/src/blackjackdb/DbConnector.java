/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {
    
    private static Connection connection;
    
    public static Connection getConn(){
        return connection;
    }
       
    public static void connectToDb(String connectionString){
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
            System.out.println("Sikeres adatbáziskapcsolat bontás. ");
        } catch(SQLException sqlException){
            System.err.println("Hiba az adatbáziskapcsolat bontásakor!" + sqlException.getMessage());
        }
    };
    
    
}
