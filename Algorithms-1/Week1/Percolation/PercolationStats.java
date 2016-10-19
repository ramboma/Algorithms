import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;
public class PercolationStats
{
    private double[] percolationThreshold;
    public PercolationStats(int N,int T)
    {
        if(N<=0||T<=0)
        {
            throw new IllegalArgumentException("Number of tests and grid size should be bigger than 1");
        }
        percolationThreshold=new double[T];
        for(int i=0;i<T;i++)
        {
            int opened=0;
            Percolation perc=new Percolation(N);
            while(!perc.percolates())
            {
                int row=StdRandom.uniform(1,N+1);
                int col=StdRandom.uniform(1,N+1);
                if(!perc.isOpen(row,col))
                {
                    perc.open(row,col);
                    opened++;
                }
            }
            percolationThreshold[i]=((double)opened/(N*N));
        }
    }
    public double mean()
    {
        return StdStats.mean(percolationThreshold);
    }
    public double stddev()
    {
        return StdStats.stddev(percolationThreshold);
    }
    public double confidenceLo()
    {
        return mean() - 1.96 * stddev() / Math.sqrt(percolationThreshold.length);
    }
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(percolationThreshold.length);
    }

    /**
     * show results
     */
    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(N, T);
        StdOut.println("mean\t\t\t\t\t= " + ps.mean());
        StdOut.println("stddev\t\t\t\t\t= " + ps.stddev());
        StdOut.println("95% confidence interval\t= " + ps.confidenceLo() + ", " + ps.confidenceHi());
        StdOut.println("Time elapsed\t\t\t= " + timer.elapsedTime());
    }
}
