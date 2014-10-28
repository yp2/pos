package yp2.pendragon.com.pl;

import org.junit.*;
import yp2.pendragon.com.pl.article.Article;
import yp2.pendragon.com.pl.dbase.DBase;
import yp2.pendragon.com.pl.engine.Engine;
import yp2.pendragon.com.pl.sale.Sale;

import javax.swing.*;

import static org.junit.Assert.*;


import java.math.BigDecimal;

/**
 * Unit test for simple App.
 */
public class AppTest {
    DBase dBase = DBase.getInstance();
    Engine engine = Engine.getInstnace();
    DefaultListModel<Article> lcdModel = new DefaultListModel<Article>();
    DefaultListModel<Article> printerModel = new DefaultListModel<Article>();


    @Test
    public void articleTest(){
        Article article = new Article(1, "Apple", new BigDecimal(10));
        assertEquals(article.getId(), new Integer(1));
        assertEquals(article.getName(), "Apple");
        assertEquals(article.getPrice(), new BigDecimal(10));
    }

    @Test
    public void dBaseTest(){
        Article goodLabel = dBase.getAricle("11");
        Article badLabel = dBase.getAricle("22");
        assertEquals(goodLabel.getName(), "Apple");
        assertNull(badLabel);
    }

//    @Test
//    public void engineModelTest(){
//        printerModel.clear();
//        lcdModel.clear();
//        engine.setPrinter(printerModel);
//        engine.setLcdDisplay(lcdModel);
//
//        //start new sale
//        engine.newSale();
//        engine.addReadedLabel("11");
//        assertEquals(1, lcdModel.getSize());
//
//        //endsale
//        engine.endSale();
//        assertEquals(2, lcdModel.getSize());
//        assertEquals(3, printerModel.getSize());
//    }

//    @Test
//    public void engineEndNewSaleTest(){
//        printerModel.clear();
//        lcdModel.clear();
//        engine.setPrinter(printerModel);
//        engine.setLcdDisplay(lcdModel);
//        // ensure that engine newSale is false
//        engine.endSale();
//
//        // try read label
//        engine.addReadedLabel("11");
//        assertEquals(0, lcdModel.getSize());
//
//        // try endSale
//        engine.endSale();
//        assertEquals(0, printerModel.getSize());
//    }

    @Test
    public void saleTest(){
        Sale sale = new Sale();

        // add articles
        sale.addArticle(dBase.getAricle("11"));
        sale.addArticle(dBase.getAricle("12"));

        assertEquals(2, sale.getArticles().size());
        assertEquals(new BigDecimal(25.55).setScale(2, BigDecimal.ROUND_DOWN).toString(),
                sale.getTotalSum());
    }



}
