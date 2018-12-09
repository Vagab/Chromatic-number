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
import java.awt.event.MouseListener;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.BasicStroke;

public class ChartComponent extends JPanel
{


	private static Graphics g;
	private static int VERTICES;
	private JButton button1;
	private int[][] nodes;
	private int xLeft = 0;
	private int yTop = 0;
	boolean flag = false;
	boolean lost = false;
	boolean won = false;
	private ChartComponent scene;
	private int iFollow = 0;
	private int[] memoryX;// = new int[VERTICES];    //We create an array that will store the values of x and the other for y
	private int[] memoryY;// = new int[VERTICES];

	private int[] memoryColor;
	private ColorationGame1 ColorDataBase;

	private JButton ColorDialog;
	private JColorDialog ColorBox;

	private JButton Hints;
	private Color HintColor;
	private String HintString = "";
	private boolean thereIsHint = false;
	private boolean foundHint = false;
	private int xHINT;
	private int yHINT;

	String path = "/Users/lucas/Desktop/Project/AlluNeed5/NP.txt";


	public ChartComponent() throws FileNotFoundException
	{



		MouseListener clicked = new MousePressListener();		//WTF
		addMouseListener(clicked);

		MouseMotionListener entered = new MouseEnteredListener();
		addMouseMotionListener(entered);

		ColorDataBase = new ColorationGame1();		//creates the color database for each node

		ColorBox = new JColorDialog();
		ColorBox.setVisible(false);

		ColorDialog = new JButton("Color 1");
		ActionListener listener = new AddListenerColor();
		ColorDialog.addActionListener(listener);

		Hints = new JButton("Hints");
		ActionListener listener2 = new AddListenerHints();
		Hints.addActionListener(listener2);

		add(ColorDialog, BorderLayout.NORTH);
		add(Hints, BorderLayout.NORTH);




	}

	public void setThings()
	{
		String path = "/Users/sterben/Desktop/Java/Project/Chromatic-Number/GUI/NP.txt";
		try {
			ReadAdjMatrix r = new ReadAdjMatrix();
			nodes = r.getGraph(path);
		} catch (FileNotFoundException exc) {
			exc.printStackTrace();
		}
		VERTICES = nodes.length;
		memoryX = new int[VERTICES];    //We create an array that will store the values of x and the other for y
		memoryY = new int[VERTICES];
		memoryColor = new int[VERTICES];
	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		GradientPaint gp1 = new GradientPaint(5, 5, Color.blue, 20, 20, Color.green, true);
		GradientPaint gp2 = new GradientPaint(100, 100, Color.black, 5, 5, Color.lightGray, true);
		GradientPaint gp3 = new GradientPaint(5, 5, Color.yellow, 20, 20, Color.red, true);
		GradientPaint gp4 = new GradientPaint(5, 5, Color.darkGray, 20, 20, Color.red, true);

		setThings();
		g2.setPaint(gp1);
		//Matrix that will store the colors
//________________________________________________________________________________
//PRINTS THE NODES



		int x;        //We create the cords for each point
		int y;

		double deg = 360 / VERTICES;    //We divide 360 by the number of vertices = each position (you'll understand)

		g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
		g2.drawString("VERTICES: " + VERTICES, 10, 750);

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

			//	g2.fillOval(x, y, 16, 16);    //And we draw the point (node)
		}


		for(int i=0; i<memoryX.length; i++){

			if(ColorDataBase.getColor(i) != null){
				g2.setColor(ColorDataBase.getColor(i)); //calls the color in the ColorationGame1 class
				//System.out.println(" " + ColorDataBase.getColor(i));
			}
			else{g2.setColor(Color.BLACK);}
			//System.out.println("2 " + ColorDataBase.getColor(i));
			g2.fillOval(memoryX[i], memoryY[i], 16, 16);
		}


		for(int i=0; i<nodes.length; i++){
			if(ColorDataBase.getColor(i) != null){
				Color testIfCanBeAssigned = ColorDataBase.getColor(i);
				for(int j=0; j<nodes.length; j++){
					if(testIfCanBeAssigned != null){
						if(i!=j){
							if(nodes[i][j] == 1 && ColorDataBase.getColor(j) == testIfCanBeAssigned && ColorDataBase.getColor(j) != null){
								System.out.println("Color not allowed");
								youHaveDied();
							}
						}
					}
				}
			}
		}

		int countVictory = 0;
		for(int i=0; i<memoryX.length; i++){

			if(ColorDataBase.getColor(i) != null){
				countVictory++;
			}
			if(countVictory==memoryX.length && !lost){
				System.out.println("You have won");
				youHaveWon();
			}
		}


//DRAW THE LINES

