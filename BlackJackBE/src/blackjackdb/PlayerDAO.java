/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;


import java.util.ArrayList;
import java.time.LocalDateTime;

public class PlayerDAO {
    
    public static ArrayList<Player> findAll(){
        ArrayList<Player> players = new ArrayList<>();
        
        for(ArrayList<Object> playerData : DbManager.getAllPlayers()){
            Player player = new Player((int) playerData.get(0), (String) playerData.get(1), (String) playerData.get(2), (LocalDateTime) playerData.get(3));
            players.add(player);
        }        
        return players;
    }    
    
    public static boolean create(Player player){
        DbManager.createPlayer(player.getName(), player.getEmail());
        return false;
    }
    
    /*If the player doesn't connected to any game, than it can be deleted. Otherwise not!*/
    public static boolean delete(Player player){
        if(DbManager.getGamesByPlayer(player.getId()).isEmpty()){
            DbManager.deletePlayer(player.getId());
            return true;
        } 
        return false;
    }
    /*The player shouldn't be updated!*/
}
