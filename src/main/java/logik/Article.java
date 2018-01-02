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
    private String PREIS_OHNE_RABATTE;
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
        PREIS_OHNE_RABATTE = (positionsOfRawData[Defines.Article.PREIS_OHNE_RABATTE]);
        PREIS_MWST = (positionsOfRawData[Defines.Article.PREIS_MWST]);
        VERSANDKOSTEN = (positionsOfRawData[Defines.Article.VERSANDKOSTEN]);
        VERSANDKOSTEN_MWST = (positionsOfRawData[Defines.Article.VERSANDKOSTEN_MWST]);
        RABATTE = (positionsOfRawData[Defines.Article.RABATTE]);
        PREIS_MIT_RABATTE = CalculateValues.calculateItemPrice(PREIS_OHNE_RABATTE, RABATTE); //Artikelpreis
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
    public void setSTÜCKZAHL(String STÜCKZAHL) {
        this.STÜCKZAHL = STÜCKZAHL;
    }
    public String getARTIKLEBEZEICHNUNG() {
        return ARTIKLEBEZEICHNUNG;
    }
    public void setARTIKLEBEZEICHNUNG(String ARTIKLEBEZEICHNUNG) {
        this.ARTIKLEBEZEICHNUNG = ARTIKLEBEZEICHNUNG;
    }
    public String getWÄHRUNG() {
        return WÄHRUNG;
    }
    public void setWÄHRUNG(String WÄHRUNG) {
        this.WÄHRUNG = WÄHRUNG;
    }
    public String getPREIS_OHNE_RABATTE() {
        return PREIS_OHNE_RABATTE;
    }
    public void setPREIS_OHNE_RABATTE(String PREIS_OHNE_RABATTE) {
        this.PREIS_OHNE_RABATTE = PREIS_OHNE_RABATTE;
    }
    public String getPREIS_MWST() {
        return PREIS_MWST;
    }
    public void setPREIS_MWST(String PREIS_MWST) {
        this.PREIS_MWST = PREIS_MWST;
    }
    public String getVERSANDKOSTEN() {
        return VERSANDKOSTEN;
    }
    public void setVERSANDKOSTEN(String VERSANDKOSTEN) {
        this.VERSANDKOSTEN = VERSANDKOSTEN;
    }
    public String getVERSANDKOSTEN_MWST() {
        return VERSANDKOSTEN_MWST;
    }
    public void setVERSANDKOSTEN_MWST(String VERSANDKOSTEN_MWST) {
        this.VERSANDKOSTEN_MWST = VERSANDKOSTEN_MWST;
    }
    public String getRABATTE() {
        return RABATTE;
    }
    public void setRABATTE(String RABATTE) {
        this.RABATTE = RABATTE;
    }
    public String getPREIS_MIT_RABATTE() {
        return PREIS_MIT_RABATTE;
    }
    public void setPREIS_MIT_RABATTE(String PREIS_MIT_RABATTE) {
        this.PREIS_MIT_RABATTE = PREIS_MIT_RABATTE;
    }
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
}
