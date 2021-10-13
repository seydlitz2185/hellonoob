import java.util.NoSuchElementException;
public class WeightedQuickUnion {
    private int[] items;

    public WeightedQuickUnion(int n) {
        this.items = new int[n];
        for (int i = 0 ;i<n;i++){
            items[i] = -1;
        }
    }

    public void validate(int v1){
        if (items.length > v1) {
            return;
        }
        throw new NoSuchElementException("v1 = "+v1+" is invalid");
    }

    public int sizeOf(int v1){
        if(items[v1]<0){
            return  -items[v1];
        }
        return sizeOf(parent(v1));
    }

    public int parent(int v1){
        if(items[v1]<0){
            return v1;
        }else {
            v1 = items[v1];
        }
        return parent(v1);
    }

    public boolean connected(int v1, int v2){
        return v1== v2 ||parent(v1) == parent(v2);
    }

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (connected(v1, v2)) return;
        if (sizeOf(v1)>sizeOf(v2)) {
            unionHelper(v2,v1);
        } else {
            unionHelper(v1,v2);
        }
    }

    private void unionHelper(int v1,int v2) {
//        if(parent(v1) >=0 && parent(v2)>=0 ) {
//            items[items[v1]] = parent(v2);
//            items[items[v2]]--;
//        } else if(parent(v1) >=0 && parent(v2)<=0 ){
//            items[items[v1]] = v2 ;
//            items[v2]--;
//        }else {
//            items[v2] += items[v1];
//            items[v1] = v2;
//        }
        items[parent(v2)] += items[parent(v1)] ;
   //     if(v2>=0){
   //         items[parent(v1)] = v2;
 //       }else {
            /*
            only use this while pass compression;
             */
            items[parent(v1)] = parent(v2);
  //      }
     }

    public int find(int v1){
        if(parent(v1) < 0){
            return v1;
        }
        items[v1] = find(parent(v1));
        return items[v1];
    }
}
