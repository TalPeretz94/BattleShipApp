package com.example.battleshipproject.pacificbattleship;

public class Tile {

    private int mStatus;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        this.mStatus = status;
    }

    public Tile() {
        mStatus = 0;
    }
}