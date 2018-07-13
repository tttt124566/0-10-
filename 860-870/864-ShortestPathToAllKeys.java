/*
We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.

We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.

For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks were chosen in the same order as the English alphabet.

Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.

 

Example 1:

Input: ["@.a.#","###.#","b.A.B"]
Output: 8
Example 2:

Input: ["@..aA","..B#.","....b"]
Output: 6
*/

class Solution {
    class State {
        int i;
        int j;
        int keys;
        
        State(int i, int j, int keys) {
            this.i = i;
            this.j = j;
            this.keys = keys;
        }
    }
    
    
    public int shortestPathAllKeys(String[] grid) {
        Queue<State> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length(); j++) {
                if (grid[i].charAt(j) == '@') {
                    queue.offer(new State(i, j, 0));
                    visited.add(0 + "," + i + "," + j);
                } else if (grid[i].charAt(j) >= 'a' && grid[i].charAt(j) <= 'f') {
                    max = Math.max(grid[i].charAt(j) - 'a' + 1 , max);
                }
            }
        }
        int step = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                State curr = queue.poll();
                if (curr.keys == (1 << max) - 1) {
                    return step;
                }
                
                for (int[] dir : dirs) {
                    int x = dir[0] + curr.i;
                    int y = dir[1] + curr.j;
                    if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length()) {
                        continue;
                    }
                    char c = grid[x].charAt(y);
                    if (c == '#') {
                        continue;
                    }
                    int keys = curr.keys;
                    if (c >= 'a' && c <= 'f') {
                        keys |= (1 << c - 'a');
                    }
                    if (c >= 'A' && c <= 'F' && ((keys >> (c - 'A')) & 1) == 0) {
                        continue;
                    }
                    if (!visited.contains(keys + "," + x + "," + y)) {
                        visited.add(keys + "," + x + "," + y);
                        queue.add(new State(x, y, keys));
                    }
                }
            }
            step++;
        }
        return -1;
    }
}
