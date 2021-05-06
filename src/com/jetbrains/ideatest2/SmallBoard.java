package com.jetbrains.ideatest2;

public class SmallBoard {

    private int[][] smallBoard;
    private static final int SIZE = 3;


    public SmallBoard() {
        this.smallBoard = new int[SIZE][SIZE];
    }

    public int[][] getSmallBoard() {
        return smallBoard;
    }


    public boolean checkIfItIsGoodNumber(int inputNumber) {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (smallBoard[i][j] == inputNumber) {
                    count++;
                }
            }
        }
        if (count == 1) {
            return true;
        }
        return false;
    }



}
