package bearmaps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    Random random =new Random(1919810);
    double[] xSeeds;
    double[] ySeeds;
    List<Point> points;
    long start,end;
    int MAX_SEEDS = 10000;
    KDTree kdt;
    NaivePointSet nps;
    Point goal ;
    public void init(){
        xSeeds = new double[MAX_SEEDS];
        ySeeds = new double[MAX_SEEDS];
        points = new ArrayList<>();
        for (int i =0 ; i< MAX_SEEDS ; i++){
            xSeeds[i] = (random.nextDouble()-0.5)*10;
            ySeeds[i] = (random.nextDouble()-0.5)*10;
            Point p = new Point(xSeeds[i],ySeeds[i]);
            points.add(p);
        }
         kdt = new KDTree(points);
         nps = new NaivePointSet(points);
         goal = new Point((random.nextDouble()-0.5)*10,(random.nextDouble()-0.5)*10);
    }

    @Test
    public void naiveTest(){
        init();
        System.out.println("goal: "+goal.toString());
        start = System.currentTimeMillis();
        Point bestNps = nps.nearest(goal.getX(),goal.getY());
        end = System.currentTimeMillis();
        System.out.println("naivePointSet total time elapsed: " + (end - start)/1000.0 +  " seconds.");
        System.out.println("bestNps: "+bestNps.toString());
        start = System.currentTimeMillis();
        Point bestKdt = kdt.nearest(goal.getX(),goal.getY());
        end = System.currentTimeMillis();
        System.out.println("KDTree total time elapsed: " + (end - start)/1000.0 +  " seconds.");
        System.out.println("bestKdt: "+bestKdt.toString());
    }

}
