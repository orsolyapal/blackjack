/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

public class Hand {
    private int id;
    private int gameId;
    private boolean blackJack;
    private boolean doubleDown;
    private boolean split;
    private boolean playerWon;
    private int points;
    private int bet;

    public Hand(int id, int gameId, boolean blackJack, boolean doubleDown, boolean split, boolean playerWon, int points, int bet){
        this.id = id;
        this.gameId = gameId;
        this.blackJack = blackJack;
        this.doubleDown = doubleDown;
        this.split = split;
        this.playerWon = playerWon;
        this.points = points;
        this.bet = bet;
    }
    
    public int getId() {
        return id;
    }

    public int getGameId() {
        return gameId;
    }

    public boolean isBlackJack() {
        return blackJack;
    }

    public boolean isDoubleDown() {
        return doubleDown;
    }

    public boolean isSplit() {
        return split;
    }

    public boolean isPlayerWon() {
        return playerWon;
    }

    public int getPoints() {
        return points;
    }

    public int getBet() {
        return bet;
    }
}
