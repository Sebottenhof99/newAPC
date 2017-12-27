import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class CreateListOfCustomers {
    private int maxLength=0;
    private List<String> rawCustomerList;
    private List<Customer> listOfCustomers = new ArrayList<Customer>();

    public CreateListOfCustomers(List<String> rawCustomerList){
        this.rawCustomerList = rawCustomerList;
        getMinMaxLengthOfCustomerValues();
        createCustomers();
    }

    public void getMinMaxLengthOfCustomerValues(){
    List<Integer> lengthList = new ArrayList<Integer>();
        for(int i = 1; i<rawCustomerList.size(); i++){

            lengthList.add(rawCustomerList.get(i).split("\t").length);
          //  System.out.println(lengthList.get(i));
        }
        Collections.sort(lengthList);
        Collections.reverse(lengthList);
        maxLength = lengthList.get(0);
        }


    public void createCustomers(){
        for(int i = 0; i<rawCustomerList.size(); i++){
            if(rawCustomerList.get(i).split("\t").length==maxLength) {
                Customer c = new Customer(rawCustomerList.get(i));
                listOfCustomers.add(c);
            }
        }
    }
}
