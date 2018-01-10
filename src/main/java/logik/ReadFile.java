package logik;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Antoshka on 29.11.2017.
 */
public class ReadFile {
    private String file;

    public ReadFile(String path) {
        file = path;
    }

    public List<String> getRawDataOfCustomers() throws IOException {
        return Files.readAllLines(Paths.get(file), Charset.forName("ISO-8859-1"));
    }

}

