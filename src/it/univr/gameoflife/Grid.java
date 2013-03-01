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
		//TODO
		return false;
	}
	
	public void changeState(int i, int j) {
		//TODO
	}
	
	public void insertShape(int i, int j, Shape shape) {
		//TODO
	}
	
	public class Cell {
		
		private final Point coordinates;
		private boolean alive;
		
		private Cell(int x, int y) {
			coordinates = new Point(x, y);
		}
	}
}
