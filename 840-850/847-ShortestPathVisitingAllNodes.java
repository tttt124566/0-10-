/*
An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.

graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.

Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.

 

Example 1:

Input: [[1,2,3],[0],[0],[0]]
Output: 4
Explanation: One possible path is [1,0,2,0,3]
Example 2:

Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
Output: 4
Explanation: One possible path is [0,1,4,2,3]
 

Note:

1 <= graph.length <= 12
0 <= graph[i].length < graph.length

*/

class Solution {
    
    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        
        Queue<Tuple> queue = new LinkedList<>();
        Set<String> set = new HashSet<>();

        for(int i = 0; i < N; i++){
            int tmp = (1 << i);
            set.add(tmp + "," + i);
            queue.add(new Tuple(tmp, i, 0));
        }
        
        while(!queue.isEmpty()){
            Tuple curr = queue.remove();
    
            if(curr.bitMask == (1 << N) - 1){
                return curr.cost;
            } else {
                int[] neighbors = graph[curr.curr];
                
                for(int v : neighbors){
                    int bitMask = curr.bitMask;
                    bitMask = bitMask | (1 << v);
                    
                    if(!set.contains(bitMask + "," + v)){
                        queue.add(new Tuple(bitMask, v, curr.cost + 1));
                        set.add(bitMask + "," + v);
                    }         
                }
            }
        }
        return -1;
    }
}

class Tuple{
    int bitMask;
    int curr;
    int cost;
    
    public Tuple(int bit, int n, int c){
        bitMask = bit;
        curr = n;
        cost = c;
    }
}
