/*
 * @author stevenyu
 */
import static org.junit.Assert.*;
import org.junit.Test;
/**
 * @author stevenyu
 */
public class TestArrayDequeGold {

    @Test
    public void testStudentArrayDeque(){
        StudentArrayDeque<Integer>  testExpected = new StudentArrayDeque();
        ArrayDequeSolution<Integer> randomTest = new ArrayDequeSolution();
        for(int i = 0; i<10; i++){
            testExpected.addFirst(StdRandom.uniform(2));
            randomTest.addFirst(StdRandom.uniform(2));
        }

        for(int i = 0; i<10; i++){
            Double numberBetweenZeroUndOne = StdRandom.uniform();
            if (numberBetweenZeroUndOne <0.5) {
                int expect = testExpected.removeFirst();
                int actual = randomTest.removeFirst();
                assertEquals("removeFirst()\n",expect,actual);
            }else {
                int expect = testExpected.removeLast();
                int actual = randomTest.removeLast();
                assertEquals("\nremoveLast()\n",expect,actual );
            }
        }





    }
}
