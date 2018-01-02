import java.io.IOException;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Main {

    public static void main(String[] args) throws IOException{
        Main m = new Main();
        m.start();
    }

    public void start() throws IOException {
        String pathToFile = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\FINAL.txt";
        ReadFile rf = new ReadFile(pathToFile);
        List<String> listOfRawCustomers = rf.getRawDataOfCustomers();



        CreateListOfCustomers createListOfCustomers = new CreateListOfCustomers(listOfRawCustomers);
        List<Customer> listOfCustomers = createListOfCustomers.getListOfCustomers();


        for(int i = 0; i<listOfCustomers.size(); i++){
            listOfCustomers.get(i).setShippingCost(CalculateValues.calculateShippingCost(listOfCustomers.get(i)));
        }
        for(int i = 0; i<listOfCustomers.size(); i++){
            listOfCustomers.get(i).setShippingCostNetto(CalculateValues.calculateNettoPrice(listOfCustomers.get(i).getShippingCost()));
            //System.out.println(listOfCustomers.get(i).getShippingCostNetto());
            System.out.println(listOfCustomers.get(i).getPrice() +" "+ listOfCustomers.get(i).getShippingCost());
        }



    }
}
