/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;


import java.util.ArrayList;
import java.time.LocalDateTime;
/**
 *
 * @author Orsi
 */
public class GameDAO {
    
    public static ArrayList<Game> getGamesByPlayer(Player player){
       ArrayList<Game> playerGames = new ArrayList<>();
       
       for(ArrayList<Object> gameData : DbManager.getGamesByPlayer(player.getId())){
           Game game = new Game((int) gameData.get(0),(int) gameData.get(1),(LocalDateTime) gameData.get(2), (LocalDateTime) gameData.get(3), (int) gameData.get(4),
           (int) gameData.get(5), (boolean) gameData.get(6),(int) gameData.get(7), new ArrayList<>());
           playerGames.add(game);
       }
       return playerGames;
    }
    
    public static ArrayList<Game> getHandsOfGamesByPlayer(Player player){
        ArrayList<Game> playerGames = new ArrayList<>();
        for(ArrayList<Object> gameData : DbManager.getGamesByPlayer(player.getId())){
           Game game = new Game((int) gameData.get(0),(int) gameData.get(1),(LocalDateTime) gameData.get(2), (LocalDateTime) gameData.get(3), (int) gameData.get(4),
           (int) gameData.get(5), (boolean) gameData.get(6),(int) gameData.get(7), new ArrayList<>());
           
           for(ArrayList<Object> handData : DbManager.getHandsByGame(game.getId())){
               Hand hand = new Hand((int) handData.get(0), (int) handData.get(1), (boolean) handData.get(2), (boolean) handData.get(3),
               (boolean) handData.get(4), (boolean) handData.get(5), (int) handData.get(6), (int) handData.get(7));
               game.addHand(hand);
           }
           
           playerGames.add(game);
       }
       return playerGames;
    }
    
    public static void create(Game game){
        DbManager.createGame(game.getPlayerId(), game.getStartDate(), game.getBankRoll(), game.getStack());
    }
    
    public static void delete(Game game){
        DbManager.deleteGame(game.getId());
    }
}
