package yp2.pendragon.com.pl.devices;


import yp2.pendragon.com.pl.article.Article;
import yp2.pendragon.com.pl.sale.Sale;

import javax.swing.*;

/**
 * Created by daniel on 28.10.14.
 */
public class Printer implements OutDevice{
    private static Printer ourInstance = new Printer();
    DefaultListModel model;

    private Printer(){

    }

    @Override
    public void println(String msg) {
        this.model.addElement(msg);
    }

    @Override
    public void println(Article article) {
        this.model.addElement(article.toString());
    }

    @Override
    public void setOutput(DefaultListModel model) {
        this.model = model;
    }

    @Override
    public DefaultListModel getOutput() {
        return this.model;
    }

    @Override
    public void clear() {
        this.model.clear();
    }

    public static Printer getInstance(){
        return ourInstance;
    }

    public void printSale(Sale sale){
        String reciptHeder = "Sale " + sale.getSaleDate();
        this.println(reciptHeder);
        for(Article art: sale.getArticles()){
            this.println(art.toString());
        }
        this.println("Total sum: " + sale.getTotalSum());
    }
}
