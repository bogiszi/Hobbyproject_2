package com.jetbrains.ideatest2;

public class BigBoard {

    private int[][] bigBoard;
    private static final int SIZE = 9;

    public BigBoard(int inputDifficulty) {
        if (inputDifficulty == 1) {
            this.bigBoard = new int[][]{
                    {1, 0, 0, 9, 0, 4, 0, 8, 2},
                    {0, 5, 2, 6, 8, 0, 3, 0, 0},
                    {8, 6, 4, 2, 0, 0, 9, 1, 0},
                    {0, 1, 0, 0, 4, 9, 8, 0, 6},
                    {4, 9, 8, 3, 0, 0, 7, 0, 1},
                    {6, 0, 7, 0, 1, 0, 0, 9, 3},
                    {0, 8, 6, 0, 3, 5, 2, 0, 9},
                    {5, 0, 9, 0, 0, 2, 1, 3, 0},
                    {0, 3, 0, 4, 9, 7, 0, 0, 8}};
        } else if (inputDifficulty == 2) {
            this.bigBoard = new int[][]{
                    {2, 0, 0, 0, 0, 1, 4, 0, 0},
                    {7, 0, 0, 0, 9, 0, 0, 0, 0},
                    {0, 3, 0, 0, 5, 6, 0, 0, 2},
                    {0, 7, 0, 2, 1, 8, 5, 0, 6},
                    {1, 0, 2, 0, 0, 5, 9, 3, 0},
                    {0, 6, 0, 0, 0, 9, 0, 0, 0},
                    {6, 0, 0, 0, 8, 0, 0, 0, 0},
                    {9, 0, 0, 5, 0, 3, 8, 0, 0},
                    {4, 1, 8, 0, 2, 0, 0, 6, 5}};
        } else {
            this.bigBoard = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 3, 0, 8, 5},
                    {0, 0, 1, 0, 2, 0, 0, 0, 0},
                    {0, 0, 0, 5, 0, 7, 0, 0, 0},
                    {0, 0, 4, 0, 0, 0, 1, 0, 0},
                    {0, 9, 0, 0, 0, 0, 0, 0, 0},
                    {5, 0, 0, 0, 0, 0, 0, 7, 3},
                    {0, 0, 2, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 0, 4, 0, 0, 0, 9}};
        }

    }

    public boolean checkIfItIsGoodNumber(Number numberWrittenToBox) {

        bigBoard[numberWrittenToBox.getX()][numberWrittenToBox.getY()] = numberWrittenToBox.getValue();

        SmallBoard checkBoard = getSmallBoard(numberWrittenToBox);

        boolean isNeededFurtherCheck = checkBoard.checkIfItIsGoodNumber(numberWrittenToBox.getValue());
        int count = 0;
        if (isNeededFurtherCheck) {
            for (int i = 0; i < SIZE; i++) {
                if (bigBoard[numberWrittenToBox.getX()][i] == numberWrittenToBox.getValue()) {
                    count++;
                }
                if (bigBoard[i][numberWrittenToBox.getY()] == numberWrittenToBox.getValue()) {
                    count++;
                }
            }
            if (count == 2) {
                return true;
            } else {
                bigBoard[numberWrittenToBox.getX()][numberWrittenToBox.getY()] = 0;
            }
        }
        return false;
    }


    private SmallBoard getSmallBoard(Number numberWrittenToBox) {
        SmallBoard checkBoard = new SmallBoard();
        if (numberWrittenToBox.getX() < 3 && numberWrittenToBox.getY() < 3) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    checkBoard.getSmallBoard()[i][j] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 3 && numberWrittenToBox.getY() < 6) {
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 6; j++) {
                    checkBoard.getSmallBoard()[i][j - 3] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 3 && numberWrittenToBox.getY() < 9) {
            for (int i = 0; i < 3; i++) {
                for (int j = 6; j < 9; j++) {
                    checkBoard.getSmallBoard()[i][j - 6] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 6 && numberWrittenToBox.getY() < 3) {
            for (int i = 3; i < 6; i++) {
                for (int j = 0; j < 3; j++) {
                    checkBoard.getSmallBoard()[i - 3][j] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 6 && numberWrittenToBox.getY() < 6) {
            for (int i = 3; i < 6; i++) {
                for (int j = 3; j < 6; j++) {
                    checkBoard.getSmallBoard()[i - 3][j - 3] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 6 && numberWrittenToBox.getY() < 9) {
            for (int i = 3; i < 6; i++) {
                for (int j = 6; j < 9; j++) {
                    checkBoard.getSmallBoard()[i - 3][j - 6] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 9 && numberWrittenToBox.getY() < 3) {
            for (int i = 6; i < 9; i++) {
                for (int j = 0; j < 3; j++) {
                    checkBoard.getSmallBoard()[i - 6][j] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 9 && numberWrittenToBox.getY() < 6) {
            for (int i = 6; i < 9; i++) {
                for (int j = 3; j < 6; j++) {
                    checkBoard.getSmallBoard()[i - 6][j - 3] = bigBoard[i][j];
                }
            }
        } else if (numberWrittenToBox.getX() < 9 && numberWrittenToBox.getY() < 9) {
            for (int i = 6; i < 9; i++) {
                for (int j = 6; j < 9; j++) {
                    checkBoard.getSmallBoard()[i - 6][j - 6] = bigBoard[i][j];
                }
            }
        }
        return checkBoard;
    }

    public String getTextToTextField(int coordinate1, int coordinate2) {
        return String.valueOf(bigBoard[coordinate1][coordinate2]);
    }
}
