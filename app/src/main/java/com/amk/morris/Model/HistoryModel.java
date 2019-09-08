package com.amk.morris.Model;

public class HistoryModel {
    private String date, status;
    private Person self, opponent;

    public Person getSelf() {
        return self;
    }

    public void setSelf(Person self) {
        this.self = self;
    }

    public Person getOpponent() {
        return opponent;
    }

    public void setOpponent(Person opponent) {
        this.opponent = opponent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
