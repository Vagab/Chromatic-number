public class ChromaticNumberTest
{
    public static void main(String[] args)
    {
        GraphGenerating g = new GraphGenerating(); //test lower bound
        int[][] m = g.pRandomGeneration();
        ChromaticNumber c = new ChromaticNumber(m);
        //g.showAdjacencyMatrix(m);
        //System.out.println(c.LowerBound());
        int n = 0;
        //long start = System.currentTimeMillis();
        //long end = start + 2*1000; // 60 seconds * 1000 ms/sec
        //while (System.currentTimeMillis() < end)
        //{
        n = c.getTheNumber();
        //}
        System.out.println(n);
    }
}