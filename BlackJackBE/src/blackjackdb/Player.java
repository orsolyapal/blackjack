/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackdb;

import java.time.LocalDateTime;

public class Player {
    private int id;
    private String name;
    private String email;
    private LocalDateTime regDate;
    
    public Player(String name, String email){
        this.name = name;
        this.email = email;
    }
    
    public Player(int id, String name, String email, LocalDateTime regDate){
        this.id = id;
        this.name = name;
        this.email = email;
        this.regDate = regDate;
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
}
