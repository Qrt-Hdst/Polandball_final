package Polandball_pliki.GameObjects;

import java.awt.image.BufferedImage;

/**
 * Klasa przodek dla wszystkich obiektów nieruchomych/nieożywionych
 */
public class StationaryObject extends GameObject {
    /**
     * konstruktor obiektu nieruchomego/nieozywionego
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public StationaryObject(int x,int y){
        super();
        x_=x;
        y_=y;
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
