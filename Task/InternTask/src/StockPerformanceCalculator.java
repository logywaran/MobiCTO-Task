public class StockPerformanceCalculator {
    public static void main(String[] args) {
        int[] prices = {750,690,685,700,710};

        int start = prices[0];
        int end = prices[prices.length-1];

        int change = end - start ;
      

        if (change > 0) {
            System.out.println("Result: Profit " + change);
        }
        else if (change < 0) {
            System.out.println("Result: Loss " + change);
        }
        else {
            System.out.println("Result: No Profit No Loss ⚖");
        }
    }
}
