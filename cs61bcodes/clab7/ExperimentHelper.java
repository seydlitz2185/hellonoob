/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        int retVal = 0;
        /*layer of an optimum binary search tree with N nodes*/
        int layer = (int)Math.ceil(Math.log((double)(N+1)/2)/Math.log(2));
        for(int i = 0 ; i<layer;i++){
            retVal += i* Math.pow(2,i);
        }
        retVal+= (N-(Math.pow(2,layer)-1)) *(layer);
        return retVal;
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N)/N;
    }


    public static void main(String[] args){
        for (int i =1 ; i<10;i++) {
            System.out.println(optimalIPL(i)+","+optimalAverageDepth(i));
        }
    }
}
