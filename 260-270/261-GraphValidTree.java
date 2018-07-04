/*
Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

Example 1:

Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
Output: true
Example 2:

Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
Output: false
Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            map.put(i, new HashSet<Integer>());
        }
        for (int[] edge: edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        boolean[] visited = new boolean[n];
        if (hasCycle(visited, map, 0, 0)) {
            return false;
        }
        
        for (int i = 0; i < visited.length; i++) {
            if (visited[i] == false) {
                return false;
            }
        }
        return true;
    }
    
    private boolean hasCycle(boolean[] visited, Map<Integer, Set<Integer>> map, int i, int parent) {
        visited[i] = true;
        for (int next : map.get(i)) {
            if (visited[next] && parent != next || !visited[next] && hasCycle(visited, map, next, i)) {
                return true;
            }
        }
        return false;
    }
}
// union find
class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i;
        }
        
        for (int[] edge: edges) {
            int iroot = find(nums, edge[0]);
            int jroot = find(nums, edge[1]);
            if (iroot == jroot) {
                return false;
            }
            nums[iroot] = jroot;
        }
        return edges.length == n - 1;
    }
    
    private int find(int[] nums, int i) {
        while (nums[i] != i) {
            nums[i] = nums[nums[i]];
            i = nums[i];
        }
        return i;
    }
}
