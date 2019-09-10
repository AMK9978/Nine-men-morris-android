package com.amk.morris.Model;

public class Board {
    private boolean tic = false;
    private static Board BOARD = new Board();

    private House[] houses = new House[]{
            new House(0),
            new House(1),
            new House(2),
            new House(3),
            new House(4),
            new House(5),
            new House(6),
            new House(7),
            new House(8),
            new House(9),
            new House(10),
            new House(11),
            new House(12),
            new House(13),
            new House(14),
            new House(15),
            new House(16),
            new House(17),
            new House(18),
            new House(19),
            new House(20),
            new House(21),
            new House(22),
            new House(23),
    };

    private Board() {
    }

    public static Board getBOARD() {
        return BOARD;
    }

    public boolean isTic() {
        return tic;
    }

    public void setTic(boolean tic) {
        this.tic = tic;
    }

    public House[] getHouses() {
        return houses;
    }

    public void setHouses(House[] houses) {
        this.houses = houses;
    }
}
