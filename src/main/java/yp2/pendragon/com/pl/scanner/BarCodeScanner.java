package yp2.pendragon.com.pl.scanner;

import java.util.Observable;

/**
 * Created by daniel on 26.10.14.
 */
public class BarCodeScanner extends Observable {

    private static BarCodeScanner ourInstance = new BarCodeScanner();
    private String barCode;

    private BarCodeScanner() {
    }

    public static BarCodeScanner getInstance(){
        return ourInstance;
    }

    public void readLabel(String barCode){
        this.barCode = barCode;
        setChanged();
        notifyObservers();
    }
    public String getLabel(){
        return barCode;
    }

}
