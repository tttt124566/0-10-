import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MinimumVerticesTraverseGraph {
    public List<Integer> getMinimumVertices(int[][] edges, int n) {
        List<Integer> result = new ArrayList<>();
        List<List<Integer>> nodes = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            nodes.add(new ArrayList<>());
            for (int j = 0; j < edges[i].length; j++) {
                if (edges[i][j] == 1) {
                    nodes.get(i).add(j);
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        for (int i = 0; i < n; i++) {
            if (!visited.contains(i)) {
                res.add(i);
                search(nodes, i, i, visited, new HashSet<>(), res);
            }
        }
        for (int num : res) {
            result.add(num);
        }
        return result;
    }

    private void search(List<List<Integer>> nodes, int curr, int start, Set<Integer> visited, Set<Integer> currVisited, Set<Integer> res) {
        visited.add(curr);
        currVisited.add(curr);
        for (int next : nodes.get(curr)) {
            if (res.contains(next) && next != start) {
                res.remove(start);
            }
            if (!currVisited.contains(next)) {
                search(nodes, next, start, visited, currVisited, res);
            }
        }
    }


    public static void main(String[] args) {
        MinimumVerticesTraverseGraph mtg = new MinimumVerticesTraverseGraph();
    }
}
