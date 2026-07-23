public class Main {

    // Linear Search: O(n)
    public static int linearSearch(Book[] books, String title) {
        for (int i = 0; i < books.length; i++) {
            if (books[i].getTitle().equalsIgnoreCase(title)) return i;
        }
        return -1;
    }

    // Binary Search: O(log n) - Requires sorted array
    public static int binarySearch(Book[] books, String title) {
        int left = 0, right = books.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int res = title.compareToIgnoreCase(books[mid].getTitle());

            if (res == 0) return mid;
            if (res > 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
}