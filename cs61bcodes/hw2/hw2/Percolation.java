package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    WeightedQuickUnionUF grids;
    int index;
    int gridSize;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        this.index = N;
        this.gridSize = N*N;
        grids =new WeightedQuickUnionUF(gridSize);
    }
    //address isolation
    public int xyTo1D(int row,int col){
        return row*index+col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        int wquIndex =xyTo1D(row,col);
        if(wquIndex <0 ||wquIndex >= gridSize){
            throw new java.lang.IllegalArgumentException("outSide");
        }
        if(grids.count()==gridSize){
            return;
        }else {
            int[] neighbours = getNeighbours(row,col);
            for (int neighbour: neighbours) {
                if(neighbour!=-1){
                    grids.connected(neighbour,wquIndex);
                }
            }
        }
        grids.union(wquIndex,wquIndex);
    }

    private int[] getNeighbours(int row,int col){
        /*neighbours[0] = upper
         *neighbours[1] = right
         *neighbours[2] = lower
         *neighbours[3] = left*/
        int[] neighbours= new int[4];
        int upper = (row-1)*index+col;
        int lower = (row+1)*index+col;
        int left = row*index+col-1;
        int right = row*index+col+1;
        if(row==index-1 ){
            neighbours[0]= upper;
            neighbours[1]= right;
            neighbours[3]= left;
            if(col==index-1){
                neighbours[1]= -1;
            }else if(col==0) {
                neighbours[3]= -1;
            }
            return neighbours;
        }else if(row%index==0){
            neighbours[1]= right;
            neighbours[2]= lower;
            neighbours[3]= left;
            if(col==index-1){
                neighbours[1]=0;
            }else if(col == 0){
                neighbours[3]=0;
            }
            return neighbours;
        }else {
            neighbours[0]=upper;
            neighbours[1]= right;
            neighbours[2]= lower;
            neighbours[3]= left;
            if(col==0){
                neighbours[3]=-1;
            }else if(col==index-1){
                neighbours[1]=-1;
            }
            return neighbours;
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        int wquIndex =xyTo1D(row,col);
        if(wquIndex <0 ||wquIndex >= gridSize){
            throw new java.lang.IllegalArgumentException("outSide");
        }
        return true;
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int wquIndex =xyTo1D(row,col);
        if(wquIndex <0 ||wquIndex >= gridSize){
            throw new java.lang.IllegalArgumentException("outSide");
        }
        return true;
    }
    // number of open sites
    public int numberOfOpenSites(){
        return 0;
    }
    // does the system percolate?
    public boolean percolates(){
        return true;
    }
    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){
        WeightedQuickUnionUF w = new WeightedQuickUnionUF(10);
        System.out.println(w.count());
        w.union(1,1);
        System.out.println();
        System.out.println(w.count());

    }
}

