package yp2.pendragon.com.pl.sale;

import yp2.pendragon.com.pl.article.Article;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by daniel on 26.10.14.
 */
public class Sale {
    private List<Article> articles = new LinkedList<Article>();
    private Date saleDate;
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/dd HH:mm:ss");
    private BigDecimal sum = new BigDecimal(0);

    public Sale(){
        saleDate = new Date();
        System.out.println(sdf.format(saleDate));
    }

    public void addArticle(Article newArticle){
        articles.add(newArticle);
        sum = sum.add(newArticle.getPrice());
    }

    public String getSaleDate(){
        return sdf.format(saleDate);
    }

    public String getTotalSum(){
        return sum.setScale(2, BigDecimal.ROUND_DOWN).toString();
    }

    public List<Article> getArticles(){
        return articles;
    }


}
