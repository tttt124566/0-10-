/*
Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LRUCache cache = new LRUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
*/

class Node {
    int key;
    int value;
    Node next;
    Node prev;
    Node(int key, int value) {
        this.key = key;
        this.value = value;
        next = null;
        prev = null;
    }
}

public class LRUCache {
    int capacity;
    int count;
    Node head, tail;
    Map<Integer, Node> map;
    
    LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        remove(map.get(key));
        addToFront(map.get(key));
        return map.get(key).value;
    }
    
    void put(int key, int value) {
        if (!map.containsKey(key)) {
            Node node = new Node(key, value);
            map.put(key, node);
            if (count < capacity) {
                count++;
                addToFront(node);
            } else {
                map.remove(tail.prev.key);
                remove(tail.prev);
                addToFront(node);
            }
        } else {
            Node node = map.get(key);
            node.value = value;
            remove(node);
            addToFront(node);
        }
    }
    
    void addToFront(Node node) {
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }
    
    void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
};

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
