import java.awt.*;

public class ColorationGame1
{

	private int MAXCOLORS;
	private Color[] colorsDataBase;
	//private Color storedColor;
	

	public ColorationGame1()	//creates the array for the colors
	{
	//	MAXCOLORS = getChromaticNumber();	//Basically we call the chromatic number here
		colorsDataBase = new Color[100];
	}
	
	public void setColor(int i, Color c)	//assign a color for a specific index
	{
		colorsDataBase[i] = c;
		System.out.println("New color " + i + " is: " + c);
	}
	
	public Color getColor(int i)	//returns the color of a specific node
	{
		return colorsDataBase[i];
	}
	
	public int getMaxColors()	//maybe delete later if useless
	{
		return MAXCOLORS;
	}
	
	
}