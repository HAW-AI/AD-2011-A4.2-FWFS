package convexHull;

public class Point {

	final int xCoord,yCoord;
	
	public Point(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	public int x(){
		return xCoord;
	}
	
	public int y(){
		return yCoord;
	}
}
