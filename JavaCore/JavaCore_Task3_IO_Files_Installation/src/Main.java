import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static boolean makeDirectory(String folder, String subFolder , StringBuilder sb) {
        if (new File(folder + subFolder + "/").mkdir()) {
            sb.append(getDateTime() + ": " + subFolder + " folder created").append("\n");
            return true;
        } else {
            sb.append(getDateTime() + ": " + "Error - " + subFolder + " folder didn't created").append("\n");
        }
        return false;
    }

    public static String subFolder(String folder, String subFolder) {
        return folder + subFolder + "/";
    }

    public static String getDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(LocalDateTime.now());
    }

    public static StringBuilder fileCreated(String fileName, StringBuilder sb) {
        return sb.append(getDateTime() + ": " + fileName + " file created").append("\n");
    }

    public static StringBuilder errorWhileFileCreating(String fileName, StringBuilder sb) {
        return sb.append(getDateTime() + ": " + fileName + " - error while creating file").append("\n");
    }


    public static void main(String[] args) {
        String folder = "c:/Games/";

        String src = "src";
        String main = "main";
        String test = "test";

        String res = "res";
        String drawables = "drawables";
        String vectors = "vectors";
        String icons = "icons";

        String savegames = "savegames";

        String temp = "temp";
        String fileNameMain = "Main.java";
        String fileNameUtils = "Utils.java";
        String fileNameTemp = "temp.txt";

        StringBuilder sb = new StringBuilder();

        if (makeDirectory(folder, src, sb)) {
            makeDirectory(subFolder(folder,src),test,sb);
            if (makeDirectory(subFolder(folder,src),main,sb)) {
                String mainSubfolder = subFolder(subFolder(folder,src),main);
                try {
                    new File(mainSubfolder + fileNameMain).createNewFile();
                    fileCreated(fileNameMain, sb);
                }catch (IOException e) {
                    errorWhileFileCreating(fileNameMain, sb);
                }
                try {
                    new File(mainSubfolder + fileNameUtils).createNewFile();
                    fileCreated(fileNameUtils,sb);
                } catch (IOException e) {
                    errorWhileFileCreating(fileNameUtils,sb);
                }
            }
        }

        if (makeDirectory(folder, res, sb)) {
            makeDirectory(subFolder(folder,res),drawables,sb);
            makeDirectory(subFolder(folder,res),vectors,sb);
            makeDirectory(subFolder(folder,res),icons,sb);
        }

        makeDirectory(folder,savegames,sb);

        if (makeDirectory(folder,temp,sb)) {
            String tempSubfolder = subFolder(folder,temp);
            try {
                File tempTxt = new File(tempSubfolder + fileNameTemp);
                tempTxt.createNewFile();
                FileWriter writer = new FileWriter(tempTxt);
                fileCreated(fileNameTemp,sb);
                writer.write(sb.toString());
                writer.close();
            } catch (IOException e) {
                errorWhileFileCreating(fileNameTemp,sb);
            }
        }

        System.out.println(sb.toString());

    }
}
