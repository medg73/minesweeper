/**
 * 
 */
package org.medg.minesweeper;

import java.io.IOException;
import java.util.Scanner;

/**
 * @author Matthew Grossman
 * @version Sep 20, 2019
 */
public class Minesweeper {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		boolean gamePlaying = true;

		Scanner scanner = new Scanner(System.in);
		
		System.out.println("starting new game");
		
		System.out.print("Enter board size: ");
		int boardSize = scanner.nextInt();
		System.out.print("Enter number of mines: ");
		int numMines = scanner.nextInt();
		System.out.print("Enter random seed: "); 
		long randomSeed = scanner.nextLong();
		
		Board board = new Board(boardSize, numMines, 
				randomSeed);
		BoardDisplay boardDisplay = new BoardDisplay(board);
		Coord startingCoord = board.getStartingCoord();
		board.isMined(startingCoord);
				
		while(gamePlaying) {
		
			System.out.println(boardDisplay.displayVisitedBoardWithCoords());	
			System.out.print("Enter x: ");
			int x = scanner.nextInt();
			System.out.print("Enter y: ");
			int y = scanner.nextInt();
			
			if(x < 0 || 
					x >= boardSize || 
					y < 0 ||
					y >= boardSize) {
				System.out.println("bad input");
				continue;
			}
			
			boolean isMined = board.isMined(new Coord(x, y));
			if(isMined) {
				System.out.println("Game Over");
				System.out.println(boardDisplay.displayVisitedBoardWithCoords());
				gamePlaying = false;
			} else if(board.foundAllMines()) {
				System.out.println("You win!");
				System.out.println(boardDisplay.displayAllBoardContents());
				gamePlaying = false;
			}
		
		}
			
		scanner.close();
	}

}
