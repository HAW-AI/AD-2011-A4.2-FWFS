package convexHull;

import java.util.*;


public class Graph {
	static int seed;
	Random x;
	Random y;
	static ArrayList<Point> points;
	List<Point> innerPoints = new LinkedList<Point>();
	List<Point> convexHull = new LinkedList<Point>();
	
	private Graph(int pointCount, int seed){
		this.x = new Random(seed);
		this.y = new Random(seed+1);
		this.seed = seed;
		points = new ArrayList<Point>();
		for(int i = 0; i < pointCount; i++){
			points.add(new Point(x.nextInt()%101, y.nextInt()%101));			
		}

		convexHull = GrahamScan.grahamScan(this);
		innerPoints.addAll(points);
		innerPoints.removeAll(convexHull);
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
		return new Graph(points.size(), seed);
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
		List<Point> hull = new LinkedList<Point>();
		hull = GrahamScan.grahamScan(this);
		List<Point> inner = new LinkedList<Point>();
		inner.addAll(points);
		inner.removeAll(hull);
		
		Collections.sort(hull);
		System.out.print("[[");
		for(Point a : hull){
			System.out.print("(" + a.x() + "," + a.y() + ")");
			
		}
		System.out.print("][");
		
		boolean sorted = false;
		
		while(!sorted){
			sorted = true;
			for(int i = 0; i+1 < inner.size(); i++){
				if(inner.get(i).distanceFromZero() > inner.get(i+1).distanceFromZero()){
					inner.add(i+2, inner.get(i));
					inner.remove(i);
					sorted=false;
					break;
				} else if(inner.get(i).distanceFromZero() == inner.get(i+1).distanceFromZero()&& inner.get(i).compareTo(inner.get(i+1))>0){
					inner.add(i+2, inner.get(i));
					inner.remove(i);
					sorted=false;
					break;
				}
			}
			
		}
		
		for(Point c : inner){
			System.out.print("(" + c.x() + "," + c.y() +  ")");
			
		}
		System.out.print("]]");
		System.out.println();
		
	}
}
