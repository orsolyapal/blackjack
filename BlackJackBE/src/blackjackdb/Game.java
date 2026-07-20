/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {    
    private int id;
    private final int playerId;
    private final LocalDateTime startDate;
    private LocalDateTime endDate;
    private final int bankRoll;
    private int stack;
    private boolean playerWon;
    private int moneyWon;
    private ArrayList<Hand> hands;   
    
    /*Start a New Game (not yet in DB)*/
    public Game(int playerId, int bankRoll){
        this.playerId = playerId;
        this.startDate = LocalDateTime.now();
        this.endDate = null;
        this.bankRoll = bankRoll;
        this.stack = bankRoll;
    };
    
    /*Get a Game from the DB */
    public Game(int id, int playerId, LocalDateTime startDate, LocalDateTime endDate, int bankRoll, int stack, boolean playerWon, int moneyWon, ArrayList<Hand> hands){
        this.id = id;
        this.playerId = playerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bankRoll = bankRoll;
        this.stack = stack;
        this.playerWon = playerWon;
        this.moneyWon = moneyWon;
        this.hands = hands; 
    }
    
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
    
    public void setHands(ArrayList<Hand> hands){
        this.hands = new ArrayList<>();
        this.addHands(hands);
    }
    
    public void addHands(ArrayList<Hand> hands){
        for(Hand hand : hands){
            this.hands.add(hand);
        }
    }
    
    public void addHand(Hand hand){
        this.hands.add(hand);
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
