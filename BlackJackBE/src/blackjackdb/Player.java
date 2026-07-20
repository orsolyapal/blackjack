/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Player {
    private int id;
    private String name;
    private String email;
    private LocalDateTime regDate;
    private ArrayList<Game> games;
    
    /* when creating a new player (not registered into DB) */
    public Player(String name, String email){
        this.name = name;
        this.email = email;
        this.regDate = LocalDateTime.now();
    }
  
    /* get a player from the DB (for listing registered players)*/
    public Player(int id, String name, String email, LocalDateTime regDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.regDate = regDate;
    }
    
    /* get a player and his games from the DB (for listing the player's games)*/
    public Player(int id, String name, String email, LocalDateTime regDate, ArrayList<Game> games){
        this.id = id;
        this.name = name;
        this.email = email;
        this.regDate = regDate;
        this.games = games;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }
    
    public void addGame(Game game){
        this.games.add(game);
    }
    
    public void addGames(ArrayList<Game> games){
        for(Game game : games){
            this.games.add(game);
        }
    }
    
    public void setGames(ArrayList<Game> games){
        this.games = new ArrayList<>();
        this.addGames(games);
    }
}
