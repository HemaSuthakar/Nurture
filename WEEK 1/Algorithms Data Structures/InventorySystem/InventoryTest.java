public class InventoryTest {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        manager.addProduct(new Product("P001", "Laptop", 10, 999.99));
        manager.updateProduct("P001", 8, 899.99);
        manager.deleteProduct("P001");
    }
}