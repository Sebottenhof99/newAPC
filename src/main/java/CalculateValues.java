import java.util.List;

/**
 * Created by Antoshka on 31.12.2017.
 */
public class CalculateValues {


    public static String calculateShippingCost(Customer c){
        double sum=0;
        List<List> list = c.getListOfAllArticles();
        for(int i = 0; i<list.size(); i++){
            String s = (String)list.get(i).get(5);
            try {
                double currentValue = Double.parseDouble(s);
                sum+=currentValue;
            }catch (NumberFormatException e){
                sum=0;
            }
        }
        return String.valueOf(sum);
    }
}
