Let's call any (contiguous) subarray B (of A) a mountain if the following properties hold:

B.length >= 3
There exists some 0 < i < B.length - 1 such that B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
(Note that B could be any subarray of A, including the entire array A.)

Given an array A of integers, return the length of the longest mountain. 

Return 0 if there is no mountain.

Example 1:

Input: [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: [2,2,2]
Output: 0
Explanation: There is no mountain.
Note:

0 <= A.length <= 10000
0 <= A[i] <= 10000
Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?


class Solution {
    public int longestMountain(int[] A) {
        int[] smallerFromLeft = new int[A.length];
        int[] smallerFromRight = new int[A.length];
        for (int i = 1; i < A.length; i++) {
            if (A[i] > A[i - 1]) {
                smallerFromLeft[i] = smallerFromLeft[i - 1] + 1;
            } else {
                smallerFromLeft[i] = 0;
            }
        } 
        
        for (int i = A.length - 2; i >= 0; i--) {
           if (A[i] > A[i + 1]) {
               smallerFromRight[i] = smallerFromRight[i + 1] + 1;
           } else {
               smallerFromRight[i] = 0;
           }
        } 
        
        int longestMountain = 0;
        for (int i = 0; i < A.length; i++) {
            if (smallerFromLeft[i] != 0 && smallerFromRight[i] != 0) {
                longestMountain = Math.max(longestMountain, smallerFromLeft[i] + smallerFromRight[i] + 1);
            }
        }
        return longestMountain;
    }
}

// To Solve in O(n) time and O(1) space
// Why? because mountains won't overlap. We can just iterate through the array to find the possible mountain at an index. Then 
// The we can go down to the lowest point. And the next time we start from the lowest point to find a second possible mountain.
class Solution {
    public int longestMountain(int[] A) {
        int max = 0;
        int i = 0;
        while(i < A.length) {
            while(i + 1 < A.length && A[i+1] == A[i]) i++; // ignore flat land
            int p = i; // start of mountain
            while(i + 1 < A.length && A[i+1] > A[i]) i++; // going up
            int q = i; // peak of mountain
            while(i + 1 < A.length && A[i+1] < A[i]) i++; // going down
            if(q > p && i > q) max = Math.max(max, i - p + 1);
            if(i == A.length - 1) break;
        }
        return max < 3 ? 0 : max;
    }
}
