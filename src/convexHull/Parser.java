package convexHull;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Point;

public class Parser {

	
	private Parser(){};
	
	public static List<Point> parse(File f){
		List<Point> list = new ArrayList<Point>();
		
		try{
		BufferedReader in = new BufferedReader(new FileReader(f));

			while(in.ready()){
			String p = in.readLine();
			
			String[]  ps = p.split(",");
			
			try{
				
			int x= Integer.parseInt(ps[0]);
			int y= Integer.parseInt(ps[1]);
			
			if(x > 100 || x < -100)
				System.out.println(x + " out of range");
			if(y > 100 || y < -100)
				System.out.println(y + " out of range");
			list.add(new PointImpl(x,y));
			}
			
			catch(NumberFormatException e){
				try{
				System.out.println(ps[0] + "," + ps[1] + " is invalid");
				}
			catch(ArrayIndexOutOfBoundsException e2){
				System.out.println("invalid file");
				
			}
			}
			
			}
			in.close();
		}
		catch(IOException e){
			System.out.println("invalid file");
			return new ArrayList<Point>();
		}
		catch(NullPointerException e){
			System.out.println("invalid file");
			return new ArrayList<Point>();
		}
		
		
		return list;
	}
}
