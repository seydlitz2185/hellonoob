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

    private static class DataIndexedCharMap <N> {
        List<N> items = new ArrayList<N>(R);
        public DataIndexedCharMap(int R) {

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
        for (char c: key.toCharArray()
        ) {

        }

        return false;
    }

    private boolean containsHelper(Node n,int i){
        return false;
    }

    @Override
    public void add(String key) {

    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        return null;
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
