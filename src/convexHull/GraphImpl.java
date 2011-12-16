package convexHull;

import java.util.*;

import Interfaces.Graph;
import Interfaces.Point;


public class GraphImpl implements Graph {
	private	final int seed;
	private final int pointCount;
	private final ArrayList<Point> points;
	private final List<Point> innerPoints = new LinkedList<Point>();
	private final List<Point> convexHull;

	public static Graph randomGraph(int pointCount){
		int rand = new Integer((int) (Math.random()*50.0));
		return new GraphImpl(pointCount, rand);
	}
	
	public static Graph graphFromList(List<Point> l){
		return new GraphImpl(l);
	}

	@Override
	public Graph sameGraph(){
		return new GraphImpl(numberOfPoints(),getSeed());
	}
	
	private GraphImpl(int pointCount, int s){
		Random x;
		Random y;
		seed = s;
		x = new Random(seed);
		y = new Random(seed+1);
		this.pointCount = pointCount;
		points = new ArrayList<Point>();
		for(int i = 0; i < pointCount; i++){
			points.add(new PointImpl(x.nextInt()%101, y.nextInt()%101));			
		}

		convexHull = GrahamScan.grahamScan(this);
		innerPoints.addAll(points);
		innerPoints.removeAll(convexHull);
	}
	
	private GraphImpl(List<Point> l){
		
		seed = 0;
		points = new ArrayList<Point>();
		points.addAll(l);
		this.pointCount = points.size();

		convexHull = GrahamScan.grahamScan(this);
		innerPoints.addAll(points);
		innerPoints.removeAll(convexHull);
		
	}
	
	private int getSeed(){
		return this.seed;
	}
	
	@Override
	public ArrayList<Point> points(){
		return points;
	}

	@Override
	public int numberOfPoints(){
		return pointCount;
	}
	
	@Override
	public void printer(){
		System.out.print("[");
		for(Point x : points){
			System.out.print("(" + x.x() + "," + x.y() + ")");
		}
		System.out.print("]");
		System.out.println();
	}
	
	@Override
	public List<Point> innerPoints(){
		List<Point> tempInner = new LinkedList<Point>();
		tempInner.addAll(innerPoints);
		return tempInner;
	}
	
	@Override
	public List<Point> convexHull(){
		List<Point> tempConvex = new LinkedList<Point>();
		tempConvex.addAll(convexHull);
		return tempConvex;
	}
	
	@Override
	public void formatPrinter(){	
		List<Point> tempInner = new LinkedList<Point>();
		tempInner.addAll(innerPoints);
		List<Point> tempConvex = new LinkedList<Point>();
		tempConvex.addAll(convexHull);
		Collections.sort(tempConvex);
		
		boolean sorted = false;
		
		while(!sorted){
			sorted = true;
			for(int i = 0; i+1 < tempInner.size(); i++){
				
				if((tempInner.get(i).distanceFromZero() > tempInner.get(i+1).distanceFromZero()) || 
						(tempInner.get(i).distanceFromZero() == tempInner.get(i+1).distanceFromZero() && 
							tempInner.get(i).compareTo(tempInner.get(i+1))>0)){
					
					tempInner.add(i+2, tempInner.get(i));
					tempInner.remove(i);
					sorted=false;
				}
			}
		}	

		System.out.print("[[");
		for(Point a : tempConvex){
			System.out.print("(" + a.x() + "," + a.y() + ")");
			
		}
		System.out.print("][");
		for(Point c : tempInner){
			System.out.print("(" + c.x() + "," + c.y() +  ")");
			
		}
		System.out.print("]]");
		System.out.println();
		
	}
}
