package dsa.livemock;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*
 Question:
    Design a rate limiter with a function
        boolean isAllowed(String userId, long timestampMillis)

    Constraints:
        Each user can make at most maxAllowed requests
        Within a rolling time window of windowMillis
        Requests older than windowMillis should not count

    Example:

        maxAllowed = 3
        window = 1000 ms

        Requests at: 10, 500, 900 → allowed
        Request at: 1000 → ❌ blocked (still 3 in [0,1000])
        Request at: 1020 → ✅ allowed (10 is now outside window)
 */

/*
 1. start by creating a map of userid and queue
 2. whenever a request comes in, check if there is a map entry for this userid
 3. if map does not contain userid, then create a queue, add the timestamp to queue and then return true as this is the first request
 4. if map contains userid, then get the queue ->
        create a loop while(!queue.isEmpty()) -> keep removing the timestamp from front of the queue that is less than (timestamp - window).
        after the end of while loop if queue.size is less than or equal to maxAllowed
            then add the timestamp to queue and return true
        else return false and do not add the timestamp to queue

 */
public class APIRateLimiter {

    private int maxAllowed;
    private long window;
    private Map<String, Deque<Long>> userRequests;

    public APIRateLimiter(int maxAllowed, long window){
        this.maxAllowed = maxAllowed;
        this.window = window;
        this.userRequests = new HashMap<>();
    }

    public boolean isAllowed(String userId, long timestamp){
        if(!userRequests.containsKey(userId)){
            Deque<Long> queue = new ArrayDeque<>();
            queue.offerLast(timestamp);
            userRequests.put(userId, queue);
            return true;
        } else {
            Deque<Long> queue = userRequests.get(userId);
            while(!queue.isEmpty() && queue.peekFirst() <= (timestamp - window)){
                queue.pollFirst();
            }
            if(queue.size() < maxAllowed){
                queue.offerLast(timestamp);
                userRequests.put(userId, queue);
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        APIRateLimiter apiRateLimiter = new APIRateLimiter(3, 1000);
        assertTrue(apiRateLimiter.isAllowed("A", 10));
        assertTrue(apiRateLimiter.isAllowed("A", 500));
        assertTrue(apiRateLimiter.isAllowed("B", 1000));
        assertTrue(apiRateLimiter.isAllowed("A", 900));
        assertFalse(apiRateLimiter.isAllowed("A", 1000));
        assertTrue(apiRateLimiter.isAllowed("A", 1020));
    }
}
