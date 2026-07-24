/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blackjackbe;

import blackjackdb.DbManager;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class BlackJackBE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<ArrayList<Object>> players = DbManager.getAllPlayers();
        displayTable(players,"Játékosok");  
        ArrayList<ArrayList<Object>> games = DbManager.getAllGames();
        displayTable(games, "Játszmák");  
        ArrayList<ArrayList<Object>> hands = DbManager.getAllHands();
        displayTable(hands, "Leosztások");  
        ArrayList<ArrayList<Object>> playersgames = DbManager.getGamesByPlayer(1);
        displayTable(playersgames, "Játékos játszmái");  
        ArrayList<ArrayList<Object>> gameshands = DbManager.getHandsByGame(1);
        displayTable(gameshands, "Játékhoz tartozó leosztások");
              
        HttpServer httpServer = null;
        try{
            httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        } catch(IOException ioException){
            System.err.println("Hiba a 8080-as porton történő http szerver szolgáltatás létrehozásakor." + ioException.getMessage());
            System.exit(2);
        }  
        httpServer.createContext("/player", new HttpPlayer());
        httpServer.setExecutor(Executors.newFixedThreadPool(10));   
        httpServer.start();
        System.out.println("API is running on http://localhost:8080");
    } 
    
    private static void displayTable(ArrayList<ArrayList<Object>> players, String resultTableName){
        System.out.println(resultTableName);
        for(ArrayList<Object> player : players){
            for(Object playerData : player){               
                System.out.print(playerData == null?"null ":playerData.toString()+" ");               
            }
            System.out.println();
        } 
        System.out.println("----------------------------------------------------------------------");
    }
}
