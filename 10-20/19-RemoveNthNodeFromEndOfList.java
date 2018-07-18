/*
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?


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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return null;
        }
        
        ListNode p = new ListNode(-1);
        p.next = head;
        int length = 0;
        ListNode t = p;
        while (t.next != null) {
            length++;
            t = t.next;
        }
        
        if (n > length || n == 0) {
            return head;
        }
        
        int step = length - n;
        t = p;
        for (int i = 0; i < step; i++) {
            t = t.next;
        }
        
        t.next = t.next.next;
        
        return p.next;
    }
}
