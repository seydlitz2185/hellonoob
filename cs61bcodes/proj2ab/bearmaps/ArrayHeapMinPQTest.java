package bearmaps;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.Random;

/**
 * @author stevenyu
 */
public class ArrayHeapMinPQTest {
    Random random =new Random(114514);
    long start,end;
    double[] seeds;
    int[] changes;
    double [] priorities;
    int MAX_SEEDS = 1000000;
    int MAX_CHANGES = 1000;
    NaiveMinPQ<Integer> naiveMinPQ;
    ArrayHeapMinPQ<Integer> arrayHeapMinPQ;
    public void init(){
        seeds = new double[MAX_SEEDS];
        priorities = new double[MAX_CHANGES];
        changes = new int[MAX_CHANGES];
        naiveMinPQ = new NaiveMinPQ<>();
        arrayHeapMinPQ = new ArrayHeapMinPQ<>();
        //minPQ.add();
        for (int i =0 ; i< MAX_SEEDS ; i++){
            seeds[i] = random.nextDouble();
        }
        for (int i =0 ; i< MAX_CHANGES; i++){
            changes[i] = random.nextInt(MAX_SEEDS);
            priorities[i] = random.nextDouble();
        }
        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            naiveMinPQ.add(i,seeds[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("naiveMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            arrayHeapMinPQ.add(i,seeds[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("arrayHeapMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    @Test
    public void naiveTest(){
        init();
       for (int i = 0; i<MAX_SEEDS;i++){
           assertEquals( naiveMinPQ.removeSmallestForTest(),arrayHeapMinPQ.removeSmallestForTest(),0);
       }
    }

    @Test
    public void containsTimeTest(){
        init();
        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            naiveMinPQ.contains(i);
        }
        end = System.currentTimeMillis();
        System.out.println("naiveMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            arrayHeapMinPQ.contains(i);
        }
        end = System.currentTimeMillis();
        System.out.println("arrayHeapMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    @Test
    public void changePriorityTest(){
        init();
        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_CHANGES; i++){
            naiveMinPQ.changePriority(changes[i],priorities[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("naiveMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_CHANGES; i++){
            arrayHeapMinPQ.changePriority(changes[i],priorities[i]);
        }
        end = System.currentTimeMillis();
        System.out.println("arrayHeapMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }

    @Test
    public void getSmallestTimeTest(){
        init();
        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            naiveMinPQ.getSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("naiveMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            arrayHeapMinPQ.getSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("arrayHeapMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }
    @Test
    public void removeSmallestTimeTest(){
        init();
        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            naiveMinPQ.removeSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("naiveMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");

        start = System.currentTimeMillis();
        for (int i =0 ; i< MAX_SEEDS; i++){
            arrayHeapMinPQ.removeSmallest();
        }
        end = System.currentTimeMillis();
        System.out.println("arrayHeapMinPQ total time elapsed: " + (end - start)/1000.0 +  " seconds.");
    }
}
