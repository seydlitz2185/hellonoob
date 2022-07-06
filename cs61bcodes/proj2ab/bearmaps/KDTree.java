package bearmaps;

import java.util.*;

/**
 * @author stevenyu
 */

public class KDTree {
    private List<Point> kdTree;
    private int size;
    private static final int k = 2;

    public KDTree(List<Point> points) {

        int bound = (int) Math.ceil(Math.log(points.size()) / Math.log(2));
        List<Point> mask = Arrays.asList(new Point[1 << bound]);
        kdTree = new ArrayList<>(mask.subList(0, mask.size() - 1));
        size = points.size();
        generateKDTree(points, 0, 0);

    }

    private Comparator<Point> xDimensionComparator = (i, j) -> Double.compare(i.getX(), j.getX());
    private Comparator<Point> yDimensionComparator = (i, j) -> Double.compare(i.getY(), j.getY());

    /*Nlog(N)*/
    void generateKDTree(List<Point> points, int depth, int base) {
        if (points.size() == 0) {
            return;
        }
        int axis = depth % k;
        Comparator<Point> comparator = (axis == 0) ? xDimensionComparator : yDimensionComparator;
        List<Point> modifiablePoints = new ArrayList<Point>(points);
        Collections.sort(modifiablePoints, comparator);
        int medium = points.size() >= 3 ? points.size() / 2 : points.size() - 1;
        List<Point> leftPoints = new ArrayList<>();
        List<Point> rightPoints = new ArrayList<>();
        Point parent = modifiablePoints.get(medium);
        kdTree.set(base, parent);
        if (points.size() >= 2) {
            leftPoints = List.copyOf(modifiablePoints.subList(0, medium));
            kdTree.set(leftChild(base), points.get(medium - 1));

        }
        if (points.size() >= 3) {
            rightPoints = List.copyOf(modifiablePoints.subList(medium + 1, points.size()));
            kdTree.set(rightChild(base), points.get(medium + 1));
        }
        generateKDTree(leftPoints, depth + 1, leftChild(base));
        generateKDTree(rightPoints, depth + 1, rightChild(base));
    }

    int size() {
        return size;
    }

    int parent(int n) {
        return (n - 1) / 2;
    }

    int leftChild(int n) {
        return n * 2 + 1;
    }

    int rightChild(int n) {
        return n * 2 + 2;
    }

    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Point best = kdTree.get(0);
        return nearestHelper(0, 0, goal, best);
    }

    private Point nearestHelper(int index, int flag, Point goal, Point best) {
        if (index > kdTree.size() || kdTree.get(index) == null) {
            return best;
        }
        Point n = kdTree.get(index);
        boolean dimension = flag == 0;
        double ngDist = Point.distance(n, goal);
        double bgDist = Point.distance(best, goal);
        best = (ngDist < bgDist) ? n : best;

        Comparator<Point> comparator = dimension ? xDimensionComparator : yDimensionComparator;
        boolean isGoalLessThanN = comparator.compare(goal, n) <= 0;
        int goodSide = isGoalLessThanN ? leftChild(index) : rightChild(index);
        int badSide = !isGoalLessThanN ? leftChild(index) : rightChild(index);
        flag = Math.abs(flag - 1);
        best = nearestHelper(goodSide, flag, goal, best);
        /**MAX_SEEDS = 100000 will check bad side*/
        Point vertical = dimension ? new Point(n.getX(), goal.getY())
                : new Point(goal.getX(), n.getY());
        boolean badSideStillHaveSomethingGood = Point.distance(
                vertical, goal) < bgDist;

        if (badSideStillHaveSomethingGood) {
            best = nearestHelper(badSide, flag, goal, best);
        }

        return best;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);
        KDTree kdt = new KDTree(List.of(p1, p2, p3));
    }

}
