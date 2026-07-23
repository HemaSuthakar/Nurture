public class LibraryTest {
    public static void main(String[] args) {
        Book[] library = {
            new Book(1, "Algorithms", "Author A"),
            new Book(2, "Data Structures", "Author B"),
            new Book(3, "Java Programming", "Author C")
        };

        int index = Main.binarySearch(library, "Data Structures");
        if (index != -1) System.out.println("Book found at index: " + index);
    }
}