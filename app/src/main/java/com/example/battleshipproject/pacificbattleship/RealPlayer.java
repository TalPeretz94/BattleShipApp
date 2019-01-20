package com.example.battleshipproject.pacificbattleship;

public class RealPlayer implements Player {
    private int[] pos;
    private Board enemyBoard;
    private int numOfTurns;

    public RealPlayer(Board enemyBoard) {
        this.enemyBoard = enemyBoard;
        this.numOfTurns =0;
    }

    public int getNumOfTurns() {
        return numOfTurns;
    }

    public void setNumOfTurns(int numOfTurns) {
        this.numOfTurns = numOfTurns;
    }

    @Override
    public int[] playTurn(Board opponnetBoard) {

        numOfTurns++;
        return pos;
    }

    @Override
    public void setPosition(int position) {
        pos = enemyBoard.arrayToMatrix(position);
    }
}
