package CDMA;
import org.junit.Test;

public class TestCDMA {
    @Test
//一组正交码片：00011011、00101110
    public static void main(String[] args) {
        station s = new station(8,"00011011");
        station t = new station(8,"00101110");

//检测s，t站码片是否正交
        if (s.QA(t.getSeq())[0] == -1) {
            //使用t站的码片解码s站发送的信息，得到全为零的比特流
            int[] str = CDMA.stream(s.out("1011010101010110"), t.out("1101010101010110"));
            s.print(s.QA(str));
//调整t的码片，使用s站的码片解码s站发送的信息，得到正确的信息
            t.print(t.QA(str));
        } else {
            System.out.println("两站码片不正交，请重新设置");
        }
        ;


    }
}
