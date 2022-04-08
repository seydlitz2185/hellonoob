import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    List<Bear> solvedBears;
    List<Bed> solvedBeds;
    /**runtime should be NlogN,no sorting on bears or beds*/
    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        solvedBears = new ArrayList<>();
        solvedBeds = new ArrayList<>();
        int cnt=0;
        while (cnt<bears.size()){
            Bear bear = bears.get(cnt++);
            BnBSolverHelper(bear,beds);

        }

    }


    private void BnBSolverHelper(Bear bear, List<Bed> beds){
        int l = 0;
        int size = beds.size();
        int r = size-1;
        Bear pivot = bear;
        while (l!=r){
            while (pivot.compareTo(beds.get(l))>0){
                l++;
            }
            while (pivot.compareTo(beds.get(r))<0){
                r--;
            }
            Bed temp = beds.get(l);
            beds.set(l,beds.get(r)) ;
            beds.set(r,temp) ;
        }
        solvedBears.add(bear);
        solvedBeds.add(beds.get(l));
        beds.remove(l);
        return;
    }



    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        // TODO: Fix me.

        return solvedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        // TODO: Fix me.

        return solvedBeds;
    }
}
