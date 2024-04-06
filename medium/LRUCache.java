package medium;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private class Node{
        private int key;
        private int val;
        Node next;
        Node prev;
        public Node(int key, int val){
            this.key = key;
            this.val = val;
        }
    }

    private Node left;
    private Node right;
    private Map<Integer,Node> cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>();
        this.left = new Node(0,0);
        this.right= new Node(0,0);
        this.left.next = this.right;
        this.right.prev = this.left;
    }

    public int get(int key) {
        if(cache.containsKey(key)){
            remove(cache.get(key));
            insert(cache.get(key));
            return cache.get(key).val;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key))
            remove(cache.get(key));
        cache.put(key,new Node(key,value));
        insert(cache.get(key));
        if(cache.size()>capacity){
            Node lru = this.left.next;
            remove(lru);
            cache.remove(lru.key);
        }
    }

    public void insert(Node node){
        Node prev = this.right.prev;
        Node next = this.right;

        prev.next = node;
        next.prev = node;

        node.next = next;
        node.prev = prev;
    }

    public void remove(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    public static void main(String[] args) {
        LRUCache obj = new LRUCache(2);
        int param_1 = obj.get(1);
        obj.put(1,1);
    }

}
