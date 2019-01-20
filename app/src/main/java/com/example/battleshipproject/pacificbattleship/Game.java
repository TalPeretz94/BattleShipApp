package com.example.battleshipproject.pacificbattleship;


public class Game {


    private Board playerBoard;
    private Board computerBoard;
    private String difficulty;
    private final int EASY_SIZE = 5;
    private final int MEDIUM_SIZE = 8;
    private final int HARD_SIZE = 10;
    private final int EASY_SHIPS = 3;
    private final int MEDIUM_HARD_SHIPS = 5;
    private final int DEAD_SHIP = 100;
    private final String PLAYER_NAME = "Player";
    private final String COMPUTER_NAME = "Computer";
    private final int MISS_INDICATOR = -10;
    private ComputerPlayer mComputerPlayer;

    public enum mTurn {PLAYER, COMPUTER}

    private mTurn turn;
    private Player realPlayer;
    private int[] pos;


    public mTurn getTurn() {
        return turn;
    }

    public Player getRealPlayer() {
        return realPlayer;
    }


    public Game(String difficulty) {

        this.difficulty = difficulty;
        createBoards(this.difficulty);
        mComputerPlayer = new ComputerPlayer();
        realPlayer = new RealPlayer(computerBoard);
        turn = mTurn.PLAYER;
    }

    public Board getPlayerBoard() {
        return playerBoard;
    }

    public Board getComputerBoard() {
        return computerBoard;
    }


    private void createBoards(String difficulty) {
        switch (this.difficulty) {

            case "Easy":
                this.playerBoard = new Board(EASY_SIZE, EASY_SHIPS, PLAYER_NAME, false);
                this.computerBoard = new Board(EASY_SIZE, EASY_SHIPS, COMPUTER_NAME, true);//true
                break;
            case "Medium":
                this.playerBoard = new Board(MEDIUM_SIZE, MEDIUM_HARD_SHIPS, PLAYER_NAME, false);
                this.computerBoard = new Board(MEDIUM_SIZE, MEDIUM_HARD_SHIPS, COMPUTER_NAME, true);
                break;
            case "Hard":
                this.playerBoard = new Board(HARD_SIZE, MEDIUM_HARD_SHIPS, PLAYER_NAME, false);
                this.computerBoard = new Board(HARD_SIZE, MEDIUM_HARD_SHIPS, COMPUTER_NAME, true);
                break;
        }
    }

    public void doTheStorm(){
        this.computerBoard.stormShipMove();
    }

    public boolean ComputerAndPlayerShoot() {


        switch (turn) {
            case PLAYER:

                pos = realPlayer.playTurn(computerBoard);
                shotShip(pos[0], pos[1], computerBoard);

                if (computerBoard.isLose())
                    return true;
                turn = mTurn.COMPUTER;
                break;
            case COMPUTER:
                if (difficulty.equalsIgnoreCase("Hard")) {
                    pos = mComputerPlayer.playHardTurn(playerBoard);
                } else {
                    pos = mComputerPlayer.playTurn(playerBoard);
                }
                shotShip(pos[0], pos[1], playerBoard);
                if (playerBoard.isLose())
                    return true;
                turn = mTurn.PLAYER;
                break;
        }
        return false;


    }


    private boolean shotShip(int row, int col, Board opponentBoard) {

        int index = opponentBoard.getMatrix()[row][col].getStatus();
        int[] myPos = new int[2];

        if (index != 0) {
            opponentBoard.getShips()[index - 1].raiseHit();
            opponentBoard.getMatrix()[row][col].setStatus(-index);
            if (!opponentBoard.getShips()[index - 1].isAlive()) {

                for (int i = 0; i < opponentBoard.getMatrix().length; i++) {
                    for (int j = 0; j < opponentBoard.getMatrix().length; j++) {
                        if (opponentBoard.getMatrix()[i][j].getStatus() == -index) {
                            opponentBoard.getMatrix()[i][j].setStatus(DEAD_SHIP);
                        }
                    }
                }


            }

            return true;


        } else {

            opponentBoard.getMatrix()[row][col].setStatus(MISS_INDICATOR);
            return false;
        }
    }


}
