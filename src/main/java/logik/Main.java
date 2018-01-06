package logik;

import java.io.IOException;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        Main m = new Main();
        m.start();
    }

    public void start() throws Exception {
        String pathToFile = "C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\4444.txt";
        ReadFile rf = new ReadFile(pathToFile);
        List<String> listOfRawCustomers = rf.getRawDataOfCustomers();



        CreateListOfCustomers createListOfCustomers = new CreateListOfCustomers(listOfRawCustomers);
        List<Customer> listOfCustomers = createListOfCustomers.getListOfCustomers();



        for(int i = 0; i<listOfCustomers.size(); i++){
           // listOfCustomers.get(i).setShippingCostNetto(CalculateValues.calculateNettoPrice(listOfCustomers.get(i).getShippingCost()));
            //System.out.println(listOfCustomers.get(i).getShippingCostNetto());
            System.out.println(listOfCustomers.get(i).getBestellnummer()+ " "+ listOfCustomers.get(i).getBestelldatum() +" "+ listOfCustomers.get(i).getPrice() +" "+ listOfCustomers.get(i).getShippingCost());
            System.out.println(listOfCustomers.get(i).getListOfAllArticles().get(0).getARTIKLEBEZEICHNUNG());
        }

        CreatePdf p = new CreatePdf("C:\\Users\\Antoshka\\Desktop\\Ama_Rechnungen\\config_invoice.txt");
        for (Customer customer :listOfCustomers) {
            p.manipulatePdf(customer);

        }




    }
}
