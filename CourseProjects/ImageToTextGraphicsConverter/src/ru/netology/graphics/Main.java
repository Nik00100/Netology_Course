package ru.netology.graphics;

import ru.netology.graphics.image.GrayScaleTextGraphicsConverter;
import ru.netology.graphics.image.TextGraphicsConverter;
import ru.netology.graphics.image.WindowsTextColorSchema;
import ru.netology.graphics.server.GServer;

import java.io.File;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        TextGraphicsConverter converter =
                new GrayScaleTextGraphicsConverter(50,50,3,new WindowsTextColorSchema());

        GServer server = new GServer(converter); // Создаём объект сервера
        server.start(); // Запускаем

        // Или то же, но с выводом на экран:
        //String url = "https://i.pinimg.com/originals/38/ac/b6/38acb61a44aa0e824b49ebe04dc52613.jpg";
        //String imgTxt = converter.convert(url);
        //System.out.println(imgTxt);
    }
}
