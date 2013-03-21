package it.univr.gameoflife;

import java.awt.Point;
import java.util.Iterator;

/**
 * Stores a predefined shape composed by its name and its points.
 * @author Giacomo Annaloro
 * @author Davide Mazzoni
 */
public class Shape implements Iterable<Point> {
	
	/**
	 * Stores each point the shape is made of.
	 */
	private final Point[] points;
	
	/**
	 * The logical name of the shape.
	 */
	private final String name;
	
	/**
	 * Constructs a shape made up of the specified points.
	 * @param name the name of the shape
	 * @param points the coordinates that correspond to the shape's cells in the graphic grid
	 */
	public Shape(String name, Point... points) {
		this.name = name;
		this.points = points;
	}
	
	/**
	 * Allows other classes to access the shape's name.
	 * @return The name of the shape.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Returns an iterator on the shape's points array.
	 */
	@Override
	public Iterator<Point> iterator() {
		return new Iterator<Point>(){
			
			private int i;
			
			@Override
			public boolean hasNext() {
				return i < points.length;
			}
			
			@Override
			public Point next() {
				return points[i++];
			}
			
			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}
