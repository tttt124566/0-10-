/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6] 

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].
*/

public class ZigzagIterator {
    Queue<Iterator<Integer>> queue;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        queue = new LinkedList<Iterator<Integer>>();
        Iterator<Integer> v1Iter = v1.iterator();
        Iterator<Integer> v2Iter = v2.iterator();
        if (v1Iter.hasNext()) {
            queue.offer(v1.iterator());
        }
        if (v2Iter.hasNext()) {
            queue.offer(v2.iterator());
        }
    }

    public int next() {
        Iterator<Integer> cIter = queue.poll();
        int next = (Integer)cIter.next();
        if (cIter.hasNext()) {
            queue.offer(cIter);
        }
        
        return next;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
