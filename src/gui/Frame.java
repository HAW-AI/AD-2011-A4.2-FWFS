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

import convexHull.Point;

public class Frame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JButton buttonNR;
	JButton buttonLR;
	List<Point> hull = new ArrayList<Point>();
	List<Point> inner = new ArrayList<Point>();
	int width,height;
	double xfaktor,yfaktor;
	int pointSize = 4;

	Frame(int x,int y){
		super("konvexe Hülle");
		setSize(x, y+50);
		xfaktor = (x/2)/100;
		yfaktor = (y/2)/100;
		width = x;
		height = y;

		buttonNR = new JButton("<HTML><CENTER><BODY>Generate<BR>new Points</BODY></HTML>");
		buttonNR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
//				hull = convexHull();
//				inner = innerPoints();
				hull = Arrays.asList(new Point(0,70),new Point(-50,-50),new Point(50,-50));
				inner = Arrays.asList(new Point(0,30),new Point(-20,-20),new Point(20,-20));
				
				repaint();
				
				}
			}
		);

		buttonLR = new JButton("<HTML><CENTER><BODY>Use last<BR>Points</BODY></HTML>");
		buttonLR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				
			inner = Arrays.asList(new Point(0,70),new Point(-50,-50),new Point(50,-50));
			hull = Arrays.asList(new Point(0,30),new Point(-20,-20),new Point(20,-20));
			
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
			  g.drawOval((int) (width/2 + (hull.get(i).x() * xfaktor))-(pointSize/2), (int) ( height/2 + (hull.get(i).y() * yfaktor))-(pointSize/2)+50, pointSize, pointSize);
			  if(!(i == hull.size()-1)){
				  g.drawLine((int) (width/2 + (hull.get(i).x() * xfaktor)), (int) ( height/2 + (hull.get(i).y() * yfaktor)+50),(int) (width/2 + (hull.get(i+1).x() * xfaktor)), (int) ( height/2 + (hull.get(i+1).y() * yfaktor)+50));
			  }
			}
			if(!hull.isEmpty())
			  g.drawLine((int) (width/2 + (hull.get(0).x() * xfaktor)), (int) ( height/2 + (hull.get(0).y() * yfaktor)+50), (int) (width/2 + (hull.get(hull.size()-1).x() * xfaktor)), (int) ( height/2 + (hull.get(hull.size()-1).y() * yfaktor)+50));

			for(Point p : inner){
			  g.drawOval((int) (width/2 + (p.x() * xfaktor))-(pointSize/2), (int) ( height/2 + (p.y() * yfaktor))-(pointSize/2)+50, pointSize, pointSize);
			}
		  }
    }

