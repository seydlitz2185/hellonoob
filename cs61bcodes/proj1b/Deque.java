public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    default boolean isEmpty(){
        return size() == 0;
    }

    int size();

    T getFirst() ;

    T getLast() ;

    void printDeque();

    T removeFirst();

    T removeLast();

    T get(int index);

}
