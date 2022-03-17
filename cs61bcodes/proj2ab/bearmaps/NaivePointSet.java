package bearmaps;

import java.util.ArrayList;
import java.util.List;

/**
 * @author stevenyu
 */
public class NaivePointSet implements PointSet{
    private List<Point> naivePointSet ;
    public NaivePointSet(List<Point> points) {
        naivePointSet = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x,y);
        Point best = naivePointSet.get(0);
        return nearestHelper(0,goal,best);
    }

    private Point nearestHelper(int index, Point goal, Point best){
        if(index >= naivePointSet.size()){return best;}
        Point n = naivePointSet.get(index);
        double nGDist = Point.distance(n,goal);
        double bGDist = Point.distance(best,goal);
        best = (nGDist < bGDist) ? n : best;
        index++;
        return nearestHelper(index,goal,best);
    }

    public static void main(String[] args){
        Point p1 = new Point(1.1, 2.2); // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        NaivePointSet nps = new NaivePointSet(List.of(p1,p2,p3));
        Point ret = nps.nearest(3.0,4.0);
        System.out.println(ret.getX());
        System.out.println(ret.getY());
    }
}
