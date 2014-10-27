package yp2.pendragon.com.pl.engine;

import yp2.pendragon.com.pl.article.ArtLine;
import yp2.pendragon.com.pl.article.Article;
import yp2.pendragon.com.pl.dbase.DBase;
import yp2.pendragon.com.pl.sale.Sale;

import javax.swing.*;

/**
 * Created by daniel on 27.10.14.
 */
public class Engine {
    private static Engine ourInstance = new Engine();
    private DBase dBase = DBase.getInstance();
    private DefaultListModel<Article> lcdModel;
    private DefaultListModel<Article> printerModel;
    private boolean newSale = false;
    private Sale sale = null;

    private Engine(){

    }

    public static Engine getInstnace(){
        return ourInstance;
    }

    public void setLcdModel(DefaultListModel<Article> lcdModel){
        this.lcdModel = lcdModel;
    }

    public void setPrinterModel(DefaultListModel<Article> printerModel){
        this.printerModel = printerModel;
    }

    public void newSale(){
        if (newSale == false){
            //new sale
            this.sale = new Sale();
            this.newSale = true;
            // clear lcd
            this.lcdModel.clear();
            this.printerModel.clear();
        }
    }

    public void endSale(){
        //end sale
        if (newSale == true){
            this.newSale = false;
            this.lcdModel.addElement(new ArtLine("Total sum: " + this.sale.getTotalSum()));

            //recipt print
            String reciptHeder = "Sale " + this.sale.getSaleDate();
            printerModel.addElement(new ArtLine(reciptHeder));

            for (Article art: sale.getArticles()){
                this.printerModel.addElement(art);
            }
            this.printerModel.addElement(new ArtLine("Total sum: " + this.sale.getTotalSum()));
        }
    }

    public void addReadedLabel(String label){
        Article scannedArticle;
        if (newSale){
            if (label.equals("")){
                // readed barcode empty
                System.out.println("Invalid bar-code");
                lcdModel.addElement(new ArtLine("Invalid bar-code"));
            } else {
                scannedArticle = dBase.getAricle(label);
                if (scannedArticle == null){
                    System.out.println("Product not found");
                    lcdModel.addElement(new ArtLine("Product not found"));
                } else {
                    // add article to lcd and to sale
                    System.out.println(scannedArticle.toString());
                    lcdModel.addElement(scannedArticle);
                    this.sale.addArticle(scannedArticle);
                }
            }
        }
    }
}
