import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BotClient extends Client{
    final static String PATH_TO_PROPERTIES = "Client/src/main/resources/application.properties";

    public BotClient(ClientProperties clientProperties) {
        super(clientProperties);
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected String getUserName() {
        String name = String.format("date_bot_%d", (int) (Math.random()*100));
        return name;
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    public static void main(String[] args) {
        ClientProperties clientProperties=null;
        try {
            clientProperties = new ClientProperties(PATH_TO_PROPERTIES);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Произошла ошибка при чтении файла настроек параметров клиента");
        }
        BotClient botClient = new BotClient(clientProperties);
        botClient.run();
    }

    public class BotSocketThread extends SocketThread {
        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            BotClient.this.sendTextMessage("Привет чату. Я бот. " +
                    "Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            // Выводим текст сообщения в консоль
            ConsoleHelper.writeMessage(message);
            //разделяем сообщение по разделителю ":"
            // имя пользователя - 1 элемент массива strings (strings[0])
            // команда вывода даты - 2 элемент массива strings (strings[1])
            String[] strings = message.split(":");
            if (strings.length != 2) return;
            String command = strings[1].trim();
            String userName = strings[0].trim();
            //проверяем текст сообщения
            String format = null;

            switch (command) {
                case "дата":
                    format = "d.MM.YYYY";
                    break;
                case "день":
                    format = "d";
                    break;
                case "месяц":
                    format = "MMMM";
                    break;
                case "год":
                    format = "YYYY";
                    break;
                case "время":
                    format = "H:mm:ss";
                    break;
                case "час":
                    format = "H";
                    break;
                case "минуты":
                    format = "m";
                    break;
                case "секунды":
                    format = "s";
                    break;
            }
            if (format != null) {
                SimpleDateFormat formatter = new SimpleDateFormat(format);
                Date date = Calendar.getInstance().getTime();
                String dateForSending = formatter.format(date);
                BotClient.this.sendTextMessage(String.format("Информация для %s: %s",userName,dateForSending));
            }
        }

    }

}
