/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class DbManager {
    private String connectionString;
    private DbConnector dbConnector;
    
    public DbManager(){
        this.dbConnector = new DbConnector();
    }
    
    public DbManager(String connectionString){
        this.connectionString = connectionString;
        this.dbConnector = new DbConnector();
    }
    
    public ArrayList<ArrayList<Object>> getPlayers(){        
        return getResultTable("select * from Players;");
    };
    
    public ArrayList<ArrayList<Object>> getGames(){
        return getResultTable("select * from Games;");
    }
    
    public ArrayList<ArrayList<Object>> getHands(){
        return getResultTable("select * from Hands;");
    }
    
    private ArrayList<ArrayList<Object>> getResultTable(String sql){
        this.dbConnector.connectToDb();
        ResultSet rs = dbConnector.selectFromDb(sql);
        this.dbConnector.closeConnectionToDb();
        return resultSetToMatrix(rs);
    }
    
    private ArrayList<ArrayList<Object>> resultSetToMatrix(ResultSet rs){
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
}
