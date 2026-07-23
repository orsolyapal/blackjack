/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blackjackbe;

import blackjackdb.DbManager;
import java.util.ArrayList;

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
