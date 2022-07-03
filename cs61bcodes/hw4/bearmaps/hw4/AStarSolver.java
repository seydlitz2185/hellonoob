package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex>implements ShortestPathsSolver<Vertex>{
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;
    private int hToEnd;
    private double distToEnd;
    private Map<Vertex, Double> distMap;
    private ArrayHeapMinPQ<Vertex> fringe;
    Map<Vertex,Integer> hMap;

    public void relax(WeightedEdge<Vertex> e){
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();
        double distToP = distMap.get(p);
        double distToQ = distToP+w;
        int hToQ= Math.abs(hToEnd-hMap.get(q));
        /**首先检查distMap是否包含q*/
        if(distMap.containsKey(q)){
            if(distToQ <  distMap.get(q)){
                if(fringe.contains(q)) {
                    fringe.changePriority(q, hToQ);
                }else {
                    fringe.add(q,distToQ + hToQ);
                }
                distMap.replace(q,distToQ);
            }
            solution.remove(q);
        }else {distMap.put(q,distToQ);fringe.add(q,distToQ + hToQ);}


    }

    public  AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        List<WeightedEdge<Vertex>> neighborEdges;
        fringe = new ArrayHeapMinPQ<>() ;
        distMap = new HashMap<>();
        hMap = BFS(input,start);
        solution=new ArrayList<>();
        if(hMap.get(end)!=null){
            hToEnd = hMap.get(end);
            fringe.add(start,hToEnd);
            distMap.put(start,0.0);
            while (fringe.size()>0 && sw.elapsedTime()<=timeout ){
                Vertex v  = fringe.removeSmallest();
                solution.add(v);
                if (v.equals(end)){
                    solutionWeight = distMap.get(end);
                    numStatesExplored=solution.size()-1;
                    outcome = SolverOutcome.SOLVED;
                    timeSpent = sw.elapsedTime();
                    return;
                }
                neighborEdges = input.neighbors(v);
                if(neighborEdges!=null) {
                    for (WeightedEdge<Vertex> e : neighborEdges) {
                        relax(e);
                    }
                }
            }
            if(sw.elapsedTime()>=timeout){
                outcome = SolverOutcome.TIMEOUT;
                timeSpent = timeout;
            }
        }else {
        outcome = SolverOutcome.UNSOLVABLE;
        timeSpent = sw.elapsedTime();
        }

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
