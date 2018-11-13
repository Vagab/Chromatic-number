import java.io.*;
import java.util.*;

public class ReadGraph {

    public final static boolean DEBUG = false;

    public final static String COMMENT = "//";
    private File inputfile;
    private int[][] adjMatrix;
    private int graphNumNodes = -1;
    public ReadGraph(File file)
    {
      this.inputfile = file;
    }
    public int[][] readMatrix()
    {
        int m = -1;

        try {
            FileReader fr = new FileReader(this.inputfile);
            BufferedReader br = new BufferedReader(fr);

            String record = new String();

            while ((record = br.readLine()) != null) {
                if (record.startsWith("//")) continue;
                break;
            }

            if (record.startsWith("VERTICES = ")) {
                graphNumNodes = Integer.parseInt(record.substring(11));
                if (DEBUG) System.out.println(COMMENT + " Number of vertices = " + graphNumNodes);
            }

            record = br.readLine();

            if (record.startsWith("EDGES = ")) {
                m = Integer.parseInt(record.substring(8));
                if (DEBUG) System.out.println(COMMENT + " Expected number of edges = " + m);
            }
            this.adjMatrix = new int[graphNumNodes][graphNumNodes];
            for (int d = 0; d < m; d++) {
                if (DEBUG) System.out.println(COMMENT + " Reading edge "+(d+1));

                record = br.readLine();
                String data[] = record.split(" ");

                if (data.length != 2) {
                    System.out.println("Error! Malformed edge line: "+record);
                    System.exit(0);
                }
                int u = Integer.parseInt(data[0]);
                int v = Integer.parseInt(data[1]);

                this.adjMatrix[u-1][v-1] = 1;
                this.adjMatrix[v-1][u-1] = 1;


            }
            for (int i = 0; i < this.adjMatrix.length; i++)
            {this.adjMatrix[i][i] = 1;}

        } catch (IOException ex) {

            System.out.println("Error! Problem reading file " + inputfile);
            System.exit(0);
        }
        return this.adjMatrix;
      }
    }
