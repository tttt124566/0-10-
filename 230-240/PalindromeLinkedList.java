/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        ListNode reversed = reverse(slow.next);
        ListNode p = head;
        ListNode q = reversed;
        while (p != null && q != null) {
            if (p.val != q.val) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }
    
    private ListNode reverse(ListNode node) {
        ListNode p = new ListNode(-1);
        p.next = node;
        ListNode q = p.next;
        while (q != null && q.next != null) {
            ListNode temp = q.next;
            q.next = q.next.next;
            temp.next = p.next;
            p.next = temp;
        }
        return p.next;
    }
}
