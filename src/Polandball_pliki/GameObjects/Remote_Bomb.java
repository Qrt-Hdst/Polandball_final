package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.Normal_BombString;
import static Polandball_pliki.GetConstans.Remote_BombString;

/**
 * Created by Matball on 2017-05-09.
 */
public class Remote_Bomb extends Bomb {

    /**
     * konstruktor obiektu klucz
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Remote_Bomb(int x, int y){
        super();
        x_=x;
        y_=y;
        buffImage_=createBufferedImage();
        image_=null;
        //image_=createImageGIF();
        name_class_object=Remote_BombString;
        explosionflag_=false;
    }

    /**
     * metoda udostepniająca obiektowi grafike
     * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
     */
    BufferedImage createBufferedImage(){
        try {
            //Image icon =new ImageIcon(new URL())
            File file = new File(Remote_BombString);
            BufferedImage bufferedImage= ImageIO.read(file);

            return bufferedImage;
        }
        catch(IOException e ){
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu normal_bomb");
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu normal_bomb");
        }
        return null;
    }
    /**
    *   metoda zwracajace zdjecie bedace gif-em <3
     *   ( uwaga w panelboard, by gif byl rysowany, musi byc  parametr observer  w drawImage  musi byc ustawiony jako "this")
     *   UWAGA - w przyszlosci bedzie chyba lepiej zastapic metody BufferedImage przez createImageGIF, jest bardziej uniwersalna
    */
    //na razie nie planuje gifa dla tej bomby
    /*Image createImageGIF(){
        try{
            image_= Toolkit.getDefaultToolkit().createImage(Normal_BombString); //ladowanie do pliku
            return image_;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Blad wczytywania obiektu typu normal_bomb");
        }
        return null;
    }*/


}
