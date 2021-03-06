package logik.defines;


public class Defines{

    public class Customer{
        public static final int BESTELLNUMMER = 0;
        public static final int NAME = 5;
        public static final int MAIL = 4;
        public static final int BESTELLDATUM = 3;
        public static final int ADRESSE1 = 17;
        public static final int ADRESSE2 = 18;
        public static final int STADT = 20;
        public static final int LAND = 23;
        public static final int PLZ = 22;
    }
    public class Article {
        public static final int STÜCKZAHL = 9;
        public static final int ARTIKLEBEZEICHNUNG = 8;
        public static final int WÄHRUNG = 10;
        public static final int PREIS_OHNE_RABATTE = 11;
        public static final int PREIS_MWST = 12;
        public static final int VERSANDKOSTEN= 13;
        public static final int VERSANDKOSTEN_MWST= 14;
        public static final int RABATTE= 25;

        public static final int SIZE_OF_ARTICLE_LIST = 6;
    }

    public class Paths{
       public static final String PATH_TO_LOGO = "handy-lux.jpg";
    }

    public class DBProperties{
        public final static String USERNAME = "db.user";
        public final static String PASSWORD = "db.password";
        public final static String URL = "db.url";
    }
}
