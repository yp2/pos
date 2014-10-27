package yp2.pendragon.com.pl.scanner;

import yp2.pendragon.com.pl.engine.Engine;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by daniel on 26.10.14.
 */
public class LabelReadHandler implements Observer {
    private Engine engine;

    public LabelReadHandler(){
        super();
        this.engine = Engine.getInstnace();
    }

    @Override
    public void update(Observable scanner, Object arg) {
        String label = ((BarCodeScanner) scanner).getLabel();
        engine.addReadedLabel(label);
        System.out.println("Scanner Readed Label: " + label);
    }
}
