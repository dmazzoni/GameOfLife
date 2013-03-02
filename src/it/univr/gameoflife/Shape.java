package it.univr.gameoflife;

import java.awt.Point;
import java.util.Iterator;
import java.util.HashSet;
import java.util.Set;

public class Shape implements Iterable<Point> {
	
	private final Set<Point> points;
	private final String name;
	
	public Shape(String name, Point... points) {
		this.name = name;
		this.points = new HashSet<Point>();
		for (Point p : points)
			this.points.add(p);
	}
	
	@Override
	public Iterator<Point> iterator() {
		return points.iterator();
	}

}
