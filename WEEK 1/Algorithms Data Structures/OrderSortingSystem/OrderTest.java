public class OrderTest {
    public static void main(String[] args) {
        Order[] orders = {
            new Order("O001", "Alice", 150.50),
            new Order("O002", "Bob", 45.00),
            new Order("O003", "Charlie", 300.00)
        };

        // Testing Bubble Sort
        Main.bubbleSort(orders);
        System.out.println("Orders sorted by Bubble Sort (Low to High):");
        for (Order o : orders) {
            System.out.println(o.getCustomerName() + ": $" + o.getTotalPrice());
        }
    }
}