import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.ArrayList;
/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolverGood {
    PriorityQueue<Flight> minStartPQ;
    PriorityQueue<Flight> minEndPQ;
    Comparator<Flight> StartComparator = Comparator.comparingInt(i -> i.startTime);
    Comparator<Flight> EndComparator = Comparator.comparingInt(i -> i.endTime);

    public FlightSolverGood(ArrayList<Flight> flights) {
        minStartPQ = new PriorityQueue<>(StartComparator);
        minEndPQ = new PriorityQueue<>(EndComparator);
        for (Flight i : flights) {
            minStartPQ.add(i);
            minEndPQ.add(i);
        }
    }

    public int solve() {
        int max = 0;
        int currentMax = 0;
        while (!minStartPQ.isEmpty() && !minEndPQ.isEmpty()) {
            Flight EarliestStart = minStartPQ.peek();
            Flight EarliestEnd = minEndPQ.peek();
            if (EarliestStart.startTime <= EarliestEnd.endTime) {
                currentMax+= EarliestStart.passengers;
                minStartPQ.poll();
            } else {
                minEndPQ.poll();
                currentMax -= EarliestEnd.passengers;
            }
            if (currentMax > max) {
                max = currentMax;
            }
        }
        return max;
    }
}
