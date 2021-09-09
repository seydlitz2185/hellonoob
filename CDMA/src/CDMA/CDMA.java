package CDMA;

import java.util.Arrays;

//一组正交码片：00011011、00101110

public class CDMA {
    public static void main(String[] args){
        station s = new station("00011011");
        station t = new station("00101110");
//        s.setSeq();
//        t.setSeq();
//检测s，t站码片是否正交
        if(s.QA(t.getSeq())[0] == -1) {
            //使用t站的码片解码s站发送的信息，得到全为零的比特流
            int[]str =stream(s.out(),t.out());
            s.print( s.QA(str));
//调整t的码片，使用s站的码片解码s站发送的信息，得到正确的信息
            t.print( t.QA(str));
        }else {
            System.out.println("两站码片不正交，请重新设置");
        };


   //     System.out.println(s.getReversequence().toString());
    }

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

