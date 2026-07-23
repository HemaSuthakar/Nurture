public class SingletonTest {

    public static void main(String[] args) {

        Logger log1 = Logger.getInstance();

        log1.writeLog("Application is starting");

        Logger log2 = Logger.getInstance();

        log2.writeLog("User logged in");

        if (log1 == log2) {
            System.out.println("Singleton Pattern implemented successfully.");
        } else {
            System.out.println("More than one object created.");
        }

        System.out.println("Hash Code of log1 : " + log1.hashCode());
        System.out.println("Hash Code of log2 : " + log2.hashCode());
    }
}