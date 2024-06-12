import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        String path = "\"D:\\Java\\FilesTest\\File1.txt\"";
        String copyFilePath = "\"D:\\Java\\FilesTest\\File2.txt\"";


        String pathTest = "\"D:\\Java\\FilesTest\\testFile.txt\"";
        FileManager.sortFile(pathTest);
    }
}

/**
 * Utility class for managing file operations.
 * @author  Show
 */
class FileManager {
    /**
     * Copies bytes from one file to another.
     *
     * @param filePath The path to the source file. Can contain mixed separators and quotes.
     * @param copyFilePath The path to the destination file. Can contain mixed separators and quotes.
     */
    public static void copyFileByBytes(String filePath, String copyFilePath) {
        filePath = pathReplacer(filePath);
        copyFilePath = pathReplacer(copyFilePath);

        try (InputStream io = new FileInputStream(filePath);
             OutputStream os = new FileOutputStream(copyFilePath)) {

            byte[] buffer = new byte[4096];
            int bytesRead = io.read(buffer);
            while(bytesRead != -1) {
                os.write(buffer, 0, bytesRead);
                bytesRead = io.read(buffer);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Copies bytes from one file to another with a specified buffer size.
     *
     * @param filePath The path to the source file. Can contain mixed separators and quotes.
     * @param copyFilePath The path to the destination file. Can contain mixed separators and quotes.
     * @param bufferSize The size of the buffer to use for copying.
     */
    public static void copyFileByBytes(String filePath, String copyFilePath, int bufferSize) {
        filePath = pathReplacer(filePath);
        copyFilePath = pathReplacer(copyFilePath);

        try (InputStream io = new FileInputStream(filePath);
             OutputStream os = new FileOutputStream(copyFilePath)) {

            byte[] buffer = new byte[bufferSize]; //Here you can change the size of copying to the buffer
            int bytesRead = io.read(buffer);
            while(bytesRead != -1) {
                os.write(buffer, 0, bytesRead);
                bytesRead = io.read(buffer);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Copies the content of a file, reading and writing character by character.
     * Uses the default buffer size (4096 characters).
     * <p>
     * (RU) Копирует содержимое файла, читая и записывая по символьно.
     * Использует стандартный размер буфера (4096 символов).
     *
     * @param filePath      The path to the source file.
     * @param copyFilePath  The path to the file where the content will be copied.
     */
    public static void copyFileByChars(String filePath, String copyFilePath) {
        filePath = pathReplacer(filePath);
        copyFilePath = pathReplacer(copyFilePath);

        try (Reader reader = new FileReader(filePath);
             Writer writer = new FileWriter(copyFilePath)) {

            char[] buffer = new char[4096];
            int charsRead = reader.read(buffer);
            while(charsRead != -1) {
                writer.write(buffer, 0, charsRead);
                charsRead = reader.read(buffer);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Копирует содержимое файла, читая и записывая по символьно.
     *
     * @param filePath      Путь к исходному файлу.
     * @param copyFilePath  Путь к файлу, в который будет скопировано содержимое.
     * @param bufferSize    Размер буфера для чтения файла.
     */
    public static void copyFileByChars(String filePath, String copyFilePath, int bufferSize) {
        filePath = pathReplacer(filePath);
        copyFilePath = pathReplacer(copyFilePath);

        try (Reader reader = new FileReader(filePath);
             Writer writer = new FileWriter(copyFilePath)) {

            char[] buffer = new char[bufferSize];
            int charsRead = reader.read(buffer);
            while(charsRead != -1) {
                writer.write(buffer, 0, charsRead);
                charsRead = reader.read(buffer);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Сортирует содержимое файла построчно и записывает отсортированные строки обратно в файл.
     *
     * @param filePath Путь к файлу, который нужно отсортировать.
     */
    public static void sortFile(String filePath) {
        List<String> list = new ArrayList<>();

        filePath = pathReplacer(filePath);
        File file = new File(filePath);

        try (Scanner scanner = new Scanner(file)) {
            while(scanner.hasNextLine()) {
                list.add(scanner.nextLine());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        Collections.sort(list);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for(String item : list) {
                writer.write(item);
                writer.newLine();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sanitizes a file path by replacing backslashes and forward slashes with the system file separator and removing quotes.
     *
     * @param filePath The file path to sanitize.
     * @return The sanitized file path.
     */
    private static String pathReplacer(String filePath) {
        filePath = filePath.replace("\\", File.separator)
                .replace("\"", "")
                .replace("/", File.separator);
        return filePath;
    }
}