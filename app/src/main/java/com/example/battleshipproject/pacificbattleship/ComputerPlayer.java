package com.example.battleshipproject.pacificbattleship;


import java.util.Random;


public class ComputerPlayer implements Player {

    private final int DEAD_SHIP = 100;


    public void think() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setPosition(int position) {
        return;
    }

    public int[] playTurn(Board board) {

        think();
        int[] returnPosition = new int[2];

        Random random = new Random();

        int positionToPlayX = random.nextInt(board.getbSize());// - 1);
        int positionToPlayY = random.nextInt(board.getbSize());// - 1);

        while (board.getMatrix()[positionToPlayX][positionToPlayY].getStatus() < 0 || board.getMatrix()[positionToPlayX][positionToPlayY].getStatus() == DEAD_SHIP) {

            positionToPlayX = random.nextInt(board.getbSize());// - 1);
            positionToPlayY = random.nextInt(board.getbSize());// - 1);

        }
        returnPosition[0] = positionToPlayX;
        returnPosition[1] = positionToPlayY;

        return returnPosition;
    }

    public int[] playHardTurn(Board board) {


        think();
        int[] returnPositin = new int[2];

        Random random = new Random();
        int isHit = random.nextInt(2);

        if (isHit != 0) {
            return playTurn(board);
        } else {
            int positionToPlayX = random.nextInt(board.getbSize()); //- 1);
            int positionToPlayY = random.nextInt(board.getbSize());// - 1);

            while (board.getMatrix()[positionToPlayX][positionToPlayY].getStatus() <= 0 || board.getMatrix()[positionToPlayX][positionToPlayY].getStatus() == DEAD_SHIP) {

                positionToPlayX = random.nextInt(board.getbSize());// - 1);
                positionToPlayY = random.nextInt(board.getbSize());// - 1);

            }
            returnPositin[0] = positionToPlayX;
            returnPositin[1] = positionToPlayY;

            return returnPositin;

        }


    }

}

