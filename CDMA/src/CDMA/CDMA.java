package CDMA;
public class CDMA {

    public static int[] stream(int []a,int[]b){
        int[] temp = new int[Math.max(a.length,b.length)];
        for(int i =0; i< temp.length;i++){
            if(i<a.length && i<b.length){
                temp[i] = a[i]+b[i];
            }else if(i<a.length){
                temp[i] = a[i];
            }else{
                temp[i] = b[i];
            }
        }
        return temp;
    }
}

