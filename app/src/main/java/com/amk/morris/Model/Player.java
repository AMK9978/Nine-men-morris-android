package com.amk.morris.Model;

import java.util.ArrayList;

public class Player extends Person {
    private Person person;
    private int playerNumber;
    private ArrayList<Piece> pieces = new ArrayList<>();

    public Player(Person person, int playerNumber) {
        super(person.getName());
        this.person = person;
        this.playerNumber = playerNumber;
        //TODO: Based on its number, assign 12 piece to him
    }

    public void movePiece(Piece piece, House origin, House destination) {
        if (!canMovePiece(piece, origin, destination)){
            return;
        }
        //TODO: Else, move this piece and intent its board and intent game
    }

    public void removePiece(Piece piece, House origin) {
        if (!canRemovePiece(piece, origin)){
            return;
        }
        //TODO: Else, remove this piece and intent its owner and board and intent game
    }

    public boolean canRemovePiece(Piece piece, House origin){
        //TODO: Add validation for removing piece of this house
        return true;
    }
    public boolean canMovePiece(Piece piece, House origin, House destination){
        //TODO: Add validation for move piece of this house to this des?
        return true;
    }
}
