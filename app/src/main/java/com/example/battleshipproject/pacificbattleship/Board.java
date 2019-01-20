package com.example.battleshipproject.pacificbattleship;

import java.util.ArrayList;

public class Board {

    private int bSize;
    private String playerType;
    private int shipsAlive;
    private Ship ships[];
    private int numOfShips;
    private Tile[][] matrix;
    private int [][] matrixOfDirections;
    private boolean isInvisable;
    private int[] shipPos;
    private final int EMPTY_TILE=0;
    private final int DEAD_TILE=100;
    private ArrayList<Ship> moveShipArr;



    public Board(int bSize, int numOfShips, String playerType, boolean oppennetShipsInvisable) {
        this.bSize = bSize;
        this.playerType = playerType;
        this.shipsAlive = numOfShips;
        ships = new Ship[numOfShips];
        createShips();
        matrix = new Tile[bSize][bSize];
        matrixOfDirections = new int[bSize][bSize];
        createBoardTile();
        runOnShips();
        this.isInvisable = oppennetShipsInvisable;
        moveShipArr = new ArrayList<>();

    }

    public boolean isInvisable() {
        return isInvisable;
    }


    public int[][] getMatrixOfDirections() {
        return matrixOfDirections;
    }

    public Tile[][] getMatrix() {
        return matrix;
    }

    public Ship[] getShips() {
        return ships;
    }

    public int getbSize() {
        return bSize;
    }

    public int[] arrayToMatrix(int position) {
        int tempRow, tempCol;
        tempRow = (int) (Math.floor(position / bSize));
        tempCol = (int) ((position % bSize));
        int[] pos = {tempCol, tempRow};
        return pos;

    }

    public Tile getTile(int position) {
        int[] temp = arrayToMatrix(position);
        return matrix[temp[0]][temp[1]];
    }


    public boolean isLose() {
        for (int i = 0; i < ships.length; i++) {
            if (ships[i].isAlive()) {
                return false;
            }
        }
        return true;
    }

    private void createBoardTile() {
        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                matrix[i][j] = new Tile();
                matrix[i][j].setStatus(0);

            }
        }
    }

    private void createShips() {

        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship(i + 1);
        }
    }

    public void stormShipMove(){
        moveShipArr.clear();
        int shipNum=0;
        for(int i =0; i<bSize; i++){
            for(int j =0; j<bSize; j++){
                if(matrix[i][j].getStatus() == -10){
                    matrix[i][j].setStatus(0);
                }
            }
        }
        shipNum = shipToMove();
        while(shipNum!=999){

            removeShipById(shipNum);
            shipNum = shipToMove();
        }
        changeShipLocation();

    }

    private void removeShipById(int shipNum){
        for(int i =0; i<bSize; i++){
            for(int j =0; j<bSize; j++){
                if(matrix[i][j].getStatus() ==shipNum ||matrix[i][j].getStatus()==-shipNum){
                    matrix[i][j].setStatus(0);
                }
            }
        }
    }

    private int shipToMove(){
        for(int i =0; i<bSize; i++){
            for(int j =0; j<bSize; j++){
                if(matrix[i][j].getStatus() >0 &&matrix[i][j].getStatus()!=100){
                    moveShipArr.add(getShipById(matrix[i][j].getStatus()));
                    return matrix[i][j].getStatus();
                }
            }
        }
        return 999;
    }

    private void changeShipLocation() {


        for (Ship myShip : moveShipArr) {
            randomPlaceShips(ships[myShip.getId()-1]);
        }

    }
    private Ship getShipById(int shipId){
        for (int i = 0; i < ships.length; i++) {
            if(ships[i].getId()==shipId){
                ships[i].setHit(0);
                return ships[i];
            }

        }
        return null;
    }

    public void randomPlaceShips(Ship theShip) {
        int randomRow = -1;
        int randomCol = -1;
        int direction = -1;
        boolean isPlaced = false;
        int placingRange = (bSize - theShip.getSize());
        shipPos = new int[2];


        while (!isPlaced) {

            int counter = 0;
            direction = (int) (Math.random() * 2);
            if (direction == 1) {
                randomRow = (int) (Math.random() * bSize);// - 1);
                randomCol = (int) (Math.random() * placingRange);
            } else {
                randomRow = (int) (Math.random() * placingRange);
                randomCol = (int) (Math.random() * bSize);// - 1);
            }


            if (direction == 1) {
                for (int i = 0; i < theShip.getSize(); i++) {
                    if (matrix[randomRow][randomCol + i].getStatus() == EMPTY_TILE) {
                        counter++;
                    }
                }
                if (counter == theShip.getSize()) {
                    isPlaced = true;
                    theShip.setHorizontal(true);
                }
            } else {
                for (int i = 0; i < theShip.getSize(); i++) {
                    if (matrix[randomRow + i][randomCol].getStatus() == EMPTY_TILE) {
                        counter++;
                    }
                }
                if (counter == theShip.getSize()) {
                    isPlaced = true;
                    theShip.setHorizontal(false);
                }
            }
        }
        if (direction == 1) {
            for (int i = 0; i < theShip.getSize(); i++) {
                matrix[randomRow][randomCol + i].setStatus(theShip.getId());
                if(i==0){
                    matrixOfDirections[randomRow][randomCol + i] = 1;
                }
                else if(i==theShip.getSize()-1){
                    matrixOfDirections[randomRow][randomCol+ i] = 3;
                }
                else{
                    matrixOfDirections[randomRow][randomCol + i] = 2;
                }
                shipPos[0] = randomRow;
                shipPos[1] = randomCol + i;
                theShip.addShipPosition(shipPos);

            }
        } else {
            for (int i = 0; i < theShip.getSize(); i++) {
                matrix[randomRow + i][randomCol].setStatus(theShip.getId());
                if(i==0) {
                    matrixOfDirections[randomRow + i][randomCol] = -1;
                }
                else if(i==theShip.getSize()-1){
                    matrixOfDirections[randomRow + i][randomCol] = -3;
                }
                else{
                    matrixOfDirections[randomRow + i][randomCol] = -2;
                }
                shipPos[0] = randomRow + i;
                shipPos[1] = randomCol;
                theShip.addShipPosition(shipPos);
            }
        }
    }

    private void runOnShips() {
        for (int i = 0; i < ships.length; i++) {
            randomPlaceShips(ships[i]);
        }
    }


}

