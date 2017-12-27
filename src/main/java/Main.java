import java.io.IOException;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Main {



    public static void main(String[] args) throws IOException {
         List<String> listOfCustomers;
        String pathToFile = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\8834115460017525.txt";
        ReadFile rf = new ReadFile(pathToFile);
        listOfCustomers = rf.getRawDataOfCustomers();
        CreateListOfCustomers createListOfCustomers = new CreateListOfCustomers(listOfCustomers);

    }
}
