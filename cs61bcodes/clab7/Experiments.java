/**
 * Created by hug.
 */
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.security.Key;
import java.util.ArrayList;
import java.util.List;


public class Experiments {

    public static void experiment1() {
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int nums = 5000;
        BST<String> b = new BST();
        String randomString;
        for (int i = 0 ;i< nums;i++){
           randomString =  CharacterUtils.getRandomString(7);
            b.add(randomString);
            xValues.add(i);
            yValues.add(b.averageDepth());
            double o = ExperimentHelper.optimalAverageDepth(b.size());
            y2Values.add(o);
        }
        XYChart chart = new XYChartBuilder().width(1920).height(1080).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("BST averageDepth", xValues, yValues);
        chart.addSeries("optimal BST averageDepth", xValues, y2Values);

        new SwingWrapper(chart).displayChart();
    }
/**In 1975, Gary Knott conducted an empirical study of binary search trees for his Ph.D.thesis.
 *  In pseudocode,his experiment was roughly follows:

 Initialize a tree by randomly inserting N items. Record the average depth observed as the ‘starting depth’.
 Randomly delete an item using asymmetric Hibbard deletion.
 Randomly insert a new item.
 Record the average depth of the tree.
 Repeat steps 2-4 a total of M times.
 Plot the data.
 Here, by asymmetric Hibbard deletion, we mean the deletion process described in class,
 where deleted nodes with two children are replaced with their successor.

 Based on his experiments, Knott conjectured that the random deletion/insertion process
 improved the height of the tree.

 A later experiment by Epplinger disproved this conjecture and showed that
 random insertion/deletions using Hibbard delete actually make things worse.
 This study also showed that if you modify Hibbard deletion to be symmetrical by randomly
 switching between picking the predecessor and the successor, then Knott’s original conjecture
 (that the depth of the tree gets better) was actually true.
 */
    public static void experiment2() {
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int nums = 80;
        BST<String> b = new BST();
        String randomString;
        for (int i = 0 ;i< nums;i++){
            randomString =  CharacterUtils.getRandomString(7);
            b.add(randomString);
        }
        for (int i = 0 ;i< nums*100;i++){
            b.deleteTakingSuccessor(b.getRandomKey());
            randomString =  CharacterUtils.getRandomString(7);
            b.add(randomString);
            xValues.add(i);
            yValues.add(b.averageDepth());

        }
        XYChart chart = new XYChartBuilder().width(1920).height(1080).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Knott's experiment", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void experiment3() {
        List<Double> yValues = new ArrayList<>();
        List<Double> y2Values = new ArrayList<>();
        List<Integer> xValues = new ArrayList<>();
        int nums = 1000;
        BST<String> b = new BST();
        BST<String> c = new BST();
        String randomString;
        for (int i = 0 ;i< nums;i++){
            randomString =  CharacterUtils.getRandomString(7);
            b.add(randomString);
            c.add(randomString);
        }

        for (int i = 0 ;i< 500000 ;i++){
            b.deleteTakingRandom(b.getRandomKey());
            c.deleteTakingSuccessor(c.getRandomKey());
            randomString =  CharacterUtils.getRandomString(7);
            b.add(randomString);
            c.add(randomString);
            xValues.add(i);
            yValues.add(b.averageDepth());
            y2Values.add(c.averageDepth());
        }
        XYChart chart = new XYChartBuilder().width(1920).height(1080).xAxisTitle("x label").yAxisTitle("y label").build();
        chart.addSeries("Knott's experiment", xValues, y2Values);
        chart.addSeries("Epplinger's experiment", xValues, yValues);
        new SwingWrapper(chart).displayChart();
    }

    public static void main(String[] args) {
        experiment3();

    }
}
