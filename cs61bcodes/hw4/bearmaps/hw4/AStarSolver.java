package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import javax.swing.*;
import java.util.*;

public class AStarSolver<Vertex>implements ShortestPathsSolver<Vertex>{
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;
    private int hToEnd;
    //private double distToEnd;
    private Map<Vertex,Vertex> edgeTo;
    private Map<Vertex, Double> distMap;
    private ArrayHeapMinPQ<Vertex> fringe;
    private Map<Vertex,Integer> hMap;
    public void relax(WeightedEdge<Vertex> e){
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        double distToP = distMap.get(p);
        double distToQ = distToP+w;
        //int hToQ= hMap.get(p);
        /**首先检查distMap是否包含q*/
        if(distMap.containsKey(q)){
            if(distToQ <  distMap.get(q)){
                if(fringe.contains(q)) {
                    //fringe.changePriority(q,distToQ + hToQ);
                    fringe.changePriority(q, distToQ);
                }else {
                    //solution.add(q);
                   // fringe.add(q,distToQ + hToQ);
                    fringe.add(q,distToQ);
                }
                distMap.replace(q,distToQ);
                edgeTo.replace(q,p);
            }
           // solution.remove(q);
        }else {
            distMap.put(q,distToQ);
            //hMap.put(q,hMap.get(p));
            //fringe.add(q,distToQ + hToQ);
            fringe.add(q,distToQ);
            edgeTo.put(q,p);
        }


    }

    public  AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        int default_bfs_dist = 0;
        Stopwatch sw = new Stopwatch();
        List<WeightedEdge<Vertex>> neighborEdges;
        fringe = new ArrayHeapMinPQ<>() ;
        distMap = new HashMap<>();
        //hMap = new HashMap<>();
        edgeTo= new HashMap<>();
        /**Don't use BFS , Try a simple counter here*/
        //hMap.put(start,default_bfs_dist);
        solution=new ArrayList<>();
            //hToEnd = hMap.get(start);
            fringe.add(start,hToEnd);
            distMap.put(start,0.0);
            while (fringe.size()>0 && sw.elapsedTime()<=timeout ) {
                Vertex v = fringe.removeSmallest();
                numStatesExplored+=1;
                //System.out.println(v);
                //solution.add(v);
                if (v.equals(end)) {
                    Stack<Vertex> solutionStack = new Stack<>();
                    Vertex target = end;
                    solutionStack.push(target);
                    while (!target.equals(start) && target !=null){
                        target = edgeTo.get(target);
                        solutionStack.push(target);
                    }
                    while (!solutionStack.empty()){
                        solution.add(solutionStack.pop());
                    }
                    solutionWeight = distMap.get(end);
                    //numStatesExplored = solution.size() - 1;
                    outcome = SolverOutcome.SOLVED;
                    timeSpent = sw.elapsedTime();
                    return;
                }
                neighborEdges = input.neighbors(v);
                if (neighborEdges != null) {
                    for (WeightedEdge<Vertex> e : neighborEdges) {
                        relax(e);
                    }
                }
                if (sw.elapsedTime() >= timeout) {
                    outcome = SolverOutcome.TIMEOUT;
                    timeSpent = timeout;
                }
            }
            outcome = SolverOutcome.UNSOLVABLE;
            timeSpent = sw.elapsedTime();


    }

    @Override
    public SolverOutcome outcome() {return outcome;}

    @Override
    public List<Vertex> solution() {return solution;}

    @Override
    public double solutionWeight() {return solutionWeight;}

    @Override
    public int numStatesExplored() {return numStatesExplored;}

    @Override
    public double explorationTime() {return timeSpent;}


    public Map<Vertex,Integer> BFS(AStarGraph<Vertex> input, Vertex start){
        Map<Vertex,Integer> bfs = new HashMap<>();
        Vertex curr;
        ArrayList<Vertex> fringe = new ArrayList<>();
        fringe.add(start);
        bfs.put(start,0);
        while (fringe.size()>0){
            curr = fringe.remove(0);
            List<WeightedEdge<Vertex>> neighbours = input.neighbors(curr);
            for (WeightedEdge w:neighbours
                 ) {
                Vertex to = (Vertex)w.to();
                if(bfs.containsKey(to)==false){
                    bfs.put(to,bfs.get(curr)+1);
                    fringe.add(to);
                }
            }
        }
        return bfs;
    }
}
