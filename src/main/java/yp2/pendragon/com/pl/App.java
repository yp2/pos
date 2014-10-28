package yp2.pendragon.com.pl;

import yp2.pendragon.com.pl.article.Article;
import yp2.pendragon.com.pl.devices.LcdDisplay;
import yp2.pendragon.com.pl.devices.Printer;
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
        // init of io devices
        final LcdDisplay lcdDisplay = LcdDisplay.getInstance();
        final Printer printer = Printer.getInstance();
        final BarCodeScanner scanner = BarCodeScanner.getInstance();
        //setting up engine
        final Engine engine = Engine.getInstnace();
        engine.setLcdDisplay(lcdDisplay);
        engine.setPrinter(printer);

        //setting observer to scanner
        final LabelReadHandler labelReadHandler = new LabelReadHandler();
        scanner.addObserver(labelReadHandler);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame posWindow = new PosWindow();
                posWindow.setTitle("POS");
                posWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                posWindow.pack();
                posWindow.setVisible(true);
            }
        });
    }
}
