import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
/**
 * @author stevenyu
 */
public class MyHashMap<Key ,Value>  implements Map61B {
    double loadFactor;
    int size;
    private Entry[] lists;
    /**Entry is a pair of Key and Value*/
     class Entry<Key,Value>{
        Key key;
        Value value;
        Entry next;
        public Entry(Key key,Value value,Entry next) {
            this.key = key;
            this.value= value;
            this.next = next;
        }
        public Entry() {
        }

        private Entry getNext(Entry entry ){
            if (entry.next == null){
                return null;
            }else {
                return entry.next;
            }
        }
        /**Return  Entry */
        private Entry get(Key key){
            if(key != null && key.equals(this.key)){
                return this;
            } else if(next == null){
                return null;
            }else {
                return next.get(key);
            }
        }
    }

    public MyHashMap() {
        lists = new Entry[16];
        this.loadFactor = 0.75;
        size = 0;
    }
    public MyHashMap(int initialSize){
        lists = new Entry[initialSize];
        this.loadFactor = 0.75;
        size = 0;
    }

    public MyHashMap(int initialSize,double loadFactor){
        lists = new Entry[initialSize];
        this.loadFactor = loadFactor;
        size = 0;
    }

    private int hashCode(Object key){
        return Math.abs(key.hashCode() % lists.length);
    }
    private void resize(){
        double load = size()/lists.length;
        if( load > this.loadFactor) {
            int newSize =lists.length*2;
            Entry[] newLists = new Entry[newSize];
            int hashCode;
            for (Entry e : lists) {
                Entry lookUp = e;
                while (lookUp != null){
                    hashCode = Math.abs(lookUp.key.hashCode()%(newSize));
                    newLists[hashCode]= new Entry(lookUp.key,lookUp.
                            value,newLists[hashCode]);
                    lookUp = lookUp.next;
                }
            }
        }
    }
    @Override
    public void clear() {
        lists = new Entry[16];
        size = 0;
        loadFactor = 0.75;
    }

    @Override
    public boolean containsKey(Object key){
        int hashCode = hashCode(key);
        if(lists[hashCode]==null){return false;}
        return lists[hashCode].get(key) != null;
    }

    @Override
    public Object get(Object key) {
        int hashCode = hashCode(key);
        if (lists[hashCode] != null){
            Entry lookUp = lists[hashCode].get(key);
            if(lookUp != null) {
                return lookUp.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(Object key, Object value) {
        int hashCode = hashCode(key);
        if(lists[hashCode] != null){
            Entry lookUp = lists[hashCode].get(key);
            if(lookUp == null){
                lists[hashCode] = new Entry(key,value,lists[hashCode]);
                size++;
                resize();
            }else {
                lookUp.value = value;
            }
        } else {
            lists[hashCode] = new Entry(key,value,lists[hashCode]);
            size++;
            resize();
        }
    }

    @Override
    public Set keySet() {
        HashSet<Key> keySet = new HashSet(size());
        for (Entry e: lists) {
            keySet(e,keySet);
        }
        return keySet;
    }

    private void keySet(Entry entry, HashSet keySet){
        while (entry != null){
            keySet.add(entry.key);
            entry = entry.next;
        }
    }
    @Override
    public Object remove(Object key) {
         if(!containsKey(key)){
             return null;
         }else {
             Entry lookUp = lists[hashCode(key)].get(key);
             Value retVal = (Value) lookUp.value;
             Entry newlist =new Entry();
             lookUp = lists[hashCode(key)];
             while (lists[hashCode(key)].getNext(lookUp)!=null) {
                 if(!lookUp.key.equals(key)){
                     newlist = new Entry(lists[hashCode(key)].key,lists[hashCode(key)].value,newlist);
                 }
                 lookUp = lookUp.next;
             }
             lists[hashCode(key)] = newlist;
             return retVal;
         }
    }

    @Override
    public Object remove(Object key, Object value) {
        return remove(key);
    }

    @Override
    public Iterator iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator<Key> implements Iterator<Key>{
         ArrayList<Key> keys;
         int index;
         public MyHashMapIterator() {
            keys = new ArrayList<>();
            toArray();
            index = 0;
         }

         private void toArray(){
             for (Entry e: lists) {
                 toArrayHelper(e,keys);
             }
         }

         private void toArrayHelper(Entry entry, ArrayList keyArray){
             while (entry != null){
                 keyArray.add(entry.key);
                 entry = entry.next;
             }
         }
        @Override
        public boolean hasNext() {
            return index<keys.size();
        }


        @Override
        public Key next() {
            return keys.get(index++);
        }
    }

    public static void main(String[] args){
        MyHashMap<String, String> q = new MyHashMap<String, String>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a");
        for ( Object s: q) {
            System.out.print(s);
            System.out.print(" ");
        }
    }
}
