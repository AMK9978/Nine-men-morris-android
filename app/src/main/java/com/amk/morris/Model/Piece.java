package com.amk.morris.Model;

import android.graphics.drawable.Drawable;

public class Piece {
    private Player owner;
    private Drawable drawable;

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }
}
