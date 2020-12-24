package micro.examin.xml2woCsv.BackTracking;

public class QueenChess {
    public static void main(String[] args) {
        int n = 8;
        boolean[][] board = new boolean[n][n];
        fillQueen(board, n, 0);
        System.out.println();
    }

    private static boolean fillQueen(boolean[][] board, int n, int row) {
        if(row== board.length){
            return true;
        }
        for (int i = 0; i < board.length; i++) {
            if (isAvilable(board, row, i)) {
                board[row][i] = true;
                if (fillQueen(board, n - 1, row + 1)) {
                    return true;
                }
                board[row][i] = false;
            }
        }
        return false;
    }

    private static boolean isAvilable(boolean[][] board, int row, int col) {
//check same row in left;
        for (int i = 0; i < col; i++) {
            if(board[row][i]){
                    return false;
            }
        }
        for (int i = row,j=col; i >=0&&j>=0; i--,j--) {
            if(board[i][j]){
                return false;
            }
        }
        for (int i = row,j=col; j>=0&&i>=board.length; i++,j--) {
            if(board[i][j]){
                return false;
            }
        }
        return true;
    }
}
