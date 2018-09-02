package logik;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;


import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class ProvideResults {


    public static String getActualInvoicePath(){
        final String BASIC_PATH = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Ama_Rechnungen";
        Date date = new Date();
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        String year = yearFormat.format(date);
        SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
        String month = monthFormat.format(date);
        SimpleDateFormat dayFormat = new SimpleDateFormat("dd");
        String day = dayFormat.format(date);
        StringBuilder stringBuilder = new StringBuilder(BASIC_PATH);
        stringBuilder.append(File.separator);
        stringBuilder.append(year);
        stringBuilder.append("-");
        stringBuilder.append(month);
        stringBuilder.append("-");
        stringBuilder.append(day);
        stringBuilder.append(File.separator);
        String fullPath = stringBuilder.toString();

        return fullPath;
    }

    public static void createFolder() throws IOException{

       String wholePath = getActualInvoicePath();
        Files.createDirectories( Paths.get(wholePath));

    }


}
