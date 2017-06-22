package Polandball_pliki.GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.DoorString;

/**
 * Klasa reprezentujaca drzwi do nastepnego poziomu
 */
public class Door extends Item {
    /**
     * Konstruktor klasy Door
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Door(int x,int y){
        super();
        x_=x;
        y_=y;
        name_class_object=DoorString;
        buffImage_= GetConstans.DoorBuff;
    }

}
