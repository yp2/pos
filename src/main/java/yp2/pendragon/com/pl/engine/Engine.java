package yp2.pendragon.com.pl.engine;

import yp2.pendragon.com.pl.article.ArtLine;
import yp2.pendragon.com.pl.article.Article;
import yp2.pendragon.com.pl.dbase.DBase;
import yp2.pendragon.com.pl.devices.LcdDisplay;
import yp2.pendragon.com.pl.devices.OutDevice;
import yp2.pendragon.com.pl.devices.Printer;
import yp2.pendragon.com.pl.sale.Sale;

import javax.swing.*;

/**
 * Created by daniel on 27.10.14.
 */
public class Engine {
    private static Engine ourInstance = new Engine();
    private DBase dBase = DBase.getInstance();
    private LcdDisplay lcdDisplay;
    private Printer printer;
    private boolean newSale = false;
    private Sale sale = null;

    private Engine(){

    }

    public static Engine getInstnace(){
        return ourInstance;
    }

    public void setLcdDisplay (OutDevice outDevice){
        this.lcdDisplay = (LcdDisplay) outDevice;
    }

    public void setPrinter(Printer printer){
        this.printer = printer;
    }

    public void newSale(){
        if (newSale == false){
            //new sale
            this.sale = new Sale();
            this.newSale = true;
            // clear lcd
            this.lcdDisplay.clear();
            this.printer.clear();
        }
    }

    public void endSale(){
        //end sale
        if (newSale == true){
            this.newSale = false;
            if (this.sale.getArticles().size() != 0) {
                //lcd total sum
                this.lcdDisplay.println("Total sum: " + this.sale.getTotalSum());

                //recipt print
                this.printer.printSale(this.sale);
            }
        }
    }

    public void addReadedLabel(String label){
        Article scannedArticle;
        if (newSale){
            if (label.equals("")){
                // readed barcode empty
                String msg = "Invalid bar-code";
                System.out.println(msg);
                lcdDisplay.println(msg);
            } else {
                scannedArticle = dBase.getAricle(label);
                if (scannedArticle == null){
                    String msg = "Product not found";
                    System.out.println(msg);
                    lcdDisplay.println(msg);
                } else {
                    // add article to lcd and to sale
                    System.out.println(scannedArticle.toString());
                    lcdDisplay.println(scannedArticle);
                    this.sale.addArticle(scannedArticle);
                }
            }
        }
    }
}
