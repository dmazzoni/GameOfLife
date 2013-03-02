package it.univr.gameoflife;

import java.awt.Point;
import java.util.Iterator;

public class Shape implements Iterable<Point> {
	
	private final Point[] points;
	private final String name;
	
	public Shape(String name, Point... points) {
		this.name = name;
		this.points = points;
	}
	
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
