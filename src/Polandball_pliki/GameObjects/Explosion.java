package Polandball_pliki.GameObjects;

import Polandball_pliki.Others.GetConstans;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static Polandball_pliki.Others.GetConstans.ExplosionString;

/**
 * Klasa reprezentujaca eksplozje bomby
 */
public class Explosion extends GameObject {
        /**
         * Flaga informujaca, czy nastapil juz koniec eksplozji
         */
        boolean end_of_explosion_;
        /**
         * Zmienna, przechowujaca gif eksplozji
         */
        public Image image_;
        /**
         * Konstruktor klasy Explosion
         * @param x obecne polozenie obiektu na osi x
         * @param y obecne polozenie obiektu na osi y
         */
        public Explosion(int x,int y){
            super();
            name_class_object=ExplosionString;
            x_=x;
            y_=y;
            buffImage_= null;
            image_=GetConstans.Explosion;
            end_of_explosion_=false;
        }
        /**
         * Konstruktor bezparametrowy klasy Explosion
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

