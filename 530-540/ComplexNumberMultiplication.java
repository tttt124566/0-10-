class Solution {
    public String complexNumberMultiply(String a, String b) {
        String[] anums = a.split("\\+|i");
        String[] bnums = b.split("\\+|i");
        int first = Integer.valueOf(anums[0]) * Integer.valueOf(bnums[0]) - Integer.valueOf(anums[1]) * Integer.valueOf(bnums[1]);
        int second = Integer.valueOf(anums[0]) * Integer.valueOf(bnums[1]) + 
            Integer.valueOf(anums[1]) * Integer.valueOf(bnums[0]);
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(first));
        sb.append("+");
        sb.append(String.valueOf(second));
        sb.append("i");
        return sb.toString();
    }
}
