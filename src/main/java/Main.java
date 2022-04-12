import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(100, 1, 99, 300);
        GameProgress gameProgress2 = new GameProgress(50, 2, 50, 500);
        GameProgress gameProgress3 = new GameProgress(20, 3, 20, 600);

        String save1 = "D:\\Netology\\project\\javacore\\files-task1\\Games\\savegames\\save1.dat";
        String save2 = "D:\\Netology\\project\\javacore\\files-task1\\Games\\savegames\\save2.dat";
        String save3 = "D:\\Netology\\project\\javacore\\files-task1\\Games\\savegames\\save3.dat";

        saveGame(save1, gameProgress1);
        saveGame(save2, gameProgress2);
        saveGame(save3, gameProgress3);

        List<String> listFiles = List.of(save1, save2, save3);

        zipFiles("D:\\Netology\\project\\javacore\\files-task1\\Games\\savegames\\save.zip", listFiles);
    }

    public static void saveGame(String file, GameProgress gameProgress) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(gameProgress);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipFiles(String path, List<String> listSaves) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(path))) {
            for (String currentPath : listSaves) {
                try (FileInputStream fis = new FileInputStream(currentPath)) {
                    File file = new File(currentPath)
                    String currentName = file.getName();

                    ZipEntry zipEntry = new ZipEntry(currentName);
                    zipOutputStream.putNextEntry(zipEntry);

                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);

                    zipOutputStream.write(buffer);
                    zipOutputStream.closeEntry();

                    file.delete();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
