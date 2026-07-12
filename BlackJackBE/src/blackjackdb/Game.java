/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {

    
    private int id;
    private int playerId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int bankRoll;
    private int stack;
    private boolean playerWon;
    private int moneyWon;
    private ArrayList<Hand> hands;   
    
    public Game(int id, int playerId, LocalDateTime startDate, int bankRoll){
        this.id = id;
        this.playerId = playerId;
        this.startDate = startDate;
        this.endDate = null;
        this.bankRoll = bankRoll;
        this.stack = bankRoll;
        this.hands = new ArrayList<>();
    };
    
    public int getId() {
        return id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public int getBankRoll() {
        return bankRoll;
    }

    public int getStack() {
        return stack;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public int getMoneyWon() {
        return moneyWon;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }
    
    public void setEndDate(LocalDateTime endDate){
        this.endDate = endDate;
    }
    
    public void setStack(int stack){
        this.stack = stack;
    }
    
    public void setPlayerWon(boolean playerWon){
        this.playerWon = playerWon;
    }
    
    public void setMoneyWon(int moneyWon){
        this.moneyWon = moneyWon;
    }
            
}
