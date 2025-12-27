package dsa.top50;


import static org.junit.Assert.assertEquals;

public class BesTimeBuyAndSellStock {

    // I feel like it needs to be solved as sliding window
    // but how to keep track of buy and sell points
    // instead of solving the problem I am trying to remember the solution
    // I am thinking we set that we first bought at prices[0] and then start the loop from prices[1]
    // if selling at prices[1] is greater than the current max profit then sell
    // or do we keep track of local minima and local maxima such that we buy at local minima and sell at local maxima
    // and while processing the loop we keep updating local minima and maxima
    // and after the loop ends we return max profit as (local maxima - local minima)
    // but one question is how to make sure local maxima always comes after local minima

    // After checking with AI, one important note:
    //      buy must come before sell
    //      So at any index i, the only valid buy prices are from indices < i.
    //.     That means:
    //          If I’m at day i, I only need to know the minimum price seen so far.

    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        int minSeenSoFar = prices[0];
        for(int i = 1; i< prices.length; i++){
            minSeenSoFar = Math.min(minSeenSoFar, prices[i]);
            maxProfit = Math.max(prices[i] - minSeenSoFar, maxProfit);
           /* if (minSeenSoFar <= prices[i])
                maxProfit = Math.max(prices[i] - minSeenSoFar, maxProfit);
            else
                minSeenSoFar = prices[i];*/
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        BesTimeBuyAndSellStock b = new BesTimeBuyAndSellStock();
        assertEquals(5, b.maxProfit(new int[]{7,1,5,3,6,4}));
        assertEquals(0, b.maxProfit(new int[]{7,6,4,3,1}));
    }
}
