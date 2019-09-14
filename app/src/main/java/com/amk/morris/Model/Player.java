package com.amk.morris.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.amk.morris.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Person implements Serializable {
    private Person person;
    private int playerNumber;
    private Piece playerPiece;
    private Context context;
    private boolean wanttoRemove = false;
    private boolean wanttoMove = false;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private Drawable drawable;
    private ArrayList<House> houses = new ArrayList<>();
    private Drawable chosen_drawable;
    private int score = 0;
    public int pfree = 12;
    public int pnum = 12;

    public Player(Context context, Person person, int playerNumber) {
        super(person.getName());
        this.context = context;
        this.person = person;
        this.playerNumber = playerNumber;
        Piece playerPiece = new Piece();
        playerPiece.setOwner(this);
        //TODO: Based on user chooses, assign piece
        if (playerNumber == 0) {
            playerPiece.setDrawable(context.getResources().getDrawable(R.drawable.whitemohre_min));
            for (int i = 0; i < 12; i++) {
                Piece piece = new Piece();
                piece.setDrawable(context.getResources().getDrawable(R.drawable.whitemohre_min));
                piece.setOwner(this);
                pieces.add(piece);
            }
        } else {
            playerPiece.setDrawable(context.getResources().getDrawable(R.drawable.whitemohre_min));
            for (int i = 0; i < 12; i++) {
                Piece piece = new Piece();
                piece.setDrawable(context.getResources().getDrawable(R.drawable.blackmohre_min));
                piece.setOwner(this);
                pieces.add(piece);
            }
        }
        this.setPlayerPiece(playerPiece);
    }

    public ArrayList<House> getHouses() {
        return houses;
    }

    public void setHouses(ArrayList<House> houses) {
        this.houses = houses;
    }

    public Piece getPlayerPiece() {
        return playerPiece;
    }

    public void setPlayerPiece(Piece playerPiece) {
        this.playerPiece = playerPiece;
    }

    public boolean isWanttoMove(House house) {
        return wanttoMove;
    }

    public void setWanttoMove(boolean wanttoMove, House house) {
        this.wanttoMove = wanttoMove;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(int score) {
        this.score = score;
    }

    public Drawable getChosen_drawable() {
        return chosen_drawable;
    }

    public void setChosen_drawable(Drawable chosen_drawable) {
        this.chosen_drawable = chosen_drawable;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean isWanttoRemove() {
        return wanttoRemove;
    }

    public void setWanttoRemove(boolean wanttoRemove) {
        this.wanttoRemove = wanttoRemove;
    }


    public boolean removePiece(House origin) {
        //TODO: Add validation for removing piece of this house
        if (!wanttoRemove)
            return false;
        return true;
    }

    public boolean movePiece(House origin, House destination) {
        //TODO: Add validation for move piece of this house to this des?
        if (!choose(origin)) {
            return false;
        }

        return true;
    }

    public boolean choose(House origin) {
        //TODO: A piece on this house, can be chosen or not?
        return true;
    }

}
