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
        
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, (a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) { // be careful to check if node is null 
                queue.offer(node);
            }
        }
        ListNode newHead = new ListNode(-1);
        ListNode p = newHead;
        while (queue.size() != 0) {
            ListNode node = queue.poll();
            if (node.next != null) { // the same here.
                queue.offer(node.next);
            }
            p.next = node;
            p = p.next;
        }
        return newHead.next;
    }
}
