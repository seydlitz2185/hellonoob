package hw3.hash;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class TestComplexOomage {

    @Test
    public void testHashCodeDeterministic() {
        ComplexOomage so = ComplexOomage.randomComplexOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    /* This should pass if your OomageTestUtility.haveNiceHashCodeSpread
       is correct. This is true even though our given ComplexOomage class
       has a flawed hashCode. */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(ComplexOomage.randomComplexOomage());
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(oomages, 10));
    }

    /* TODO: Create a list of Complex Oomages called deadlyList
     * that shows the flaw in the hashCode function.
     */

    @Test
    public void testWithDeadlyParams() {
        List<Oomage> deadlyList = new ArrayList<>();

        // Your code here.
        int N = 1000;
        /*哈希函数的问题是每次乘256，相当于左移8位，对于一个32位的int型hashCode，
        * ComplexOomage.params的最后四个元素(这里是Integer)决定hashCode，其他的都移丢
        * 若这四个元素相等则导致碰撞
        * 解决方式：哈希函数中的不应使用2^n作为乘子，最好使用一个素数*/
        for (int i = 0; i < N; i += 1) {
            List<Integer> buffer =  ComplexOomage.randomComplexOomage().params;
            buffer.add(1);
            buffer.add(1);
            buffer.add(1);
            buffer.add(1);
            deadlyList.add(new ComplexOomage(buffer));
        }

        assertTrue(OomageTestUtility.haveNiceHashCodeSpread(deadlyList, 10));
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestComplexOomage.class);
    }
}
