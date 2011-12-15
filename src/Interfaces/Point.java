package Interfaces;

public interface Point extends Comparable<Point>{

	public int x();

	public int y();

	public double distanceFromZero();

	public int compareTo(Point o);

	public int hashCode();

	public boolean equals(Object obj);

}