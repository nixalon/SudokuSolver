package com.company;

class Sudokusolve {

    private int[][] board;

    // Skapar konstruktor för att göra 9x9 rutor.
    public Sudokusolve(int[][] board){
        // 9x9 rutor
        this.board = new int[9][9];

        // Skapar 9st columns & rows
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                this.board[i][j] = board[i][j];
            }
        }
    }

    private boolean possible(int row, int column, int value){
        //kontrollera rad och kolumn
        for(int i = 0; i < 9; i++){
            if(board[column][i] == value || board[i][row] == value){
                return false;
            }
        }
        return true;
    }

    //kontrollera lilla rutan 3x3
    private boolean boxCheck(int row, int column, int value) {
        int rowStart = row - row % 3;
        int colStart = column - column% 3;
        for(int i = rowStart; i < rowStart + 3; i++){
            for(int j = colStart; j < colStart + 3; j++){
                if(board[i][j] == value){
                    return true;
                }
            }
        }
        return false;
    }

    //För att tillsätta ett nytt nummer så behöver possible() returnera false för samtliga argument.
    private boolean setNum(int row, int column, int value){
        return !(possible(row, column, value) || boxCheck(row, column, value));
    }

    public boolean solve(){
        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                if(board[r][c] == 0){
                    for(int num = 1; num <= 9; num++){
                        if(setNum(r,c,num)){
                            board[r][c] = num;              // backtrack sker här!
                            if(solve()){
                                return true;
                            } else {
                                board[r][c] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        // true betyder att sudokun är löst.
        return true;
    }

    public void printSudoku(){
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(" " + board[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[][] sudokuPuzzle =  {
                {0, 0, 0, 8, 0, 4, 9, 3, 7},
                {0, 7, 4, 1, 0, 0, 0, 8, 0},
                {8, 3, 2, 0, 0, 0, 4, 0, 0},
                {2, 0, 5, 3, 0, 0, 7, 4, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 6},
                {1, 4, 3, 0, 0, 0, 2, 0, 0},
                {0, 0, 7, 0, 9, 0, 6, 0, 0},
                {0, 2, 1, 7, 5, 6, 8, 9, 0},
                {6, 5, 9, 2, 3, 0, 0, 7, 4},
        };
        Sudokusolve sudoku = new Sudokusolve(sudokuPuzzle);

        System.out.println("Sudoku startbräda:");
        sudoku.printSudoku();

        if(sudoku.solve()){
            System.out.println("Tadaaaaa!!");
            // print solved sudoku
            sudoku.printSudoku();
        } else {
            System.err.println("För svårt, fattar inte...");
        }
    }
}
