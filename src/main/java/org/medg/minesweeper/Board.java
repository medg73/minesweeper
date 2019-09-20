/**
 * 
 */
package org.medg.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Matthew Grossman
 * @version Sep 20, 2019
 */
public class Board {
	private int boardSize;
	private int numMines;
	
	int[][] board = null;
	Random random = null;
	
	List<Coord> visitedCoords = new ArrayList<Coord>();
	
	public Board(int boardSize, int numMines, long randSeed) {
		this.boardSize = boardSize;
		this.numMines = numMines;
		random = new Random(randSeed);
		
		board = new int[boardSize][boardSize];
		int numSquares = boardSize * boardSize;

		double chanceOfMine = (numMines * 1.0) / (numSquares * 1.0);
		placeMines(boardSize, numMines, chanceOfMine);
		
		setMineCounts(boardSize);
		
		
	}
	
	/**
	 * @param x
	 * @param y
	 * @return
	 */
	public int getContents(int x, int y) {
		return board[x][y];
	}

	public int getBoardSize() {
		return boardSize;
	}

	/**
	 * @param coord
	 * @return
	 */
	public boolean isVisited(Coord coord) {
		if(visitedCoords.contains(coord)) {
			return true;
		}
		return false;
	}

	/**
	 * @param c
	 */
	public boolean isMined(Coord c) {
		if(!visitedCoords.contains(c)) { 
			visitedCoords.add(c);
		}
		if(board[c.x][c.y] == -1) {
			return true;
		}
		return false;
		
	}

	
	/**
	 * @return
	 */
	public Coord getStartingCoord() {
		
		Coord startCoord = null;
		do {
			startCoord = new Coord(random.nextInt(getBoardSize()),
				random.nextInt(getBoardSize()));
		} while(board[startCoord.x][startCoord.y] != 0);
		
		return startCoord;

	}
	
	/**
	 * @return
	 */
	public boolean foundAllMines() {
		for(Coord coord : visitedCoords) {
			if(board[coord.x][coord.y] == -1) {
				return false;
			}
		}
		if(visitedCoords.size() == (getBoardSize() * getBoardSize() - numMines)) {
			return true;
		}
		return false;
	}

	

	/**
	 * @param boardSize
	 */
	private void setMineCounts(int boardSize) {
		for(int x = 0; x < boardSize; x++) {
			for(int y = 0; y < boardSize; y++) {
				if(board[x][y]== -1) {
					int xStart;
					int xEnd;
					int yStart;
					int yEnd;
					if(x > 0 ) {
						xStart = x - 1;
					} else {
						xStart = 0;
					}
					if(x == boardSize - 1) {
						xEnd = x;
					} else {
						xEnd = x + 1;
					}
					
					if(y > 0) {
						yStart = y - 1;
					} else {
						yStart = 0;
					}
					if(y == boardSize - 1) {
						yEnd = y;
					} else {
						yEnd = y + 1;
					}
					
					for(int i = xStart; i <= xEnd; i++) {
						for(int j = yStart; j <= yEnd; j++) {
							if(i == x && j == y ||
									board[i][j]== -1) {
								continue;
							}
							board[i][j]++;
						}
					}
					
				}
				
			}
		}
	}

	/**
	 * @param boardSize
	 * @param numMines
	 * @param chanceOfMine
	 */
	private void placeMines(int boardSize, int numMines, double chanceOfMine) {
		int numMinesPlaced = 0;
		
		while(numMinesPlaced < numMines) {
			for(int x = 0; x< boardSize; x++) {
				for(int y = 0; y < boardSize; y++) {
					if(board[x][y] != -1 &&
							random.nextDouble() < chanceOfMine && 
							numMinesPlaced < numMines) {
						board[x][y] = -1;
						numMinesPlaced++;
					}
				}
			}
		}
	}














}
