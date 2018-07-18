/*
Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

Example:

Input:
[
  1->4->5,
  1->3->4,
  2->6
]
Output: 1->1->2->3->4->4->5->6
*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (int i = 0; i < lists.length; i++) {
            // be careful that we need to check if element in lists is null.
            if (lists[i] != null) {
                pq.offer(lists[i]);
            }
        }
        ListNode p = new ListNode(-1);
        ListNode res = p;
        while (!pq.isEmpty()) {
            ListNode current = pq.poll();
            p.next = current;
            // check if the next list node is not null. If it is not, then we push to the priority queue.
            if (current.next != null) {
                pq.offer(current.next);
            }
            p = p.next;
        }
        return res.next;
    }
}
