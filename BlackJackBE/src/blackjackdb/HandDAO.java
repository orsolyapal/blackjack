/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.util.ArrayList;

/**
 *
 * @author Orsi
 */
public class HandDAO {
    public static ArrayList<Hand> getHandsByGame(Game game){
        ArrayList<Hand> hands = new ArrayList<>();
        
        for(ArrayList<Object> handData : DbManager.getHandsByGame(game.getId())){
            Hand hand = new Hand((int) handData.get(0), (int) handData.get(0), 
                    (boolean) handData.get(0), (boolean) handData.get(0), (boolean) handData.get(0), 
                    (boolean) handData.get(0),(int) handData.get(0), (int) handData.get(0));
            hands.add(hand);
        }        
        return hands;
    }
}
