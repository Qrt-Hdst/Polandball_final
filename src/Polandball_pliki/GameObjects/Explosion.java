package Polandball_pliki.GameObjects;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.GetConstans.ExplosionString;
import static Polandball_pliki.GetConstans.NaziBallString;
import static Polandball_pliki.GetConstans.Normal_BombString;

/**
 * Created by Matball on 2017-05-20.
 */

/**
 * Klasa Eksplozja dziedziczaca po obiekcie GameObject
 */
public class Explosion extends GameObject {
        /**
         * flaga czy eksplozja sie skonczyla
         */
        boolean end_of_explosion_;
        /**
         * Zmienna, przechowujaca gif
         */
        Image image_;
        /**
         * konstruktor obiektu typu eksplozja
         * @param x obecne polozenie obiektu na osi x
         * @param y obecne polozenie obiektu na osi y
         */
        public Explosion(int x,int y){
            super();
            name_class_object=ExplosionString;
            x_=x;
            y_=y;
            buffImage_=createBufferedImage();
            image_=createImageGIF();
            end_of_explosion_=false;
        }

        /**
         * konstruktor bezparametrowy
         */
        public Explosion(){
            super();
            name_class_object=ExplosionString;
            x_=0;
            y_=0;
            buffImage_=null;
            image_=null;
            end_of_explosion_=false;
        }

        /**
         * metoda udostepniająca obiektowi grafike
         * @return zwraca stworzone zdjecie, lub w wypadku zlapania wyjatku -null
         */
        BufferedImage createBufferedImage(){
            try {
                File file = new File(ExplosionString);
                BufferedImage bufferedImage= ImageIO.read(file);

                return bufferedImage;
            }
            catch(IOException e ){
                e.printStackTrace();

                System.out.println("Blad wczytywania obiektu1");
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Blad wczytywania obiektu2");
            }
            return null;
        }
        /**
         *   metoda zwracajace zdjecie bedace gif-em <3
         *   ( uwaga w panelboard, by gif byl rysowany, musi byc  parametr observer  w drawImage  musi byc ustawiony jako "this")
         *   UWAGA - w przyszlosci bedzie chyba lepiej zastapic metody BufferedImage przez createImageGIF, jest bardziej uniwersalna
         */
        Image createImageGIF(){
            try{
                image_= Toolkit.getDefaultToolkit().createImage(ExplosionString); //ladowanie do pliku
                return image_;
            }catch (Exception e) {
                e.printStackTrace();
                System.out.println("Blad wczytywania obiektu3");
            }
            return null;
        }

        /**
         * ZWraca Image bedacy gifem
         *
         * @return gif z eksplozja
         */
        public Image getGIF() {
            return image_;
        }

        /**
         * metoda zwracajaca flage, czy eksplozja sie powinna juz zakonczyc
         * @return zwraca flage
         */
        public boolean get_end_of_explosion() {
            return end_of_explosion_;
        }

        /**
         * metoda ustawiajaca flage, czy eksplozja trwa, czy sie skonczyla
         */
        public void set_end_of_explosion(boolean end_of_explosion) {
            this.end_of_explosion_ = end_of_explosion;
        }
}

