/*
There are a total of n courses you have to take, labeled from 0 to n-1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: 2, [[1,0]] 
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished   
             course 0. So the correct course order is [0,1] .
Example 2:

Input: 4, [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both     
             courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
             So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
Note:

The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
*/

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        
        for (int[] prerequisite : prerequisites) {
            map.putIfAbsent(prerequisite[1], new HashSet<Integer>());
            map.get(prerequisite[1]).add(prerequisite[0]);
        }
        
        boolean[] onPath = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        
        List<Integer> res = new ArrayList<Integer>();
        
        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                if (findOrderHelper(map, onPath, visited, i, res)) {
                    return new int[0];
                }
            }
        }
        int[] s = new int[numCourses];
        
        for (int i = 0; i < res.size(); i++) {
            s[numCourses - 1 - i] = res.get(i);
        }
        
        return s;
    }
    
    private boolean findOrderHelper(Map<Integer, Set<Integer>> map, boolean[] onPath, boolean[] visited, int i, List<Integer> res) {
        if (onPath[i]) {
            return true;
        }
        if (visited[i]) {
            return false;
        }
        onPath[i] = true;
        visited[i] = true;
        if (map.containsKey(i)) {
            for (int next: map.get(i)) {
                if (findOrderHelper(map, onPath, visited, next, res)) {
                    return true;
                }
            }
        }
        
        onPath[i] = false;
        res.add(i);
        return false;
    }
}
