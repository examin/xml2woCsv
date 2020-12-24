package micro.examin.xml2woCsv.Leetcode;

import java.util.HashSet;

public class Soduko {
	public static void main(String[] args) {
		char[][] input = {{'5', '3', '.', '.', '7', '.', '.', '.', '.'},
				{'6', '.', '.', '1', '9', '5', '.', '.', '.'},
				{'.', '.', '.', '.', '.', '.', '.', '.', '.'},
				{'8', '.', '.', '.', '6', '.', '.', '.', '3'},
				{'4', '.', '.', '8', '.', '3', '.', '.', '1'},
				{'7', '.', '.', '.', '2', '.', '.', '.', '6'},
				{'.', '6', '.', '.', '.', '.', '2', '8', '.'},
				{'.', '.', '.', '4', '1', '9', '.', '.', '5'},
				{'.', '.', '.', '.', '8', '.', '.', '7', '9'}
		};
		Soduko s = new Soduko();
		System.out.println(s.isValidSudoku(input));
	}

	public boolean isValidSudoku(char[][] board) {
		if (checkRowCol(board)&&checkSquare(board)) {
			return true;
		}
		return false;
	}

	private boolean checkRowCol(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			HashSet<Character> rowOccr = new HashSet<>();
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != '.') {
					if (rowOccr.contains(board[i][j])) {
						return false;
					}
					rowOccr.add(board[i][j]);
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			HashSet<Character> rowOccr = new HashSet<>();
			for (int j = 0; j < board.length; j++) {
				if (board[j][i] != '.') {
					if (rowOccr.contains(board[j][i])) {
						return false;
					}
					rowOccr.add(board[j][i]);
				}
			}
		}
		return true;
	}

	private boolean checkSquare(char[][] board) {
		for (int i = 0; i < board.length; i += 3) {
			for (int j = 0; j < board.length; j += 3) {
				HashSet<Character> rowOccr = new HashSet<>();
				for (int k = i; k < i + 3; k += 1) {
					for (int l = j; l < j + 3; l += 1) {
						if (board[k][l] != '.') {
							if (rowOccr.contains(board[k][l])) {
								return false;
							}
							rowOccr.add(board[k][l]);

						}
					}

				}
				
			}
		}
		return true;
	}
}
