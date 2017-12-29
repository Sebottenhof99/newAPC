import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Customer {
    private String data;
    private String bestelldatum;
    private String bestellnummer;
    private String name;
    private String mail;
    private String adress1;
    private String adress2;
    private String city;
    private String country;
    private String postalCode;
    private List<List> listOfAllArticles = new ArrayList<List>();
    private List<String> article;


    public Customer(String data){
        this.data=data;
        distributeData();
        addArticleFromRawData();
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
        String[] positionsOfRawData = data.split("\t");
        article= new ArrayList<String>();
        //List Article: Stückzahl, Artikelbezeichnung, Währung, Preis, Preis_Mwst, Versand, Versand_Mwst, Rabatte ,
        article.add(positionsOfRawData[Defines.Article.STÜCKZAHL]);
        article.add(positionsOfRawData[Defines.Article.ARTIKLEBEZEICHNUNG]);
        article.add(positionsOfRawData[Defines.Article.WÄHRUNG]);
        article.add(positionsOfRawData[Defines.Article.PREIS]);
        article.add(positionsOfRawData[Defines.Article.PREIS_MWST]);
        article.add(positionsOfRawData[Defines.Article.VERSANDKOSTEN]);
        article.add(positionsOfRawData[Defines.Article.VERSANDKOSTEN_MWST]);
        article.add(positionsOfRawData[Defines.Article.RABATTE]);
        listOfAllArticles.add(article);
    }

    public void addOneMoreArticle(String data){
        this.data=data;
        addArticleFromRawData();
    }




    public String getBestelldatum() {
        return bestelldatum;
    }
    public void setBestelldatum(String bestelldatum) {
        this.bestelldatum = bestelldatum;
    }
    public String getBestellnummer() {
        return bestellnummer;
    }
    public void setBestellnummer(String bestellnummer) {
        this.bestellnummer = bestellnummer;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getAdress1() {
        return adress1;
    }
    public void setAdress1(String adress1) {
        this.adress1 = adress1;
    }
    public String getAdress2() {
        return adress2;
    }
    public void setAdress2(String adress2) {
        this.adress2 = adress2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
