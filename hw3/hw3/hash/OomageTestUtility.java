package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int n = oomages.size();
        int flag = 0;
        int[] vis = new int[M+1];
        for(int i=0;i<M;i++) {
            vis[i] = 0;
        }
        for(int i=0;i<n;i++) {
            Oomage o = oomages.get(i);
            int bucketNum = (o.hashCode()%0x7FFFFFFF)%M;
            //System.out.println(bucketNum+" "+vis[bucketNum]);
            bucketNum = (bucketNum+M)%M;
            vis[bucketNum]++;
        }
        for(int i=0;i<M;i++) {
            int x = vis[i];
            if(x<=n/50||x>=n/2.5) {
                //System.out.println(x+" "+i);
                flag = 1;
                break;
            }
        }
        if(flag==0) {
            return true;
        }
        return false;
    }
}
