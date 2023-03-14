package ru.netology.graphics.image;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.net.URL;

public class GrayScaleTextGraphicsConverter implements TextGraphicsConverter{
    private int width;
    private int height;
    private double maxRatio;
    private TextColorSchema schema;

    public GrayScaleTextGraphicsConverter(int width, int height, double maxRatio, TextColorSchema schema) {
        this.width = width;
        this.height = height;
        this.maxRatio = maxRatio;
        this.schema = schema;
    }

    @Override
    public String convert(String url) throws IOException, BadImageSizeException {
        String result = null;
        // Скачиваем картинку из интернета
        BufferedImage img = ImageIO.read(new URL(url));
        //BufferedImage img = ImageIO.read(new File(url));
        //размеры скачанной картинки
        int imgWidth = img.getWidth();
        int imgHeight = img.getHeight();
        //проверка на соотношение сторон
        if ((double)(imgWidth/imgHeight) > maxRatio) {
            throw new BadImageSizeException((double)(imgWidth/imgHeight), maxRatio);
        }
        //размеры картинки, приведенные в соответствие
        //с максимальными размерами картинки
        double ratioWidth = imgWidth/width;
        double ratioHeight = imgHeight / height;
        if (ratioWidth > ratioHeight) {
            imgWidth = (int) (imgWidth/ratioWidth);
            imgHeight = (int) (imgHeight/ratioWidth);
        } else {
            imgWidth = (int) (imgWidth/ratioHeight);
            imgHeight = (int) (imgHeight/ratioHeight);
        }
        // Изменяем размеры картинки  на новые.
        Image scaledImage = img.getScaledInstance(imgWidth, imgHeight, BufferedImage.SCALE_SMOOTH);
        // Создадим новую пустую картинку c новыми размерами,
        // с чёрно-белой цветовой палитрой:
        BufferedImage bwImg = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_BYTE_GRAY);
        // Создаем инструмент для рисования на ч/б картинке:
        Graphics2D graphics = bwImg.createGraphics();
        // Копируем содержимое из суженной картинки:
        graphics.drawImage(scaledImage, 0, 0, null);

        // Создаем инструмент для прохода по пикселям:
        WritableRaster bwRaster = bwImg.getRaster();
        // Двойной цикл по всем столбцам (ширина)
        // и строкам (высота) изображения, для преобразования пикселей картинки в символы
        // и запись символов в двумерный массив
        char[][] symbols = new char[imgWidth][imgHeight];
        for (int j = 0; j < imgHeight; j++) {
            for (int i = 0; i < imgWidth; i++) {
                int color = bwRaster.getPixel(i, j, new int[3])[0];
                char c = schema.convert(color);
                symbols[i][j] = c;//запоминаем символ c
            }
        }
        //собираем символы в строку
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < imgHeight; j++) {
            for (int i = 0; i < imgWidth; i++) {
                sb.append(symbols[i][j]).append(symbols[i][j]);
                if (i == imgWidth-1) {
                    sb.append("\n");
                }

            }
        }
        return sb.toString();
    }

    @Override
    public void setMaxWidth(int width) {
        this.width = width;
    }

    @Override
    public void setMaxHeight(int height) {
        this.height = height;
    }

    @Override
    public void setMaxRatio(double maxRatio) {
        this.maxRatio = maxRatio;
    }

    @Override
    public void setTextColorSchema(TextColorSchema schema) {
        this.schema = schema;
    }

}
