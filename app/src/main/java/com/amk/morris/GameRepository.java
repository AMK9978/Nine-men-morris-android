package com.amk.morris;

import android.app.Dialog;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Window;

import com.amk.morris.Model.Board;
import com.amk.morris.Model.Game;
import com.amk.morris.Model.House;
import com.amk.morris.Model.Player;

import java.util.ArrayList;


public class GameRepository {
    private Game game;
    private Player players[];
    private Board board;
    private int turn = 0;
    private int ad = 0;
    private House b1[][];
    private House b2[][];
    private House b3[][];
    private House b4[][];
    private House barr[][][];
    private House barr2[][] = new House[3][3];
    private House home = null;
    private boolean finish = false;
    private int self = 0;
    private String gameStatus = "";

    public GameRepository(Player player1, Player player2) {
        this.game = new Game(player1, player2);
        this.board = game.getBoard();
        this.players = game.getPlayers();
        init();
    }

    public boolean isFinish() {
        return finish;
    }

    public Player[] getPlayers() {
        return players;
    }

    public int getTurn() {
        return turn;
    }

    public int getSelf() {
        return self;
    }

    public void setSelf(int self) {
        this.self = self;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    private void init() {
        b1 = new House[][]{{board.getHouses()[1], board.getHouses()[2], board.getHouses()[3]},
                {board.getHouses()[4], board.getHouses()[5], board.getHouses()[6]}, {board.getHouses()[7],
                board.getHouses()[8], board.getHouses()[9]}};

        b2 = new House[][]{{board.getHouses()[1], board.getHouses()[4], board.getHouses()[7]},
                {board.getHouses()[10], board.getHouses()[11], board.getHouses()[12]}, {board.getHouses()[22],
                board.getHouses()[19], board.getHouses()[16]}};

        b3 = new House[][]{{board.getHouses()[9], board.getHouses()[6], board.getHouses()[3]},
                {board.getHouses()[13], board.getHouses()[14], board.getHouses()[15]}, {board.getHouses()[18],
                board.getHouses()[21], board.getHouses()[24]}};

        b4 = new House[][]{{board.getHouses()[16], board.getHouses()[17], board.getHouses()[18]},
                {board.getHouses()[19], board.getHouses()[20], board.getHouses()[21]}, {board.getHouses()[22],
                board.getHouses()[23], board.getHouses()[24]}};

        barr = new House[][][]{b1, b2, b3, b4};
    }

    public Board getBoard() {
        return board;
    }

    public String process(int id) {
        String msg = "";
        if (!players[turn].isWanttoRemove()) {
            //means that I don't want to remove the piece of this house
            //Move method or Choose method?
            if (players[turn].pfree > 0) {
                Log.i("TAG", "Choose");
                // Choose() method Actives
                if (choose(players[turn], board.getHouses()[id])) {
                    //Choose() processing was Successful
                    //TODO: Play put song
                    Log.i("TAG", "What's the turn? " + turn);
                    if (turn == 0){
                        msg = "firstsong";
                    }else {
                        msg = "secondsong";
                    }

                    Log.i("TAG", "Choose successful");
                    board.getHouses()[id].getImageView().setImageDrawable(players[turn].getDrawable());
                    if (!players[turn].isWanttoRemove()) {
                        //It's possible that after put piece, Dooz happened
                        if (turn == 0) {
                            turn = 1;
                            accessMove(players[turn], players[0]);
                        } else {
                            turn = 0;
                            accessMove(players[turn], players[1]);
                        }
                    } else {
                        Log.i("TAG", "Delete!, turn:" + turn);
                    }
                }
            } else {
                // Move() method Actives
                /*In the first We must recognize Player Want to move this board's piece or want to put
                  here a piece that he caught earlier*/
                Log.i("TAG", "move! and wants? " + players[turn].isWanttoMove(board.getHouses()[id]));
                if (players[turn].isWanttoMove(board.getHouses()[id])) {
                    if (home != null) {
                        home.setDrawable(players[turn].getDrawable());
                    }
                    Log.i("TAG", "HERE, Chosen one thing");
                    board.getHouses()[id].setDrawable(players[turn].getChosen_drawable());
                    home = board.getHouses()[id];
                    //TODO: Play choose song
                    players[0].playChooseSong();
                    msg = "choosesong";
                } else if (home != null) {
                    //means that a piece choose and now Player wants to put it here
                    if (movePiece(players[turn], home, board.getHouses()[id])) {//Move() processed successful
                        board.getHouses()[id].setDrawable(players[turn].getDrawable());
                        home.setDrawable(null);
                        home = null;
                        //TODO: Play put song
                        Log.i("TAG", "What's the turn? " + turn);
                        if (turn == 0){
                            msg = "firstsong";
                        }else {
                            msg = "secondsong";
                        }

                        if (!players[turn].isWanttoRemove()) {//It's possible that after put piece, Dooz happened
                            this.game.setStatus("یه مهره تکون بده");
                            if (turn == 0) {
                                turn = 1;
                                accessMove(players[1], players[0]);
                                Log.i("TAG", "Move! , turn:" + turn);
                            } else {
                                turn = 0;
                                accessMove(players[0], players[1]);
                                Log.i("TAG", "Move! , turn:" + turn);
                            }
                        } else {
                            this.game.setStatus("یه مهره حذف کن");
                            Log.i("TAG", "Delete! , turn:" + turn);
                        }
                    } else {
                        Log.i("TAG", "HEEYYY!");
                    }
                }
            }

        } else {
            //Player wants to Delete the piece of this house
            if (removePiece(players[turn], board.getHouses()[id])) {
                if (turn == 0) {
                    turn = 1;
                    accessMove(players[1], players[0]);
                    if (players[turn].pfree > 0) {
                        this.game.setStatus("یه مهره بذار");
                    } else {
                        this.game.setStatus("یه مهره تکون بده");
                    }
                } else {
                    turn = 0;
                    accessMove(players[0], players[1]);
                    if (players[turn].pfree > 0) {
                        this.game.setStatus("یه مهره بذار");
                    } else {
                        this.game.setStatus("یه مهره تکون بده");
                    }
                }
                Log.i("TAG", "Move! ,turn:" + turn);
            }
        }
        return msg;
    }

    private boolean accessMove(Player player, Player opponent) {
        if (player.getHouses().size() == 0) {
            return true;
        }
        //We can be sure that current player has house
        //We want to check all of his houses
        int turns;
        for (int m = 0; m < player.getHouses().size(); m++) {
            turns = 0;
            for (int i = 0; i < 4; i++) {
                //Houses was divided into 4 arrays and we check all of them
                barr2 = barr[i];
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (player.getHouses().get(m) == barr2[k][j]) { //processing on the a house starts
                            ArrayList<House> b = new ArrayList<>(); //to contain it's neighbours
                            try {
                                b.add(barr2[k - 1][j]);
                            } catch (Exception ignored) {
                            }
                            try {
                                b.add(barr2[k + 1][j]);
                            } catch (Exception ignored) {
                            }
                            try {
                                b.add(barr2[k][j - 1]);
                            } catch (Exception ignored) {
                            }
                            try {
                                b.add(barr2[k][j + 1]);
                            } catch (Exception ignored) {
                            }
                            //Maximum of a house neighbours is 4 houses
                            //All of it's neighbours are saved to checked
                            for (int l = 0; l < 4; l++) {
                                try {
                                    if (!b.get(l).isOccupied()) {
                                        //means that one house exists that's not occupied
                                        System.out.println("current house: " + player.getHouses().get(m).getIndex());
                                        System.out.println("free house: " + b.get(l).getIndex());
                                        return true;
                                    }
                                } catch (Exception ignored) {
                                }
                            }
                            //When program becomes here means that the house found but It had no neighbours
                            //So it's essential to check another array of 4 array to check has any neighbour?
                            if (turns != 1) {
                                turns = 1;
                            }
                        }
                    }
                }
            }
        }

