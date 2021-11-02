import java.util.*;
import java.util.concurrent.Callable;
/**
 * This program run in \(\theta\)\N^2!
 * no good
 * @author Steven Yu
 */

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    public class TimePair{
        int startTime;
        int endTime;

        public TimePair(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public int getStartTime(){
            return startTime;
        }

        public int getEndTime(){
            return endTime;
        }
    }
    public class FlightList {
        TimePair timePair;
        HashSet<Flight> flightSet;

        public FlightList(int start, int end, Flight ... a) {
            timePair = new TimePair(start,end);
            flightSet = new HashSet<>() ;
            flightSet.addAll(Arrays.asList(a));
        }

        public FlightList(int start, int end, HashSet<Flight> a) {
            timePair = new TimePair(start,end);
            flightSet = (HashSet<Flight>) a.clone();
        }
        public int startTime() {
            return timePair.getStartTime();
        }

        public int endTime() {
            return timePair.getEndTime();
        }

        public int passengers(){
            int retVal= 0;
            for (Flight f:
                 flightSet) {
                retVal += f.passengers();
            }
            return retVal;
        }
    }
    Comparator<FlightList> flightListComparator =(i,j)->(-(i.passengers()- j.passengers()));

    PriorityQueue<FlightList> virtualFlights= new PriorityQueue<>(flightListComparator);
    PriorityQueue<Flight> passengerPQ;
    public FlightSolver(ArrayList<Flight> flights) {
        /* FIX ME */
        Comparator<Flight> inAirComparatorEquivalent = (i,j) -> {
            int small = Math.max(i.startTime(), j.startTime());
            int big = Math.min(i.endTime(), j.endTime());
            if( small<= big){
                FlightList flight = new FlightList(small,big,i,j);
                virtualFlights.add(flight);
            }else {
                if(i.passengers()- j.passengers()>0){
                    FlightList flight = new FlightList(i.startTime,i.endTime,i);
                    virtualFlights.add(flight);
                }else {
                    FlightList flight = new FlightList(j.startTime,j.endTime,j);
                    virtualFlights.add(flight);
                }
            }

            return i.passengers- j.passengers;
        };

        passengerPQ = new PriorityQueue<Flight>(flights.size(),inAirComparatorEquivalent);
        for (Flight f: flights) {
            passengerPQ.add(f);
        }

    }

    public FlightList merge(FlightList a, FlightList b){
        HashSet<Flight> newFlightSet =  (HashSet<Flight>) b.flightSet.clone();
        for (Flight flight:
                a.flightSet) {
            newFlightSet.add(flight);
        }
        FlightList mergedFlightList = new FlightList(a.startTime(),b.endTime(),newFlightSet);
        return mergedFlightList;
    }

    public FlightList solveHelper (PriorityQueue<FlightList> flightLists){
        PriorityQueue<FlightList> retVal = new PriorityQueue<>(flightListComparator);
            Iterator tempIterator = flightLists.iterator();
            while (tempIterator.hasNext()) {
                FlightList temp = (FlightList) tempIterator.next();
                Iterator iterator = flightLists.iterator();
                while (iterator.hasNext()) {
                    FlightList cache = (FlightList) iterator.next();
                    boolean isSame = (temp.startTime() == cache.startTime()) && (temp.endTime() == cache.endTime());
                    if (!isSame) {
                        int small = Math.max(temp.startTime(), cache.startTime());
                        int big = Math.min(temp.endTime(), cache.endTime());
                        if (small <= big) {
                            retVal.add(merge(temp, cache));
                            break;
                        } else if(temp.flightSet.size()==1 && cache.flightSet.size()==1) {
                            Flight tempFlight;
                            if (temp.passengers() - cache.passengers() > 0) {
                                tempFlight = new Flight(temp.startTime(), temp.endTime(), temp.passengers());
                                FlightList flight = new FlightList(temp.startTime(), temp.endTime(), tempFlight);
                                retVal.add(flight);
                            }
                        }
                    }
                }
            }
        if (retVal.size() == flightLists.size()||retVal.size()==1) {
            PriorityQueue<FlightList> Largest = new PriorityQueue<>();
            return retVal.peek();
        }
        return solveHelper(retVal);
    }
/*Finding the largest number of people that have ever been in flight at once.*/
    public int solve() {
        return solveHelper(virtualFlights).passengers();
    }

}
