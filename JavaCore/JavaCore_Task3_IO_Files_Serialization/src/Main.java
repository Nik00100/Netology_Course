import java.io.*;
import java.util.zip.*;

public class Main {
    public static void saveGame(String fileName, GameProgress gameProgress) {
        try {
            FileOutputStream outputStream = new FileOutputStream(fileName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(gameProgress);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String zipFileName, String[] files) {
       try (ZipOutputStream zip = new ZipOutputStream(new FileOutputStream(zipFileName))) {
           for (int i=0; i< files.length; i++) {
               FileInputStream fileInputStream = new FileInputStream(files[i]);
               // получаем название файла
               String entryName = files[i].split("/")[zipFileName.split("/").length - 1];
               // добавляем объект ZipEntry в архив
               zip.putNextEntry(new ZipEntry(entryName));
               // считываем содержимое файла в массив byte
               byte[] buffer = new byte[fileInputStream.available()];
               fileInputStream.read(buffer);
               // добавляем содержимое к архиву
               zip.write(buffer);
               // закрываем текущую запись для новой записи
               zip.closeEntry();
           }
        } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public static void main(String[] args)  {
        GameProgress progress1 = new GameProgress(100, 5,15,12.2);
        GameProgress progress2 = new GameProgress(80, 8,25,37.5);
        GameProgress progress3 = new GameProgress(70, 10,35,52.1);

        String fileName1 = "c:/Games/savegames/save1.txt";
        String fileName2 = "c:/Games/savegames/save2.txt";
        String fileName3 = "c:/Games/savegames/save3.txt";
        String zipFileName = "c:/Games/savegames/saved.zip";

        String[] files = {fileName1,fileName2,fileName3};

        saveGame(fileName1,progress1);
        saveGame(fileName2,progress2);
        saveGame(fileName3,progress3);

        zipFiles(zipFileName,files);

        System.out.println(progress1.toString());
    }
}
