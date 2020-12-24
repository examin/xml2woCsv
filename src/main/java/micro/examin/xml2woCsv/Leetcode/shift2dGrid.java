package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class shift2dGrid {
	public static void main(String[] args) {
		int[][] grid = {{3, 8, 1, 9}, {19, 7, 2, 5}, {4, 6, 11, 10}, {12, 0, 21, 13}};
		int k = 4;
		shift2dGrid obj = new shift2dGrid();
		obj.shiftGrid(grid, k);
	}

	public List<List<Integer>> shiftGrid(int[][] grid, int k) {
		int i = 0;
		int j = 0;
		k = k % (grid.length * grid[0].length);


		int row1 = (int) Math.floor(k / grid.length);
		int col1 = k % grid[0].length;


		int[][] res = new int[grid.length][grid[0].length];
		for (int y = col1; y < grid[0].length; y++, j++) {
			res[row1][y] = grid[i][j];
		}
		i++;
		i = i % grid.length;
		j = j % grid[0].length;
		int p = row1 + 1;
		int q = 0 ;
		for (p = row1 + 1; p < grid.length; p++, i++, i = i % grid.length) {
			for (q = 0; q < grid[0].length; q++, j++, j = j % grid[0].length) {
				res[p][q] = grid[i][j];
			}
		}
		row1 = (int) Math.floor(k / grid.length);
		col1 = k % grid[0].length;
		i = i % grid.length;
		j = j % grid[0].length;
		for(int x = 0;x<=row1-1;x++,i++,i = i % grid.length){
			for(int y = 0;y<grid[0].length;y++, j++,j = j % grid[0].length){
				res[x][y] = grid[i][j];
			}
		}
		for (int y = 0; y < col1; y++, j++) {
			res[i][j] = grid[row1][y];
		}

		for (int x = 0; x < grid.length; x++) {
			System.out.println(Arrays.toString(res[x]));
		}
		return new LinkedList();
	}
}
