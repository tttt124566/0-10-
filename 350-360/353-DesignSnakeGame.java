/*

Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

Example:

Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);

Initially the snake appears at position (0,0) and the food at (1,2).

|S| | |
| | |F|

snake.move("R"); -> Returns 0

| |S| |
| | |F|

snake.move("D"); -> Returns 0

| | | |
| |S|F|

snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

| |F| |
| |S|S|

snake.move("U"); -> Returns 1

| |F|S|
| | |S|

snake.move("L"); -> Returns 2 (Snake eats the second food)

| |S|S|
| | |S|

snake.move("U"); -> Returns -1 (Game over because snake collides with border)

*/

class SnakeGame {

    /** Initialize your data structure here.
        @param width - screen width
        @param height - screen height 
        @param food - A list of food positions
        E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
    
    Deque<int[]> queue = new ArrayDeque<int[]>();
    Set<Integer> set = new HashSet<Integer>();
    int[][] food;
    int width;
    int height;
    int len;
    int score;
    public SnakeGame(int width, int height, int[][] food) {
        this.food = food;
        this.width = width;
        this.height = height;
        queue.offer(new int[]{0, 0});
        score = 0;
        set.add(0);
        len = 0;
    }
    
    /** Moves the snake.
        @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down 
        @return The game's score after the move. Return -1 if game over. 
        Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        if (score == -1) {
            return -1;
        }
        
        int row = queue.peekFirst()[0];
        int col = queue.peekFirst()[1];

        switch(direction) {
            case "U":
                row--;
                break;
            case "R":
                col++;
                break;
            case "D":
                row++;
                break;
            case "L":
                col--;
                break;
        }
        
        int[] last = queue.pollLast();
        set.remove(last[0] * width + last[1]);
        if (row < 0 || col < 0 || row >= height || col >= width || set.contains(row * width + col)) {
            return score = -1;
        }
        
        queue.offerFirst(new int[]{row, col});
        set.add(row * width + col);
        
        if (len < food.length && row == food[len][0] && col == food[len][1]) {
            queue.offerLast(last);
            set.add(last[0] * width + last[1]);
            len++;
            return ++score;
        }
        
        return score;
    }
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */
