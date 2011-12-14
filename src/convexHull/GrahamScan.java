package convexHull;

import java.util.*;

public class GrahamScan {

	private static List<Point> angleList;
	private static List<Point> graphPoints;
	public static List<Point> grahamScan(Graph x){
		graphPoints = x.points();
		angleList = new LinkedList<Point>();
		angleList.add(firstPoint(graphPoints));
		graphPoints.removeAll(angleList);
		angleList.addAll(graphPoints);
		boolean sorted = false;
		
		while(!sorted){
		sorted = true;
			for(int i = 1; i < angleList.size()-1; i++){
				if(radialPointOrder(angleList.get(0), angleList.get(i), angleList.get(i+1))<0){
					angleList.add(i, angleList.get(i+1));
					angleList.remove(i+2);
					sorted = false;
				} else if (radialPointOrder(angleList.get(0), angleList.get(i), angleList.get(i+1))==0){
					angleList.remove(i+1);
					sorted = false;
				}
			}
		}
		
		for(int k = 1; k+1 < angleList.size();){
			if(radialPointOrder(angleList.get(k-1), angleList.get(k+1), angleList.get(k))<0){
				k++;
			} else { 
				angleList.remove(k);
				k--;
			}
			
		}
		
		return angleList;
	}
	
	private static int radialPointOrder(Point t0, Point t1, Point t2){
		return (((t1.x()-t0.x())*(t2.y()-t0.y()))-((t2.x()-t0.x())*(t1.y()-t0.y())));
	}
	
	private static Point firstPoint(List<Point> points){
		Point temp = points.get(0);		
		for(Point x : points){
			if(x.x() < temp.x()){temp = x;}
			else if(x.x() == temp.x() && x.y() < temp.y()) {temp = x;}			
		}		
		return temp;
	}
	
}
