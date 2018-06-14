class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        Set<String> set = new HashSet<String>();
        for (int i = 0; i < banned.length; i++) {
            set.add(banned[i]);
        }
        // remember to replace all the punctuations with ""
        String[] words =  paragraph.replaceAll("\\pP" , "").toLowerCase().split("\\s+");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (!set.contains(word)) {
                map.putIfAbsent(word, 0);
                map.put(word, map.get(word) + 1);
            }
        }
        int maxFrequency = 0;
        String res = null;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                maxFrequency = entry.getValue();
                res = entry.getKey();
            }
        }
        return res;
    }
}
