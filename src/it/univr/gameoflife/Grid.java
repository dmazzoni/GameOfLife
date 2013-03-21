package it.univr.gameoflife;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores a logical grid of cells.
 * @author Giacomo Annaloro
 * @author Davide Mazzoni
 *
 */
public class Grid {
	
	/**
	 * Stores the cell matrix.
	 */
	private final boolean[][] cells;
	
	/**
	 * Stores the coordinates of permanently dead cells.
	 */
	private final Set<Point> deadCellCoordinates;
	
	/**
	 * Constructs and initializes a <code>Grid</code> of the specified size. All cells are initially dead.
	 * @param size the size of the grid
	 */
	public Grid(Dimension size) {
		cells = new boolean[size.height][size.width];
		this.deadCellCoordinates = new HashSet<Point>();
	}
	
	/**
	 * Constructs and initializes a <code>Grid</code> of the specified size, starting from another grid.
	 * @param size the size of the grid
	 * @param other the grid to start from
	 * @param keepStates if <code>true</code> initial cell states are copied from the passed grid; otherwise all the cells are initially dead
	 */
	public Grid(Dimension size, Grid other, boolean keepStates) {
		cells = new boolean[size.height][size.width];
		if(keepStates)
			for (int i = 0; (i < size.height) && (i < other.cells.length); i++)
				for (int j = 0; (j < size.width) && (j < other.cells[0].length); j++)
					cells[i][j] = other.cells[i][j];
		this.deadCellCoordinates = other.deadCellCoordinates;
	}
	
	/**
	 * Determines the cell state at the specified coordinates in this grid.
	 * @param p the cell coordinates
	 * @return The {@link CellState} of the specified cell.
	 */
	public CellState getState(Point p) {
		if(deadCellCoordinates.contains(p))
			return CellState.PERMANENTLY_DEAD;
		return cells[p.x][p.y] ? CellState.ALIVE : CellState.DEAD;
	}
	
	/**
	 * Determines the cell state at the specified coordinates in this grid.
	 * @param i the row index
	 * @param j the column index
	 * @return The {@link CellState} of the specified cell.
	 */
	public CellState getState(int i, int j) {
		return getState(new Point(i,j));
	}
	
	/**
	 * Switches the cell at position <code>p</code> in this grid to the opposite state.
	 * If the cell is alive it becomes dead, and vice versa.
	 * @param p the <code>Point</code> representing the coordinates of the cell.
	 */
	public void changeState(Point p) {
		cells[p.x][p.y] = !cells[p.x][p.y];
	}
	
	/**
	 * Switches the cell at the specified coordinates in this grid to the opposite state.
	 * If the cell is alive it becomes dead, and vice versa.
	 * @param i the row index
	 * @param j the column index
	 */
	public void changeState(int i, int j) {
		cells[i][j] = !cells[i][j];
	}
	
	/**
	 * Marks the cell at the specified coordinates as permanently dead.
	 * @param i the row index
	 * @param j the column index
	 */
	public void markDeadCell(int i, int j) {
		deadCellCoordinates.add(new Point(i, j));
	}
	
	/**
	 * Inserts a shape into this grid, with its upper left corner positioned at <code>dest</code>.
	 * @param dest the point representing the destination coordinates.
	 * @param shape the shape to insert.
	 */
	public void insertShape(Point dest, Shape shape) {
		int absX, absY;
		for (Point shapePoint : shape) {
			absX = dest.x + shapePoint.x;
			absY = dest.y + shapePoint.y;
			if (absX >= 0 && absX < cells.length && absY >= 0 && absY < cells[0].length)
				cells[absX][absY] = true;
		}
	}
	
}
