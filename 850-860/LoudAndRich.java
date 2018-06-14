class Solution {
    int[] res;
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        res = new int[quiet.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = -1;
        }
        for (int i = 0; i < richer.length; i++) {
            if (!map.containsKey(richer[i][1])) {
                map.put(richer[i][1], new ArrayList<Integer>());
            }
            map.get(richer[i][1]).add(richer[i][0]);
        }
        for (int i = 0; i < res.length; i++) {
            loudAndRichHelper(map, i, quiet);
        }
        return res;
    }
    
    private int loudAndRichHelper(HashMap<Integer, List<Integer>> map, int i, int[] quiet) {
        if (res[i] != -1) {
            return res[i];
        }
        res[i] = i;
        List<Integer> list = map.get(i);
        int noise = quiet[res[i]];
        if (list != null) {
            for (int j = 0; j < list.size(); j++) {
                int n = loudAndRichHelper(map, list.get(j), quiet);
                if (quiet[n] < noise) {
                    noise = quiet[n];
                    res[i] = n;
                }
            }    
        }
        return res[i];
    }
}
