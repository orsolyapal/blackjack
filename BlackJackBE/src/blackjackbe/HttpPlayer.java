/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjackbe;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public class HttpPlayer implements HttpHandler{
    @Override
    public void handle(HttpExchange exchange){ 
        System.out.println("HttpPlayer.handle");
        Cors.apply(exchange);
        try{
            switch (exchange.getRequestMethod()){
                case "GET" -> getAll();
                case "POST" -> create();
                case "DELETE" -> delete();
                case "OPTIONS" -> options();
                default -> clientError(exchange);
            }
        }catch(IOException ioException){
            System.err.println("Hiba a (HttpPlayer) Játékosok HTTP válasz küldése során." + ioException.getMessage());
        }
    }

    private void getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void create() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void delete() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void options() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void clientError(HttpExchange exchange) throws IOException {
        System.err.println("Hibás kliens oldali kérés: " + exchange.getRequestMethod());
        exchange.sendResponseHeaders(405, -1);
    }
}
