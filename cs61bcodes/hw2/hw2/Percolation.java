package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import java.util.HashSet;
public class Percolation {
    private WeightedQuickUnionUF grids;
    private int index;
    private int gridSize;
    private int virtualTopSite;
    private int virtualDownSite;
    private HashSet<Integer> openedGirds;
    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N){
        this.index = N;
        this.gridSize = N*N;
        virtualTopSite = gridSize;
        virtualDownSite = gridSize+1;
        /*add virtual top site and virutal down site */
        grids =new WeightedQuickUnionUF(gridSize+2);
        for (int i = 0; i < index;i++) {
            grids.union(virtualTopSite,i);
            grids.union(virtualDownSite,index*(index-1)+i);
        }
        openedGirds = new HashSet<>();
    }
    //address isolation
    private int xyTo1D(int row,int col){
        return row*index+col;
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col){
        int wquIndex =xyTo1D(row,col);
        if(wquIndex <0 ||wquIndex >= gridSize){
            throw new java.lang.IllegalArgumentException("outSide");
        }
        openedGirds.add(wquIndex);
        if(grids.count()==gridSize){
            return;
        }else {
            int[] neighbours = getNeighbours(row,col);
            for (int neighbour: neighbours) {
                if (openedGirds.contains(neighbour)){
                    grids.union(neighbour,wquIndex);
                }
            }
        }
    }

    private int[] getNeighbours(int row,int col){
        int[] neighbours= new int[4];
        int upper = (row-1)*index+col;
        int lower = (row+1)*index+col;
        int left = row*index+col-1;
        int right = row*index+col+1;
        neighbours[0]=upper;
        neighbours[1]= right;
        neighbours[2]= lower;
        neighbours[3]= left;
        if(row==index-1 ){
            neighbours[2]= -1;
            if(col==index-1){
                neighbours[1]= -1;
            }else if(col==0) {
                neighbours[3]= -1;
            }
            return neighbours;
        }else if(row%index==0){
            neighbours[0]= -1;
            if(col==index-1){
                neighbours[1]=-1;
            }else if(col == 0){
                neighbours[3]=-1;
            }
            return neighbours;
        }else {
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
        return openedGirds.contains(wquIndex);
    }
    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        int wquIndex =xyTo1D(row,col);
        if(wquIndex <0 ||wquIndex >= gridSize){
            throw new java.lang.IllegalArgumentException("outSide");
        }
        return openedGirds.contains(wquIndex)&&grids.connected(wquIndex,virtualTopSite);
    }
    // number of open sites
    public int numberOfOpenSites(){
        return openedGirds.size();
    }
    // does the system percolate?
    public boolean percolates(){
        return grids.connected(virtualTopSite,virtualDownSite);
    }
    public String getOpenedGrids(){
        return openedGirds.toString();
    }

    public int getGridSize() {
        return gridSize;
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args){
//        int index = 10;
//        Percolation p = new Percolation(index);
//        while (!p.percolates()) {
//            for (int i = 0; i < (int) Math.pow(index, 2); i++) {
//                int row = (int) (Math.random() * 10);
//                int col = (int) (Math.random() * 10);
//                try {
//                    p.open(row, col);
//                } catch (Exception e) {
//                    continue;
//                }
//                System.out.println(p.getOpenedGrids());
//            }
//            System.out.println(p.percolates());
//            if(!p.percolates()){
//                p=new Percolation(index);
//            }
//        }
    }
}

