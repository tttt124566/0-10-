/*
In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node, plus every node has exactly one parent, except for the root node which has no parents.

The given input is a directed graph that started as a rooted tree with N nodes (with distinct values 1, 2, ..., N), with one additional directed edge added. The added edge has two different vertices chosen from 1 to N, and was not an edge that already existed.

The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] that represents a directed edge connecting nodes u and v, where u is a parent of child v.

Return an edge that can be removed so that the resulting graph is a rooted tree of N nodes. If there are multiple answers, return the answer that occurs last in the given 2D-array.

Example 1:
Input: [[1,2], [1,3], [2,3]]
Output: [2,3]
Explanation: The given directed graph will be like this:
  1
 / \
v   v
2-->3
Example 2:
Input: [[1,2], [2,3], [3,4], [4,1], [1,5]]
Output: [4,1]
Explanation: The given directed graph will be like this:
5 <- 1 -> 2
     ^    |
     |    v
     4 <- 3
Note:
The size of the input 2D-array will be between 3 and 1000.
Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.
*/

class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] candidate1 = {-1, -1};
        int[] candidate2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int[] edge : edges) {
            if (parent[edge[1]] == 0) {
                parent[edge[1]] = edge[0];
            } else {
                candidate2 = new int[] { edge[0], edge[1] };
                candidate1 = new int[] {parent[edge[1]], edge[1]};
                edge[1] = 0;
            }
        }
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int[] edge: edges) {
            if (edge[1] == 0) {
                continue;
            }
            int father = edge[0];
            int child = edge[1];
            if (find(parent, father) == child) {
                if (candidate1[0] == -1) {
                    return edge;
                }
                return candidate1;
            }
            parent[child] = father;
        }
        return candidate2;
    }
    
    private int find(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        } 
        return i;
    }
}
