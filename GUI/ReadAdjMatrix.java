import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;
import java.util.Scanner;
import java.util.Arrays;

public class ReadAdjMatrix {
    public int[][] getGraph(String path) throws FileNotFoundException {
        Scanner input = new Scanner(new BufferedReader(new FileReader(path)));
// pre-read in the number of rows/columns
        int rows = 0;
        while(input.hasNextLine())
        {
            ++rows;
            Scanner colReader = new Scanner(input.nextLine());
        }
        Scanner sc = new Scanner(new BufferedReader(new FileReader(path)));
        int [][] myArray = new int[rows][rows];
        while(sc.hasNextLine()) {
            for (int i=0; i<myArray.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j=0; j<line.length; j++) {
                    myArray[i][j] = Integer.parseInt(line[j]);
                }
            }
        }
        //System.out.println(Arrays.deepToString(myArray));
        return myArray;

    }
    public void setGraph(String creationWay) throws IOException {
        String path = new String("graph.txt");
        int[][] nodes;
        if (creationWay == "Randomize")
        {
            GraphGenerating graph = new GraphGenerating();
            nodes = graph.pRandomGeneration();
        }
        else if (creationWay == "Custom")
        {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter the number of vertices: ");
            int vertices = in.nextInt();
            System.out.println("Enter the number of edges(note, that number of edges has to be greater" +
                    " then half of the number of vertices): ");
            int edges = in.nextInt();
            GraphGenerating graph = new GraphGenerating(edges, vertices);
            nodes = graph.pRandomGeneration();
        }
        else
        {
            System.out.println("Creating graph");
            nodes = this.getGraph("graph.txt");
        }
        String row = "";
        BufferedWriter writer = new BufferedWriter(new FileWriter("NP.txt"));
        for (int i = 0; i < nodes.length; i++)
        {
            for (int j = 0; j < nodes.length; j++)
            {
                row += (nodes[i][j] + " ");
            }
            writer.write(row + "\n");
            row = "";
        }
        writer.close();
    }
}