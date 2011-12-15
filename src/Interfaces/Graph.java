package Interfaces;

import java.util.ArrayList;
import java.util.List;

import convexHull.PointImpl;

public interface Graph {

	public Graph sameGraph();

	public ArrayList<Point> points();

	public int numberOfPoints();

	public void printer();

	public List<Point> innerPoints();

	public List<Point> convexHull();

	public void formatPrinter();

}