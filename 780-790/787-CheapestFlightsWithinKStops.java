/*
There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w.

Now given all the cities and fights, together with starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.

Example 1:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
Output: 200
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
Example 2:
Input: 
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
Output: 500
Explanation: 
The graph looks like this:


The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
Note:

The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
The size of flights will be in range [0, n * (n - 1) / 2].
The format of each flight will be (src, dst, price).
The price of each flight will be in the range [1, 10000].
k is in the range of [0, n - 1].
There will not be any duplicated flights or self cycles.
*/

class Solution {
    private class City{
        int id;
        int costFromSrc;
        int stopFromSrc;
        public City(int id, int costFromSrc, int stopFromSrc){
            this.id = id;
            this.costFromSrc = costFromSrc;
            this.stopFromSrc = stopFromSrc;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] srcToDst = new int[n][n];
        for(int i = 0; i < flights.length; i++)
            srcToDst[flights[i][0]][flights[i][1]] = flights[i][2];

        PriorityQueue<City> minHeap = new PriorityQueue<>((a, b) -> {
            if (a.costFromSrc == b.costFromSrc) {
                return a.stopFromSrc - b.stopFromSrc;
            } else {
                return a.costFromSrc - b.costFromSrc;
            }
        });

        minHeap.offer(new City(src,0,0));

        int[] cost = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost[src] = 0;
        int[] stop = new int[n];
        Arrays.fill(stop, Integer.MAX_VALUE);
        stop[src] = 0;

        // for a city, the parameters are combination of stop and cost.
        
        while(!minHeap.isEmpty()){
            City curCity = minHeap.poll();
            if(curCity.id == dst) return curCity.costFromSrc;
            if(curCity.stopFromSrc == K + 1) continue;
            int[] nexts = srcToDst[curCity.id];
            for(int i = 0; i < n; i++){
                if(nexts[i] != 0){
                    int newCost = curCity.costFromSrc + nexts[i];
                    int newStop = curCity.stopFromSrc + 1;
                    if(newCost < cost[i]){
                        minHeap.offer(new City(i, newCost, newStop)); // everytime we need to offer a new city 
                        cost[i] = newCost;
                    }
                    else if(newStop < stop[i]){
                        minHeap.offer(new City(i, newCost, newStop));
                        stop[i] = newStop;
                    }
                }
            }
        }

        return cost[dst] == Integer.MAX_VALUE? -1:cost[dst];
    }

}
