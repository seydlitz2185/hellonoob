import java.util.ArrayList;
import java.util.Arrays;
/**
 * @author stevenyu
 */
/*
(1)grid is an array of 2-element arrays representing
the grid positions (in [row, column] format) at
which grid are thrown in sequence (i.e. a dart is
thrown at position grid[t] at time t). You may
assume all elements of grid are unique, valid
positions within the grid.
(2)A bubble is stuck if
It is in the topmost row of the grid, or
It is orthogonally adjacent to a bubble that is stuck.
 */

public class BubbleGrid {
    int[][] grid;
    UnionFind u;
    public BubbleGrid() {
        this.grid = new int[][]{{1, 1, 1,1,1},
                                {0, 1, 1,0,1},
                                {1, 0, 0,1,0},
                                {1, 1, 1,1,0}};
    }
    public BubbleGrid(int[][] grid){
        this.grid = grid;
    }
    public void toWqu(){
        u = new UnionFind(grid.length*grid[0].length);
        for (int i = 0 ;i<grid.length;i++){
            for(int j = 0; j<grid[0].length; j++){
                if (grid[i][j] == 0){
                    /*empty*/
                    u.items[(i)*grid[0].length+j] = -999;
                    continue;
                }
                if(i!= grid.length-1 &&grid [i][j] == 1 && grid[i+1][j]==1 && isStuck (i+1,j)){
                    u.union((i*grid[0].length+j),((i+1)*grid[0].length+j));

                }else if( i!= grid.length-1&&j!=grid[0].length-1  &&grid[i][j] == 1 && grid[i+1][j+1]==1 && isStuck(i+1,j+1)){
                    u.union((i*grid[0].length+j),((i+1)*grid[0].length+(j+1)));

                }else if(i!= grid.length-1&&j!=0 && grid[i][j] == 1 && grid[i+1][j-1]==1 && isStuck(i+1,j-1)){
                    u.union((i*grid[0].length+j),((i+1)*grid[0].length+(j-1)));

                }
            }
        }

    }
    public void isFall(){
        for (int i = 0 ;i<grid.length;i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(!isStuck(i,j)){
                    grid[i][j] = 0;
                }
            }
        }
    }

    public boolean isStuck(int i,int j){
        if(grid[i][j]!= 0&&(i == grid.length-1 || grid[0].length == 1)){
            return true;
        } else if(grid[i][j] == 0){
            return false;
        } else if (j == 0 &&(isStuck(i+1,j)||isStuck(i+1,j+1))){
            return true;
        } else if (j == grid[0].length-1 &&(isStuck(i+1,j)||isStuck(i+1,j-1))){
            return true;
        }else {
            return j != 0 && j != grid[0].length - 1 && (isStuck(i+1,j)||isStuck(i+1,j+1)||isStuck(i+1,j-1));
        }
    }
    public int[] popBubbles(int[][] darts) {
        ArrayList<Integer> bubbles = new ArrayList<>();
        /*store sizeof UnionFind before throw oneshot*/
        int cache;
        printGrid();
        for (int[] oneShot: darts) {
            System.out.println();
            System.out.println("------我是一条华丽的分割线------");
            System.out.println();
            System.out.println("向" + Arrays.toString(oneShot) + "扔出飞镖");
            cache = sizeOfGrid();
            grid[oneShot[0]][oneShot[1]] = 0;

            if (u.find(oneShot[0] * grid[0].length + oneShot[1])== -999) {
                /*hit empty*/
                bubbles.add(0);
            } else {
                /*refresh grid and UnionFind*/
                isFall();
                toWqu();
                cache -= sizeOfGrid();
                bubbles.add(cache);
             }
            printGrid();
        }
        return bubbles.stream().mapToInt(Integer::intValue).toArray();
    }

    public int sizeOfGrid(){
        int cache = 0;
        for (int i = 0 ;i<grid[0].length;i++){
            if (u.sizeOf(u.items.length-i-1)!=999) {
                cache += u.sizeOf(u.items.length - i-1);
            }
        }
        return cache;
    }
    public void printGrid(){
        System.out.println("当前的网格为：");
        for (int[] row: grid) {
            System.out.println(Arrays.toString(row));
        }
    }
    public static void main(String[] args) {
        BubbleGrid b = new BubbleGrid();
        b.toWqu();
        int[][] darts = new int[][]{{3,3},{3,2},{3,0},{3,1},{3,3},{3,2}};
        System.out.println("准备投掷飞镖...");
        System.out.println("正在向以下坐标投掷飞镖:");
        for (int[] row: darts) {
            System.out.println(Arrays.toString(row));
        }
        int[] i = b.popBubbles(darts);
        System.out.println("投掷结束");
        System.out.println("每次投掷击落的bubble数分别为：");
        System.out.println(Arrays.toString(i));
    }
}