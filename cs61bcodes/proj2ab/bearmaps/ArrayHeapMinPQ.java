package bearmaps;
import java.util.*;

import static bearmaps.PrintHeapDemo.printFancyHeapDrawing;
import static bearmaps.PrintHeapDemo.printSimpleHeapDrawing;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ <T> {
     private ArrayList<PriorityNode> items;
     private Map<T,Integer> itemsIndexMap;
     private int size;
     public ArrayHeapMinPQ (){
         items = new ArrayList<>();
         itemsIndexMap = new HashMap<>();
         size = 0;
     }
/**
 * Your getSmallest, contains, size and changePriority methods must run in O(log(n)) time
 * Your add and removeSmallest must run in O(log(n)) average time*/

    private void swap(int n, int parent){
        PriorityNode tempNode = new PriorityNode(items.get(n).item,items.get(n).priority);
        PriorityNode tempParent =  new PriorityNode(items.get(parent).item,items.get(parent).priority);

        itemsIndexMap.replace(tempNode.getItem(),n,parent);
        itemsIndexMap.replace(tempParent.getItem(),parent,n);
        items.remove(n);
        items.add(n, tempParent);
        items.remove(parent);
        items.add(parent,tempNode);
    }

    void swim(int n){
        int parent = parent(n);
        if(items.get(parent).compareTo(items.get(n)) >0){
            swap(n,parent);
            swim(parent);
        }
    }

    void swimDown(int n){
        int leftChild = leftChild(n);
        int rightChild = rightChild(n);
        if(items.get(n).compareTo(items.get(leftChild)) >0){
            swap(n,leftChild);
            swimDown(leftChild);
        }
        if(items.get(n).compareTo(items.get(rightChild)) >0){
            swap(n,rightChild);
            swimDown(rightChild);
        }

    }

    int parent(int n){
        return (n - 1) / 2;
    }
    /*return index of child node*/
    int leftChild(int n){
        if(n*2+1>=size()){return n;}
        return n*2+1;
    }

    int rightChild(int n){
        if(n*2+2>=size()){return n;}
        return n*2+2;
    }
    @Override
    public void add(T item, double priority) {
        if(contains(item)){throw new IllegalArgumentException();}
        PriorityNode node = new PriorityNode(item, priority);
        itemsIndexMap.put(node.getItem(),items.size());
        size++;
        items.add(node);
        swim(items.indexOf(node));
    }

    @Override
    public boolean contains(T item) {
        return itemsIndexMap.containsKey(item.hashCode());
    }

    @Override
    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return items.get(0).getItem();
    }

    @Override
    public T removeSmallest() {
        T result = items.get(0).getItem();
        size--;
        items.add(0, items.get(items.size()-1));
        items.remove(items.size()-1);
        swimDown(0);
        return result ;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if(!contains(item)){throw new IllegalArgumentException();}
        int index = itemsIndexMap.get(item.hashCode()) ;
        PriorityNode node =  items.get(index);
        node.setPriority(priority);
        swimDown(index);
        swim(index);

    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }


    public static void main(String[] args){
        ArrayHeapMinPQ<Integer> minPQ = new ArrayHeapMinPQ<>();
        minPQ.add(19,19);
        minPQ.add(3,3);
        minPQ.add(4,4);
        minPQ.add(9,9);
        minPQ.add(11,11);
        minPQ.add(12,12);
        minPQ.add(1,1);
        minPQ.changePriority(1,10);
        Integer[] heap = new Integer[minPQ.size()+1];
        heap[0]=0;
        /*for (int i = 0 ; i< minPQ.size(); i++) {
            heap[i+1] = minPQ.items.get(i).item;
        }*/
        int size = minPQ.size();
        for (int i = 0 ; i< size; i++) {
            heap[i+1] = minPQ.getSmallest();
            minPQ.removeSmallest();
        }
        printFancyHeapDrawing(heap);
    }
}
