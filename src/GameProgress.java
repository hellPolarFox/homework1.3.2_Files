import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameProgress implements Serializable {

    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }

    public void saveGame(String path, GameProgress gameProgress) throws Exception {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void zipFiles(String path, List<String> listSaves) throws Exception {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(path))) {
                for (String s : listSaves) {
                    FileInputStream fis = new FileInputStream(s);
                    ZipEntry entry = new ZipEntry(s.substring(s.lastIndexOf('\\') + 1));
                    zout.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zout.write(buffer);
                    fis.close();
                    zout.closeEntry();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

}
