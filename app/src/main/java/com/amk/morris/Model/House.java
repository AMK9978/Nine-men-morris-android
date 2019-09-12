package com.amk.morris.Model;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class House {
    private int index;
    private Piece piece = null;
    private Drawable drawable = null;
    private ImageView imageView;
    private boolean tic;

    public House(int index) {
        this.index = index;
    }


    public boolean isTic() {
        return tic;
    }

    public void setTic(boolean tic) {
        this.tic = tic;
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

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
