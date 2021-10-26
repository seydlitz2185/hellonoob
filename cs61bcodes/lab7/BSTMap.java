import com.sun.jdi.Value;

import java.util.*;
import java.lang.UnsupportedOperationException;
import java.lang.IllegalArgumentException;
/**
 * @author stevenyu
 */
public class BSTMap<Key extends Comparable<Key>, Value >implements Map61B{
    private Node root;
    public class Node {
        Key key;
        Value val;
        private Node LeftChild, RightChild;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }

        public void copyVal(Node node) {
            this.key = node.key;
            this.val = node.val;
            this.size = node.size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        this.root = null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public Object get(Object key) {
        return get(root,(Key) key);
    }

    public Value get(Node node,Key key) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }else if(node==null){
            return null;
        }else if(key.compareTo(node.key) <0){
            return get(node.LeftChild,key);
        }else if(key.compareTo(node.key) >0){
            return get(node.RightChild,key);
        }else {
            return node.val;
        }
    }

    @Override
    public int size() {
        return size(root);
    }

    public int size(Node node){
        if (node == null) {
            return 0;
        } else{ return node.size;}
    }

    @Override
    public void put(Object key, Object value) {
        if(key==null){
            throw new  IllegalArgumentException("calls put() with a null key");
        } else if(value==null){
            throw new  IllegalArgumentException("calls put() with a null value");
        }
        this.root = put(root,(Key) key, (Value) value);
    }

    public Node put(Node node,Key key, Value val) {
        if(node == null){
            return new Node(key,val,1);
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.LeftChild = put(node.LeftChild,key,val);
        }else if(cmp > 0){
            node.RightChild = put(node.RightChild,key,val);
        }else {
            node.val = val;
        }
        node.size = 1 + size(node.LeftChild) + size(node.RightChild);
        return node;
    }

    public void printInOrder(){
        /*To the left*/
        printNode(root);
    }

    private void printNode(Node node){
        if(node==null){
            return;
        }
        printNode(node.LeftChild);
        System.out.println(node.key+" "+node.val);
        printNode(node.RightChild);
    }



    @Override
    public Set keySet() {
        Set<Key> keySet = new HashSet<>();
        keySet(root,keySet);
        return keySet;
    }

    private void keySet(Node node,Set<Key> keySet){
        if(node == null ){return;}
        keySet(node.LeftChild,keySet);
        keySet.add(node.key);
        keySet(node.RightChild,keySet);
    }

    private class BSTMapIterator<Key> implements Iterator<Key>{
        Stack<Key> keys;

        public BSTMapIterator() {
            this.keys = new Stack<>();
        }

        @Override
        public boolean hasNext() {
            return keys.size() < size();
        }

        @Override
        public Key next() {
            toStack(root,keys);
            return keys.pop();
        }

        public void toStack(Node node,Stack<Key> keys){
            if(node == null){return ;}
            toStack(node.LeftChild,keys);
            keys.add((Key) node.key);
            toStack(node.RightChild,keys);
        }
    }

    public Key getPre(Node node, Key key){
        int compareVal = node.key.compareTo(key);
        if(compareVal<0 && node.size == 1){
            return node.key;
        } else if(compareVal<0 && node.size >1){
            if( node.RightChild !=null){
                return getPre(node.RightChild,key);
            }else {
                return node.key;
            }
        }else {
            return  getPre(node.LeftChild,key);
        }
    }

    public Key getSuc(Node node,Key key){
        int compareVal = node.key.compareTo(key);
        if(compareVal>0 && node.size == 1){
            return node.key;
        }else if(compareVal>0 && node.size >1 ){
            if( node.LeftChild !=null){
                return getSuc(node.LeftChild,key);
            }else {
                return node.key;
            }

        }else {
            return  getSuc(node.RightChild,key);
        }
    }
    public Node getNode(Key key){
        return getNode(root,key);
    }

    public Node getNode(Node node ,Key key){
        if (key == null) {
            throw new IllegalArgumentException("calls getNode() with a null key");
        }else if(node==null){
            return null;
        }else if(key.compareTo(node.key) <0){
            return getNode(node.LeftChild,key);
        }else if(key.compareTo(node.key) >0){
            return getNode(node.RightChild,key);
        }else {
            return node;
        }
    }

    @Override
    public Object remove(Object key) {
        if (!containsKey(key)) {
            return null;
        } else {
            return removeHelper(root, (Key) key);
        }
    }
    private Value removeHelper(Node node, Key key) {
        int compareVal = node.key.compareTo(key);
        if (compareVal > 0) {
            node.size--;
            return removeHelper(node.LeftChild, key);
        } else if (compareVal < 0) {
            node.size--;
            return removeHelper(node.RightChild, key);
        }else if(node == root && size() ==1 ){
            Value val = root.val;
            root = null;
            return val;
        }else {
            Value retVal = node.val;

            if(node.LeftChild == null && node.RightChild == null){
                return retVal;
            }else if(node.LeftChild != null && node.RightChild == null){
                Node temp = node.LeftChild;
                node.copyVal(node.LeftChild);
                node.LeftChild = temp.LeftChild;
                node.RightChild = temp.RightChild;
            }else if(node.LeftChild == null){
                Node temp = node.RightChild;
                node.copyVal(node.RightChild);
                node.LeftChild = temp.LeftChild;
                node.RightChild = temp.RightChild;
            }else {
                Node successor = getNode(getSuc(root,node.key));
                Node successorPre = getNode(getSuc(root,successor.key));
                Node predecessor = getNode(getPre(root,node.key));
                node.copyVal(successor);
                node.LeftChild = predecessor;
                node.RightChild = successorPre;
                removeHelper(successor,successor.key);
            }
            return retVal;
        }


    }
    @Override
    public Object remove(Object key, Object value) {
        if (key == null) {
            return null;
        } else {
            return removeHelper(root, (Key) key);
        }
    }

    @Override
    public Iterator iterator() {
        return new BSTMapIterator();
    }

    public static void main(String[] args){

    }

}
