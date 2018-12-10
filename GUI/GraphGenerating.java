import java.lang.*;

public class GraphGenerating
{
  private int numberOfVerticies;
  private int numberOfEdges;
  private boolean DEBUG = false;
  private int[][] adjacencyMatrix;
  private int[] edgList;

  public GraphGenerating(int numberOfEdges, int numberOfVerticies) {

      this.numberOfEdges = numberOfEdges;
      this.numberOfVerticies = numberOfVerticies;
      int[][] adjacencyMatrix = new int[this.numberOfVerticies][this.numberOfVerticies];
      this.adjacencyMatrix = adjacencyMatrix;
  }
  public GraphGenerating() {
      this.numberOfVerticies = this.randomWithRange(20,50);
      this.numberOfEdges = this.randomWithRange(0, ((this.numberOfVerticies * (this.numberOfVerticies - 1) / 2)));
      int[][] adjacencyMatrix = new int[this.numberOfVerticies][this.numberOfVerticies];
      this.adjacencyMatrix = adjacencyMatrix;
  }

  public boolean isOkay() {
      if (this.numberOfEdges > (this.numberOfVerticies * (this.numberOfVerticies - 1) / 2)) {
          return false;
      }   else {
          return true; }
  }

  public int randomWithRange(int min, int max) {
      int range = (max - min) + 1;
      return (int)(Math.random() * range) + min;
  }

  public boolean isSymmetric(int[][] matrix) {
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

  public int getMaxEdgesNode(int[] edgList)
  {
      int maxEdgesNode = 0;
      int maxEdgesNodeIndex = 0;
      for (int i = 0; i < edgList.length; i++)
      {
          if (edgList[i] > maxEdgesNode)
          {
              maxEdgesNode = edgList[i];
              maxEdgesNodeIndex = i;
          }
      }
      return maxEdgesNodeIndex;
  }

  public int[] getListOfEdges(int row, int[][] m)
  {
    edgList = new int[m.length];
    for (int i = row; i < m.length; i++)
    {
      for (int j = 0; j < m.length; j++)
      {
        if (m[i][j] == 1)
        {
          edgList[i]++;
        }
      }
    }
    return edgList;
  }

  public int[][] transpose(int[][] m)
  {
    int temp;
    for (int row = 0; row < m.length; row++) {
      for (int col = row; col < m.length; col++) {
        temp = m[row][col];
        m[row][col] = m[col][row];
        m[col][row] = temp;
      }
    }
    return m;
  }

  public void sortGraph(int row, int[][] m)
  {
    int temp;
    int maxNodes = getMaxEdgesNode(getListOfEdges(row, m));
    if (maxNodes != row) {
      for (int col = 0; col < m.length; col++) {
        temp = m[row][col];
        m[row][col] = m[maxNodes][col];
        m[maxNodes][col] = temp;
      }
      m = transpose(m);
      for (int col = 0; col < m.length; col++) {
        temp = m[row][col];
        m[row][col] = m[maxNodes][col];
        m[maxNodes][col] = temp;
      }
      m = transpose(m);
    } if (row < m.length - 1) {
      sortGraph(++row, m);
    }
  }

  public int[][] pRandomGeneration() {
      if (this.isOkay()){
          int row;
          int col;
          int count = 0;
          while (count < this.numberOfEdges) {
              row = randomWithRange(0, this.numberOfVerticies-1);
              col = randomWithRange(row, this.numberOfVerticies-1);
              if(DEBUG){System.out.print(row + " " + col + "\n");}
              if(row != col && this.adjacencyMatrix[row][col] != 1) {
                  this.adjacencyMatrix[row][col] = 1;
                  this.adjacencyMatrix[col][row] = 1;
                  count++; }
          }
          for (int i = 0; i < adjacencyMatrix.length; i++){
              this.adjacencyMatrix[i][i] = 1;
          }
      }  else {
        System.out.println("Wrong number of edges");
      }
      if(DEBUG) {
          int count = 0;
          for(int row = 0; row < this.adjacencyMatrix.length; row++) {
              for(int col = row; col < this.adjacencyMatrix[0].length; col++) {
                  if(row != col && this.adjacencyMatrix[row][col] == 1)
                      count++;
              }
          } System.out.println(this.numberOfEdges + " = " + count);
      } if (isSymmetric(this.adjacencyMatrix)) {
        sortGraph(0, this.adjacencyMatrix);
        return this.adjacencyMatrix;
      } else {
        System.out.println("Matrix isn't symmetric.");
        sortGraph(0, this.adjacencyMatrix);
        return this.adjacencyMatrix;
      }
  }

  public void showAdjacencyMatrix(int[][] g)
  {
      //this.pRandomGeneration();
      for (int row = 0; row < g.length; row++)
      {   for (int col = 0; col < g.length; col++)
          { System.out.print(g[row][col] + " "); }
            System.out.println();
      }
  }

  public static void main(String[] args)
  {
      GraphGenerating g = new GraphGenerating(16,10);
      int[][] m = g.pRandomGeneration();
      g.showAdjacencyMatrix(m);
  }
}
