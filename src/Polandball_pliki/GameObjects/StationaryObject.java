package Polandball_pliki.GameObjects;

import java.awt.image.BufferedImage;

/**
 * Klasa przodek dla wszystkich obiektów nieruchomych/nieożywionych
 */
public class StationaryObject extends GameObject {

    int numberofline_;
    int numberofcolumn_;

    /**
     * konstruktor obiektu nieruchomego/nieozywionego
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public StationaryObject(int x,int y, int numberofline, int numberofcolumn){
        super();
        x_=x;
        y_=y;
        numberofline_=numberofline;
        numberofcolumn_=numberofcolumn;
        buffImage_=null;
        name_class_object=null;
    }

    /**
     * konstruktor bezparametrowy
     */
    public StationaryObject(){
        super();
        x_=0;
        y_=0;
        buffImage_=null;
    }
}
