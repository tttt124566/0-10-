/*
You have k lists of sorted integers in ascending order. Find the smallest range that includes at least one number from each of the k lists.

We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.

Example 1:
Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
Output: [20,24]
Explanation: 
List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
List 2: [0, 9, 12, 20], 20 is in range [20,24].
List 3: [5, 18, 22, 30], 22 is in range [20,24].
Note:
The given list may contain duplicates, so ascending order means >= here.
1 <= k <= 3500
-105 <= value of elements <= 105.
For Java users, please note that the input type has been changed to List<List<Integer>>. And after you reset the code template, you'll see this point.
*/

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        
        int max = Integer.MIN_VALUE; 
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            pq.add(new Node(nums.get(i).get(0), i, 0));
            max = Math.max(max, nums.get(i).get(0));
        }
        int range = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        while (pq.size() == nums.size()) {
            Node curr = pq.poll();
            if (max - curr.val < range) {
				range = max - curr.val;
				start = curr.val;
				end = max;
			}
            if (curr.index + 1 < nums.get(curr.row).size()) {
				curr.index = curr.index + 1;
				curr.val = nums.get(curr.row).get(curr.index);
				pq.offer(curr);
				if (curr.val > max) {
					max = curr.val;
				}
			}
        }
        
        return new int[] {start, end};
    }
    
    private class Node {
        public int val;
        public int index;
        public int row;
        Node(int val, int row, int index) {
            this.val = val;
            this.index = index;
            this.row = row;
        }
    }
}
