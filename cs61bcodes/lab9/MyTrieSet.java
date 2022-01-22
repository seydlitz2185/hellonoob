
import java.util.ArrayList;
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
        public DataIndexedCharMap (int R) {
            items = (Node[]) new Object[R];

        }

        private void addNode(int i, Node n){
            items[i] = n;
        }

        private boolean contiansNode(int i){
            if(!(null == items[i])){
                return true;
            }
            return false;
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
        Node temp;
        char[] keyArray = key.toCharArray();


        return false;
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
        temp = new Node(true,R);
        curr.next.addNode(keyArray[keyArray.length-1], temp);
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        return null;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args) {
        String s = "The";
        String t = "The great";
        String p = "Thus";
        MyTrieSet mts = new MyTrieSet();
        mts.add(s);
        mts.add(t);
        mts.add(p);
    }
}
