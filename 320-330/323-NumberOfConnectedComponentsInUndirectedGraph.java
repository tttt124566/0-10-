/*
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.

Example 1:

Input: n = 5 and edges = [[0, 1], [1, 2], [3, 4]]

     0          3
     |          |
     1 --- 2    4 

Output: 2
Example 2:

Input: n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]]

     0           4
     |           |
     1 --- 2 --- 3

Output:  1
Note:
You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
*/

class UF {
    private int[] root = null;
    private int n;
    
    UF(int n) {
        this.n = n;
        root = new int[n];
        for (int i = 0; i < n; i++) {
            root[i] = i;
        }
    }
    
    public int find(int i) {
        while (i != root[i]) {
            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }
    
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        root[qRoot] = pRoot;
    }
    
    public int getCount() {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (root[i] == i) {
                count++;
            }
        }
        return count;
    }
}

public class Solution {
    
    public int countComponents(int n, int[][] edges) {
        UF uf = new UF(n);
        for (int[] edge: edges) {
            uf.union(edge[0], edge[1]);
        }
        return uf.getCount();
    }
}
