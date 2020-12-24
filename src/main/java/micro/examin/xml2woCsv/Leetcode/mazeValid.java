package micro.examin.xml2woCsv.Leetcode;

import java.util.Scanner;

public class mazeValid {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int numOftests = scn.nextInt();
		for (int i = 0; i < numOftests; i++) {
			int row = scn.nextInt();
			int column = scn.nextInt();
			scn.nextLine();
			char[][] maze = new char[row][column];
			for (int x = 0; x < row; x++) {
				maze[x] = scn.nextLine().toCharArray();
			}
			int count = 0;
			for (int x = 0; x < column; x++) {
				if(maze[0][x]=='.'){
					count++;
				}
				if(maze[row-1][x]=='.'){
					count++;
				}
			}
			for (int x = 0; x < row; x++) {
				if(maze[x][0]=='.'){
					count++;
				}
				if(maze[x][column-1]=='.'){
					count++;
				}
			}
			System.out.println(count);
			//on boundires see if two openings are
		}
	}
}
