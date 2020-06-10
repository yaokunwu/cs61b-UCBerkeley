package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int T;
    private double[] thre;

    private double singleExp(int N, PercolationFactory pf) {
        Percolation single = pf.make(N);
        while (!single.percolates()) {
            int row = StdRandom.uniform(N);
            int col = StdRandom.uniform(N);
            single.open(row, col);
        }
        return single.opensize / (double) (N * N);
    }
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 | T <= 0) {
            throw new IllegalArgumentException("invalid input!");
        }

        this.T = T;
        double[] curr = new double[T];
        for (int i = 0; i < T; i += 1) {
            double result = singleExp(N, pf);
            curr[i] = result;
        }
        this.thre = curr;
    }   // perform T independent experiments on an N-by-N grid
    public double mean() {
        return StdStats.mean(thre);
    }                                           // sample mean of percolation threshold
    public double stddev() {
        return StdStats.stddev(thre);
    }                                         // sample standard deviation of percolation threshold
    public double confidenceLow() {
        double aver = mean();
        double sigm = stddev();
        return aver - 1.96 * sigm / Math.sqrt(T);
    }                                  // low endpoint of 95% confidence interval
    public double confidenceHigh() {
        double aver = mean();
        double sigm = stddev();
        return aver + 1.96 * sigm / Math.sqrt(T);
    } // high endpoint of 95% confidence interval

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationFactory pf = new PercolationFactory();
        PercolationStats simu = new PercolationStats(N, T, pf);
        System.out.println("mean = " + simu.mean());
        System.out.println("stddev = " + simu.stddev());
        System.out.println("95% confidence interval = " + "[" + simu.confidenceLow() + " , "
                + simu.confidenceHigh() + "]");
    }
}
