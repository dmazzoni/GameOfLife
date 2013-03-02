package it.univr.gameoflife;

import java.awt.Point;

public class Grid {
	
	private final Cell[][] cells;
	
	/**
	 * Constructs and initializes a grid of the specified size. All cells are initially dead.
	 * @param width the number of columns
	 * @param height the number of rows
	 */
	public Grid(int width, int height) {
		cells = new Cell[height][width];
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[0].length; j++)
				cells[i][j] = new Cell(i, j);
	}
	
	/**
	 * Determines if the cell at position <code>p</code> in this grid is alive.
	 * @param p the point representing the coordinates of the cell.
	 * @return <code>true</code> if the cell is alive, <code>false</code> otherwise.
	 */
	public boolean isAlive(Point p) {
		return cells[p.x][p.y].alive;
	}
	
	/**
	 * Switches the cell at position <code>p</code> in this grid to the opposite state.
	 * If the cell is alive it becomes dead, and vice versa.
	 * @param p the point representing the coordinates of the cell.
	 */
	public void changeState(Point p) {
		cells[p.x][p.y].alive = !cells[p.x][p.y].alive;
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
				cells[absX][absY].alive = true;
		}
	}
	
	/**
	 * Represents a single cell of a grid.
	 */
	public class Cell {
		
		private final Point coordinates;
		private boolean alive;
		
		private Cell(int x, int y) {
			coordinates = new Point(x, y);
		}
	}
}
