package com.amk.morris.Model;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.util.Log;

import com.amk.morris.R;

import java.io.IOException;
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
    private MediaPlayer firstSong, secondSong, chooseSong;

    public Player(Context context, Person person, int playerNumber) {
        super(person.getName());
        this.context = context;
        this.person = person;
        this.playerNumber = playerNumber;
        Piece playerPiece = new Piece();
        playerPiece.setOwner(this);
        firstSong = new MediaPlayer();
        secondSong = new MediaPlayer();
        chooseSong = new MediaPlayer();
        AssetFileDescriptor descriptor;
        AssetFileDescriptor descriptor1;
        AssetFileDescriptor descriptor2;
        try {
            descriptor = context.getAssets().openFd("p1.mp3");
            firstSong.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());
            descriptor.close();
            descriptor1 = context.getAssets().openFd("p2.mp3");
            secondSong.setDataSource(descriptor1.getFileDescriptor(), descriptor1.getStartOffset(), descriptor1.getLength());
            descriptor1.close();
            descriptor2 = context.getAssets().openFd("procc.mp3");
            chooseSong.setDataSource(descriptor2.getFileDescriptor(), descriptor2.getStartOffset(), descriptor2.getLength());
            descriptor2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        if (house.isOccupied()) {
            return house.getOwner() == this;
        }
        return false;
    }

    public void setWanttoMove(boolean wanttoMove, House house) {
        this.wanttoMove = wanttoMove;
    }

    public void playFirstSong() {

        try {
            firstSong.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("TAG", "First song");
        firstSong.start();
    }

    public void playSecondSong() {
        try {
            secondSong.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        secondSong.start();
    }

    public void playChooseSong() {
        try {
            chooseSong.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chooseSong.start();
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
