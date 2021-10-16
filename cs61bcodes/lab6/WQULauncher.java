public class WQULauncher {
    public static void main(String[] args){
        WeightedQuickUnion w = new WeightedQuickUnion(9);
//        int i = 1;
//        while (i == 1 || i<20){
//            w.union((int)(10*Math.random()),(int)(10*Math.random()));
//            i++;
//        }

        w.union(2,3);
        w.union(1,2);
        w.union(5,7);
        w.union(8,4);
        w.union(7,2);
        w.union(0,6);
        w.union(6,4);
        w.union(6,3);


    }
}
