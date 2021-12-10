package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int bucketNum;
        int buckets[] = new int[M];
        double minBucketRange = oomages.size()/50;
        double maxBucketRange = oomages.size()/2.5;
        for (Oomage o:oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF ) % M;
            buckets[bucketNum]+=1;
        }

        for (int i: buckets) {
            if((double)i <  minBucketRange || (double)i>maxBucketRange ){return false;}
        }
        return true;
    }
}
