/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DbManager {
    private static String connectionString;
       
    public static void setURL(String URL){
        connectionString = URL;
    };
    
    public static void setURL(String driver, String dbType, String host, int port, String dbName, String userName, String password){
        connectionString = 
                driver +
                ":" + dbType +
                "://" + host +
                ":" + port +
                "/" + dbName +
                "?" + userName +
                "&" + password;
    };
    
    public static ArrayList<ArrayList<Object>> getPlayers(){        
        return getResultTable("select * from Players;");
    };
    
    public static ArrayList<ArrayList<Object>> getGames(){
        return getResultTable("select * from Games;");
    }
    
    public static ArrayList<ArrayList<Object>> getHands(){
        return getResultTable("select * from Hands;");
    }
    
    public static ArrayList<ArrayList<Object>> getGamesByPlayer(int playerId){
        Connection conn = DbConnector.getConn();
        try{
            PreparedStatement pstmt = conn.prepareStatement("select * from Games where playerId = ?");
            pstmt.setInt(1, playerId);
            return getResultTable(pstmt);
        } catch(SQLException sqlException){
            System.err.println("Hiba a játékos játszmáinak lekérdezése során!" + sqlException.getMessage());
            return null;
        }
    }
    
    public static boolean createPlayer(String name, String email){
        Connection conn = DbConnector.getConn();
        try{
            PreparedStatement pstmt = conn.prepareStatement("insert into players (name, email) values(?,?)");
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            return pstmt.execute();
        } catch(SQLException sqlException){
            System.err.println("Hiba a játékos játszmáinak lekérdezése során!" + sqlException.getMessage());
            return false;
        }
    }
    
    private static ArrayList<ArrayList<Object>> getResultTable(PreparedStatement pstmt){
        DbConnector.connectToDb();
        ResultSet rs = DbConnector.selectFromDb(pstmt);
        DbConnector.closeConnectionToDb();
        return resultSetToMatrix(rs);
    }
    
    private static ArrayList<ArrayList<Object>> getResultTable(String sql){
        DbConnector.connectToDb();
        ResultSet rs = DbConnector.selectFromDb(sql);
        DbConnector.closeConnectionToDb();
        return resultSetToMatrix(rs);
    }
    
    private static ArrayList<ArrayList<Object>> resultSetToMatrix(ResultSet rs){
        ArrayList<ArrayList<Object>> resultMatrix = new ArrayList<>();
        try{               
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnNumber = rsMetaData.getColumnCount();
            while(rs.next()){
                ArrayList<Object> row = new ArrayList<>();
                for(int i = 0; i < columnNumber; i++){
                    row.add(rs.getObject(i+1));
                }
                resultMatrix.add(row);
            }
        }catch(SQLException sqlException){
            System.err.println("Hiba a lekérdezett eredményhalmaz bejárása során! " + sqlException.getMessage());
        }
        return resultMatrix;
    }
    
     private static ResultSet getResultSet(String sql){
        DbConnector.connectToDb();
        ResultSet rs = DbConnector.selectFromDb(sql);
        DbConnector.closeConnectionToDb();
        return rs;
    }
}
