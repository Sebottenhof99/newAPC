import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Antoshka on 31.12.2017.
 */
public class CalculateValues {


    public static String calculateShippingCost(Customer c){
        double sum=0;
        List<Article> list = c.getListOfAllArticles();

        for(int i = 0; i<list.size(); i++){
            String s = list.get(i).getVERSANDKOSTEN();
            try {
                double currentValue = Double.parseDouble(s);
                sum+=currentValue;
            }catch (NumberFormatException e){
                sum=0;
            }
        }

         return String.valueOf(sum);
    }

    public static String calculateItemPrice(String priceWithoutDiscount, String discount){
        double priceWithoutDiscountD = Double.parseDouble(priceWithoutDiscount);
        double discountD = Double.parseDouble(discount.substring(1));
        double price = priceWithoutDiscountD - discountD;

        price = Math.round((price)*100.0)/100.0;
        DecimalFormat df =   new DecimalFormat( ",##0.00" );
        return df.format(price);
    }

    public static String calculatePriceWithoutMWST(String priceS, String mwstS){
        double priceD = Double.parseDouble(priceS.replace(",","."));
        double mwstD = Double.parseDouble(mwstS.replace(",","."));
        double price= priceD - mwstD;
        price = Math.round((price)*100.0)/100.0;
        DecimalFormat df =   new DecimalFormat( ",##0.00" );
        return df.format(price);

    }

    public static String calculateNettoPrice(String bruttoValue){
        bruttoValue=bruttoValue.replace(",",".");
        double nettoValue = Double.parseDouble(bruttoValue);
        nettoValue = Math.round((nettoValue / 1.19)*100.0)/100.0;
        DecimalFormat df =   new DecimalFormat( ",##0.00" );
        String netto = df.format(nettoValue);
        return netto;
    }

    public static String calculateWholePrice(Customer c){
    double price=0;
    for(int i = 0; i<c.getListOfAllArticles().size(); i++){
        String priceS = c.getListOfAllArticles().get(i).getPREIS_MIT_RABATTE();
        priceS = priceS.replace(",",".");
        double d = Double.parseDouble(priceS);
        price+=d;
    }
        DecimalFormat df =   new DecimalFormat( ",##0.00" );
        return df.format(price);
    }
}
