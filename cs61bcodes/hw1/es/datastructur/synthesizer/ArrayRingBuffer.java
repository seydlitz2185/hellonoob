package es.datastructur.synthesizer;
import edu.princeton.cs.algs4.StdAudio;

import java.lang.RuntimeException;
import java.util.Iterator;
import java.util.Comparator;

public class ArrayRingBuffer<T>  implements  BoundedQueue<T> ,Comparable<ArrayRingBuffer>{
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[])new Object[capacity];
        first = last = fillCount = 0;
    }

    @Override
    public void enqueue(T x) {
        if(isFull()){
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount++;
        if(last == capacity()-1){
            last =0;
        }else {
            last++;
        }
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if(fillCount ==0){
            throw  new RuntimeException("Ring buffer underflow");
        }
        T returnValue = rb[first];
        rb[first] = null;
        fillCount--;
        if(first == capacity()-1){
            first =0;
        }else {
            first++;
        }
        return returnValue ;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if(fillCount ==0){
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }


    @Override
    public int compareTo(ArrayRingBuffer o) {
        Comparator<ArrayRingBuffer> ca = new ArrayRingBufferComparator();
        return ca.compare(this ,o);
    }

    @Override
    public boolean equals(Object o){

        return compareTo((ArrayRingBuffer) o)== 1 ;
    }
    private static class ArrayRingBufferComparator implements Comparator<ArrayRingBuffer> {

        @Override
        public int compare(ArrayRingBuffer o1, ArrayRingBuffer o2) {
            if(o1.capacity()!=o2.capacity() || o1.fillCount()!=o2.fillCount()){
                return -1;
            } else {
                for (int i=0; i<o1.capacity();i++){
                    if(!o1.rb[i].equals(o2.rb[i]) ){
                        return -1;
                    }
                }
                return 1;
            }
        }
    }

    private class ArrayRingBufferIterator implements Iterator<T>{
        private int counter;
        public ArrayRingBufferIterator(){
            counter = 0;
        }
        @Override
        public boolean hasNext() {
            return counter < capacity();
        }

        @Override
        public T next() {
            T returnItem = rb[counter];
            counter++;
            return returnItem;
        }
    }
}
