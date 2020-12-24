package micro.examin.xml2woCsv.Try;

import java.util.Arrays;

public class Sudoku {
    public static void main(String[] args) {
        int[][] board = new int[][]
                {
                        {3, 0, 6, 5, 0, 8, 4, 0, 0},
                        {5, 2, 0, 0, 0, 0, 0, 0, 0},
                        {0, 8, 7, 0, 0, 0, 0, 3, 1},
                        {0, 0, 3, 0, 1, 0, 0, 8, 0},
                        {9, 0, 0, 8, 6, 3, 0, 0, 5},
                        {0, 5, 0, 0, 9, 0, 6, 0, 0},
                        {1, 3, 0, 0, 0, 0, 2, 5, 0},
                        {0, 0, 0, 0, 0, 0, 0, 7, 4},
                        {3, 0, 5, 2, 0, 6, 3, 0, 0}
                };
        System.out.println(solve(0, 0, board));
        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }
        System.out.println("yoyo");
    }

    private static boolean solve(int row, int col, int[][] board) {
            if (col == board.length) {
                col = 0;
                row++;
                if (row == board.length) {
                    return true;
                }
            }
        if (board[row][col] == 0) {
            for (int i = 1; i <= board.length; i++) {
                if (isValid(row, col, i, board)) {
                    board[row][col] = i;
                    if (solve(row, col + 1, board)) {
                        return true;
                    } else {
                        board[row][col] = 0;
                    }
                }
            }
        } else {
            if (isValid(row,col,board[row][col],board)&&solve(row, col + 1, board)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isValid(int row, int col, int val, int[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][col] == val&&i!=row) {
                return false;
            }
            if (board[row][i] == val&&i!=col) {
                return false;
            }
        }
        int rowStart = ((row / 3) * 3);
        int colStart = ((col / 3) * 3);
        for (int i = rowStart; i < rowStart + 3; i++) {
            for (int j = colStart; j < colStart + 3; j++) {
                if (board[i][j] == val&&i!=row&&i!=col) {
                    return false;
                }
            }
        }
        return true;
    }
}
