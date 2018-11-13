import java.io.*;
import java.util.*;
class ColEdge
  {
  int u;
  int v;
  }
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
        //String inputfile = args[0];
        //boolean seen[] = null;

        //! n is the number of vertices in the graph
        //int graphNumNodes = -1;

        //! m is the number of edges in the graph
        int m = -1;

        //! e will contain the edges of the graph
        //ColEdge e[] = null;

        try {
            FileReader fr = new FileReader(this.inputfile);
            BufferedReader br = new BufferedReader(fr);

            String record = new String();

            //! THe first few lines of the file are allowed to be comments, staring with a // symbol.
            //! These comments are only allowed at the top of the file.

            //! -----------------------------------------
            while ((record = br.readLine()) != null) {
                if (record.startsWith("//")) continue;
                break; // Saw a line that did not start with a comment -- time to start reading the data in!
            }

            if (record.startsWith("VERTICES = ")) {
                graphNumNodes = Integer.parseInt(record.substring(11));
                if (DEBUG) System.out.println(COMMENT + " Number of vertices = " + graphNumNodes);
            }

            //seen = new boolean[graphNumNodes+1];

            record = br.readLine();

            if (record.startsWith("EDGES = ")) {
                m = Integer.parseInt(record.substring(8));
                if (DEBUG) System.out.println(COMMENT + " Expected number of edges = " + m);
            }

            //e = new ColEdge[m];
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
                /*e[d] = new ColEdge();

                e[d].u = Integer.parseInt(data[0]);
                e[d].v = Integer.parseInt(data[1]);

                seen[e[d].u] = true;
                seen[e[d].v] = true;*/

                this.adjMatrix[u-1][v-1] = 1;
                this.adjMatrix[v-1][u-1] = 1;

                //if (DEBUG) System.out.println(COMMENT + " Edge: "+ e[d].u +" "+e[d].v);

            }
            for (int i = 0; i < this.adjMatrix.length; i++)
            {this.adjMatrix[i][i] = 1;}

            /*String surplus = br.readLine();
            if (surplus != null) {
                if (surplus.length() >= 2) {
                    if (DEBUG)
                        System.out.println(COMMENT + " Warning: there appeared to be data in your file after the last edge: '" + surplus + "'");
                }
            }
*/
        } catch (IOException ex) {
            // catch possible io errors from readLine()
            System.out.println("Error! Problem reading file " + inputfile);
            System.exit(0);
        }
/*
        for (int x = 1; x <= graphNumNodes; x++) {
            if (seen[x] == false) {
                if (DEBUG) System.out.println(COMMENT + " Warning: vertex "+x+" didn't appear in any edge : it will be considered a disconnected vertex on its own.");
            }
        }

        //


        this.adjMatrix = new int[graphNumNodes][graphNumNodes];
        for (int i = 0; i < e.length; i++) {
            this.adjMatrix[e[i].u - 1][e[i].v - 1] = 1;
            this.adjMatrix[e[i].u - 1][e[i].u - 1] = 1;
            this.adjMatrix[e[i].v - 1][e[i].u - 1] = 1;
        }
        */
        return this.adjMatrix;
      }
    }
