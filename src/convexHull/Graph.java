package convexHull;

import java.util.*;


public class Graph {
	static int seed;
	static int pointCount;
	Random x;
	Random y;
	static ArrayList<Point> points;
	List<Point> innerPoints = new LinkedList<Point>();
	List<Point> convexHull = new LinkedList<Point>();
	
	private Graph(int pointCount, int seed){
		this.x = new Random(seed);
		this.y = new Random(seed+1);
		this.seed = seed;
		this.pointCount = pointCount;
		points = new ArrayList<Point>();
		for(int i = 0; i < pointCount; i++){
			points.add(new Point(x.nextInt()%101, y.nextInt()%101));			
		}

		convexHull = GrahamScan.grahamScan(this);
		innerPoints.addAll(points);
		innerPoints.removeAll(convexHull);
		Collections.sort(convexHull);
		
		boolean sorted = false;
		
		while(!sorted){
			sorted = true;
			for(int i = 0; i+1 < innerPoints.size(); i++){
				if(innerPoints.get(i).distanceFromZero() > innerPoints.get(i+1).distanceFromZero()){
					innerPoints.add(i+2, innerPoints.get(i));
					innerPoints.remove(i);
					sorted=false;
					break;
				} else if(innerPoints.get(i).distanceFromZero() == innerPoints.get(i+1).distanceFromZero()&& innerPoints.get(i).compareTo(innerPoints.get(i+1))>0){
					innerPoints.add(i+2, innerPoints.get(i));
					innerPoints.remove(i);
					sorted=false;
					break;
				}
			}
		}
	}
	
	public static ArrayList<Point> points(){
		return points;
	}
	
	public static Graph randomGraph(int pointCount){
		int rand = new Integer((int) (Math.random()*50.0));
		return new Graph(pointCount, rand);
	}
	
	public static Graph graphFromSeed(int pointCount, int seed){
		return new Graph(pointCount, seed);
	}
	
	public static int noOfPoints(){
		return points.size();
	}
	
	public static Graph sameGraph(){
		return new Graph(pointCount, seed);
	}
	
	public static void printer(){
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

		System.out.print("[[");
		for(Point a : convexHull){
			System.out.print("(" + a.x() + "," + a.y() + ")");
			
		}
		System.out.print("][");
		for(Point c : innerPoints){
			System.out.print("(" + c.x() + "," + c.y() +  ")");
			
		}
		System.out.print("]]");
		System.out.println();
		
	}
}
