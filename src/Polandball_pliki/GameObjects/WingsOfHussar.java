package Polandball_pliki.GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.WingsOfHussarString;

/**
 * Klasa reprezentujaca skrzydla husarskie
 */
public class WingsOfHussar extends Item {
    /**
     * Konstruktor klasy WingsOfHussar
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public WingsOfHussar(int x, int y){
        super();
        x_=x;
        y_=y;
        name_class_object=WingsOfHussarString;
        buffImage_= GetConstans.WingsOfHussarBuff;
    }

}
