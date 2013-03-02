package it.univr.gameoflife;

import java.awt.Point;

public class Grid {
	
	private final Cell[][] cells;
	
	public Grid(int width, int height) {
		cells = new Cell[height][width];
		for (int i = 0; i < cells.length; i++)
			for (int j = 0; j < cells[0].length; j++)
				cells[i][j] = new Cell(i, j);
	}
	
	public boolean isAlive(int i, int j) {
		return cells[i][j].alive;
	}
	
	public void changeState(int i, int j) {
		cells[i][j].alive = !cells[i][j].alive;
	}
	
	public void insertShape(int i, int j, Shape shape) {
		int absX, absY;
		for (Point p : shape) {
			absX = p.x + j;
			absY = p.y + i;
			if (absX < cells[0].length && absY < cells.length)
				cells[i][j].alive = true;
		}
	}
	
	public class Cell {
		
		private final Point coordinates;
		private boolean alive;
		
		private Cell(int x, int y) {
			coordinates = new Point(x, y);
		}
	}
}
