package it.univr.gameoflife;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Game {
	
	private Grid current;
	private Set<Point> deadCellsCoordinates;

	public Game(int width, int height) {
		this.current = new Grid(width, height);
		this.deadCellsCoordinates = new HashSet<Point>();
	}
	
	public Grid getGrid() {
		return this.current;
	}
	
	public class NextGeneration {
		private Grid next;
		private Slave[] slaves;
		
		private class Slave extends Thread {
			
			@Override
			public void run() {
				//TODO
			}
		}
	}
}

