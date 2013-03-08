package it.univr.gameoflife;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashSet;
import java.util.Set;

/**
 * Stores a logical Game Of Life instance, and provides a concurrent generation calculator.
 * @author Giacomo Annaloro
 * @author Davide Mazzoni
 * 
 */
public class Game {
	
	/**
	 * Stores the current generation of cells.
	 */
	private Grid grid;
	
	/**
	 * Stores the coordinates of permanently dead cells.
	 */
	private final Set<Point> deadCellCoordinates;
	
	/**
	 * Stores the current width and height of the game.
	 */
	private Dimension size;

	/**
	 * Constructs and initializes a <code>Game</code> of the specified size.
	 * @param width the number of columns
	 * @param height the number of rows
	 */
	public Game(int width, int height) {
		this.size = new Dimension(width, height);
		this.grid = new Grid(size);
		this.deadCellCoordinates = new HashSet<Point>();
	}
	
	/**
	 * Returns the cell grid of this game.
	 * @return A {@link Grid} that stores the current generation of cells.
	 */
	public Grid getGrid() {
		return this.grid;
	}
	
	/**
	 * Returns the size of this game.
	 * @return A {@link Dimension} object that stores width and height of this game.
	 */
	public Dimension getSize() {
		return this.size;            
	}
	
	/**
	 * Resizes the current game to the specified width and height. 
	 * The state of existing cells that fit into the new size is maintained. New cells, if any, are initially dead.
	 * @param width the new number of columns
	 * @param height the new number of rows
	 */
	public void resize(int width, int height) {
		this.size = new Dimension(width, height);
		this.grid = new Grid(size, grid);
	}
	
	/**
	 * Computes the next generation of cells concurrently.
	 */
	public class NextGeneration {
		
		/**
		 * Buffer used to store the next generation during computation.
		 */
		private final Grid next;
		
		/**
		 * Identifies a unique cell in the linearized grid. Used to distribute load among worker threads.
		 */
		private int i;
		
		/**
		 * The total number of new cell states to be computed.
		 */
		private final int limit = size.height * size.width;
		
		/**
		 * Computes the next generation using the specified number of threads.
		 * @param numOfThreads the number of threads
		 */
		public NextGeneration(int numOfThreads) {
			next = new Grid(size);
			
			Slave[] slaves = new Slave[numOfThreads];
			for(int pos = 0; pos < slaves.length; pos++) {
				slaves[pos] = new Slave();
				slaves[pos].start();
			}	
			
			for(Slave slave: slaves)
				try {
					slave.join();
				} catch(InterruptedException e) {}
			
			Game.this.grid = next;
		}
		
		/**
		 * Assigns to each calling thread a unique cell index. This method is synchronized.
		 * @return The index of the cell whose state the calling thread has to compute.
		 */
		private synchronized int nextIndex() {
			return i++;
		}
		
		/**
		 * <code>Slave</code> threads compute the next generation of cells.
		 * Each slave runs in a loop consisting of two phases: 
		 * <ol><li>Calling the enclosing instance of {@link NextGeneration} to obtain a cell index.</li>
		 * <li>Computing its next state and storing it in the buffer matrix.</li></ol>
		 */
		private class Slave extends Thread {
			
			@Override
			public void run() {
				int slaveIndex;
				while((slaveIndex = NextGeneration.this.nextIndex()) < limit) {
					Point p = new Point(slaveIndex / size.width, slaveIndex % size.width);
					if(!(deadCellCoordinates.contains(p))) {
						int neighbours = countNeighbours(p);
						if (neighbours == 3 || (neighbours == 2 && grid.isAlive(p)))
							next.changeState(p);
					}	
				}
			}

			/**
			 * Returns the number of alive neighbours of the cell at position <code>p</code>
			 * @param p the <code>Point</code> representing the coordinates of the cell.
			 * @return The number of alive neighbours of the given cell in the current generation.
			 */
			private int countNeighbours(Point p) {
				int count = 0;
				for(int dx = -1; dx <= 1; dx++)
					for(int dy = -1; dy <= 1; dy++) {
						Point neighbour = new Point(p.x + dx, p.y + dy);
						if ((dx != 0 || dy != 0) && neighbour.x >= 0 && neighbour.x < size.height 
								&& neighbour.y >= 0 && neighbour.y < size.width && grid.isAlive(neighbour))
							count++;
					}
				return count;
			}
		}
		
	}
}

