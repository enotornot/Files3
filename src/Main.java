import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    private static final String pathToSave = "D://Games/savegames/";
    private static final String zipSave = "D://Games/savegames/gameProgressSave.zip";

    public static void main(String[] args) {

        openZip(zipSave, pathToSave);
        System.out.println(openProgress(pathToSave + "save3.date"));


    }

    public static void openZip(String zipSave, String pathToSave) {
        try {
            ZipInputStream zin = new ZipInputStream(new FileInputStream(zipSave));
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fos = new FileOutputStream(pathToSave + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fos.write(c);
                }
                fos.flush();
                zin.closeEntry();
                fos.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameProgress openProgress(String path) {
        GameProgress gameProgress = null;
        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return gameProgress;
    }
}