package yp2.pendragon.com.pl.article;

import java.math.BigDecimal;

/**
 * Created by daniel on 26.10.14.
 */
public class Article {
    private Integer id;
    private String name;
    private BigDecimal price;

    public Article(){
    }

    public Article (Integer id, String name, BigDecimal price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.name + " --- Price: " + this.price.setScale(2, BigDecimal.ROUND_DOWN).toString();
    }
}
