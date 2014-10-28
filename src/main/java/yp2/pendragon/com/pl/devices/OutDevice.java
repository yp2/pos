package yp2.pendragon.com.pl.devices;

import yp2.pendragon.com.pl.article.Article;

import javax.swing.*;

/**
 * Created by daniel on 28.10.14.
 */
public interface OutDevice {

    public void println(String msg);

    public void println(Article article);

    public void setOutput(DefaultListModel model);

    public DefaultListModel getOutput();

    public void clear();

}
