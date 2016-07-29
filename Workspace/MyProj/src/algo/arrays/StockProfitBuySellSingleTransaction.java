package algo.arrays;

public class StockProfitBuySellSingleTransaction {
	//keep track of the minimum till now from the left and try selling when we get a higher value 
	public int maxProfit(int[] prices) {
		if (prices.length == 0 || prices.length == 1)
			return 0;
		int profit = 0, min = prices[0];
		for (int i = 1; i < prices.length; i++) {
			if (min < prices[i]) {
				profit = Math.max(profit, prices[i] - min);
			} else {
				min = prices[i];
			}
		}
		return profit;
	}
}
