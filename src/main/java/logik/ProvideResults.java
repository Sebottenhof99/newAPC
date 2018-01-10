package logik;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ProvideResults {

    public static void moveAllFilesToTargetFolder() throws IOException {
        Path root = Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Ama_Rechnungen");
        Path work = Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Ama_Rechnungen\\work");
        Date date = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(date);
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        String month = monthFormat.format(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        String day = dayFormat.format(date);


        String wholePath = root.toString()+ File.separator + year + File.separator + month + File.separator +day+File.separator;
        Path dist = Paths.get(wholePath);

        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(work)) {
            for (Path path : directoryStream) {
                Path d2 = dist.resolve(path.getFileName());
                Files.move(path, d2, REPLACE_EXISTING);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }



    }

    public static String createFolder() throws IOException{
        Path root = Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Ama_Rechnungen");
        Path work = Paths.get("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Ama_Rechnungen\\work");
        Date date = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(date);
        SimpleDateFormat monthFormat = new SimpleDateFormat("MMMM");
        String month = monthFormat.format(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        String day = dayFormat.format(date);


        String wholePath = root.toString()+ File.separator + year + File.separator + month + File.separator +day+File.separator;
        Path dist = Paths.get(wholePath);
        Files.createDirectories( Paths.get(wholePath));
        return wholePath;
    }


}