		g2.setStroke(new BasicStroke(1.5F));
		g2.setColor(Color.BLACK);
		for (int i = 0; i < nodes.length; i++) {
			for (int j = 0; j < nodes.length; j++) {
				if (nodes[i][j] == 1) {        //check if edge
					g2.drawLine(memoryX[i] + 8, memoryY[i] + 8, memoryX[j] + 8, memoryY[j] + 8); //Draws the line and add +8 for each to be in the center of the node
				}
			}
		}

		if(flag){
			//g2.setStroke(new BasicStroke(4.0F));	//draws thicker lines for the connections
			g2.setPaint(gp3);
			for(int i=0; i<memoryX.length; i++){
				if(nodes[iFollow][i]==1){
					g2.drawLine(memoryX[iFollow] + 8, memoryY[iFollow] + 8, memoryX[i] + 8, memoryY[i] + 8);
				}
			}
		}

		if(lost){
			g2.setColor(Color.BLACK);
			g2.fillRect(400, 300, 500, 200);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g2.setPaint(gp4);
			g2.drawString("  You Have Died", 500, 400);
		}
		if(won){
			g2.setPaint(gp1);
			g2.fillRect(400, 300, 500, 200);
			g2.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g2.setPaint(gp4);
			g2.drawString(" You Have Won", 500, 400);
		}

		for(int i=0; i<memoryX.length; i++){
			if(memoryX[i] == xHINT && ColorDataBase.getColor(i) != null){
				thereIsHint = false;
				xHINT = 0;
				yHINT = 0;
			}
		}

		if(foundHint){
			if(thereIsHint){
				g2.setColor(Color.WHITE);
				g2.fillRect(980, 5, 500, 80);
				g2.setPaint(HintColor);
				g2.fillRect(1300, 20, 50, 50);
				g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				g2.setPaint(gp1);
				g2.drawString(HintString, 1000, 50);
				g2.setPaint(gp1);
				g2.fillOval(xHINT, yHINT, 16, 16);
				g2.setPaint(gp4);
				g2.drawOval(xHINT, yHINT, 16, 16);
			}
			else{
				g2.setColor(Color.WHITE);
				g2.fillRect(980, 5, 500, 80);
				g2.setFont(new Font("TimesRoman", Font.PLAIN, 20));
				g2.setPaint(gp1);
				g2.drawString(HintString, 1000, 50);
			}
		}
		flag=false;



	}

	public void youHaveDied()
	{
		lost = true;
		repaint();
	}
	public void youHaveWon()
	{
		won = true;
		repaint();
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

	class MousePressListener implements MouseListener
	{
		public void mousePressed(MouseEvent event){}
		public void mouseReleased(MouseEvent event){}
		public void mouseClicked(MouseEvent event)
		{
			int mouseX = event.getX();
			int mouseY = event.getY();
			clickedForColor(mouseX, mouseY);
			System.out.println(mouseX + "  " + mouseY);
		}
		public void mouseEntered(MouseEvent event){}
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
				//g2.clearRect(0, 0, 1000, 800);
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
	public void clickedForColor(int v, int w)
	{
		for(int i=0; i<memoryX.length; i++){
			if(memoryX[i]<=v && memoryX[i]+30>=v && memoryY[i]<=w && memoryY[i]+30>w){

				ColorDataBase.setColor(i, ColorBox.getColorDialog());

				System.out.println("yolo");
				repaint();
			}
		}


	}
	class AddListenerColor implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//ColorDialog.setVisible(true);

			ColorBox.setVisible(true);
			System.out.println("Yep man");

		}
	}
	class AddListenerHints implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			System.out.println("Hints");
			//for(int i=0; i<memoryX.length; i++){
			//	if(memoryX[i]<=v && memoryX[i]+30>=v && memoryY[i]<=w && memoryY[i]+30>w){
			foundHint = true;

			for(int i=0; i<nodes.length; i++){
				if(ColorDataBase.getColor(i) == null){

					for(int j=0; j<nodes.length; j++){
						if (nodes[i][j] == 1 && i != j && ColorDataBase.getColor(j) != null) {
							Color testIfCanBeAssigned = ColorDataBase.getColor(j);
							System.out.println("HINT: Don't paint the node " + i + " in: " + testIfCanBeAssigned);
							HintString = "HINT: Don't paint the node " + i + " in: ";
							HintColor = testIfCanBeAssigned;
							xHINT = memoryX[i];
							yHINT = memoryY[i];
							thereIsHint = true;
							break;
							//if(testIfCanBeAssigned != null){
							//if(i!=j){
							//	if(nodes[i][j] == 1 && ColorDataBase.getColor(j) == testIfCanBeAssigned && ColorDataBase.getColor(j) != null){


						}
						else
							thereIsHint = false;

					}
				}
				if(thereIsHint){
					repaint();
					break;}
				else
					HintString = "HINT: Choose a new color";
				HintColor = Color.WHITE;
				repaint();
				//else{

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