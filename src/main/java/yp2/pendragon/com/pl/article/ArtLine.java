package yp2.pendragon.com.pl.article;

/**
 * Created by daniel on 27.10.14.
 */
public class ArtLine extends Article{
    private String msg;

    public ArtLine(String msg){
        this.msg = msg;
    }

    public String toString(){
        return this.msg;
    }
}
