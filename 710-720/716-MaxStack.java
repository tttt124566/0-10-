/*
Design a max stack that supports push, pop, top, peekMax and popMax.

push(x) -- Push element x onto stack.
pop() -- Remove the element on top of the stack and return it.
top() -- Get the element on the top.
peekMax() -- Retrieve the maximum element in the stack.
popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
Example 1:
MaxStack stack = new MaxStack();
stack.push(5); 
stack.push(1);
stack.push(5);
stack.top(); -> 5
stack.popMax(); -> 5
stack.top(); -> 1
stack.peekMax(); -> 5
stack.pop(); -> 1
stack.top(); -> 5
Note:
-1e7 <= x <= 1e7
Number of operations won't exceed 10000.
The last four operations won't be called when stack is empty.
*/

// Two Solutions:
// One uses two stack. Max Stack and Stack. Similar to Min Stack
// The other uses TreeMap and double linked list.

class MaxStack {

    class Node {
        public int val;
        public Node prev;
        public Node next;
        Node(int val) {
            prev = null;
            next = null;
            this.val = val;
        }
    }
    TreeMap<Integer, List<Node>> treeMap = new TreeMap<>();
    Node head;
    Node tail;
    /** initialize your data structure here. */
    public MaxStack() {
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }
    
    public void push(int x) {
        Node node= new Node(x);
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        treeMap.computeIfAbsent(node.val, k -> new ArrayList<>()).add(node);
    }
    
    public int pop() {
        Node node = tail.prev;
        remove(node);
        return node.val;
    }
    
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        treeMap.get(node.val).remove(node);
        if (treeMap.get(node.val).size() == 0) {
            treeMap.remove(node.val);
        }
    }
    
    public int top() {
        return tail.prev.val;
    }
    
    public int peekMax() {
        return treeMap.lastKey();
    }
    
    public int popMax() {
        List<Node> nodes = treeMap.get(treeMap.lastKey());
        Node node = nodes.get(nodes.size() - 1);
        remove(node);
        return node.val;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
