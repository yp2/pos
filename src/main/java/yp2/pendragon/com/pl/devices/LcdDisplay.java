package yp2.pendragon.com.pl.devices;

import yp2.pendragon.com.pl.article.Article;

import javax.swing.*;

/**
 * Created by daniel on 28.10.14.
 */
public class LcdDisplay implements OutDevice {

    private static LcdDisplay ourInstance = new LcdDisplay();
    DefaultListModel model;

    private LcdDisplay(){

    }

    public static LcdDisplay getInstance(){
        return ourInstance;
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
    public void clear(){
        this.model.clear();
    }
}
