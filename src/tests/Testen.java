package tests;

import static org.junit.Assert.*;
import static convexHull.GraphImpl.*;
import static convexHull.GrahamScan.*;
import org.junit.Test;

import Interfaces.Graph;
import Interfaces.Point;


public class Testen {

	@Test
	public void tests() {

		Graph testGraph = randomGraph(20);
		testGraph.printer();
		testGraph = testGraph.sameGraph();
		testGraph.printer();
		testGraph = testGraph.sameGraph();
		testGraph.printer();
		
		for(Point x : grahamScan(testGraph)){
			System.out.print("(" + x.x() + "," + x.y() + ")");
			
		}
		System.out.println();
		
		testGraph.formatPrinter();
	}

}
