package algo.arrays;

public class StockPricesBuySellMutlipleTransaction {
	public static void main(String[] args) {
		int[] prices = { 100, 180, 260, 310, 40, 535, 695 };
		System.out.println(maxProfit(prices));

	}

	public static int maxProfit(int[] prices) {
		int n = prices.length, i = 0;
		int profit = 0;
		while (i < n) {
			while ((i + 1) < n && prices[i + 1] <= prices[i])
				i++;// price[i] will be local minima wen loop fails;
			if (i == n - 1)
				break;// buy made at the end of the array..!! cant sell.
			int buy = prices[i];
			i++;
			while ((i + 1) < n && prices[i + 1] >= prices[i])
				i++;// price[i] will be local maxima wen loop fails;
			int sell = prices[i];
			profit += (sell - buy);
		}
		return profit;
	}

}
