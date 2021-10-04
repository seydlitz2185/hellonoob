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
        StudentArrayDeque<Integer> randomTest= new StudentArrayDeque();
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                randomTest.addLast((int)(10*StdRandom.uniform()));
            } else {
                randomTest.addFirst((int)(10*StdRandom.uniform()));
            }
        }
        //randomTest.printDeque();
        for (int i = 0; i < 10; i++ ) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                randomTest.removeFirst();
            } else {
                randomTest.removeLast();
            }
        }
        randomTest.printDeque();

        ArrayDequeSolution<Integer> anotherRandomTest = new ArrayDequeSolution();
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                anotherRandomTest.addLast((int)(10*StdRandom.uniform()));
            } else {
                anotherRandomTest.addFirst((int)(10*StdRandom.uniform()));
            }
        }

        for (int i = 0; i < 10; i++ ) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            if (numberBetweenZeroAndOne < 0.5) {
                anotherRandomTest.removeFirst();
            } else {
                anotherRandomTest.removeLast();
            }
        }

    }
}
