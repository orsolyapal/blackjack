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
       DbManager dbManager = new DbManager();
       ArrayList<ArrayList<Object>> players = dbManager.getPlayers();
       displayTable(players);  
       ArrayList<ArrayList<Object>> games = dbManager.getGames();
       displayTable(games);  
       ArrayList<ArrayList<Object>> hands = dbManager.getHands();
       displayTable(hands);  
    } 
    
    private static void displayTable(ArrayList<ArrayList<Object>> players){
        for(ArrayList<Object> player : players){
            for(Object playerData : player){               
                System.out.print(playerData == null?"null ":playerData.toString()+" ");               
            }
            System.out.println();
        } 
    }
}
