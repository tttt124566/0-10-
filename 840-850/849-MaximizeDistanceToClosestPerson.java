class Solution {
    public int maxDistToClosest(int[] seats) {
        int oneIndex = -1;
        int maxDistance = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                if (oneIndex == -1) {
                    maxDistance = Math.max(maxDistance, i);
                } else {
                    maxDistance = Math.max(maxDistance, (i - oneIndex) / 2);
                }
                oneIndex = i;
            }
        }
        return Math.max(maxDistance, seats.length - 1 - oneIndex);
    }
}
