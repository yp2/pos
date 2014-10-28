package yp2.pendragon.com.pl.gui;

import yp2.pendragon.com.pl.article.Article;
import yp2.pendragon.com.pl.devices.LcdDisplay;
import yp2.pendragon.com.pl.devices.OutDevice;
import yp2.pendragon.com.pl.devices.Printer;
import yp2.pendragon.com.pl.engine.Engine;
import yp2.pendragon.com.pl.scanner.BarCodeScanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by daniel on 26.10.14.
 */
public class PosWindow extends JFrame {

    private JPanel labelPanel;
    private JPanel panePanel;
    private JPanel southPanel;
    private JLabel lcdLabel;
    private JLabel printerLabel;
    private JScrollPane lcdPane;
    private JScrollPane printerPane;
    private JList lcdList;
    private JList printerList;
    private JPanel scannerPanel;
    private JPanel buttonPanel;
    private JTextField scannerInput;
    private JButton saleButton;
    private JButton exitButton;
    private JButton readButton;
    private DefaultListModel lcdModel;
    private DefaultListModel printerModel;
    private Engine engine = Engine.getInstnace();
    private BarCodeScanner scanner = BarCodeScanner.getInstance();
    private OutDevice lcdDisplay = LcdDisplay.getInstance();
    private OutDevice printer = Printer.getInstance();

    public PosWindow (){

        this.lcdModel = new DefaultListModel();
        this.printerModel = new DefaultListModel();
        lcdDisplay.setOutput(lcdModel);
        printer.setOutput(printerModel);

        //gui elements
        labelPanel = new JPanel(new GridLayout(1,2));
        panePanel = new JPanel(new GridLayout(1,2));
        southPanel = new JPanel();
        lcdLabel = new JLabel("LCD");
        printerLabel = new JLabel("Printer");

        lcdList = new JList(lcdModel);
        lcdPane = new JScrollPane(lcdList);

        printerList = new JList(printerModel);
        printerPane = new JScrollPane(printerList);

        scannerPanel = new JPanel();
        buttonPanel = new JPanel(new GridLayout(2,1));
        scannerInput = new JTextField();
        saleButton = new JButton("Sale");
        exitButton = new JButton("Exit");
        readButton = new JButton("Read Label");

        readButton.addActionListener(new ReadBarcodeListener());
        saleButton.addActionListener(new SaleListener());
        exitButton.addActionListener(new ExitListener());

        buttonPanel.add(saleButton);
        buttonPanel.add(exitButton);

        scannerInput.setColumns(10);
        scannerPanel.add(new JLabel("Scanner Label Input"));
        scannerPanel.add(scannerInput);
        scannerPanel.add(readButton);

        labelPanel.add(lcdLabel);
        labelPanel.add(printerLabel);

        panePanel.add(lcdPane);
        panePanel.add(printerPane);
        panePanel.setPreferredSize(new Dimension(400, 400));
        buttonPanel.setPreferredSize(new Dimension(100, 50));

        southPanel.add(scannerPanel, BorderLayout.WEST);
        southPanel.add(buttonPanel, BorderLayout.EAST);

        this.add(labelPanel, BorderLayout.NORTH);
        this.add(panePanel, BorderLayout.CENTER);
        this.add(southPanel, BorderLayout.SOUTH);

        // setting readButton to inactive
        readButton.setEnabled(false);
        // setting exitButton to inactive
        exitButton.setEnabled(false);
        scannerInput.setEditable(false);
    }

    class ReadBarcodeListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String label = scannerInput.getText();
            scanner.readLabel(label);
            scannerInput.setText("");
        }
    }

    class SaleListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //setting active read and exit button
            readButton.setEnabled(true);
            exitButton.setEnabled(true);
            scannerInput.setEditable(true);

            //setting sale button inactive
            saleButton.setEnabled(false);

            //engine new sale
            engine.newSale();
        }
    }

    class ExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            //setting inactive read and exit button
            readButton.setEnabled(false);
            exitButton.setEnabled(false);
            scannerInput.setEditable(false);

            //setting sale button active
            saleButton.setEnabled(true);

            //engine end sale
            engine.endSale();
        }
    }
}
