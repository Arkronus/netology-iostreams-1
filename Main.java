import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        final String gamesPath = "C://Games";
        StringBuilder log = new StringBuilder();

//      В папке Games создайте несколько директорий: src, res, savegames, temp.
        String[] items = {"src", "res", "savegames", "temp"};
        log.append(createFileStructure(gamesPath, items, true));

//      В каталоге src создайте две директории: main, test.
        String srcPath = gamesPath + "//src";
        log.append(createFileStructure(srcPath, new String[]{"main", "test"}, true));

//      В подкаталоге main создайте два файла: Main.java, Utils.java.
        log.append(createFileStructure(srcPath + "//main", new String[]{"Main.java", "Utils.java"}, false));

//      В каталог res создайте три директории: drawables, vectors, icons.
        log.append(createFileStructure(gamesPath + "//res", new String[]{"drawables", "vectors", "icons"}, true));

//      В директории temp создайте файл temp.txt.
        log.append(createFileStructure(gamesPath+"//temp", new String[]{"temp.txt"}, false));

        String logPath = gamesPath + "//temp//temp.txt";
        try(FileWriter writer = new FileWriter(logPath, true)) {
            writer.write(log.toString());
            writer.flush();
        } catch (IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static String createFileStructure(String mainPath, String[] items, boolean folder) {
        StringBuilder sb = new StringBuilder();
        for (String item: items){
            File itemPath = new File(String.format("%s//%s", mainPath, item));
            try {
                if (folder) {
                    itemPath.mkdir();
                    sb.append(String.format("Folder %s created\n", item));
                }
                else {
                    itemPath.createNewFile();
                    sb.append(String.format("File %s created\n", item));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
