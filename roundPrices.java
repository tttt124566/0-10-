/*
公司 list 价格分成好几个部分，但是都是整数，如果在美金是整数，到了欧洲的网页显示汇率转 换之后就变成了 floating point，然后要 round 成整数，但是全部加起来 round，和单独 round 再加 起来，结果会不一样
# base price 100 => 131.13 => 131
# cleaning fee 20 => 26.23 => 26
 # service fee
# tax #
# sum
那么问题就来了，给个 input list of floating points, 要求 output list of integers, 满足以下两个 constraint， 就是和跟 Round(x1+x2+... +xn)的结果一样，但是 minimize output 和 input 的绝对值差
10 => 13.54 => 14 5 => 6.5 => 7
=> 177.4E => 178E 135$ => 178.93E => 179E
之和
#Input: A = [x1, x2, ..., xn]
# Sum T = Round(x1+x2+... +xn) ; 178.93E => 179-google 1point3acres # Output: B = [y1, y2, ...., yn]
# Constraint #1: y1+y2+...+yn = T
# Constraint #2: minimize sum(abs(diff(xi - yi)))
举例
# A = [1.2, 2.3, 3.4]
# Round(1.2 + 2.3 + 3.4) = 6.9 => 7 # 1 + 2 + 3 => 6
# 0.2 + 0.3 + 0.4 = 1.0
# 1 + 3 + 3 => 7
# 0.2 + 0.7 + 0.4 = 1.3
# 1 + 2 + 4 => 7
# 0.2 + 0.3 + 0.6 = 1.1 所以[1,2,4]比[1,3,3]要好
先将所有 floor(x)加起来统计出如果所有都 floor 的话还差多少，按照 ceil 以后需要加的价格排 序，贪心取最小的补齐即可。
做法是这样:比如[1.2, 2.5, 3.6, 4.0]
建个新数列是原来数字的 int, arr = [1, 2, 3, 4]
然后算需要补多少个数字才能到需要的 sum. round(1.2+2.5+3.6+4.0) - (1+2+3+4) = 1， 补 1 个数字 就好
现在把原数组和 arr 的差值排列一下。 差值是[0.2, 0.5, 0.6, 0]. 我们要从差值最大的数补起，我把 index 排列了一下就是[2, 1, 0, 3]，按这个顺序补数字就好。
因为我们只需要补一个数字，先补 index = 2 的, 所以最后的就结果是 [1, 2, 4]

*/


import java.util.Comparator;
import java.util.PriorityQueue;

class NumWithDiff {
    private int index;
    private double diff;
    private int num;

    NumWithDiff(int index, int num, double diff) {
        this.index = index;
        this.diff = diff;
        this.num = num;
    }

    public int getIndex() {
        return index;
    }

    public double getDiff() {
        return diff;
    }

    public int getNum() {
        return num;
    }
}


public class RoundPrices {

    public int[] roundPrices(double[] arr) {
        PriorityQueue<NumWithDiff> pq = new PriorityQueue<NumWithDiff>(
                new Comparator<NumWithDiff>() {
                    @Override
                    public int compare(NumWithDiff o1, NumWithDiff o2) {
                        if (o1.getDiff() <= o2.getDiff()) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                }
        );

        int curSum = 0;
        double doubleCurSum = 0;
        for (int i = 0; i < arr.length; i++) {
            int floor = (int) arr[i];
            curSum += floor;
            doubleCurSum += arr[i];
            double diff = arr[i] - floor;
            pq.add(new NumWithDiff(i, floor, diff));
        }

        int theDiff = (int)Math.round(doubleCurSum) - curSum;
        int[] res = new int[arr.length];
        while (pq.size() > 0) {
            NumWithDiff first = pq.poll();
            if (theDiff > 0) {
                res[first.getIndex()] = first.getNum() + 1;
                theDiff--;
            } else {
                res[first.getIndex()] = first.getNum();
            }
        }
        return res;
    }

    public static void main(String[] args) {
        RoundPrices rp = new RoundPrices();
        double[] arr = {1.2, 2.3, 3.4};
        int[] res = rp.roundPrices(arr);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i]);
            System.out.print(",");
        }
        System.out.println();
    }
}
