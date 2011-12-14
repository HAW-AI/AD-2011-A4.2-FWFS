package convexHull;

import java.util.*;


public class Graph {
	private	int seed;
	private int pointCount;
	private ArrayList<Point> points;
	private List<Point> innerPoints = new LinkedList<Point>();
	private List<Point> convexHull = new LinkedList<Point>();

	public static Graph randomGraph(int pointCount){
		int rand = new Integer((int) (Math.random()*50.0));
		return new Graph(pointCount, rand);
	}

	public Graph sameGraph(){
		return new Graph(noOfPoints(),getSeed());
	}
	
	private Graph(int pointCount, int s){
		Random x;
		Random y;
		seed = s;
		x = new Random(seed);
		y = new Random(seed+1);
		this.pointCount = pointCount;
		points = new ArrayList<Point>();
		for(int i = 0; i < pointCount; i++){
			points.add(new Point(x.nextInt()%101, y.nextInt()%101));			
		}

		convexHull = GrahamScan.grahamScan(this);
		innerPoints.addAll(points);
		innerPoints.removeAll(convexHull);
	}
	
	private int getSeed(){
		return this.seed;
	}
	
	public ArrayList<Point> points(){
		return points;
	}

	public int noOfPoints(){
		return pointCount;
	}
	
	public void printer(){
		System.out.print("[");
		for(Point x : points){
			System.out.print("(" + x.x() + "," + x.y() + ")");
		}
		System.out.print("]");
		System.out.println();
	}
	
	public List<Point> innerPoints(){
		return innerPoints;
	}
	
	public List<Point> convexHull(){
		return convexHull;
	}
	
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
				if(tempInner.get(i).distanceFromZero() > tempInner.get(i+1).distanceFromZero()){
					tempInner.add(i+2, tempInner.get(i));
					tempInner.remove(i);
					sorted=false;
					break;
				} else if(tempInner.get(i).distanceFromZero() == tempInner.get(i+1).distanceFromZero()&& tempInner.get(i).compareTo(tempInner.get(i+1))>0){
					tempInner.add(i+2, tempInner.get(i));
					tempInner.remove(i);
					sorted=false;
					break;
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
