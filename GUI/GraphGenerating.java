import java.lang.*;

public class GraphGenerating
{
  private int numberOfVerticies;
  private int numberOfEdges;
  private boolean DEBUG = false;
  private int[][] adjacencyMatrix;

  public GraphGenerating(int numberOfEdges, int numberOfVerticies)
  {
    this.numberOfEdges = numberOfEdges;
    this.numberOfVerticies = numberOfVerticies;
    int[][] adjacencyMatrix = new int[this.numberOfVerticies][this.numberOfVerticies];
    this.adjacencyMatrix = adjacencyMatrix;
  }
  public GraphGenerating()
  {
    this.numberOfVerticies = this.randomWithRange(20,50);
    System.out.println(this.numberOfVerticies);
    this.numberOfEdges = this.randomWithRange(this.numberOfVerticies/2, ((this.numberOfVerticies * (this.numberOfVerticies - 1) / 2)));
    int[][] adjacencyMatrix = new int[this.numberOfVerticies][this.numberOfVerticies];
    this.adjacencyMatrix = adjacencyMatrix;
  }

  public int getNumberOfVerticies()
  {return this.numberOfVerticies;}

  public boolean isOkay()
  {
    if (this.numberOfEdges > (this.numberOfVerticies * (this.numberOfVerticies - 1) / 2)
            || (numberOfEdges < 0) || (numberOfVerticies < 0) || (this.numberOfEdges <= this.numberOfVerticies/2))
    {return false;}
    else
    {return true;}
  }

  public int randomWithRange(int min, int max)
  {
   int range = (max - min) + 1;
   return (int)(Math.random() * range) + min;
  }

  public boolean isSymmetric(int[][] matrix)
  {
    int[][] newMatrix = matrix;
    int temp;
    for (int row = 0; row < matrix.length; row++)
    {   for (int col = row; col < matrix[0].length; col++)
      {
        temp = newMatrix[row][col];
        newMatrix[row][col] = newMatrix[col][row];
        newMatrix[col][row] = temp;
      }
    }
    return matrix == newMatrix;
  }

  public int[][] pRandomGeneration()
  {
    if (this.isOkay())
    {
      int row;
      int col;
      int count = 0;
      while (count < this.numberOfEdges)
      {
        row = randomWithRange(0, this.numberOfVerticies-1);
        col = randomWithRange(row, this.numberOfVerticies-1);
        if(DEBUG){System.out.print(row + " " + col + "\n");}
        if(row != col && this.adjacencyMatrix[row][col] != 1){
          this.adjacencyMatrix[row][col] = 1;
          this.adjacencyMatrix[col][row] = 1;
          count++;}
      }

      for (int i = 0; i < this.adjacencyMatrix.length; i++)
      {
        this.adjacencyMatrix[i][i] = 1;
      }
    }
    else{System.out.println("Wrong number of edges");}
    if(DEBUG)
    {
      int count = 0;
      for(int row = 0; row < this.adjacencyMatrix.length; row++)
      { for(int col = row; col < this.adjacencyMatrix[0].length; col++)
        { if(row != col && this.adjacencyMatrix[row][col] == 1)
          count++;
        }
      }
      {System.out.println(this.numberOfEdges + " = " + count);}
    }
    if (isSymmetric(this.adjacencyMatrix))
    {return this.adjacencyMatrix;}
    else
    { System.out.println("Matrix isn't symmetric.");
      return this.adjacencyMatrix;}
  }

  public void showAdjacencyMatrix()
  {
    this.pRandomGeneration();
    for (int row = 0; row < this.adjacencyMatrix.length; row++)
    { for (int col = 0; col < this.adjacencyMatrix.length; col++)
      { System.out.print(this.adjacencyMatrix[row][col] + " ");}
      System.out.println();
    }
  }

}
