package es.datastructur.synthesizer;
import java.lang.RuntimeException;

import edu.princeton.cs.algs4.StdAudio;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void enqueueTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        for (int i =0; i<10;i++){
            arb.enqueue(12.1);
        }
        assertEquals(arb.isFull(),true);
    }

    @Test
    public void dequeueTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        for (int i =0; i<10;i++){
            arb.enqueue(12.1);
        }
        for (int i =0; i<9;i++){
            arb.dequeue();
        }
        assertEquals(arb.dequeue(),12.1);
        assertEquals(arb.isEmpty(),true);

    }

    @Test
    public void peekTest(){
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        for (int i =0; i<10;i++){
            arb.enqueue(12.1);
        }
        assertEquals(arb.peek(),12.1);
        assertEquals(arb.fillCount(),10);
    }

    @Test
    public void equalTest(){
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        ArrayRingBuffer expected = new ArrayRingBuffer(10);
        ArrayRingBuffer unexpected = new ArrayRingBuffer(10);
        ArrayRingBuffer lessArb = new ArrayRingBuffer(9);
        for (Object d: arb){
            arb.enqueue(12.1);
        }
        for (Object b: expected){
            expected.enqueue(12.1);
        }
        for (Object b: unexpected){
            unexpected.enqueue(12.2);
        }
        for (Object b: lessArb){
            lessArb.enqueue(12.1);
        }

        assertEquals(arb.equals(expected),true);
        assertNotEquals(arb.equals(unexpected),true);
        assertNotEquals(arb.equals(lessArb),true);
    }

}
