/*
Given a singly linked list, determine if it is a palindrome.

Example 1:

Input: 1->2
Output: false
Example 2:

Input: 1->2->2->1
Output: true
Follow up:
Could you do it in O(n) time and O(1) space?


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
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        slow = reverse(slow);
        
        while(head != null & slow != null) {
            if (head.val != slow.val) {
                return false;
            }
            head = head.next;
            slow = slow.next;
        }
        
        return true;
    }
    
    private ListNode reverse(ListNode node) {
        ListNode dummy = new ListNode(-1);
        dummy.next = node;
        ListNode p = node;
        while (p != null && p.next != null) {
            ListNode temp = dummy.next;
            dummy.next = p.next;
            p.next = p.next.next;
            dummy.next.next = temp;
        }
        return dummy.next;
    }
}
