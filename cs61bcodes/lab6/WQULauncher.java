public class WQULauncher {
    public static void main(String[] args){
        WeightedQuickUnion w = new WeightedQuickUnion(10);
        int i = 1;
        while (i == 1 || i<20){
            w.union((int)(10*Math.random()),(int)(10*Math.random()));
            i++;
        }

//        w.union(0,1);
//        w.union(3,1);
//        w.union(2,1);
//        w.union(8,9);
//        w.union(7,9);
//        w.union(6,9);
//        w.union(9,1);
//        w.find(6);

    }
}
