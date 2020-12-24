package micro.examin.xml2woCsv.Leetcode;

public class KnightTour {
	int[][] moves = {{2, 1}, {1, 2}, {-1, -2}, {-2, -1}, {-1, 2}, {1, -2}, {-2, 1}, {2, -1}};

	public static void main(String[] args) {
		int n = 8;
		String start = "d4";
		String end = "c6";
		int sx = start.charAt(0)-'a'+1;
		int sy = Integer.parseInt(String.valueOf(start.charAt(1)));

		int tx = end.charAt(0)-'a'+1;
		int ty = Integer.parseInt(String.valueOf(end.charAt(1)));
		int[][] chess = new int[n][n];
		KnightTour obj = new KnightTour();
		System.out.println(obj.minKnightStep(chess,sx,sy,tx,ty));
	}

	public int minKnightStep(int[][] chess, int itrI, int itrJ, int tarI, int tarJ) {
		if (itrI == tarI && itrJ == tarJ) {
			return 0;
		}
		if(chess[itrI][itrJ]!=0){
			return chess[itrI][itrJ];
		}
		int min = 1000000007;
		for (int[] move : moves) {
			if (validMove(chess, itrI+move[0], itrJ+move[1])) {
				chess[itrI + move[0]][itrJ + move[1]]=1;
				min = Math.min(min, 1+minKnightStep(chess, itrI + move[0], itrJ + move[1], tarI, tarJ));
				chess[itrI + move[0]][itrJ + move[1]]=0;
			}
		}
		chess[itrI][itrJ] = min==1000000007?0:min;
		return min;
	}

	private boolean validMove(int[][] chess, int itrI, int itrJ) {

		if (itrI >= 0 && itrJ >= 0 && itrI < chess.length && itrJ < chess[0].length) {
			return chess[itrI][itrJ]==0?true:false;
		} else {
			return false;
		}
	}
}
