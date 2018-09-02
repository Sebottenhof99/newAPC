
 package logik;

 import java.io.File;
 import java.io.FileInputStream;
 import java.io.FileOutputStream;
 import java.io.IOException;
 import java.time.LocalDate;
 import java.time.format.DateTimeFormatter;
 import java.time.format.FormatStyle;
 import java.util.List;
 import java.util.Properties;
 import com.itextpdf.io.image.ImageDataFactory;

 import com.itextpdf.kernel.color.Color;
 import com.itextpdf.kernel.geom.PageSize;
 import com.itextpdf.kernel.pdf.PdfDocument;
 import com.itextpdf.kernel.pdf.PdfWriter;
 import com.itextpdf.layout.Document;
 import com.itextpdf.layout.border.Border;
 import com.itextpdf.layout.element.*;
 import com.itextpdf.layout.property.HorizontalAlignment;
 import com.itextpdf.layout.property.TextAlignment;
 import logik.defines.Defines;


 public class CreatePdf {
    Customer customer;
    String pathOfPropsFile;
    public CreatePdf(String pathOfPropsFile){
        this.pathOfPropsFile=pathOfPropsFile;
    }
    public  final String DEST = ProvideResults.getActualInvoicePath();
    public  final String LOGO = this.getClass().getClassLoader().getResource(Defines.Paths.PATH_TO_LOGO).toExternalForm().replace("file:\\","").trim();

    public void manipulatePdf(Customer customer) throws Exception {
        this.customer = customer;

        String filename = "Rechnung"+ "_" + customer.getBestellnummer() + ".pdf";
        customer.setSavedFileName(filename);
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST + filename));


        PageSize ps = PageSize.A4;
        Document doc = new Document(pdfDoc, ps);
        Image logo = new Image(ImageDataFactory.create(LOGO)).setHeight(75).setWidth(250);
        Table table0 = new Table(2);
        Cell logotip = new Cell().add(logo).setBorder(Border.NO_BORDER).setHorizontalAlignment(HorizontalAlignment.LEFT);


        Properties props = new Properties();
        props.load(new FileInputStream("C:\\Users\\"+System.getProperty("user.name")+"\\Desktop\\Ama_Rechnungen\\config_invoice.txt"));


        Text nameOfBesitzer =  new Text(props.getProperty("Name"))
                .setBold();
        Paragraph p1 = new Paragraph()
                .setTextAlignment(TextAlignment.RIGHT)
                .setFontSize(10)
                .setFixedLeading(10);
        p1.add(nameOfBesitzer);
        p1.add("\n" +
                props.getProperty("Street")+"\n" +
                props.getProperty("City")+"\n" +
                props.getProperty("Country")+"\n" +
                props.getProperty("Ust-ID")+"\n" +
                props.getProperty("Mail_Adress"));


        Cell besitzer = new Cell().add(p1).setBorder(Border.NO_BORDER);
        table0.addCell(logotip);
        table0.addCell(besitzer);

        Text nameOfCustomer = new Text(customer.getName()).setBold();

        Paragraph dataOfCustomer = new Paragraph()
                .setFontSize(10)
                .setFixedLeading(10);
        dataOfCustomer.add(nameOfCustomer);
        dataOfCustomer.add( "\n" +
                customer.getAdress1() + "\n" +
                customer.getAdress2() + "\n" +
                customer.getPostalCode() + " " + customer.getCity() + "\n" +
                customer.getCountry() + "\n\n" +
                customer.getMail());

        Cell käufer = new Cell().add(dataOfCustomer).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.LEFT);


        Paragraph p3 = new Paragraph(getInvoiceID()+"\n")
                .setFontSize(16)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBold();
        Cell rechnungsn = new Cell().add(p3).setBorder(Border.NO_BORDER).setTextAlignment(TextAlignment.RIGHT);
        table0.addCell(käufer);
        table0.addCell(rechnungsn);

        doc.add(table0);
        doc.add(new Paragraph());




        Table table = new Table(2)
                .setTextAlignment(TextAlignment.LEFT)
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setWidthPercent(50)
                .setFontSize(10);

        table.addCell(new Cell().add("Datum")).setFontColor(Color.DARK_GRAY).setBold();
        table.addCell(new Cell().add("Bestellnummer")).setFontColor(Color.DARK_GRAY).setBold();;
        table.addCell(new Cell().add(customer.getBestelldatum()));
        table.addCell(new Cell().add(customer.getBestellnummer()));

        doc.add(table);
        doc.add(new Paragraph());
        doc.add(new Paragraph());

        Table table2 = new Table(100)
                .setTextAlignment(TextAlignment.LEFT)
                .setHorizontalAlignment(HorizontalAlignment.RIGHT)
                .setFontSize(10)
                .setWidthPercent(100);


        Cell stückZahl = new Cell(1, 5).add("Stückzahl").setFontColor(Color.DARK_GRAY).setBold();
        table2.addCell(stückZahl);
        Cell artikelBezeichnung = new Cell(1, 55).add("Artikelbezeichnung").setFontColor(Color.DARK_GRAY).setBold();
        table2.addCell(artikelBezeichnung);
        Cell netto = new Cell(1, 10).add("Netto "+ customer.getListOfAllArticles().get(0).getWÄHRUNG() ).setFontColor(Color.DARK_GRAY).setBold();
        table2.addCell(netto);
        Cell MwSt = new Cell(1, 5).add("MwSt.").setFontColor(Color.DARK_GRAY).setBold();
        table2.addCell(MwSt);
        Cell preis = new Cell(1, 10).add("MwSt "+customer.getListOfAllArticles().get(0).getWÄHRUNG()).setFontColor(Color.DARK_GRAY).setBold();
        table2.addCell(preis);
        Cell zwischensumme = new Cell(1, 15).add("Zwischensumme "+ customer.getListOfAllArticles().get(0).getWÄHRUNG()).setFontColor(Color.DARK_GRAY).setBold();
        table2.addCell(zwischensumme);

        addArticlesToTable(table2, customer.getListOfAllArticles());
        addVersandKosten(table2,customer);
        doc.add(table2);
        Paragraph p4 = new Paragraph("Gesamtnettobetrag "+customer.getListOfAllArticles().get(0).getWÄHRUNG()+" "+customer.getNettoPrice()+"\n"+
                "Mehrwertsteuerbetrag "+customer.getListOfAllArticles().get(0).getWÄHRUNG()+" "+ customer.getMwsTSum()+"\n")
              //  "Rabatte (-) oder weitere Kosten (+):  EUR " + customer.getRabatt()+"\n")
                .setTextAlignment(TextAlignment.RIGHT).setFontSize(10)
                .setFixedLeading(14);
        Text gesBetrag = new Text("Gesamtbetrag inkl. MwSt. " +customer.getListOfAllArticles().get(0).getWÄHRUNG() + " ").setBold();
        Text summe = new Text(customer.getPrice());
        p4.add(gesBetrag);
        p4.add(summe);

        doc.add(p4);
        Paragraph p8 = new Paragraph("______________________________________________________________________________");
        Paragraph p9 = new Paragraph(props.getProperty("Finanzamt")+"\n" +
                props.getProperty("Steuernummer")+"\n" +
                props.getProperty("Gerichtstand")+"\n" +
                props.getProperty("Bankdaten")+"\n" +
                props.getProperty("BIC")+"\n" +
                "Rechnungsdatum entspricht Lieferdatum")
                .setTextAlignment(TextAlignment.CENTER).setFixedLeading(12);

        doc.add(p8);
        doc.add(p9);
        doc.close();
    }

    public String getInvoiceID() throws IOException {
        String RECHNUNGSNUMMER="";

        RECHNUNGSNUMMER+= LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)).replace(".","") + "A";
        Properties p = new Properties();
        File f = new File(pathOfPropsFile+"props.txt");

        if (f.exists()) {
            p.load(new FileInputStream(f.getAbsolutePath()));

            String s = p.getProperty("RechnungsID");
            int j = Integer.parseInt(s);
            s=String.valueOf(++j);
            RECHNUNGSNUMMER+=s;
            p.setProperty("RechnungsID", s);
            p.store(new FileOutputStream(f.getAbsolutePath()), null);

        } else {
            p.setProperty("RechnungsID", "1");
            RECHNUNGSNUMMER+="1";
            p.store(new FileOutputStream(f.getAbsolutePath()), null);

        }
        return RECHNUNGSNUMMER;
    }


    public void addArticlesToTable(Table table, List<Article> list){

            for (int i = 0; i <list.size() ; i++) {
                for (int j = 0; j <Defines.Article.SIZE_OF_ARTICLE_LIST ; j++) {
                    switch (j){

                            case 0:table.addCell(new Cell(1,5).add((String) list.get(i).getSTÜCKZAHL()));
                                break;
                            case 1:table.addCell(new Cell(1,55).add(new Paragraph((String) list.get(i).getARTIKLEBEZEICHNUNG()).setFixedLeading(10)));
                                break;
                            case 2:table.addCell(new Cell(1,10).add((String) list.get(i).getPREIS_OHNE_MWST()));
                                break;
                             case 3:table.addCell(new Cell(1,5).add((String) "19%"));
                                break;
                            case 4:table.addCell(new Cell(1,10).add((String) list.get(i).getPREIS_MWST()));
                                break;
                            case 5:table.addCell(new Cell(1,15).add((String)list.get(i).getPREIS_MIT_RABATTE() ));
                                break;
                    }
                }
            }
        }



    public void addVersandKosten(Table table, Customer customer){

        table.addCell(new Cell(1,5).add("1"));
        table.addCell(new Cell(1,55).add("Versand und Verpackung"));
        table.addCell(new Cell(1,10).add(customer.getShippingCostNetto()));
        if (customer.getShippingCostNetto().equals("")){
            table.addCell(new Cell(1,5).add(("")));
        }else{
            table.addCell(new Cell(1,5).add(("19%")));
        }
        table.addCell(new Cell(1,10).add(customer.getShippingCostMwSt()));
        table.addCell(new Cell(1,15).add(customer.getShippingCost()));
    }


}


