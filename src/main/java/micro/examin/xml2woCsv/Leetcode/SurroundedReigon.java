package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;

public class SurroundedReigon {
    public static void main(String[] args) {
        char[][] arr =
                {{'X','O','X','O','X','O'}, {'O','X','O','X','O','X'},{'X','O','X','O','X','O'}, {'O','X','O','X','O','X'}};
        solve(arr);
    }

    public static void solve(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == 'O') {
                board[i][0] = '1';
                visitWholeIsland(board, i, 0, 1);
            }
            if (board[i][board[0].length - 1] == 'O') {
                board[i][board[0].length - 1] = '1';
                visitWholeIsland(board, i, board[0].length - 1, 1);
            }
        }
        for (int i = 0; i < board[0].length; i++) {
            if (board[0][i] == 'O') {
                board[0][i] = '1';
                visitWholeIsland(board, 0, i, 1);
            }
            if (board[board.length-1][i] == 'O') {
                board[board.length-1][i]= '1';
                visitWholeIsland(board, board.length-1, i, 1);
            }
        }

        for(int i =0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]  == 'O'){
                    board[i][j] = 'X';
                }
                else if(board[i][j]  == '1'){
                    board[i][j] = 'O';
                }
            }
        }
        for(int i =0;i<board.length;i++){
            System.out.println(Arrays.toString(board[i]));
        }
    }

    public static void visitWholeIsland(char grid[][], int i, int j, int count) {
        if (i == 2 && j == 2) {
            System.out.println();
        }
        int chances[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int curr[] : chances) {
            int x = i + curr[0];
            int y = j + curr[1];
            if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) {
                if (grid[x][y] == 'O') {
                    grid[x][y] = '1';
                    visitWholeIsland(grid, x, y, count);
                }
            }
        }
    }
}
