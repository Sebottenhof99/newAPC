package logik;

import logik.defines.Defines;

/**
 * Created by Antoshka on 31.12.2017.
 */
public class Article {

    private String stückzahl;
    private String artikelbezeichnung;
    private String währung;
    private String preisMwst;
    private String versandkosten;
    private String versandkostenMwst;
    private String preisMitRabatt;
    private String preisOhneMwst;

    private String data;


     Article(String data){
        this.data = data;
        seperateData();
    }

    private void seperateData(){
        String[] positionsOfRawData = data.split("\t");
        stückzahl = (positionsOfRawData[Defines.Article.STÜCKZAHL]);
        artikelbezeichnung = (positionsOfRawData[Defines.Article.ARTIKLEBEZEICHNUNG]);
        währung = (positionsOfRawData[Defines.Article.WÄHRUNG]);
        preisMwst = (positionsOfRawData[Defines.Article.PREIS_MWST]);
        versandkosten = (positionsOfRawData[Defines.Article.VERSANDKOSTEN]);
        versandkostenMwst = (positionsOfRawData[Defines.Article.VERSANDKOSTEN_MWST]);
        preisMitRabatt = CalculateValues.calculateItemPrice(positionsOfRawData[Defines.Article.PREIS_OHNE_RABATTE], positionsOfRawData[Defines.Article.RABATTE]); //Artikelpreis
        preisOhneMwst=CalculateValues.calculatePriceWithoutMWST(preisMitRabatt,preisMwst);

    }


    public String getSTÜCKZAHL() {
        return stückzahl;
    }

    public String getARTIKLEBEZEICHNUNG() {
        return artikelbezeichnung;
    }

    public String getWÄHRUNG() {
        return währung;
    }

    public String getPREIS_MWST() {
        return preisMwst;
    }

    public String getVERSANDKOSTEN() {
        return versandkosten;
    }

    public String getVERSANDKOSTEN_MWST() {
        return versandkostenMwst;
    }

    public String getPREIS_MIT_RABATTE() {
        return preisMitRabatt;
    }

    public String getPREIS_OHNE_MWST() {
        return preisOhneMwst;
    }
}
