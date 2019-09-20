/**
 * 
 */
package org.medg.minesweeper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author Matthew Grossman
 * @version Sep 20, 2019
 */
public class BoardTest {
	
	@Test
	public void testBoardCreation() {
		int boardsize = 5;
		int nummines = 5;
		long randSeed = 1;
		Board board = new Board(boardsize, nummines, randSeed);
		BoardDisplay boardDisplay = new BoardDisplay(board);
		
		String expectedFullBoard = 
				" 1  *  1  0  0 \n" + 
				" 1  1  2  2  2 \n" + 
				" 0  0  1  *  * \n" + 
				" 0  1  3  4  3 \n" + 
				" 0  1  *  *  1 \n";
		
		
		assertEquals(expectedFullBoard,  
				boardDisplay.displayAllBoardContents());
		
		List<Coord> visitedSquares = Arrays.asList(
				new Coord(0, 0), 
				new Coord(0, 1), 
				new Coord(1, 1), 
				new Coord(1, 3),
				new Coord(4, 4),
				new Coord(0, 1));
		
		Coord mineCoord = new Coord(1, 0);
		
		for(Coord c: visitedSquares) {
			assertFalse(board.isMined(c));
		}
		assertFalse(board.foundAllMines());;
		assertTrue(board.isMined(mineCoord));
		
		String expectedVisitedBoard = 
				" 1  *  ?  ?  ? \n" + 
				" 1  1  ?  ?  ? \n" + 
				" ?  ?  ?  ?  ? \n" + 
				" ?  1  ?  ?  ? \n" + 
				" ?  ?  ?  ?  1 \n";
		
		assertEquals(expectedVisitedBoard, 
				boardDisplay.displayVisitedBoard());
				
		Coord startingCoord = board.getStartingCoord();
		assertEquals(0,  startingCoord.x);
		assertEquals(3,  startingCoord.y);
				
		assertFalse(board.foundAllMines());
		
		String expectedVisitedBoardWithCoordString =
				"   0  1  2  3  4  \n" + 
				"------------------\n" + 
				"0| 1  *  ?  ?  ? \n" + 
				"1| 1  1  ?  ?  ? \n" + 
				"2| ?  ?  ?  ?  ? \n" + 
				"3| ?  1  ?  ?  ? \n" + 
				"4| ?  ?  ?  ?  1 \n";
				
		assertEquals(expectedVisitedBoardWithCoordString,
				boardDisplay.displayVisitedBoardWithCoords());
	}
	
	@Test
	public void testFoundAllMines() {
		int boardsize = 5;
		int nummines = 5;
		long randSeed = 1;
		Board board = new Board(boardsize, nummines, randSeed);
		BoardDisplay boardDisplay = new BoardDisplay(board);
		
		for(int x = 0; x < boardsize; x++) {
			for(int y = 0; y < boardsize; y++) {
				if( (x == 1 && y == 0) ||
					(x == 3 && y == 2) ||
					(x == 4 && y == 2) ||
					(x == 2 && y == 4) ||
					(x == 3 && y == 4) ) {
					continue;
				} else {
					board.isMined(new Coord(x, y));
				}
			}
		}
		
		String expectedVisitedBoard = 
				" 1  ?  1  0  0 \n" + 
				" 1  1  2  2  2 \n" + 
				" 0  0  1  ?  ? \n" + 
				" 0  1  3  4  3 \n" + 
				" 0  1  ?  ?  1 \n";

		assertEquals(expectedVisitedBoard,  
				boardDisplay.displayVisitedBoard());;
		assertTrue(board.foundAllMines());
		
	}

	
	

}
