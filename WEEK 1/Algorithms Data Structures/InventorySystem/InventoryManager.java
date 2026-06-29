import java.util.HashMap;
import java.util.Map;

public class InventoryManager {
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Added: " + product.getProductName());
    }

    public void updateProduct(String id, int qty, double price) {
        if (inventory.containsKey(id)) {
            Product p = inventory.get(id);
            p.setQuantity(qty);
            p.setPrice(price);
            System.out.println("Updated: " + id);
        }
    }

    public void deleteProduct(String id) {
        inventory.remove(id);
        System.out.println("Deleted: " + id);
    }
}