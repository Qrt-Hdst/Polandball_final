package Polandball_pliki.GameObjects;

import java.awt.image.BufferedImage;

/**
 * Created by Matball on 2017-04-17.
 */
public class StationaryObject extends GameObject {
    /**
     * konstruktor obiektu
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public StationaryObject(int x,int y){
        super();
        x_=x;
        y_=y;
        buffImage_=null;
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
