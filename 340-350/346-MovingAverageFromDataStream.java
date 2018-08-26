/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Example:

MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

class MovingAverage {

    private int[] window;
    private long sum;
    private int inserted;
    private int size;
    private int n;
    
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        window = new int[size];
        sum = 0;
        inserted = 0;
        n = 0;
        this.size = size;
    }
    
    public double next(int val) {
        if (n < size) {
            n++;
        }
        sum -= window[inserted];
        sum += val;
        window[inserted] = val;
        inserted = (inserted + 1 ) % size;
        return (double) sum / n;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
