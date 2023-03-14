package ru.netology.springboot_courseproject_moneytransferservice.logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Logger {

    private static Logger log = null;
    private final AtomicInteger numberMsg = new AtomicInteger(-1);

    private Logger() {
    }

    public static Logger getLog() {
        return Objects.isNull(log) ? log = new Logger() : log;
    }

    public void log(String msg) {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("(dd.MM.yy / HH:mm:ss)");
        String str;
        if (numberMsg.get() <= 0) {
            str = String.format("%s %s \n", msg, dateFormat.format(date));
        } else {
            str = String.format("log № %d %s %s \n", numberMsg.get(), dateFormat.format(date), msg);
        }
        numberMsg.getAndIncrement();

        try (FileOutputStream fos = new FileOutputStream("logFile.txt", true)) {
            byte[] bytes = str.getBytes();
            fos.write(bytes, 0, bytes.length);
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
}
