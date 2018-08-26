/*Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
Example 1:

Input: tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:

Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
*/

class Solution {
    Map<String, PriorityQueue<String>> map;
    List<String> route;
    public List<String> findItinerary(String[][] tickets) {
        map = new HashMap<>();
        route = new LinkedList<>();
        for (String[] ticket: tickets) {
            map.computeIfAbsent(ticket[0], k -> new PriorityQueue<String>()).add(ticket[1]);
        }
        visit("JFK");
        return route;
    }
    
    private void visit(String airport) {
        while (map.containsKey(airport) && map.get(airport).size() != 0) {
            visit(map.get(airport).poll());
        }
        route.add(0, airport);
    }
}

欧拉路径解析：
https://www.jianshu.com/p/b5949ed7082b
