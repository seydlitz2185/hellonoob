import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolverSlow {
    List<Bear> solvedBears;
    List<Bed> solvedBeds;
    /**runtime should be NlogN,no sorting on bears or beds*/
    public BnBSolverSlow(List<Bear> bears, List<Bed> beds) {
        solvedBears = new ArrayList<>();
        solvedBeds = new ArrayList<>();
        Bear br = bears.remove(0);
        List<Bed> less = new ArrayList<>(), greater = new ArrayList<>();
        BnBAppender(br,less,greater,beds);
        BnBSolverHelper(bears,less,greater);


    }
    private void BnBAppender(Bear br,List<Bed> less,List<Bed> greater,List<Bed> goodSide){
        int size = goodSide.size();
        for (int i =0 ; i< size;i++){
            Bed bd = goodSide.get(i);
            if(br.compareTo(bd) ==0){
                solvedBears.add(br);
                solvedBeds.add(bd);
                continue;
            }
            if(br.compareTo(bd)>0){
                less.add(bd);
            }else {
                greater.add(bd);
            }
        }
    }

    private void BnBSolverHelper(List<Bear> bears ,List<Bed> lessSide, List<Bed> greaterSide){
        while (!bears.isEmpty()) {
            Bear br = bears.remove(0);
            List<Bed> less = new ArrayList<>(), greater = new ArrayList<>();
            Bed preBed = solvedBeds.get(solvedBeds.size() - 1);
            int size;
            if (br.compareTo(preBed) < 0) {
                BnBAppender( br, less, greater, lessSide);
                greater.addAll(greaterSide);
            } else {
                BnBAppender(br, less, greater, greaterSide);
                less.addAll(lessSide);
            }
            lessSide = less;
            greaterSide = greater;
        }
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
