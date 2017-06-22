package Polandball_pliki.GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.SkrzynkaString;

/**
 * Klasa reprezentujaca skrzynke - zniszczalny element terenu
 */
public class Skrzynka extends Terrain {
    /**
     * Konstruktor klasy Skrzynka
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Skrzynka(int x,int y){
        super();
        x_=x;
        y_=y;
        name_class_object=SkrzynkaString;
        buffImage_= GetConstans.SkrzynkaBuff;
    }

}
