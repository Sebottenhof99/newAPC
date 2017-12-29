import java.io.IOException;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Main {



    public static void main(String[] args) throws IOException {
        System.out.println("Starting...");
         List<String> listOfCustomers;
        String pathToFile = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\8834115460017525.txt";
        System.out.println("File selected.");
        ReadFile rf = new ReadFile(pathToFile);
        listOfCustomers = rf.getRawDataOfCustomers();
        System.out.println("File Reading completed.");
        CreateListOfCustomers createListOfCustomers = new CreateListOfCustomers(listOfCustomers);

    }
}