        //means that current player has no neighbours that are non occupied
        player.setScore(1);
        opponent.setScore(1);
        //So scores must be sent to DB
//        put score(new Player[]{player, opponent});
        return false;
    }

    private boolean Ticfind(House b) {
        checker();
        for (int i = 0; i < 4; i++) {
            barr2 = barr[i];
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (barr2[j][k] == b) {
                        //process
                        int n = 0;
                        for (int l = 0; l < 3; l++) {
                            if (b.getOwner() == barr2[j][l].getOwner()) {
                                n++;
                            }
                        }
                        if (n == 3) {
                            for (int l = 0; l < 3; l++) {
                                barr2[j][l].setTic(true);
                            }
                            //music plays
                            //Time Increase
                            return true;
                        }

                    }
                }

            }
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (barr2[k][j] == b) {
                        //process
                        int n = 0;
                        for (int l = 0; l < 3; l++) {
                            if (barr2[l][j].getOwner() == b.getOwner()) {
                                n++;
                            }
                        }
                        if (n == 3) {
                            for (int l = 0; l < 3; l++) {
                                barr2[l][j].setTic(true);
                            }
                            //music plays
                            //Time Increase

                            return true;
                        }

                    }
                }

            }
        }

        return false;
    }

    private void checker() {

        for (int i = 0; i < 25; i++) {
            board.getHouses()[i].setTic(false);
        }
        for (int i = 0; i < 4; i++) {
            barr2 = barr[i];
            for (int j = 0; j < 3; j++) {
                if (!barr2[j][0].isOccupied()) {
                    if (barr2[j][0].getOwner() == barr2[j][1].getOwner()) {
                        if (barr2[j][1].getOwner() == barr2[j][2].getOwner()) {
                            barr2[j][0].setTic(true);
                            barr2[j][1].setTic(true);
                            barr2[j][2].setTic(true);
                        }
                    }
                }
            }
            for (int j = 0; j < 3; j++) {
                if (!barr2[0][j].isOccupied()) {
                    if (barr2[0][j].getOwner() == barr2[1][j].getOwner()) {
                        if (barr2[1][j].getOwner() == barr2[2][j].getOwner()) {
                            barr2[0][j].setTic(true);
                            barr2[1][j].setTic(true);
                            barr2[2][j].setTic(true);
                        }
                    }
                }
            }
        }
    }

    public boolean accessDelete() {
        int a = 0;
        for (int i = 0; i < board.getHouses().length; i++) {
            if (board.getHouses()[i].isTic()) {
                a++;
            }
        }
        if (a == board.getHouses().length) {
            for (int i = 0; i < board.getHouses().length; i++) {
                board.getHouses()[i].setTic(true);
            }
            ad = 1;
            return false;
        }
        return true;
    }


    private boolean movePiece(Player player, House destination, House origin) {
        //We must check Accessibility condition for moving from h to b here
        if (!origin.isOccupied()) {
            for (int i = 0; i < 4; i++) {
                barr2 = barr[i];
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (barr2[j][k] == destination) {
                            try {
                                if (barr2[j][k - 1] == origin) {
                                    players[turn].getHouses().remove(destination);
                                    destination.setTic(false);
                                    destination.setOwner(null);
                                    destination.setOccupied(false);
                                    players[turn].getHouses().add(origin);
                                    origin.setTic(true);
                                    origin.setOccupied(true);
                                    origin.setOwner(players[turn]);
                                    if (Ticfind(origin)) {
                                        players[turn].setWanttoRemove(true);
                                    }

                                    return true;
                                }

                            } catch (Exception e) {
                            }
                            try {
                                if (barr2[j][k + 1] == origin) {
                                    players[turn].getHouses().remove(destination);
                                    destination.setTic(false);
                                    destination.setOwner(null);
                                    destination.setOccupied(false);
                                    players[turn].getHouses().add(origin);
                                    origin.setTic(true);
                                    origin.setOccupied(true);
                                    origin.setOwner(players[turn]);
                                    if (Ticfind(origin)) {
                                        players[turn].setWanttoRemove(true);
                                    }
                                    return true;
                                }

                            } catch (Exception e2) {
                            }
                        }
                    }
                }
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (barr2[k][j] == destination) {
                            //process
                            try {
                                if (barr2[k - 1][j] == origin) {
                                    players[turn].getHouses().remove(destination);
                                    destination.setTic(false);
                                    destination.setOwner(null);
                                    destination.setOccupied(false);
                                    players[turn].getHouses().add(origin);
                                    origin.setTic(true);
                                    origin.setOccupied(true);
                                    origin.setOwner(players[turn]);
                                    if (Ticfind(origin)) {
                                        players[turn].setWanttoRemove(true);
                                    }
                                    return true;
                                }

                            } catch (Exception e) {
                            }
                            try {
                                if (barr2[k + 1][j] == origin) {
                                    players[turn].getHouses().remove(destination);
                                    destination.setTic(false);
                                    destination.setOwner(null);
                                    destination.setOccupied(false);
                                    players[turn].getHouses().add(origin);
                                    origin.setTic(true);
                                    origin.setOccupied(true);
                                    origin.setOwner(players[turn]);
                                    if (Ticfind(origin)) {
                                        players[turn].setWanttoRemove(true);
                                    }
                                    return true;
                                }

                            } catch (Exception e2) {
                            }
                        }

                    }
                }
            }

        }
        return false;
    }


    private boolean removePiece(Player player, House b) {
        int a = 0;
        if (b.isOccupied()) {
            if (b.getOwner() != players[turn]) {
                if (!b.isTic()) {
                    b.getImageView().setImageDrawable(null);
                    player.setWanttoRemove(false);
                    b.setOccupied(false);
                    b.setOwner(null);
                    if (ad == 1) {
//                        Board.Return(player, turn);
                        ad = 0;
                    }

                    if (turn == 0) {
                        players[1].pnum--;
                        players[1].getHouses().remove(b);
                        if (players[1].pfree > 0) {
                            this.game.setStatus("یه مهره بذار");
                        } else {
                            this.game.setStatus("یه مهره تکون بده");
                        }
                    } else {
                        players[0].pnum--;
                        players[0].getHouses().remove(b);
                        if (players[0].pfree > 0) {
                            this.game.setStatus("یه مهره بذار");
                        } else {
                            this.game.setStatus("یه مهره تکون بده");
                        }
                    }
                    if (!gameCondition(players)) {
                        this.finish = true;
                        return false;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private boolean gameCondition(Player[] player) {
            if (player[0].pnum==2) {
                //player[0] lost
                //Put Scores into DB
                //Sort and Put Top5 Names and Scores in TableController
                putscore(player);
                //backing data to earlier
                if (0 == self){
                    this.gameStatus = "lose";
                }else{
                    this.gameStatus = "win";
                }
                return false;
            }
            if (player[1].pnum==2) {
                //player[1] lost
                //Put Scores into DB
                //Sort and Put Top5 Names and Scores in TableController
                if (1 == self){
                    this.gameStatus = "lose";
                }else{
                    this.gameStatus = "win";
                }
                putscore(player);
                //

                return false;
            }

            return true;
    }

    private void putscore(Player[] player) {
    }

    private boolean choose(Player player, House b) {
        if (!b.isOccupied()) {
            b.setOccupied(true);
            b.setOwner(players[turn]);
            player.getHouses().add(b);
            player.pfree--;
            if (player.pfree <= 0){
                this.game.setStatus("یه مهره تکون بده");
            }
            if (Ticfind(b)) {
                player.setWanttoRemove(true);
                this.game.setStatus("یه مهره از حریف رو حذف کن");
            }
            return true;
        }

        return false;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
