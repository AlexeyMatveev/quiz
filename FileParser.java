import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;

import static java.util.Arrays.asList;

public class FileParser {

    private static final int UNICODE_FLAG = 0x80;

    private File file;

    public FileParser(File file) {
        this.file = file;
    }

    public String getContent() throws IOException {
        return getContent(true);
    }

    public String getContentWithoutUnicode() throws IOException {
        return getContent(false);
    }

    private String getContent(boolean withUnicode) throws IOException {
        StringBuilder output = new StringBuilder();

        try (FileInputStream i = new FileInputStream(file)) {
            int data;
            while ((data = i.read()) > 0) {
                if (withUnicode || data < UNICODE_FLAG) {
                    output.append((char) data);
                }
            }
        }
        return output.toString();
    }

    public void saveContent(String content) throws IOException {
        Files.write(file.toPath(), asList(content));
    }
}
