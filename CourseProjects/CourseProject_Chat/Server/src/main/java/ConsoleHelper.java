import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static ServerLogger logger = ServerLogger.getLog();

    public static void writeMessage(String message) {
        System.out.println(message);
        logger.log(message);
    }

    public static String readString() {
        while (true) {
            try {
                String result = reader.readLine();
                if (result != null) return result;
            } catch (IOException e) {
                writeMessage("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
                logger.log("Произошла ошибка при попытке ввода текста. Попробуйте еще раз.");
            }
        }
    }

    public static int readInt() {
        while (true) {
            try {
                Integer result = Integer.parseInt(readString());
                if (result != null) return result;
            } catch (NumberFormatException e) {
                writeMessage("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
                logger.log("Произошла ошибка при попытке ввода числа. Попробуйте еще раз.");
            }
        }
    }

}
