import java.util.List;

/**
 * Created by Antoshka on 26.12.2017.
 */
public class Customer {
    private String data;

    private String bestellnummer;
    private String name;
    private String mail;
    private String adress1;
    private String adress2;
    private String city;
    private String country;
    private String postalCode;
    protected List<List> listOfAllArticles;

    public Customer(String data){
        this.data=data;
        System.out.println(data);
        distributeData();
    }

    public void distributeData(){
        String[] values = data.split("\t");
        bestellnummer = values[Defines.Customer.BESTELLNUMMER];
        /*name;
        mail;
        adress1;
        adress2;
        city;
        country;
        postalCode;*/
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
