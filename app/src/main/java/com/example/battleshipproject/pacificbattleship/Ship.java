package com.example.battleshipproject.pacificbattleship;

import java.util.ArrayList;

public class Ship {

    private int id;
    private int size;
    private int hit;
    private boolean isAlive;
    private boolean isPlaced;
    private boolean horizontal = true;
    private ArrayList<int[]> shipPosition;


    public Ship(int id) {
        this.id = id;
        setSize(id);
        this.isAlive = true;
        this.hit = 0;
        this.isPlaced = false;
        shipPosition = new ArrayList<>();


    }
	public void setHit(int hit) {
        this.hit = hit;
    }							 

    public ArrayList<int[]> getShipPosition() {
        return shipPosition;
    }

    public void addShipPosition(int[] pos) {
        shipPosition.add(pos);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int id) {
        switch (id) {

            case 1:
            case 2:
                this.size = 2;
                break;
            case 3:
            case 4:
                this.size = 3;
                break;
            case 5:
                this.size = 4;
                break;
        }
    }

    public int getHit() {
        return hit;
    }

    public void raiseHit() {
        this.hit++;
        if (this.hit == this.size) {
            setAlive(false);
        }
    }

    public boolean isAlive() {

        return isAlive;
    }

    private void setAlive(boolean alive) {
        isAlive = alive;
    }

    public boolean isPlaced() {
        return isPlaced;
    }

    public void setPlaced(boolean placed) {
        isPlaced = placed;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

}
