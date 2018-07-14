/*
/*
Given a robot cleaner in a room modeled as a grid.

Each cell in the grid can be empty or blocked.

The robot cleaner with 4 given APIs can move forward, turn left or turn right. Each turn it made is 90 degrees.

When it tries to move into a blocked cell, its bumper sensor detects the obstacle and it stays on the current cell.

Design an algorithm to clean the entire room using only the 4 given APIs shown below.

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean move();

  // Robot will stay on the same cell after calling turnLeft/turnRight.
  // Each turn will be 90 degrees.
  void turnLeft();
  void turnRight();

  // Clean the current cell.
  void clean();
}
Example:

Input:
room = [
  [1,1,1,1,1,0,1,1],
  [1,1,1,1,1,0,1,1],
  [1,0,1,1,1,1,1,1],
  [0,0,0,1,0,0,0,0],
  [1,1,1,1,1,1,1,1]
],
row = 1,
col = 3

Explanation:
All grids in the room are marked by either 0 or 1.
0 means the cell is blocked, while 1 means the cell is accessible.
The robot initially starts at the position of row=1, col=3.
From the top left corner, its position is one row below and three columns right.
*/
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */
class Solution {
    public void cleanRoom(Robot robot) {
        // A number can be added to each visited cell
        // use string to identify the class
        Set<String> set = new HashSet<>();
        int cur_dir = 0;   // 0: up, 90: right, 180: down, 270: left
        backtrack(robot, set, 0, 0, 0);
    }
    
     public void backtrack(Robot robot, Set<String> set, int i, 
    			int j, int cur_dir) {
         String temp = i + "->" + j;
         if (set.contains(temp)) {
             return;
         }
         robot.clean();
         set.add(temp);
         
         for (int k = 0; k < 4; k++) {
             if (robot.move()) {
                 int x = i;
                 int y = j;
                 switch(cur_dir) {
                     case 0:
                         x = i - 1;
                         break;
    				case 90:
    					// go right
    					y = j+1;
    					break;
    				case 180:
    					// go down
    					x = i+1;
    					break;
    				case 270:
    					// go left
    					y = j-1;
    					break;
    				default:
    					break;
                 }
                 backtrack(robot, set, x, y, cur_dir);
                 robot.turnRight();
                 robot.turnRight();
                 robot.move();
                 robot.turnLeft();
                 robot.turnLeft();
             }
             
             robot.turnRight();
             cur_dir += 90;
             cur_dir %= 360;
         }
     }
}
