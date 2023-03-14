import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    private static Logger instance = null;

    protected int num = 1;

    private Logger() {}

    public static Logger getInstance() {
        if (instance==null) instance = new Logger();
        return instance;
    }

    public void log(String msg) {
        System.out.println("[" + getCurrentLocalDateTimeStamp() +" " + num++ + "] " + msg);
    }

    public String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}