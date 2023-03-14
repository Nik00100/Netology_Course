import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientLogger {
    private static ClientLogger log = null;
    private int numberMsg = 0;

    private ClientLogger() {
    }

    public static ClientLogger getLog() {
        if (log == null) {
            log = new ClientLogger();
        }
        return log;
    }

    public void log(String msg) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Date time = new Date();

        numberMsg++;
        String str = "log № " + numberMsg + " " + formatter.format(time) + " " + msg + "\n";
        try (FileWriter writer = new FileWriter("Client/src/main/resources/clientLog.txt", true)) {
            writer.write(str);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}