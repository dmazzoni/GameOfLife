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
	
	public boolean isAlive(Point p) {
		return cells[p.x][p.y].alive;
	}
	
	public void changeState(Point p) {
		cells[p.x][p.y].alive = !cells[p.x][p.y].alive;
	}
	
	public void insertShape(Point point, Shape shape) {
		int absX, absY;
		for (Point shapePoint : shape) {
			absX = point.x + shapePoint.x;
			absY = point.y + shapePoint.y;
			if (absX < cells.length && absY < cells[0].length)
				cells[absX][absY].alive = true;
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
