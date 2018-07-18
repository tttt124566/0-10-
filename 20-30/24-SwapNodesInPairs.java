/*
Given a linked list, swap every two adjacent nodes and return its head.

Example:

Given 1->2->3->4, you should return the list as 2->1->4->3.
Note:

Your algorithm should use only constant extra space.
You may not modify the values in the list's nodes, only nodes itself may be changed
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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode p = new ListNode(-1);
        p.next = head;
        ListNode t = p;
        while (t.next != null && t.next.next != null) {
            ListNode temp = t.next.next;
            t.next.next = temp.next;
            temp.next = t.next;
            t.next = temp;
            t = t.next.next;
        }
        
        return p.next;
    }
}
