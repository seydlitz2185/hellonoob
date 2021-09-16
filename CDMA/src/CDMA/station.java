/**
 * @author stevenyu
 */
package CDMA;
import java.util.ArrayList;

public class station {
    private int bit ;
    private String str;
    private int[] seq;
    private int[] reverseq;
    private ArrayList<Integer>sequence;
    private ArrayList<Integer>reversequence;

    public station (int bit,String s){
        this.bit = bit;
        this.str = s;
        this.setSeq();
    }

    private int[] stringToInt(String s){
        String[] temp = s.split("");
        int []a = new int[s.length()];
        for (int i = 0; i<s.length(); i++) {
            a[i] = Integer.parseInt(temp[i]);
        }
        return a;
    }
    private void setBit(int a){
        System.out.print("请设置本站码片宽度：");
        this.bit = a;
    }

    private void setStr(String s){
        System.out.print("请设置本站码片：");
        str = s;
    }

    public void setSequence(){
        int[] temp = stringToInt(str);
        sequence = new ArrayList(bit);
        reversequence = new ArrayList(bit);
        for (int i = 0; i<bit; i++) {
            if(temp[i] == 0){
                sequence.add(i,-1);}
            else{sequence.add(i,1);}
        }
        for (int i = 0; i<bit; i++) {
            reversequence.add(i,-sequence.get(i));
        }
    }

    public void setSeq(){

        this.seq = new int[bit];
        this.reverseq = new int[bit];
        int[] temp = stringToInt(str);
        for (int i = 0; i<bit; i++) {
            if(temp[i] == 1) {
                seq[i] = 1;
            }else{
                seq[i] = -1;
            }
        }

        for (int i = 0; i<bit; i++) {
            reverseq[i] = -seq[i];
        }
    }
    public int[] QA(int[] a){
        int[] innerProduct = new int[(a.length/seq.length)];
        int cnt = 0;
        for (int i = 0; i<a.length; i++){
            cnt += this.seq[i%seq.length] * a[i];
            if( i == a.length -1 && a.length == seq.length && cnt !=0){
                innerProduct[0] = cnt/seq.length; return innerProduct;}
            if((i+1)%(seq.length) == 0 ){
                if(cnt/seq.length == 0 ||cnt/seq.length == -1 ) {
                    innerProduct[(i+1)/seq.length - 1] = -1;
                }else if(cnt/seq.length == 1) {
                    innerProduct[(i+1)/seq.length - 1] = 1;}
                cnt =0;
            }
        }
        return innerProduct;
    }
    public void in (int[]a){
        QA(a);
    }
    public int[] out(String s){
        String str = s;
        int[] temp = stringToInt(str);
        System.out.println("已发送比特流："+s);
        int[] out = new int[temp.length*bit];
        for(int i = 0;i<temp.length;i++) {
            if (temp[i] == 1) {
                if (bit * ((i) + 1) - bit * (i) >= 0) {
                    System.arraycopy(seq,
                            bit * (i) - (i * bit),
                            out,
                            bit * (i),
                            bit * ((i) + 1) - bit * (i));
                } else if (temp[i] == 0) {
                    for (int j = bit * (i); j < bit * ((i) + 1); j++) {
                        out[j] = reverseq[j - (i * bit)];
                    }
                }
            }
        }
        return  out;
    }

    public void print(int []a){
        for (Integer i:
                a) {
            System.out.print(i+" ");
        }
        System.out.println();
    }
    public ArrayList getSequence(){
        return sequence;
    }
    public ArrayList getReversequence(){
        return reversequence;
    }
    public int[] getSeq(){
        return seq;
    }
    public  int[]  getReverseq(){
        return reverseq;
    }
}
