package convexHull;

import Interfaces.Point;

public class PointImpl implements  Point{

	private final int xCoord,yCoord;
	
	public PointImpl(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	@Override
	public int x(){
		return xCoord;
	}
	
	@Override
	public int y(){
		return yCoord;
	}
	
	@Override
	public int distanceFromZero(){
		return (x()*x()+y()*y());
	}

	@Override
	public int compareTo(Point o) {
		if(x() > o.x()){return 1;}
		else if(x() < o.x()){ return -1; }
		else if(y() > o.y()){return 1;}
		else if(y() < o.y()){return -1;}
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoord;
		result = prime * result + yCoord;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (xCoord != other.x())
			return false;
		if (yCoord != other.y())
			return false;
		return true;
	}
}
