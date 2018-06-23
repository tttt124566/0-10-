/*
Given a sorted array, two integers k and x, find the k closest elements to x in the array. The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.

Example 1:
Input: [1,2,3,4,5], k=4, x=3
Output: [1,2,3,4]
Example 2:
Input: [1,2,3,4,5], k=4, x=-1
Output: [1,2,3,4]
Note:
The value k is positive and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 104
Absolute value of elements in the array and x will not exceed 104
UPDATE (2017/9/19):
The arr parameter had been changed to an array of integers (instead of a list of integers). Please reload the code definition to get the latest changes.
*/
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<Integer>();
        int index = getIndex(arr, x);
        int i = index;
        int j = index + 1;
        while ((i >= 0 || j <= arr.length - 1) && k > 0) {
            if (i < 0) {
                list.add(arr[j]);
                j++;
            } else if (j > arr.length - 1) {
                list.add(arr[i]);
                i--;
            } else if (Math.abs(arr[i] - x) <= Math.abs(arr[j] - x)) {
                list.add(arr[i]);
                i--;
            } else {
                list.add(arr[j]);
                j++;
            }
            k--;
        }
        Collections.sort(list);
        return list;
    }
    
    private int getIndex(int[] arr, int x) {
        int start = 0;
        int end = arr.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return start;
    }
}
