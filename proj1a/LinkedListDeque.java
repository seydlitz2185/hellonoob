/**
 * @author stevenyu
 */
public  class LinkedListDeque<T> implements List<T>{

    private class ListNode{
        public ListNode front;
        public T item;
        public ListNode rear;

        public ListNode(ListNode front, T item, ListNode rear) {
            this.front = front;
            this.item = item;
            this.rear = rear;
        }
    }
    private int size;
    private final ListNode first;
    private final ListNode last;

    public LinkedListDeque() {
        first = new ListNode(null, null, null);
        last = new ListNode(first, null, null);
        first.rear =last;
        this.size = 0;
    }

    public LinkedListDeque(LinkedListDeque l) {
        first = new ListNode(null, (T) l.first.item, null);
        last = new ListNode(first,(T)l.last.item,null);
        ListNode ptr = l.first.rear;
        while(ptr.rear!= null){
            addLast(ptr.item);
            ptr = ptr.rear;
        }
        this.size = l.size;
    }

    /**Adds an item of type T to the front of the deque.*/

    @Override
    public void addFirst(T item){
        ListNode temp = new ListNode(first,item,null);
        first.rear.front = temp;
        temp.rear = first.rear;
        first.rear = temp;
        size ++;
    }
    /** Adds an item of type T to the back of the deque.*/
    @Override
    public void addLast(T item){
        ListNode temp = new ListNode(null,item,last);
        last.front.rear = temp;
        temp.front = last.front;
        last.front = temp;
        size ++;
    }
    /**Returns true if deque is empty, false otherwise.*/
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    /**Returns the number of items in the deque.*/
    @Override
    public int size(){
        return size;
    }

    @Override
    public T getFirst() {
        return first.rear.item;
    }

    @Override
    public T getLast() {
        return last.front.item;
    }

    /**Prints the items in the deque from first to last,
     *  separated by a space. Once all the items have been printed,
     *  print out a new line.*/
    @Override
    public void printDeque(){
        ListNode ptr= first.rear;
        while (ptr.rear != null){
            System.out.print(ptr.item+" ");
            ptr = ptr.rear;
        }
        System.out.println();
    }

    /***/
    @Override
    public T removeFirst(){
        if (first == null){
            return null;
        }else {
            T ret = first.rear.item;
            first.rear.rear.front = first;
            first.rear = first.rear.rear;
            size--;
            return ret;
        }

    }
    /***/
    @Override
    public T removeLast(){
        if (last == null){
            return null;
        }else {
            T ret = last.front.item;
            last.front.front.rear = last;
            last.front= last.front.front;
            size--;
            return ret;
        }
    }



    /***/
    @Override
    public T get(int index){
        if(index<0 || index > size-1){
            return null;
        }else{
            ListNode ptr = first;
            for(int i = 0; i<=index; i++){
                ptr = ptr.rear;
            }
            return ptr.item;
        }
    }
    public ListNode getRecursiveHelper(ListNode l ,int index){
        if(index == 0){
            return l;
        }
        return getRecursiveHelper(l.rear,index-1);
    }
    public T getRecursive(int index){
        if(index<0 || index >=size){
            return null;
        }
        return getRecursiveHelper(first.rear,index).item;
    }
}
