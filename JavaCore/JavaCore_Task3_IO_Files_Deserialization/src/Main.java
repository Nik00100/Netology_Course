import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static ArrayList<String> openZip(String zipFileName, String extractPath)  {
        ArrayList<String> filePathes = new ArrayList<>();
        String filePath;
        ZipEntry entry;
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(zipFileName))){
            while ((entry = zip.getNextEntry()) != null ) {
                String fileName = entry.getName();
                filePath = extractPath + "/" + fileName;
                filePathes.add(filePath);
                FileOutputStream outputStream = new FileOutputStream(filePath);
                while (zip.available() > 0){
                    outputStream.write(zip.read());
                }
                outputStream.flush();
                zip.closeEntry();
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePathes;
    }



    public static GameProgress openProgress(String fileName) {

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName))){
            try {
                GameProgress gameProgress = (GameProgress) input.readObject();
                return gameProgress;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String zipFileName = "C:/Games/savegames/saved.zip";
        String extractPath = "C:/Games/savegames";
        ArrayList<String> filePathes = openZip(zipFileName,extractPath);

        for (String path : filePathes) {
            System.out.println(openProgress(path).toString());
        }
    }
}
