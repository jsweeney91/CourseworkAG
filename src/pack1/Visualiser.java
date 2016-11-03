package pack1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.geom.Line2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Visualiser extends JComponent{
	
	private ArrayList<Line2D.Double> lines;
	
	Visualiser(int width, int height){
		super();
		setPreferredSize(new Dimension(width, height));
		this.lines = new ArrayList<Line2D.Double>();
	}
	
	public void addLine (Edge ed){
		
		double x1= ed.getNode1().getX();
		double y1 = ed.getNode1().getY();
		double x2= ed.getNode2().getX();
		double y2 = ed.getNode2().getY();
		
		int width = (int)getPreferredSize().getWidth(); 
		int height = (int)getPreferredSize().getHeight();

		x1 = width/Node.maxX * x1;
		y1 = height/Node.maxY * y1;
		x2 = width/Node.maxX* x2;
		y2 = height/Node.maxY * y2;

		
		double padding = 0.80;
		
		x1 = (x1*padding)+(width-width*padding)/2;
		x2 = (x2*padding)+(width-width*padding)/2;
		y1 = (y1*padding)+(height-height*padding)/2;
		y2 = (y2*padding)+(height-height*padding)/2;

	
		Line2D.Double line = new Line2D.Double(x1, y1, x2, y2);
		this.lines.add(line);
		repaint();
	}
	
	
	public void paintComponent(Graphics g){
		
		System.out.println(lines.size());
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
		Dimension d = getPreferredSize();
		int counter = 0;
		for(Line2D.Double line : lines){
			if(counter%2==0){
				g.setColor(Color.black);
				g.drawLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2());
			}else{
				g.setColor(Color.red);
				g.drawLine((int)line.getX1(),(int)line.getY1(),(int)line.getX2(),(int)line.getY2());
			}
			counter++;
		}
	}

}