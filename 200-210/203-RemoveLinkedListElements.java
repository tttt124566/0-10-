/*
Remove all elements from a linked list of integers that have value val.

Example:

Input:  1->2->6->3->4->5->6, val = 6
Output: 1->2->3->4->5
Seen this question in a real interview before?  

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
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode former = dummyNode;
        ListNode now = head;
        while (now != null) {
            if (now.val == val) {
                former.next = now.next;
                now = former.next;
                continue;
            } else {
                former = now;
                now = now.next;
            }
        }
        
        return dummyNode.next;
    }
}
