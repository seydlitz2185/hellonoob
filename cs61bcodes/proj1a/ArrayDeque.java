
/**
 * @author stevenyu
 * Final
 */
public class ArrayDeque<Item> implements List<Item>{
    /**R = size / items. length
     * Half array size when R < 0.25.*/
    private int size;

    private Item[] items;
    private int nextFirst;
    private int nextLast;
    public ArrayDeque() {
        this.items = (Item[]) new Object[8];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }
    public ArrayDeque(ArrayDeque a) {
        this.items = (Item[]) new Object[a.items.length];
        for (int i = 0; i < a.items.length;i++ ) {
            this.items[i] = (Item)a.items[i];
        }
        this.nextFirst = a.nextFirst;
        this.nextLast = a.nextLast;
    }
    /**SizeBalance has problem!!!!!!*/
    public void SizeBalance(){
        double R = (double) size/items.length;
        if (R < 0.25 && items.length >= 16){
            resize(items.length/2);
        }else if(R >=1 || ((nextLast == items.length)||(nextFirst == -1))
        ){
            resize(items.length*2);
        }
    }
    /**resize,items[size-1] is empty */
//        private void resize(int capacity) {
//            Item[] a =(Item[]) new Object[capacity];
//            System.arraycopy(items, 0, a, 0, size);
//            items = a;
//        }
//        private void resizeF(int capacity) {
//            Item[] a =(Item[]) new Object[capacity];
//            System.arraycopy(items, nextFirst, a, 0, size);
//            nextFirst=0;
//            nextLast = size-1;
//            items = a;
//        }
    private void alignCenter(int length){
        nextFirst = (length-size)/2-1;
        nextLast = (length+size)/2;
    }
    private void resize(int capacity){
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items,nextFirst+1,a,(capacity-size)/2,size);
        items = a;
        alignCenter(items.length);
    }
    //        private void moveNextFirst(){
//            if(nextFirst +1== items.length){
//                nextFirst = 0;
//            }else {nextFirst++;}
//        }
//        private void moveNextLast(){
//            if(nextLast+1 == items.length){
//                nextLast = 0;
//            }else {nextLast++;}
//        }
    @Override
    public void addFirst(Item x){
        items[nextFirst] = x;
        nextFirst--;
        size++;
        SizeBalance();
//            moveNextFirst();
    }

    @Override
    public void addLast(Item x) {
        items[nextLast] = x;
        nextLast++;
        size++;
        SizeBalance();
        //           moveNextLast();
    }


    @Override
    public Item removeFirst(){
        if(nextLast-nextFirst == 1){
            alignCenter(items.length);
            return null;
        }
        Item ret;
        ret = items[++nextFirst];
        items[nextFirst] = null;
        if (ret != null){
            size--;
        }
        SizeBalance();
        return ret;
    }
    /***/
    @Override
    public Item removeLast(){
        if(nextLast-nextFirst == 1){
            alignCenter(items.length);
            return null;
        }
        Item ret;
        ret = items[--nextLast];
        items[nextLast] = null;
        if (ret != null){
            size--;
        }
        SizeBalance();
        return ret;

    }
    @Override
    public Item get(int i) {
        return items[i];
    }


    @Override
    public Item getFirst() {
        return items[nextFirst-1];
    }

    @Override
    public Item getLast() {
        return items[nextLast-1];
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque(){
        for(Item x:items){
            System.out.print(x+" ");
        }
        System.out.println();
    }


}

