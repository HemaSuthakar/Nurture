public class EcommerceSearchDemo {

    public static void main(String[] args) {

        Product[] products = {
                new Product(105, "Laptop", "Electronics"),
                new Product(101, "Shoes", "Fashion"),
                new Product(103, "Phone", "Electronics"),
                new Product(104, "Watch", "Accessories"),
                new Product(102, "Bag", "Fashion")
        };

        System.out.println("=== Linear Search ===");
        Product linearResult =
                SearchAlgorithms.linearSearch(products, 104);

        if (linearResult != null) {
            System.out.println(linearResult);
        } else {
            System.out.println("Product not found.");
        }

        Product[] sortedProducts = {
                new Product(101, "Shoes", "Fashion"),
                new Product(102, "Bag", "Fashion"),
                new Product(103, "Phone", "Electronics"),
                new Product(104, "Watch", "Accessories"),
                new Product(105, "Laptop", "Electronics")
        };

        System.out.println("\n=== Binary Search ===");

        Product binaryResult =
                SearchAlgorithms.binarySearch(sortedProducts, 104);

        if (binaryResult != null) {
            System.out.println(binaryResult);
        } else {
            System.out.println("Product not found.");
        }
    }
}