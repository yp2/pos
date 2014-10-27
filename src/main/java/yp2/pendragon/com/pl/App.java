package yp2.pendragon.com.pl;

import yp2.pendragon.com.pl.article.Article;
import yp2.pendragon.com.pl.engine.Engine;
import yp2.pendragon.com.pl.gui.PosWindow;
import yp2.pendragon.com.pl.scanner.BarCodeScanner;
import yp2.pendragon.com.pl.scanner.LabelReadHandler;


import javax.swing.*;
import java.awt.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        final DefaultListModel<Article> lcdModel = new DefaultListModel<Article>();
        final DefaultListModel<Article> printerModel = new DefaultListModel<Article>();
        final BarCodeScanner scanner = BarCodeScanner.getInstance();
        //setting up engine
        final Engine engine = Engine.getInstnace();
        engine.setLcdModel(lcdModel);
        engine.setPrinterModel(printerModel);

        final LabelReadHandler labelReadHandler = new LabelReadHandler();


        scanner.addObserver(labelReadHandler);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame posWindow = new PosWindow(scanner, lcdModel, printerModel);
                posWindow.setTitle("POS");
                posWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                posWindow.pack();
                posWindow.setVisible(true);
            }
        });
    }
}
