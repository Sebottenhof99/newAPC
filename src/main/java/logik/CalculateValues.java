package logik;

import javafx.beans.value.ObservableNumberValue;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Antoshka on 31.12.2017.
 */
public class CalculateValues {


    public static String calculateShippingCost(Customer c) {
        try {
            double sum = 0;
            List<Article> list = c.getListOfAllArticles();
            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i).getVERSANDKOSTEN();
                s = s.replace(",", ".");
                try {
                    double currentValue = Double.parseDouble(s);
                    sum += currentValue;
                } catch (NumberFormatException e) {
                    sum = 0;
                }
            }

            sum = Math.round((sum) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat(",##0.00");
            return df.format(sum);
        }catch (NumberFormatException e){
            return "0,00";
        }
    }

    public static String calculateItemPrice(String priceWithoutDiscount, String discount){
        try {
            double priceWithoutDiscountD = Double.parseDouble(priceWithoutDiscount.replace(",", "."));
            double discountD = Double.parseDouble(discount.substring(1).replace(",", "."));
            double price = priceWithoutDiscountD - discountD;

            price = Math.round((price) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat(",##0.00");
            return df.format(price);
        }
        catch (NumberFormatException e){
            return "0,00";
        }
    }

    public static String calculatePriceWithoutMWST(String priceS, String mwstS){
        try {
            double priceD = Double.parseDouble(priceS.replace(",", "."));
            double mwstD = Double.parseDouble(mwstS.replace(",", "."));
            double price = priceD - mwstD;
            price = Math.round((price) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat(",##0.00");
            return df.format(price);
        }catch (NumberFormatException e){
            return "0,00";
        }

    }


    public static String calculateShippingCostNetto(Customer c){
        try {

            double sum = 0;
            List<Article> list = c.getListOfAllArticles();

            for (int i = 0; i < list.size(); i++) {
                String s = list.get(i).getVERSANDKOSTEN_MWST().replace(",", ".");
                try {
                    double currentValue = Double.parseDouble(s);
                    sum += currentValue;
                } catch (NumberFormatException e) {
                    sum = 0;
                }
            }

            sum = Math.round((sum) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat(",##0.00");
            return df.format(sum);
        }catch (NumberFormatException e){
            return "0,00";
        }
    }



    public static String calculateNettoPrice(String bruttoValue){
        try {
            bruttoValue = bruttoValue.replace(",", ".");
            double nettoValue = Double.parseDouble(bruttoValue);
            nettoValue = Math.round((nettoValue / 1.19) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat(",##0.00");
            String netto = df.format(nettoValue);
            return netto;
        }catch (NumberFormatException e){
            return "0,00";
        }
    }

    public static String calculateWholePrice(Customer c) {

        try {
            double price = 0;
            for (int i = 0; i < c.getListOfAllArticles().size(); i++) {
                String priceS = c.getListOfAllArticles().get(i).getPREIS_MIT_RABATTE();
                priceS = priceS.replace(",", ".");
                double d = Double.parseDouble(priceS);
                price += d;
            }
            double versand = Double.parseDouble(c.getShippingCost().replace(",","."));
            price+=versand;
            System.out.println(price);
            DecimalFormat df = new DecimalFormat(",##0.00");
            return df.format(price);
        }catch (NumberFormatException e){
            return "0,00";
        }
    }


    public static String calculateMwsTSum(Customer customer){
        double result = Double.parseDouble(customer.getPrice().replace(",",".")) - Double.parseDouble(customer.getNettoPrice().replace(",","."));
        try {
            result = Math.round((result) * 100.0) / 100.0;
            return new DecimalFormat(",##0.00").format(result);
        }catch (NumberFormatException e){
            return "0,00";
        }

    }

    public static String calculateMwsTShippingSum(Customer customer){
       try {
           String price = customer.getShippingCost();
           String netto = customer.getShippingCostNetto();
           price = price.replace(",", ".");
           netto = netto.replace(",", ".");
           double priceD = Double.parseDouble(price);
           double nettoD = Double.parseDouble(netto);
           double result = priceD - nettoD;
           result = Math.round((result) * 100.0) / 100.0;
           DecimalFormat df = new DecimalFormat(",##0.00");
           return df.format(result);
       }catch (NumberFormatException e){
           return "0,00";
       }
    }

    public static String calculateWholeNettoPrice(Customer c){
       double sum = 0;
        try {
            sum += Double.parseDouble(c.getShippingCostNetto().replace(",", "."));
            for (int i = 0; i < c.getListOfAllArticles().size(); i++) {
                String s = c.getListOfAllArticles().get(i).getPREIS_OHNE_MWST();
                sum += Double.parseDouble(s.replace(",", "."));
            }
            sum = Math.round((sum) * 100.0) / 100.0;
            DecimalFormat df = new DecimalFormat(",##0.00");
            return df.format(sum);
        }catch (NumberFormatException e){
            return "0,00";
        }


        }

    public static String splitBestelldatum(String rawDate){
        String date ="";
        final String SEPERATOR=".";
        if(rawDate.length()>10){
            date+=rawDate.substring(8,10);
            date+=SEPERATOR;
            date+=rawDate.substring(5,7);
            date+=SEPERATOR;
            date+=rawDate.substring(0,4);
        }
        return date;
    }
}
