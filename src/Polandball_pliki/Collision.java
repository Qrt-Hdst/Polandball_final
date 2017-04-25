package Polandball_pliki;

import Polandball_pliki.GameObjects.LivingObject;
import Polandball_pliki.GameObjects.Polandball;
import Polandball_pliki.GameObjects.StationaryObject;

import java.util.ArrayList;

import static Polandball_pliki.PanelBoard.SizeWidthIcon;
import static Polandball_pliki.PanelBoard.SizeHeightIcon;

public class Collision {

    /**
     * zmienna okreslajaca, czy nastapila kolizja
     */
    boolean isCollision;

    /**
     *  Metoda stwierdzajaca, czy zachodzi kolizja
     */
    public Collision(int x1, int x2){
        if(x1+SizeWidthIcon>x2){
            isCollision=true;
        }else{
            isCollision=false;
        }

        /*if( (x1+SizeWidthIcon>x2  || x1<x2+SizeWidthIcon
                || y1+SizeHeightIcon>y2 || y1<y2+SizeHeightIcon){
            isCollision=true;
        }
        else{isCollision=false;}*/
    }
}
