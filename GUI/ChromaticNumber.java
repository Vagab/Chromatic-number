import java.lang.*;
import Jama.*;

public class ChromaticNumber
{
    private int[][] adjMatrix;
    private int[] nodesColor;
    private int graphNumNodes;
    private int[] color;

    public ChromaticNumber(int[][] g)
    {
        this.adjMatrix = g;
        this.graphNumNodes = g.length;
        this.nodesColor = new int[this.graphNumNodes];
    }
    public boolean isSafe(int n, int g[][], int color[], int c) {
        for (int i = 0; i < this.graphNumNodes; i++) {
            if (g[n][i] == 1 && c == color[i]) {
                return false;
            }
        }
        return true;
    }

    //RECURSIVE FUNCTION TO SOLVE "M" COLORING PROBLEM(SPECIFIC NUMBER)
    public boolean recursiveColoring(int g[][], int m, int color[], int n) {
        //System.out.println("ENTERED RECURSIVE COLORING--------------");
        //if all vertices are colored return true
        if (n == this.graphNumNodes) {
            return true;
        }
        //try to assign different colors to node n
        for (int c = 1; c <= m; c++) {
            //System.out.println("ENTERED FIRST CYCLE OF RECURSIVE COLORING--------------");
            //check if it's safe to assign
            if (isSafe(n, g, color, c)) {
                //System.out.println("AT IS SAFE PART-----------------");
                color[n] = c;
                //recursion to color all remainning vertices
                if (recursiveColoring(g, m, color, n + 1)) {
                    return true;
                }
                //if we can't color with color c(didn't work out), then remove c
                color[n] = 0;
            }
        }
        //System.out.println("RETURNING FALSE-------------");
        //if there is no color that can be assigned, then we can't color it anyway <=> return false
        return false;
    }

    //SOLVING M COLORING USING BACKTRACKING
    public int ColoringTheGraph(int g[][], int m) {
        //System.out.println("ENTERED COLORINGTHEGRAPH--------------");
        //Assign all of color values to 0, so there won't be mistakes with empty elements
        color = new int[this.graphNumNodes];
        for (int i = 0; i < this.graphNumNodes; i++) {
            color[i] = 0;
        }
        // Call recursiveColoring() for vertex 0
        if (!recursiveColoring(g, m, color, 0)) {
            //System.out.println("For " + m + " solution does not exist");
            return 0;
        }
        //ystem.out.println("chromatic number: " + m);
        return m;
    }

    public int UpperBound(int[] color) {
        //numberOfColors is the number of colors:)
        int numberOfColors = 1;
        // we iterate through each node
        for (int n = 0; n < this.graphNumNodes; n++) {
            boolean colorAssigned = false;
            //trying to assign one of colors
            while (!colorAssigned) {
                for (int c = 0; c < numberOfColors; c++) {
                    // if we find a color that is safe to assign:
                    if (isSafe(n, this.adjMatrix, color, c)) {
                        // we are done here, assigning color and exiting the for-loop
                        color[n] = c;
                        colorAssigned = true;
                        break;
                    }
                }
                //If we haven't assigned color to a current node, simply create another color
                if (!colorAssigned) {
                    color[n] = numberOfColors;
                    numberOfColors++;
                    colorAssigned = true;
                }
            }
        }
        return numberOfColors;
    }
    public double LowerBound()
    {
        //this.pRandomGeneration();
        Matrix m = new Matrix(this.adjMatrix.length,this.adjMatrix.length);
        for (int row = 0; row < this.adjMatrix.length; row++){
            for (int col = 0; col < this.adjMatrix.length; col++){
                double val = this.adjMatrix[row][col];
                m.set(row,col,val);
                m.set(col,row,val);
            }
        }

        EigenvalueDecomposition eigen = m.eig();
        double [] realPart = eigen.getRealEigenvalues();

    /*for(int i = 0; i < realPart.length; i++) {
      System.out.println("Eigen Value " + i + " is " +
             "[" + realPart[i] + "]");
    }*/

        double min = (1000);
        double max = -1000;
        double hoffman = 0;

        for(int i = 0; i < realPart.length; i++)
        {
            if (realPart[i]<min)
            {
                min = realPart[i];
            }
        }
        for(int z = 0; z < realPart.length; z++)
        {
            if (realPart[z]>max)
            {
                max = realPart[z];
            }
        }
        hoffman = (1 - (max/min));
        hoffman = hoffman - hoffman%1;
        return (hoffman);
    }

    public int getTheNumber()
    {
     //   System.out.println("the upper bound is: " + (UpperBound(this.nodesColor)));
       // System.out.println("the lower bound is: " + 2);
        int min = UpperBound(this.nodesColor);
        int[] chromNumbers = new int[min];
        //if(LowerBound() >= this.graphNumNodes)
        //{
          //  return this.graphNumNodes;
        //}
            for (int i = UpperBound(this.nodesColor); i > Math.round(LowerBound()); i--) {
                //n = c.getTheNumber();
                if (min > ColoringTheGraph(this.adjMatrix, i) && ColoringTheGraph(this.adjMatrix, i) != 0) {
                    min = ColoringTheGraph(this.adjMatrix, i);
                }
            }
            return min;
    }
}