package com.example.battleshipproject.pacificbattleship;

public interface Player {

    public void setPosition(int position);

    public int[] playTurn(Board opponnetBoard);
}
