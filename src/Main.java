import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static final String SAVES_PATH = "D://Games/savegames/";

    public static void main(String[] args) throws Exception {

        List<String> listSaves = new ArrayList<>();

        GameProgress gameProgress1 = new GameProgress(100, 1, 1, 10.0);
        GameProgress gameProgress2 = new GameProgress(87, 2, 2, 43.1);
        GameProgress gameProgress3 = new GameProgress(54, 3, 3, 112.2);

        gameProgress1.saveGame(SAVES_PATH + "save1.dat", gameProgress1);
        gameProgress2.saveGame(SAVES_PATH + "save2.dat", gameProgress2);
        gameProgress3.saveGame(SAVES_PATH + "save3.dat", gameProgress3);
        System.out.println("Сохранение прогресса игры выполнено");

        File save1 = new File(SAVES_PATH + "save1.dat");
        File save2 = new File(SAVES_PATH + "save2.dat");
        File save3 = new File(SAVES_PATH + "save3.dat");

        listSaves.add(save1.getAbsolutePath());
        listSaves.add(save2.getAbsolutePath());
        listSaves.add(save3.getAbsolutePath());

        gameProgress1.zipFiles(SAVES_PATH + "saves.zip", listSaves);
        System.out.println("Файлы сохранений помещены в архив saves.zip");

        try {
            if (save1.delete() && save2.delete() && save3.delete()) {
                System.out.println("Файлы сохранений вне архива удалены");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }


    }

}
