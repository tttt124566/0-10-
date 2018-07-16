/*
Equations are given in the format A / B = k, where A and B are variables represented as strings, and k is a real number (floating point number). Given some queries, return the answers. If the answer does not exist, return -1.0.

Example:
Given a / b = 2.0, b / c = 3.0. 
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? . 
return [6.0, 0.5, -1.0, 1.0, -1.0 ].

The input is: vector<pair<string, string>> equations, vector<double>& values, vector<pair<string, string>> queries , where equations.size() == values.size(), and the values are positive. This represents the equations. Return vector<double>.

According to the example above:

equations = [ ["a", "b"], ["b", "c"] ],
values = [2.0, 3.0],
queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ]. 
The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.


*/

class Solution {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        HashMap<String, ArrayList<String>> pairs = new HashMap<>();
        HashMap<String, ArrayList<Double>> valuePairs = new HashMap<>();
        for (int i = 0; i < equations.length; i++) {
            pairs.computeIfAbsent(equations[i][0], k -> new ArrayList<String>()).add(equations[i][1]);
            pairs.computeIfAbsent(equations[i][1], k -> new ArrayList<String>()).add(equations[i][0]);
            valuePairs.computeIfAbsent(equations[i][0], k -> new ArrayList<Double>()).add(values[i]);
            valuePairs.computeIfAbsent(equations[i][1], k -> new ArrayList<Double>()).add(1 / values[i]);
        }
        
        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String[] query = queries[i];
            result[i] = dfs(query[0], query[1], pairs, valuePairs, new HashSet<String>(), 1.0);
            if (result[i] == 0.0) result[i] = -1.0;
        }
        return result;
    }
    
    private double dfs(String start, String end, HashMap<String, ArrayList<String>> pairs, HashMap<String, ArrayList<Double>> values, HashSet<String> set, double value) {
        if (set.contains(start)) return 0.0;
        if (!pairs.containsKey(start)) return 0.0;
        if (start.equals(end)) return value;
        set.add(start);
        
        ArrayList<String> strList = pairs.get(start);
        ArrayList<Double> valueList = values.get(start);
        double tmp = 0.0;
        for (int i = 0; i < strList.size(); i++) {
            tmp = dfs(strList.get(i), end, pairs, values, set, value*valueList.get(i));
            if (tmp != 0.0) {
                break;
            }
        }
        set.remove(start);
        return tmp;
    }
}


class Solution {
    double y=0;
	public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
    
		HashMap<String,HashMap<String,Double>> graph=new HashMap<>();
		for(int i=0;i<equations.length;i++)
		{
			if(!graph.containsKey(equations[i][0]))
				graph.put(equations[i][0],new HashMap<String,Double>());
			if(!graph.containsKey(equations[i][1]))
				graph.put(equations[i][1],new HashMap<String,Double>());
			graph.get(equations[i][0]).put(equations[i][1],values[i]);
			graph.get(equations[i][1]).put(equations[i][0],1/values[i]);
		}
		
		double[] ans=new double[queries.length];
		for(int i=0;i<queries.length;i++)
		{
			if(!graph.containsKey(queries[i][0])||!graph.containsKey(queries[i][1]))
			{	ans[i]=-1;
				continue;
			}
			if(queries[i][0].equals(queries[i][1]))
			{
				ans[i]=1;
				continue;
			}
			
			if(dfs(graph,queries[i][0],queries[i][1],new HashSet<String>(),1))
			{	
				ans[i]=y;
				y=0;
			}
            else
				ans[i]=-1;
		}
		return ans;
    }
	
	boolean dfs(HashMap<String,HashMap<String,Double>> graph,String start,String end,HashSet<String>set,double ans)
	{
		boolean flag=false;
		if(start.equals(end))
		{	flag=true;
			y=ans;
			return flag;
		}
		set.add(start);
		HashMap<String,Double> map=graph.get(start);
		for(String x:map.keySet())
		{
			if(!set.contains(x))
			{
				flag=dfs(graph,x,end,set,ans*map.get(x));
				if(flag)
					return flag;
			}
		}
		return flag;
	}
}
