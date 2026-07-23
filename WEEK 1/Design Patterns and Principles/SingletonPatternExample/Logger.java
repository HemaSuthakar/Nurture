public class Logger {

    private static Logger logger;

    private Logger() {
        System.out.println("Logger started");
    }

    public static Logger getInstance() {

        if (logger == null) {
            logger = new Logger();
        }

        return logger;
    }

    public void writeLog(String message) {
        System.out.println("[LOG] " + message);
    }
}