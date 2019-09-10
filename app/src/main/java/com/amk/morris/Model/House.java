package com.amk.morris.Model;

import android.graphics.drawable.Drawable;

public class House {
    private int index;
    private Piece piece;
    private Drawable drawable = null;

    public House(int index) {
        this.index = index;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
