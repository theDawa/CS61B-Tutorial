package hw2;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    // perform T independent experiments on an N-by-N grid
    private int[] b;
    private int T;
    public PercolationStats(int N, int T, PercolationFactory pf){
        Percolation a = pf.make(N);
        b = new int[T];
        for (int i = 0; i < T; i++){
            int j = 0;
            while(!a.percolates()){
                a.open(StdRandom.uniform(0, N), StdRandom.uniform(0, N));
                j++;
            }
            b[i] = j;
        }
    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(b);
    }
    public double stddev(){
        return StdStats.stddev(b);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow(){
        return this.mean() - (this.stddev() / Math.sqrt(T));
    }                                  // low endpoint of 95% confidence interval
    public double confidenceHigh(){
        return this.mean() + (this.stddev() / Math.sqrt(T));
    }                                 // high endpoint of 95% confidence interval

}
