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

/* using 0, 1, 2, to differentiate not visited, visiting, visited.*/

class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] visited = new int[n];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i=0; i<n; ++i) { 
            adjList.add(new ArrayList<Integer>()); 
        }
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        if (hasCycle(-1, 0, visited, adjList)) { 
            return false; 
        }  // has cycle
        for (int v: visited) { 
            if (v == 0) { 
                return false; 
            } 
        }  // not 1 single connected component
        return true;
    }
    
    private boolean hasCycle(int pred, int vertex, int[] visited, List<List<Integer>> adjList) {
        visited[vertex] = 1;  // current vertex is being visited
        for (Integer succ: adjList.get(vertex)) {  // successors of current vertex
            if (succ != pred) {  // exclude current vertex's predecessor
                if (visited[succ] == 1) { return true; }  // back edge/loop detected!
                else if (visited[succ] == 0) {
                    if (hasCycle(vertex, succ, visited, adjList)) { 
                        return true; 
                    }
                }
            }
        }
        visited[vertex] = 2;
        return false;
    }
}

/* BFS */

public class Solution {
    public boolean validTree(int n, int[][] edges) {
        int[] visited = new int[n];
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i=0; i<n; ++i) { adjList.add(new ArrayList<Integer>()); }
        for (int[] edge: edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(0); visited[0] = 1;  // vertex 0 is in the queue, being visited
        while (!q.isEmpty()) {
            Integer cur = q.removeFirst();
            for (Integer succ: adjList.get(cur)) {
                if (visited[succ] == 1) { return false; }  // loop detected
                if (visited[succ] == 0) { q.addLast(succ); visited[succ] = 1; }
            }
            visited[cur] = 2;  // visit completed
        }
        for (int v: visited) { if (v == 0) { return false; } }  // # of connected components is not 1
        return true;
    }
}

