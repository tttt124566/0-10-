/*
138. Copy List with Random Pointer
DescriptionHintsSubmissionsDiscussSolution
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.
*/

/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    Map<RandomListNode, RandomListNode> nodeMap = new HashMap<>();
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode p1 = dummy;
        RandomListNode p = head;
        while (p != null) {
            RandomListNode node = new RandomListNode(p.label);
            nodeMap.put(p, node);
            p1.next = node;
            p = p.next;
            p1 = p1.next;
        }
        
        p = head;
        while (p != null) {
            nodeMap.get(p).random = nodeMap.get(p.random);
            p = p.next;
        }
        
        return dummy.next;
    }
}
