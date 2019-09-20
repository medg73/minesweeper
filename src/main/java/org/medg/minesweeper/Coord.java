/**
 * 
 */
package org.medg.minesweeper;

/**
 * @author Matthew Grossman
 * @version Sep 20, 2019
 */
public class Coord {

	public final int x;
	public final int y;
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return("x = " + x + ", y = " + y);
	}
	
	@Override
	public boolean equals(Object object) {
		if(object instanceof Coord) {
			Coord otherCoord = (Coord)object;
			if(this.x == otherCoord.x && this.y == otherCoord.y) {
				return true;
			}
		}
		return false;
	}
}
