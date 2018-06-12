class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode newNode = new ListNode(-1);
        int carry = 0;
        int sum = 0;
        ListNode p = newNode;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Value = 0;
            int l2Value = 0;
            if (l1 != null) {
                l1Value = l1.val;
            }
            if (l2 != null) {
                l2Value = l2.val;
            }
            sum = l1Value + l2Value + carry;
            p.next = new ListNode(sum % 10);
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
            p = p.next;
        }
        return newNode.next;
    }
}
