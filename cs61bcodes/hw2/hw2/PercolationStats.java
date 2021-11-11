package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
/**
 * @author stevenyu
 */
public class PercolationStats {
    double[] percolationThreshold;
    /* perform T independent experiments on an N-by-N grid*/
    public PercolationStats(int N, int T, PercolationFactory pf){
        percolationThreshold = new double[T];
        if(N<=0 || T<=0){
            throw new java.lang.IllegalArgumentException("outSide");
        }
        for(int i = 0;i<T;i++){
            Percolation p = pf.make(N);
            int times = 0;
            while (!p.percolates()) {
                int row = (int) (StdRandom.uniform() * 10);
                int col = (int) (StdRandom.uniform() * 10);
                try {
                    p.open(row, col);
                } catch (Exception e) {
                    continue;
                }
            }
            percolationThreshold[i] = p.numberOfOpenSites()/(double) p.getGridSize();
        }
    }


    /* sample mean of percolation threshold*/
    public double mean(){
        return StdStats.mean(percolationThreshold);
    }
    /* sample standard deviation of percolation threshold*/
    public double stddev(){
        return StdStats.stddev(percolationThreshold);
    }
    /* low endpoint of 95% confidence interval*/
    public double confidenceLow(){
        return mean()-(1.96*Math.sqrt(stddev())/Math.sqrt(percolationThreshold.length));
    }
    /* high endpoint of 95% confidence interval*/
    public double confidenceHigh(){
        return  mean()+(1.96*Math.sqrt(stddev())/Math.sqrt(percolationThreshold.length));
    }
}
