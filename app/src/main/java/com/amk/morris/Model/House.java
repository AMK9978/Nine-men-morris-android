package com.amk.morris.Model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class House {
    private int index;
    private Drawable drawable = null;
    private ImageView imageView;
    private boolean tic = false;
    private Player owner;
    private boolean isOccupied = false;

    public House(int index) {
        this.index = index;
    }


    public boolean isTic() {
        return tic;
    }

    public void setTic(boolean tic) {
        this.tic = tic;
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
        this.imageView.setImageDrawable(drawable);
    }

    public ImageView getImageView() {
        return imageView;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
