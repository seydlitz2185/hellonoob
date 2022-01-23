
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author stevenyu
 */
public class MyTrieSet implements TrieSet61B{
    /**ASCII*/
    private static final int R = 128;
    /**root of trie */
    private Node root;

    public MyTrieSet() {
        this.root = new Node(false,R);
    }

    private static class DataIndexedCharMap <Node> {
        private Node[] items ;
        private ArrayList<Integer> kids;
        public DataIndexedCharMap (int R) {
            items = (Node[]) new Object[R];
            kids = new ArrayList<>();
        }

        private void addNode(int i, Node n){
            items[i] = n;
            kids.add(i);
        }

        private boolean contiansNode(int i){
            if(!(null == items[i])){
                return true;
            }
            return false;
        }

        private int[] getKids(){
            int[] ret = new int[kids.toArray().length];
            for (int i = 0 ; i< kids.toArray().length; i++){
                ret[i] =(int) kids.toArray()[i];
            }
            return ret;
        }
    }

    private static class Node{
        private boolean isKey;
        private DataIndexedCharMap next;

        private Node(boolean isKey, int R){
            this.isKey = isKey;
            next = new DataIndexedCharMap<Node>(R);
        }
    }

    @Override
    public void clear() {
        root = new Node(false,R);
    }

    @Override
    public boolean contains(String key) {
        Node curr = root;
        char[] keyArray = key.toCharArray();
        int index;
        for (int i = 0; i< keyArray.length;i++){
            index = keyArray[i];
            if(curr.next.contiansNode(index)) {
                curr = (Node) curr.next.items[index];
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void add(String key) {
        Node curr = root;
        Node temp;
        char[] keyArray = key.toCharArray();
        int index;
        for (int i = 0; i< keyArray.length-1;i++){
            index = keyArray[i];
            if(!curr.next.contiansNode(index)) {
                temp = new Node(false,R);
                curr.next.addNode(index, temp);
                curr = temp;
            }else {
                curr = (Node) curr.next.items[index];
            }
        }
        if(!curr.next.contiansNode(keyArray[keyArray.length - 1])) {
            temp = new Node(true, R);
            curr.next.addNode(keyArray[keyArray.length - 1], temp);
        }else {
            curr = (Node) curr.next.items[keyArray[keyArray.length - 1]];
            curr.isKey = true;
        }
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> resultSet = new ArrayList<>();
        StringBuffer buffer = new StringBuffer();
        char[] prefixArray = prefix.toCharArray();
        Node curr = root;
        int index ;
        if(contains(prefix)){
            for (int i = 0; i< prefix.length()-1;i++){
                index = prefixArray[i];
                curr = (Node) curr.next.items[index];
            }
            if(null != curr.next.getKids()){
                curr = (Node) curr.next.items[prefixArray[prefixArray.length-1]];
                for (int i : curr.next.getKids()
                     ) {
                    keysWithPrifixHelper(prefix+(char)i,(Node) curr.next.items[i],resultSet);
                }
            }else{
                resultSet.add(prefix);
            }
            return resultSet;
        }else {
            return null;
        }
    }

    private void keysWithPrifixHelper(String s, Node n,List<String> x){
        if(n.isKey ){
            x.add(s);
        }
        for (int i : n.next.getKids()) {
            keysWithPrifixHelper(s+(char)i,(Node) n.next.items[i],x);
        }
    }
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args) {
        String s = "same";
        String t = "sam";
        String p = "Thus";
        MyTrieSet mts = new MyTrieSet();
        mts.add(s);
        mts.add(t);
        mts.add(p);
        System.out.println(mts.keysWithPrefix("sa"));
    }
}
