/**
 * @author stevenyu
 */
public class Sort {
    public static void sort(String[] s) {
        /**Find the smallest item
         * move it to the front
         * selection sort the rest*/

    }
    /**Sort x starting at the position start*/
    public static void sort(String[] s,int start){
        if(start == s.length){
            return;
        }
        int smallestIndex = findSmallest(s,start);
        swap(s,start,smallestIndex);
        sort(s,start+1);
    }
    public static void swap(String[] s ,int a,int b){
        /**
         * swap s[a] to s[b]
         */
        String temp = s[a];
        s[a] = s[b];
        s[b]= temp;
    }

    public static int findSmallest(String[] s,int start){
        int smallestIndex = start;
        for(int i = start; i<s.length;i++) {
            int x = s[i].compareToIgnoreCase( s[smallestIndex ]);
            if(x<0){
                smallestIndex = i;
            }
        }
        return  smallestIndex;
    }

/**      public static void selectSortAsc(String[] s){
        for(int i =0 ; i< s.length-1;i++){
            int j = findSmallest(s);
            if(i!=j){
                swap(s,i,j);
            }
        }
    }
*/

}