package hw3.hash;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.*;


public class TestSimpleOomage {

    @Test
    public void testHashCodeDeterministic() {
        SimpleOomage so = SimpleOomage.randomSimpleOomage();
        int hashCode = so.hashCode();
        for (int i = 0; i < 100; i += 1) {
            assertEquals(hashCode, so.hashCode());
        }
    }

    @Test
    public void testHashCodePerfect() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(5, 20, 10);
        assertEquals(ooA,ooA2);
        assertNotEquals(ooA,ooB);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooB);
        assertFalse(hashSet.contains(ooA));
        HashMap<Integer,SimpleOomage> hashMap = new HashMap<>();
        /*hashCode is not perfect, ooA and ooB have same hashcode,
       using this hashCode as Key causes override in hashMap*/
        hashMap.put(ooB.hashCode(),ooB);
        hashMap.put(ooA.hashCode(),ooA);
        assertTrue(hashMap.containsValue(ooA));
        assertTrue(hashMap.containsValue(ooB));
    }

    @Test
    public void testEquals() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        SimpleOomage ooB = new SimpleOomage(50, 50, 50);
        assertEquals(ooA, ooA2);
        assertNotEquals(ooA, ooB);
        assertNotEquals(ooA2, ooB);
        assertNotEquals(ooA, "ketchup");
    }


    @Test
    public void testHashCodeAndEqualsConsistency() {
        SimpleOomage ooA = new SimpleOomage(5, 10, 20);
        SimpleOomage ooA2 = new SimpleOomage(5, 10, 20);
        HashSet<SimpleOomage> hashSet = new HashSet<>();
        hashSet.add(ooA);
        assertTrue(hashSet.contains(ooA2));
    }

    /* TODO: Uncomment this test after you finish haveNiceHashCodeSpread in OomageTestUtility */
    @Test
    public void testRandomOomagesHashCodeSpread() {
        List<Oomage> oomages = new ArrayList<>();
        int N = 10000;

        for (int i = 0; i < N; i += 1) {
            oomages.add(SimpleOomage.randomSimpleOomage());
        }
        boolean isNiceSpread = OomageTestUtility.haveNiceHashCodeSpread(oomages, 10);
        assertTrue(isNiceSpread);
    }

    /** Calls tests for SimpleOomage. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestSimpleOomage.class);
    }
}
