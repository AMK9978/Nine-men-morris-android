package com.amk.morris.Model;

public class Game {
    private Board board;
    private Player players[] = new Player[2];

    public Game(Player player1, Player player2) {
        board = Board.getBOARD();
        players[0] = player1;
        players[1] = player2;
    }

    public Player[] getPlayers() {
        return players;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}
