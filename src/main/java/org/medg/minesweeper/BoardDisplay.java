/**
 * 
 */
package org.medg.minesweeper;

/**
 * @author Matthew Grossman
 * @version Sep 20, 2019
 */
public class BoardDisplay {
	
	private Board board;

	public BoardDisplay(Board board) {
		this.board = board;
	}
	
	public String displayAllBoardContents() {
		
		StringBuilder sBuilder = new StringBuilder();
		
		for(int y = 0; y < board.getBoardSize(); y++) {
			for(int x = 0; x < board.getBoardSize(); x++) {
				sBuilder.append(" ");
				displaySquare(sBuilder, y, x);
				sBuilder.append(" ");
			}
			sBuilder.append("\n");
		}
		
		return sBuilder.toString();
		
	}
	
	public String displayVisitedBoard() {
		StringBuilder sBuilder = new StringBuilder();
		
		for(int y = 0; y < board.getBoardSize(); y++) {
			for(int x = 0; x < board.getBoardSize(); x++) {
				displayRowWithHiddenSquares(sBuilder, y, x);
			}
			sBuilder.append("\n");
		}
		
		return sBuilder.toString();
	}
	
	public String displayVisitedBoardWithCoords() {
	StringBuilder sBuilder = new StringBuilder();
		
		sBuilder.append("   ");
		for(int x = 0; x < board.getBoardSize(); x++) {
			sBuilder.append(x);
			sBuilder.append("  ");
		}
		sBuilder.append("\n");
		for(int x = 0; x < board.getBoardSize() * 3 + 3; x++) {
			sBuilder.append("-");
		}
		sBuilder.append("\n");
		
		
		for(int y = 0; y < board.getBoardSize(); y++) {
			sBuilder.append(y);
			sBuilder.append("|");
			for(int x = 0; x < board.getBoardSize(); x++) {
				displayRowWithHiddenSquares(sBuilder, y, x);
			}
			sBuilder.append("\n");
		}
		
		return sBuilder.toString();
	}
	
	
	
	/**
	 * @param sBuilder
	 * @param y
	 * @param x
	 */
	private void displayRowWithHiddenSquares(StringBuilder sBuilder, int y, int x) {
		sBuilder.append(" ");
		Coord coord = new Coord(x, y);
		if(board.isVisited(coord)) {
			displaySquare(sBuilder, y, x);
		} else {
			sBuilder.append("?");
		}
		sBuilder.append(" ");
	}

	/**
	 * @param sBuilder
	 * @param y
	 * @param x
	 */
	private void displaySquare(StringBuilder sBuilder, int y, int x) {
		if(board.getContents(x, y) == -1) {
			sBuilder.append("*");
		} else  {
			sBuilder.append(board.getContents(x, y));
		}
	}
	
}
