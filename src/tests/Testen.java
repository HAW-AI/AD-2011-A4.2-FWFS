package tests;

import static org.junit.Assert.*;
import static convexHull.Graph.*;
import static convexHull.GrahamScan.*;
import org.junit.Test;

import convexHull.Graph;
import convexHull.Point;

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
		testGraph.formatPrinter();
	}

}
