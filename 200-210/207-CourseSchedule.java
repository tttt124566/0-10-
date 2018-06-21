/*

There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

Example 1:

Input: 2, [[1,0]] 
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: 2, [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

*/

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            map.put(prerequisites[i][1], new HashSet<Integer>());
        }
        for (int i = 0; i < prerequisites.length; i++) {
            map.get(prerequisites[i][1]).add(prerequisites[i][0]);
        }
        
        boolean[] visited = new boolean[numCourses];
        boolean[] onPath = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (canFinishHelper(map, visited, onPath, i)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean canFinishHelper(Map<Integer, Set<Integer>> map, boolean[] visited, boolean[] onPath, int i) {
        if (visited[i]) {
            return false;
        }
        visited[i] = true;
        onPath[i] = true;
        if (map.containsKey(i)) {
            for (Integer j : map.get(i)) {
                if (onPath[j] == true || canFinishHelper(map, visited, onPath, j)) {
                    return true;
                }
            }            
        }
        onPath[i] = false;
        return false;
    }
}

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Integer, Integer> degrees = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            degrees.put(i, 0);
            map.put(i, new HashSet<Integer>());
        }
        
        for (int[] prerequisite : prerequisites) {
            map.get(prerequisite[1]).add(prerequisite[0]);
            degrees.put(prerequisite[0], degrees.get(prerequisite[0]) + 1);
        }
        
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (degrees.get(i) == 0) {
                queue.offer(i);
            }
        }
        int count = 0;
        
        while (!queue.isEmpty()) {
            int r = queue.poll();
            count++;
            for (int next : map.get(r)) {
                degrees.put(next, degrees.get(next) - 1);
                if (degrees.get(next) == 0) {
                    queue.offer(next);
                }
            }
        }
        
        return count == numCourses;
    }
    
}
