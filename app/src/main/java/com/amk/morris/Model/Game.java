package com.amk.morris.Model;

public class Game {
    public static Game GAME;
    private static Board board;
    private Game(Player player1, Player player2){
        board = Board.getBOARD();
    }

}
