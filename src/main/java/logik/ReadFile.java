package logik;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by Antoshka on 29.11.2017.
 */
public class ReadFile {
    String file;

    public ReadFile(String path) {
        file = path;
    }

    public List<String> getRawDataOfCustomers() throws IOException {
        Path p1 = Paths.get(file);
        List<String> list = Files.readAllLines(p1, Charset.forName("ISO-8859-1"));
        return list;
    }

}

