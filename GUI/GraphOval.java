import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GradientPaint;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.applet.Applet;
import java.awt.*;
import java.util.Random;
import java.util.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import java.awt.BasicStroke;

/*
Just somewhere to store some stuff to use:

g2.setStroke(new BasicStroke(7.0F));

*/


public class GraphOval
{

	/*public static void main(String[] args)
	{
		JFrame frame = new JFrame();

		frame.setSize(1200, 1450);
		frame.setTitle("Graph Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		JComponent component = new ChartComponent();
		frame.add(component);


		frame.setVisible(true);
	}*/
}

class ChartComponent extends JPanel
{

	private static Graphics g;
	private static int VERTICES;
	private JButton button1;
	private int[][] nodes;
	private int xLeft = 0;
	private int yTop = 0;
	boolean flag = false;
	private ChartComponent scene;
	private int iFollow = 0;
	private int[] memoryX;// = new int[VERTICES];    //We create an array that will store the values of x and the other for y
	private int[] memoryY;// = new int[VERTICES];
	String path = "/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI/NP.txt";

//	private int[][] graph;

	public ChartComponent() throws FileNotFoundException
	{
		ReadAdjMatrix r = new ReadAdjMatrix();
		nodes = r.getGraph(path);
		MouseMotionListener entered = new MouseEnteredListener();
		addMouseMotionListener(entered);
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		//panel.setBackground(Color.BLACK);

		GradientPaint gp1 = new GradientPaint(5, 5, Color.blue, 20, 20, Color.green, true);
		GradientPaint gp2 = new GradientPaint(25, 25, Color.black, 5, 5, Color.lightGray, true);
		GradientPaint gp3 = new GradientPaint(5, 5, Color.yellow, 20, 20, Color.red, true);

		g2.setPaint(gp1);

		//GraphGenerating graph1 = new GraphGenerating(50,50);    //Here we generate the graph by calling the GraphGenerating object

		//int[][] nodes;    //We create a matrix that will be the copy of the one generated in the GraphGenerating Object

		//nodes = graph1.pRandomGeneration();
		//nodes = graph;
		VERTICES = nodes.length;
		memoryX = new int[VERTICES];    //We create an array that will store the values of x and the other for y
		memoryY = new int[VERTICES];
//________________________________________________________________________________
//PRINTS THE NODES



		int x;        //We create the cords for each point
		int y;

		double deg = 360 / VERTICES;    //We divide 360 by the number of vertices = each position (you'll understand)





		for (int i = 0; i < VERTICES; i++) {    //NOW HERE COMES THE MAGIC: Basically we will print everything as an oval so there is some nasty manipulations
			Location point = new Location(100, 100);    //We create a default one
			point.rotate(new Location(600, 200), Math.toRadians(i * deg));    //And we use some radians properties to draw each node

			x = (int) point.x;            //We put the new values for x and y from the point
			y = (int) point.y;

			if (y > 600) {                    //Some manipulations to make look everything like an Oval and not a circle
				y = y - 600;
				y = y / 2;
				y = y + 600;
			}
			if (y < 600) {
				y = 600 - y;
				y = y / 2;
				y = 600 - y;
			}

			memoryX[i] = x;                //We store the new coords into the matrices for each point
			memoryY[i] = y;

			g2.fillOval(x, y, 16, 16);    //And we draw the point (node)
		}

//________________________________________________________________________________
//PRINTS THE MATRIX TO SEE WHAT'S GOING ON
		/*System.out.println();
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				System.out.print(nodes[i][j] + " ");
			}
			System.out.println();
		}*/


	/*	if(nodes.length<15){g2.setStroke(new BasicStroke(10.0F));}
		else if(nodes.length>=15){g2.setStroke(new BasicStroke(7.0F));}
		else if(nodes.length>=30){g2.setStroke(new BasicStroke(2.0F));}*/

		g2.setStroke(new BasicStroke(4.0F));
		g2.setPaint(gp2);

//DRAW THE LINES


		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				if (nodes[i][j] == 1) {        //check if edge
					g2.drawLine(memoryX[i] + 8, memoryY[i] + 8, memoryX[j] + 8, memoryY[j] + 8); //Draws the line and add +8 for each to be in the center of the node
				}
			}
		}

		if(flag){
			g2.setPaint(gp3);
			for(int i=0; i<memoryX.length; i++){
				if(nodes[iFollow][i]==1){
					g2.drawLine(memoryX[iFollow] + 8, memoryY[iFollow] + 8, memoryX[i] + 8, memoryY[i] + 8);
				}
			}
		}
		flag=false;
	}



	class MouseEnteredListener implements MouseMotionListener
	{
		public void mouseDragged(MouseEvent event) {}
		public void mouseMoved(MouseEvent event) {


			int x = event.getX();
			int y = event.getY();

			highlightTest(x, y);

		}

		public void mouseExited(MouseEvent event){}
	}
	public void highlightTest(int v, int w)
	{

		for(int i=0; i<memoryX.length; i++){
			if(memoryX[i]<=v && memoryX[i]+30>=v && memoryY[i]<=w && memoryY[i]+30>w){
				flag = true;
				xLeft = memoryX[i];
				yTop = memoryY[i];
				iFollow = i;
				repaint();
				break;
			}
			else{
				repaint();
			}

			xLeft=0;
			yTop=0;
			iFollow=0;
		}
	}




}

class Location
{
	public double x;
	public double y;

	public Location(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double dist(Location location)
	{
		return Math.sqrt(Math.pow(location.x - x, 2) + Math.pow(location.y - y, 2));
	}
	//Rotates the point the amount in angle around the point center
	public void rotate(Location center, double angle)
	{
		double d = dist(center);
		x = center.x + (Math.cos(angle) * d);
		y = center.y + (Math.sin(angle) * d);
	}





}