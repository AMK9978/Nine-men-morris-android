package com.amk.morris.Model;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.amk.morris.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Player extends Person implements Serializable {
    private Person person;
    private int playerNumber;
    private Context context;
    private boolean wanttoRemove = false;
    private boolean wanttoMove = false;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private Drawable drawable;
    private Drawable chosen_drawable;
    private int score = 0;
    public int pfree = 12;
    public int pnum = 12;

    public Player(Context context, Person person, int playerNumber) {
        super(person.getName());
        this.context = context;
        this.person = person;
        this.playerNumber = playerNumber;
        //TODO: Based on user chooses, assign piece
        if (playerNumber == 0) {
            for (int i = 0; i < 12; i++) {
                Piece piece = new Piece();
                piece.setDrawable(context.getResources().getDrawable(R.drawable.whitemohre_min));
                piece.setOwner(this);
                pieces.add(piece);
            }
        } else {
            for (int i = 0; i < 12; i++) {
                Piece piece = new Piece();
                piece.setDrawable(context.getResources().getDrawable(R.drawable.blackmohre_min));
                piece.setOwner(this);
                pieces.add(piece);
            }
        }
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

    public void movePiece(House origin, House destination) {
        if (!canMovePiece(origin, destination)) {
            return;
        }
        //TODO: Else, move this piece and intent its board and intent game
    }

    public void removePiece(Piece piece, House origin) {
        if (!canRemovePiece(origin)) {
            return;
        }
        //TODO: Else, remove this piece and intent its owner and board and intent game
    }

    public boolean canRemovePiece(House origin) {
        //TODO: Add validation for removing piece of this house
        if (!wanttoRemove)
            return false;
        return true;
    }

    public boolean canMovePiece(House origin, House destination) {
        //TODO: Add validation for move piece of this house to this des?
        if (!canChoose(origin)) {
            return false;
        }
        return true;
    }

    public boolean canChoose(House origin) {
        //TODO: A piece on this house, can be chosen or not?
        return true;
    }

}
