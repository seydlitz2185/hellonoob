package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

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
    private class h{
        int distTo,edgeTo,hVGoal;
        public h(int distTo,int edgeTo,int h) {
            this.distTo = distTo;
            this.edgeTo = edgeTo;
            this.hVGoal = h;
        }

        private void updateDistTo(int distTo){
            this.distTo = distTo;
        }
        public int getDistTo(){
            return distTo;
        }
    }
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        ArrayHeapMinPQ<Vertex> fringe = new ArrayHeapMinPQ<>();
        HashMap<Vertex,h> map = new HashMap<>();
        while (fringe.size()>0){

        }
        if(fringe.size() ==0) {
            outcome = SolverOutcome.UNSOLVABLE;
            timeSpent = sw.elapsedTime();
        }
        if(sw.elapsedTime()>=timeout){
            outcome = SolverOutcome.TIMEOUT;
            timeSpent = timeout;
            return;
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
