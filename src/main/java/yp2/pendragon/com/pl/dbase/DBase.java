package yp2.pendragon.com.pl.dbase;

import yp2.pendragon.com.pl.article.Article;

import java.math.BigDecimal;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by daniel on 27.10.14.
 */
public class DBase {
    private static DBase ourInstance = new DBase();
    private Dictionary articleDBase = new Hashtable();

    private DBase(){
        // adding articles to articleDBase
        articleDBase.put("11", new Article(1, "Apple", new BigDecimal(10.34)));
        articleDBase.put("12", new Article(2, "Orange", new BigDecimal(15.21)));
        articleDBase.put("13", new Article(3, "Carrot", new BigDecimal(3.00)));
        articleDBase.put("14", new Article(4, "Apple Juice", new BigDecimal(56.12)));
        articleDBase.put("15", new Article(5, "Orange Juice", new BigDecimal(67.56)));
        articleDBase.put("16", new Article(6, "Carrot Juice", new BigDecimal(40.12)));
    }

    public static DBase getInstance(){
        return ourInstance;
    }

    public Article getAricle(String label){
        return (Article) articleDBase.get(label);
    }
}
