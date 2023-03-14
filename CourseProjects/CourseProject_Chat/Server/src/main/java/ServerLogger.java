import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerLogger {

    private static ServerLogger log = null;
    private int numberMsg = 0;

    private ServerLogger() {
    }

    public static ServerLogger getLog() {
        if (log == null) {
            log = new ServerLogger();
        }
        return log;
    }

    public void log(String msg) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date time = new Date();

        numberMsg++;
        String str = "log № " + numberMsg + " " + formatter.format(time) + " " + msg + "\n";
        try (FileWriter writer = new FileWriter("Server/src/main/resources/serverLog.txt", true)) {
            writer.write(str);
        } catch (IOException ex) {
            ConsoleHelper.writeMessage(ex.getMessage());
        }
    }
}