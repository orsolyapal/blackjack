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
import java.sql.Statement;
import java.time.LocalDateTime;

public class DbManager {
    private static String connectionString = 
            "jdbc" +
            ":mariadb" +
            "://localhost:3306" +
            "/blackjack" +
            "?user=root&password=";    
        
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
    
    public static void setURL(String URL){
        connectionString = URL;
    };
    
    public static ArrayList<ArrayList<Object>> getAllPlayers(){        
        return getResultTable("select * from Players;");
    };
    
    public static ArrayList<ArrayList<Object>> getAllGames(){
        return getResultTable("select * from Games;");
    }
    
    public static ArrayList<ArrayList<Object>> getAllHands(){
        return getResultTable("select * from Hands;");
    }
    
     public static ArrayList<ArrayList<Object>> getGamesByPlayer(int playerId){
        ArrayList<ArrayList<Object>> resultTable;
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        try{
            PreparedStatement pstmt = conn.prepareStatement("select * from Games where playerId = ?;");
            pstmt.setInt(1, playerId);
            ResultSet rs = pstmt.executeQuery();
            resultTable = resultSetToArray(rs);
        } catch(SQLException sqlException){
            System.err.println("Hiba a játékos játszmáinak lekérdezése során!" + sqlException.getMessage());
            resultTable = new ArrayList<>();
        }
        DbConnector.closeConnectionToDb();
        return resultTable;
    }  
    
    public static ArrayList<ArrayList<Object>> getHandsByGame(int gameId){
        ArrayList<ArrayList<Object>> resultTable;
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        try{
            PreparedStatement pstmt = conn.prepareStatement("select * from Hands where gameId = ?;");
            pstmt.setInt(1, gameId);
            ResultSet rs = pstmt.executeQuery();
            resultTable = resultSetToArray(rs);
        } catch(SQLException sqlException){
            System.err.println("Hiba a játék leosztásainak lekérdezése során!" + sqlException.getMessage());
            resultTable = new ArrayList<>();
        }
        DbConnector.closeConnectionToDb();
        return resultTable;
    }
    
    private static ArrayList<ArrayList<Object>> getResultTable(PreparedStatement pstmt){
        DbConnector.connectToDb(connectionString);
        ResultSet rs = selectFromDb(pstmt);
        DbConnector.closeConnectionToDb();
        return resultSetToArray(rs);
    }
    
    private static ArrayList<ArrayList<Object>> getResultTable(String sql){
        DbConnector.connectToDb(connectionString);
        ResultSet rs = selectFromDb(sql);
        DbConnector.closeConnectionToDb();
        return resultSetToArray(rs);
    }
    
    public static ResultSet selectFromDb(String sql){
        Connection conn = DbConnector.getConn();
        ResultSet rs = null;
        try{
            Statement stmt = conn.createStatement();             
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
    
     private static ArrayList<ArrayList<Object>> resultSetToArray(ResultSet rs){
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
    
    public static boolean createPlayer(String name, String email){ 
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        boolean success = false;
        try{
            PreparedStatement pstmt = conn.prepareStatement("insert into players (name, email) values(?,?);");
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            success = pstmt.execute();
        } catch(SQLException sqlException){
            System.err.println("Hiba a játékos játszmáinak lekérdezése során!" + sqlException.getMessage());
        }
        DbConnector.closeConnectionToDb();
        return success;
    }
    
    public static boolean deletePlayer(int playerId){
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        boolean success = false;
        try{
            PreparedStatement pstmt = conn.prepareStatement("delete from players where id = ?;");
            pstmt.setInt(1, playerId);
            success = pstmt.execute();
        } catch(SQLException sqlException){
            System.err.println("Hiba a játékos játszmáinak lekérdezése során!" + sqlException.getMessage());
        }
        DbConnector.closeConnectionToDb();
        return success;
    }
    
    public static boolean createGame(int playerId, LocalDateTime startDate, int bankRoll, int stack){
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        boolean success = false;
        try{
            PreparedStatement pstmt = conn.prepareStatement("insert into games (playerId, startDate, bankRoll, stack) values(?,?,?,?,?);");
            pstmt.setInt(1, playerId);
            pstmt.setObject(2, startDate);
            pstmt.setInt(3, bankRoll);
            pstmt.setInt(4, stack);
            success = pstmt.execute();
        } catch(SQLException sqlException){
            System.err.println("Hiba a játék létrehozása során!" + sqlException.getMessage());
        }
        DbConnector.closeConnectionToDb();
        return success;
    }
    
    public static boolean deleteGame(int gameId){
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        boolean success = false;
        try{
            PreparedStatement pstmt = conn.prepareStatement("delete from games where id = ?;");
            pstmt.setInt(1, gameId);
            success = pstmt.execute();
        } catch(SQLException sqlException){
            System.err.println("Hiba a játék törlése során!" + sqlException.getMessage());
        }
        DbConnector.closeConnectionToDb();
        return success;
    }
    
    public static boolean createHand(int gameId, boolean blackJack, boolean doubleDown, boolean split, boolean playerWon, int points, int bet){
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        boolean success = false;
        try{
            PreparedStatement pstmt = conn.prepareStatement("insert into Hands (gameId, blackJack, doubleDown, split, playerWon, points, bet) values(?,?,?,?,?);");
            pstmt.setInt(1, gameId);
            pstmt.setBoolean(2, blackJack);
            pstmt.setBoolean(3, doubleDown);
            pstmt.setBoolean(4, split);
            pstmt.setBoolean(4, playerWon);
            pstmt.setInt(1, points);
            pstmt.setInt(1, bet);
            success = pstmt.execute();
        } catch(SQLException sqlException){
            System.err.println("Hiba a játék létrehozása során!" + sqlException.getMessage());
        }
        DbConnector.closeConnectionToDb();
        return success;
    }
    
    public static boolean deleteHand(int handId){
        DbConnector.connectToDb(connectionString);
        Connection conn = DbConnector.getConn();
        boolean success = false;
        try{
            PreparedStatement pstmt = conn.prepareStatement("delete from Hands where id = ?;");
            pstmt.setInt(1, handId);
            success = pstmt.execute();
        } catch(SQLException sqlException){
            System.err.println("Hiba a játék törlése során!" + sqlException.getMessage());
        }
        DbConnector.closeConnectionToDb();
        return success;
    }
}
