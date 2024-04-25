package gui.helperclases;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtility {

    public static Path generateUniqueFilePath(Path directory, String fileName) {
        int count = 1;
        Path filePath = directory.resolve(fileName);
        String baseFileName = fileName.substring(0, fileName.lastIndexOf('.'));
        String extension = fileName.substring(fileName.lastIndexOf('.'));

        while (Files.exists(filePath)) {
            String numberedFileName = baseFileName + "_" + count + extension;
            filePath = directory.resolve(numberedFileName);
            count++;
        }
        return filePath;
    }

    public static void copyFileToDirectory(File source, Path destination) throws IOException {
        Files.copy(source.toPath(), destination);
    }
}
