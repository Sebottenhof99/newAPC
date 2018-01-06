package logik;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antoshka on 31.12.2017.
 */
public class Article {
    private String STÜCKZAHL;
    private String ARTIKLEBEZEICHNUNG;
    private String WÄHRUNG;
    private String PREIS_MWST;
    private String VERSANDKOSTEN;
    private String VERSANDKOSTEN_MWST;
    private String RABATTE;
    private String PREIS_MIT_RABATTE;
    private String PREIS_OHNE_MWST;

    private String data;


    public Article(String data){
        this.data = data;
        seperateData();
    }

    public void seperateData(){
        String[] positionsOfRawData = data.split("\t");
        STÜCKZAHL = (positionsOfRawData[Defines.Article.STÜCKZAHL]);
        ARTIKLEBEZEICHNUNG = (positionsOfRawData[Defines.Article.ARTIKLEBEZEICHNUNG]);
        WÄHRUNG = (positionsOfRawData[Defines.Article.WÄHRUNG]);
        PREIS_MWST = (positionsOfRawData[Defines.Article.PREIS_MWST]);
        VERSANDKOSTEN = (positionsOfRawData[Defines.Article.VERSANDKOSTEN]);
        VERSANDKOSTEN_MWST = (positionsOfRawData[Defines.Article.VERSANDKOSTEN_MWST]);
        RABATTE = (positionsOfRawData[Defines.Article.RABATTE]);
        PREIS_MIT_RABATTE = CalculateValues.calculateItemPrice(positionsOfRawData[Defines.Article.PREIS_OHNE_RABATTE], RABATTE); //Artikelpreis
        PREIS_OHNE_MWST=CalculateValues.calculatePriceWithoutMWST(PREIS_MIT_RABATTE,PREIS_MWST);

    }

    public List<String> toList(){
        List<String> list = new ArrayList<String>();
        list.add(STÜCKZAHL);
        list.add(ARTIKLEBEZEICHNUNG);
        list.add(WÄHRUNG);
//...
        return list;
    }

    public String getSTÜCKZAHL() {
        return STÜCKZAHL;
    }
    public String getARTIKLEBEZEICHNUNG() {
        return ARTIKLEBEZEICHNUNG;
    }
    public String getWÄHRUNG() {
        return WÄHRUNG;
    }
    public String getPREIS_MWST() {
        return PREIS_MWST;
    }
    public String getVERSANDKOSTEN() {
        return VERSANDKOSTEN;
    }
    public String getVERSANDKOSTEN_MWST() {
        return VERSANDKOSTEN_MWST;
    }
    public String getPREIS_MIT_RABATTE() {
        return PREIS_MIT_RABATTE;
    }
    public String getPREIS_OHNE_MWST() {
        return PREIS_OHNE_MWST;
    }
}