package it.univr.gameoflife;

import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

public class Game {
	
	private Grid grid;
	private final Set<Point> deadCellsCoordinates;
	private int width;
	private int height;

	public Game(int width, int height) {
		this.grid = new Grid(width, height);
		this.deadCellsCoordinates = new HashSet<Point>();
		this.width = width;
		this.height = height;
	}
	
	public Grid getGrid() {
		return this.grid;
	}
	
	private class NextGeneration {
	
		private Integer i;
		private Grid current;
		private Grid next;
			
		private NextGeneration (int numOfThreads) {
			current = Game.this.grid;
			next = new Grid(width, height);
			
		
			Slave[] slaves = new Slave[numOfThreads];
			for(int pos = 0; pos < slaves.length; pos++) {
				slaves[pos] = new Slave();
				slaves[pos].start();
			}	
			
			for(Slave slave: slaves)
				try {
					slave.join();
				} catch(InterruptedException e) {}
			
			current = next;
		}
		
		private class Slave extends Thread {
			
			@Override
			public void run() {
				int slaveIndex;
				while(i < height * width) {
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
}

