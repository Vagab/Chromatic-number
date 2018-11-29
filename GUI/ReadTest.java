import java.io.*;

public class ReadTest
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        ReadAdjMatrix r = new ReadAdjMatrix();
        r.setGraph("File");
        String path = "NP.txt";
        int[][] g = r.getGraph(path);
        /*for (int i = 0; i < g.length; i++)
        {
            for(int j = 0; j < g.length; j++)
            {
                System.out.print(g[i][j] + " ");
            }
            System.out.println();
        }*/
    }
}

