/*

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);

*/

TODO: change to use ood.
class Twitter {

    class Tweet {
        public int timestamp;
        public int tweetId;
        public Tweet next;
        
        Tweet(int timestamp, int tweetId) {
            this.timestamp = timestamp;
            this.tweetId = tweetId;
            next = null;
        }
    }
    
    int time = 0;
//     Map<Integer, User> userMap;
    
//     class User {
//         int userId;
//         Tweet tweetHead;
//         Set<Integer> followees;
//         User(int userId) {
//             this.userId = userId;
//             tweetHead = null;
//         }
        
//         public void post(Tweet tweet) {
//             Tweet newhead = new Tweet(time++, tweetId);
//             newhead.next = tweetHead;
//             tweetHead = newHead;
//         }
        
//         public void follow(int userId) {
//             followees.add(userId);
//         }
        
//         public void unfollow(int userId) {
//             followees.remove(userId);
//         }
//     }
    
    Map<Integer, Tweet> UserTweetMap;
    Map<Integer, Set<Integer>> followMap;
    
    /** Initialize your data structure here. */
    public Twitter() {
        UserTweetMap = new HashMap<>();
        followMap = new HashMap<>();
        
        // userMap = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet newhead = new Tweet(time++, tweetId);
        if (!followMap.containsKey(userId)) {
            followMap.put(userId, new HashSet<Integer>());
        }
        followMap.get(userId).add(userId);
        if (!UserTweetMap.containsKey(userId)) {
            UserTweetMap.put(userId, newhead);
        } else {
            Tweet head = UserTweetMap.get(userId);
            newhead.next = head;
            UserTweetMap.put(userId, newhead);
        }
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<Tweet> pq = new PriorityQueue<Tweet>((a, b) -> b.timestamp - a.timestamp);
        for (Integer followeeId : followMap.getOrDefault(userId, new HashSet<Integer>())) {
            if (UserTweetMap.containsKey(followeeId)) {
                pq.add(UserTweetMap.get(followeeId));
            }
        }
        
        int count = 10;
        List<Integer> res = new ArrayList<Integer>();
        while (!pq.isEmpty() && count > 0) {
            Tweet first = pq.poll();
            res.add(first.tweetId);
            count--;
            if (first.next != null) {
                pq.offer(first.next);
            }
        }
        
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId)) {
            followMap.put(followerId, new HashSet<Integer>());
        }
        followMap.get(followerId).add(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (!followMap.containsKey(followerId) || followerId == followeeId) {
            return;
        }
        followMap.get(followerId).remove(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
