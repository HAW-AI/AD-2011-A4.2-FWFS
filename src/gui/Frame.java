package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Interfaces.Graph;
import Interfaces.Point;

import convexHull.GraphImpl;
import convexHull.PointImpl;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton buttonNR;
	JButton buttonLR;
	List<Point> hull = new ArrayList<Point>();
	List<Point> inner = new ArrayList<Point>();
	double xfaktor,yfaktor;
	int pointSize = 4;
	int originX,originY;
	Graph g = GraphImpl.randomGraph(20);

	Frame(int x,int y){
		super("konvexe Huelle");
		setSize(x, y);
		System.out.println(x);
		System.out.println(y);
		originX = (x/2);
		originY = ((y-50)/2) + 50;
		
		xfaktor = ((x-50)/2.0)/100.0;
		yfaktor = ((y-120)/2.0)/100.0;
		
		System.out.println(xfaktor);
		System.out.println(yfaktor);
		System.out.println(originX);
		System.out.println(originY);

		buttonNR = new JButton("<HTML><CENTER><BODY>Generate<BR>new Points</BODY></HTML>");
		buttonNR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				g = GraphImpl.randomGraph(20);
				hull = g.convexHull();
				inner = g.innerPoints();
				g.formatPrinter();
				
				repaint();
				
				}
			}
		);

		buttonLR = new JButton("<HTML><CENTER><BODY>Use last<BR>Points</BODY></HTML>");
		buttonLR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {

				g = g.sameGraph();
				hull = g.convexHull();
				inner = g.innerPoints();
				g.formatPrinter();
				repaint();

				}
			}
		);

		Container content = getContentPane();
		
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(1, 2));
		menu.setPreferredSize(new Dimension(x/2, 50));
		
		menu.add(buttonNR, BorderLayout.WEST);
		menu.add(buttonLR, BorderLayout.EAST);

		content.add(BorderLayout.NORTH, menu);
	}
	
	
	
	  public void paint( Graphics g ){
        super.paint(g);
		for(int i = 0 ; i < hull.size();i++){
			  if(!(i == hull.size()-1));
			  g.drawOval((int) (originX + (hull.get(i).x() * xfaktor))-(pointSize/2), (int) ( originY + (hull.get(i).y() * yfaktor))-(pointSize/2), pointSize, pointSize);
			  if(!(i == hull.size()-1)){
				  g.drawLine((int) (originX + (hull.get(i).x() * xfaktor)), (int) ( originY + (hull.get(i).y() * yfaktor)),(int) (originX + (hull.get(i+1).x() * xfaktor)), (int) ( originY + (hull.get(i+1).y() * yfaktor)));
			  }
			}
			if(!hull.isEmpty())
			  g.drawLine((int) (originX + (hull.get(0).x() * xfaktor)), (int) ( originY + (hull.get(0).y() * yfaktor)), (int) (originX + (hull.get(hull.size()-1).x() * xfaktor)), (int) ( originY + (hull.get(hull.size()-1).y() * yfaktor)));

			for(Point p : inner){
			  g.drawOval((int) (originX + (p.x() * xfaktor))-(pointSize/2), (int) ( originY + (p.y() * yfaktor))-(pointSize/2), pointSize, pointSize);
			}
		  }
    }

