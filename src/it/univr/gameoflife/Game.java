package it.univr.gameoflife;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Game {
	
	private Grid current;
	private Grid next;
	private Set<Point> deadCellsCoordinates;
	private int width;

	public Game(int width, int height) {
		this.current = new Grid(width, height);
		this.deadCellsCoordinates = new HashSet<Point>();
		this.width = width;
	}
	
	public Grid getGrid() {
		return this.current;
	}
	
	public void nextGeneration(int numOfThreads) {
		
		Integer i;
		
		Slave[] slaves = new Slave[numOfThreads];
		for(int i = 0; i < slaves.length; i++) {
			slaves[i] = new Slave();
			slaves[i].run();
		}	
	}
		
	private class Slave extends Thread {

		@Override
		public void run() {
			int slaveIndex;
			while(i < LIMITE) {
				synchronized(i) {
					slaveIndex = i++;
				}
				Point p = new Point((int)i / width, (int) i % width);
				if(!(deadCellsCoordinates.contains(p)))
					checkNeighbour(p);
			}
		}

		private void checkNeighbour(Point p) {
			for(int dx = -1; dx <= 1; dx++)
				for(int dy = -1; dy <= 1; dy++)
					if(p.x + dx > 0 && p.x + dx < XXXX && p.y + dy



		}
	}
	
}

