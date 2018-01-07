package logik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Customer {
    private String data;
    private String savedFileName;


    private String bestelldatum;
    private String bestellnummer;
    private String name;
    private String mail;
    private String adress1;
    private String adress2;
    private String city;
    private String country;
    private String postalCode;

    private List<Article> listOfAllArticles = new ArrayList<Article>();

    public Customer(String data){
        this.data=data;
        distributeData();
        addArticleFromRawData();

    }




    public String getPrice(){
        return CalculateValues.calculateWholePrice(this);
    }
    public String getNettoPrice(){ return CalculateValues.calculateWholeNettoPrice(this);}
    public String getMwsTSum(){
        return CalculateValues.calculateMwsTSum(this);
    }

    public void distributeData(){

        String[] values = data.split("\t");
        bestellnummer = values[Defines.Customer.BESTELLNUMMER];
        name=values[Defines.Customer.NAME];
        mail=values[Defines.Customer.MAIL];
        adress1=values[Defines.Customer.ADRESSE1];
        adress2=values[Defines.Customer.ADRESSE2];
        city=values[Defines.Customer.STADT];
        country=values[Defines.Customer.LAND];
        postalCode=values[Defines.Customer.PLZ];
        bestelldatum=values[Defines.Customer.BESTELLDATUM];


    }

   public void addArticleFromRawData(){
       Article a = new Article(data);
       listOfAllArticles.add(a);
   }

    public void addOneMoreArticle(String data){
        this.data=data;
        addArticleFromRawData();
    }


    public String getShippingCostNetto() {
        return CalculateValues.calculatePriceWithoutMWST(this.getShippingCost(),CalculateValues.calculateShippingCostNetto(this));
    }
    public String getShippingCost() {
        return CalculateValues.calculateShippingCost(this);
    }
    public String getShippingCostMwSt(){
        return CalculateValues.calculateMwsTShippingSum(this);
    }
    public String getBestelldatum() {
        return CalculateValues.splitBestelldatum(this.bestelldatum);
    }
    public String getBestellnummer() {
        return bestellnummer;
    }
    public String getName() {
        return name;
    }
    public String getMail() {
        return mail;
    }
    public String getAdress1() {
        return adress1;
    }
    public String getAdress2() {
        return adress2;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public List<Article> getListOfAllArticles() {
        return listOfAllArticles;
    }
    public String getSavedFileName() {
        return savedFileName;
    }
    public void setSavedFileName(String savedFileName) {
        this.savedFileName = savedFileName;
    }


}
