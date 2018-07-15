/*

*/

import java.util.*;

public class TravelBuddy {

    private Set<String> myWishList;
    private List<Buddy> buddyList;

    private int getSimilarity (Set<String> wishListB) {
        Set<String> intersection = new HashSet<>(myWishList);
        intersection.retainAll(wishListB);
        return intersection.size();
    }

    public TravelBuddy(Set<String> myWishList, Map<String, Set<String>> friendsWishListMap) {
        this.myWishList = myWishList;
        this.buddyList = new ArrayList<>();
        for (Map.Entry<String, Set<String>> entry : friendsWishListMap.entrySet()) {
            int similarity = getSimilarity(entry.getValue());
            if (similarity >= entry.getValue().size() / 2) {
                buddyList.add(new Buddy(entry.getKey(), getSimilarity(entry.getValue()), entry.getValue()));
            }
        }
    }

    public List<String> recommendCities(int k) {
        Collections.sort(buddyList);
        List<String> res = new ArrayList<>();
        for (Buddy buddy : buddyList) {
            Set<String> diff = new HashSet<>(buddy.wishList);
            diff.removeAll(myWishList);
            if (diff.size() <= k) {
                res.addAll(diff);
                k -= diff.size();
            } else {
                for (String rec : diff) {
                    res.add(rec);
                    k--;
                    if (k == 0) {
                        break;
                    }
                }
            }
            if (k == 0) {
                break;
            }
        }
        return res;
    }

    class Buddy implements Comparable<Buddy> {
        int similarity;
        Set<String> wishList;
        String name;

        Buddy(String name, int similarity, Set<String> wishList) {
            this.name = name;
            this.similarity = similarity;
            this.wishList = wishList;
        }

        @Override
        public int compareTo(Buddy o) {
            return o.similarity - similarity;
        }
    }


    public static void main(String[] args) {
        Set<String> myWishList = new HashSet<>(){{
            add("a");
            add("b");
            add("c");
            add("d");

        }};

        Set<String> buddyListA = new HashSet<>() {{
            
        }}
        TravelBuddy tb = new TravelBuddy()
    }
}
