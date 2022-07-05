package bearmaps.proj2ab;
import java.util.*;

import static edu.princeton.cs.introcs.StdOut.print;


public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ <T> {
     private ArrayList<PriorityNode> items;
   //  private PriorityNode[] itemsArr;
     private Map<T,Integer> itemsIndexMap;
     private int size;
     public ArrayHeapMinPQ (){
         items = new ArrayList<>();
  //       itemsArr =  new ArrayHeapMinPQ.PriorityNode[10];
         itemsIndexMap = new HashMap<>();
         size = 0;
     }
    public ArrayHeapMinPQ (List l, HashMap m){
        items = (ArrayList)l;
        //       itemsArr =  new ArrayHeapMinPQ.PriorityNode[10];
        itemsIndexMap = m;
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

//        PriorityNode temp = itemsArr[parent];
//        itemsArr[parent] = itemsArr[n];
//        itemsArr[n] = temp;
    }

    void swim(int n){
        int parent = parent(n);
        if(items.get(parent).compareTo(items.get(n)) >0){
            swap(n,parent);
            swim(parent);
        }
    }

    void sink(int parent){
        int youngestChild = leftChild(parent);
        if(youngestChild == parent){return;}
        if(rightChild(parent) != parent) {
            if (items.get(rightChild(parent)).compareTo(items.get(youngestChild)) < 0) {
                youngestChild = rightChild(parent);
            }
        }
        if (items.get(parent).compareTo(items.get(youngestChild)) > 0) {
            swap(parent, youngestChild);
            sink(youngestChild);
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

    /*void resize(){
        if(size()>= itemsArr.length){
            PriorityNode[] temp;
            temp = new ArrayHeapMinPQ.PriorityNode[2*itemsArr.length];
            System.arraycopy(itemsArr,0,temp,0,size);
            itemsArr = temp;
        }else if((double)size()/itemsArr.length <= 0.25 && itemsArr.length>10){
            PriorityNode[] temp = new ArrayHeapMinPQ.PriorityNode[itemsArr.length/2];
            System.arraycopy(itemsArr,0,temp,0,size);
            itemsArr = temp;
        }
    }
    */

    @Override
    public void add(T item, double priority) {
        //resize();

         if(contains(item)){throw new IllegalArgumentException();}
        PriorityNode node = new PriorityNode(item, priority);
        itemsIndexMap.put(node.getItem(),size());
        items.add(node);
//        itemsArr[size()] = node;
        swim(size());
        size++;

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
        //PriorityNode p = items.get(0);
        //return p.item;
        //return itemsArr[0].item;
        return items.get(0).getItem();
    }

    @Override
    public T removeSmallest() {
        //resize();
        PriorityNode smallest = items.get(0);
        T result = smallest.getItem();
        itemsIndexMap.remove(result);
        size--;
        smallest.item = items.get(size()).getItem();
        smallest.priority = (items.get(size()).getPriority());
        itemsIndexMap.replace(items.get(size()).getItem(),0);
        items.remove(size());
        sink(0);

        return result ;
    }

    public Double removeSmallestForTest() {
        //resize();
        PriorityNode smallest = items.get(0);
        double result = smallest.getPriority();
        size--;
        smallest.item = items.get(size()).getItem();
        smallest.priority = (items.get(size()).getPriority());
        itemsIndexMap.remove(smallest);
        itemsIndexMap.replace(items.get(size()).getItem(),0);
        items.remove(size());
        sink(0);

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
        sink(index);
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
    }
}
