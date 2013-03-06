package it.univr.gameoflife;

import java.awt.Dimension;
import java.awt.Point;

public class Grid {
	
	private final boolean[][] cells;
	
	/**
	 * Constructs and initializes a grid of the specified size. All cells are initially dead.
	 * @param size the size of the grid
	 */
	public Grid(Dimension size) {
		cells = new boolean[size.height][size.width];
	}
	
	/**
	 * Determines if the cell at position <code>p</code> in this grid is alive.
	 * @param p the point representing the coordinates of the cell.
	 * @return <code>true</code> if the cell is alive, <code>false</code> otherwise.
	 */
	public boolean isAlive(Point p) {
		return cells[p.x][p.y];
	}
	
	/**
	 * Switches the cell at position <code>p</code> in this grid to the opposite state.
	 * If the cell is alive it becomes dead, and vice versa.
	 * @param p the point representing the coordinates of the cell.
	 */
	public void changeState(Point p) {
		cells[p.x][p.y] = !cells[p.x][p.y];
	}
	
	/**
	 * Inserts a shape into this grid, with its upper left corner positioned at the coordinates specified by <code>p</code>.
	 * @param point the point representing the destination coordinates.
	 * @param shape the shape to insert.
	 */
	public void insertShape(Point point, Shape shape) {
		int absX, absY;
		for (Point shapePoint : shape) {
			absX = point.x + shapePoint.x;
			absY = point.y + shapePoint.y;
			if (absX < cells.length && absY < cells[0].length)
				cells[absX][absY] = true;
		}
	}
	
}
