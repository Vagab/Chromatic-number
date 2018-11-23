import java.awt.Graphics;
import java.awt.Color;
import javax.swing.JComponent;
import javax.swing.JFrame;
import java.applet.Applet;
import java.awt.*;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class GraphOval
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		
		frame.setSize(1200, 1450);
		frame.setTitle("Graph Generator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JComponent component = new ChartComponent();
		frame.add(component);
		
		
		frame.setVisible(true);
	}
}

class ChartComponent extends JComponent
{
	
	private static Graphics g;
	private static int VERTICES;
	private JButton button1;

	
	public void paintComponent(Graphics g) {

		GraphGenerating graph1 = new GraphGenerating();    //Here we generate the graph by calling the GraphGenerating object

		int[][] nodes;    //We create a matrix that will be the copy of the one generated in the GraphGenerating Object

		nodes = graph1.pRandomGeneration();

		VERTICES = graph1.getNumberOfVerticies();

//________________________________________________________________________________
//PRINTS THE NODES

		int x;        //We create the cords for each point
		int y;

		double deg = 360 / VERTICES;    //We divide 360 by the number of vertices = each position (you'll understand)

		int[] memoryX = new int[VERTICES];    //We create an array that will store the values of x and the other for y
		int[] memoryY = new int[VERTICES];

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

			g.fillOval(x, y, 16, 16);    //And we draw the point (node)
		}

//________________________________________________________________________________
//PRINTS THE MATRIX TO SEE WHAT'S GOING ON
		System.out.println();
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				System.out.print(nodes[i][j] + " ");
			}
			System.out.println();
		}

//DRAW THE LINES

		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				if (nodes[i][j] == 1) {        //check if edge
					g.drawLine(memoryX[i] + 8, memoryY[i] + 8, memoryX[j] + 8, memoryY[j] + 8); //Draws the line and add +8 for each to be in the center of the node
				}
			}
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


