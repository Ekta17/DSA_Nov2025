package dsa.topten;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/*
 Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCache {

    int capacity;
    Map<Integer, Node> cache;
    Node head, tail;

    private class Node{
        int key;
        int value;
        Node prev, next;

        public Node(){

        }

        public Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.cache = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key){
        if(!cache.containsKey(key))
            return -1;

        Node n = cache.get(key);
        detactNode(n);
        addToHead(n);
        return n.value;
    }

    public void put(int key, int value){
        if(cache.containsKey(key)){ // Update value no need to check size
           Node n = cache.get(key);
           n.value = value;
           cache.put(key,n);
           detactNode(n);
           addToHead(n);
        } else {
            Node n = new Node(key, value);
            if(cache.size() == capacity){ // first remove from tail
                removeFromTail();
            }
            // add new node to head
            cache.put(key, n);
            addToHead(n);
        }
    }

    private void addToHead(Node n){
       n.next = head.next;
       head.next.prev = n;
       head.next = n;
       n.prev = head;
    }

    private void detactNode(Node n){
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }

    private void removeFromTail(){
        Node n = tail.prev;
        detactNode(n);
        cache.remove(n.key);
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        assertEquals(-1, lruCache.get(2));
        lruCache.put(2,2); //[2]
        lruCache.put(1,1);//[1,2]
        assertEquals(2, lruCache.get(2)); //[2,1]
        lruCache.put(3,3); //[3,2]
        assertEquals(-1, lruCache.get(1)); //[3,2]
        lruCache.put(2,4);
        assertEquals(4, lruCache.get(2));
        lruCache.put(5,5);
        assertEquals(-1, lruCache.get(3));
    }
}
