public class BuilderTest {
    public static void main(String[] args) {
        // Creating a custom computer configuration
        Computer myPC = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .build();

        System.out.println("Constructed: " + myPC);
    }
}