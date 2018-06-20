/*
An undirected, connected tree with N nodes labelled 0...N-1 and N-1 edges are given.

The ith edge connects nodes edges[i][0] and edges[i][1] together.

Return a list ans, where ans[i] is the sum of the distances between node i and all other nodes.

Example 1:

Input: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: 
Here is a diagram of the given tree:
  0
 / \
1   2
   /|\
  3 4 5
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.  Hence, answer[0] = 8, and so on.
Note: 1 <= N <= 10000
*/

    // res[i] the sum distance of subtree i
    // count[i] the count of nodes in substree i
    // first dfs: count[root] += count[i];
    // res[root] = sum(res[i]) + sum(count[i]);
    // from the above, we can calculate out the sum distance from each substree and the node counts of each subtree.
    // Then second dfs: to update the sum distance from the current node to all the other nodes.
    // res[i] = res[root] - count[i] + N - count[i];
class Solution {
    List<HashSet<Integer>> trees;
    int[] res;
    int[] count;
    int n;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        trees = new ArrayList<HashSet<Integer>>();
        for (int i = 0; i < N; i++) {
            trees.add(new HashSet<Integer>());
        }
        for (int[] edge: edges) {
            trees.get(edge[0]).add(edge[1]);
            trees.get(edge[1]).add(edge[0]);
        }
        res = new int[N];
        count = new int[N];
        n = N;
        dfs1(0, new HashSet<Integer>());
        dfs2(0, new HashSet<Integer>());
        return res;
    }
    
    private void dfs1(int root, HashSet<Integer> seen) {
        seen.add(root);
        for (int i : trees.get(root)) {
            if (!seen.contains(i)) {
                dfs1(i, seen);
                count[root] += count[i];
                res[root] += count[i] + res[i];
            }
        }
        count[root] += 1;
    }
    
    private void dfs2(int root, HashSet<Integer> seen) {
        seen.add(root);
        for (int i : trees.get(root)) {
            if (!seen.contains(i)) {
                res[i] = res[root] - count[i] + n - count[i];
                dfs2(i, seen);
            }
        }
    }
}
