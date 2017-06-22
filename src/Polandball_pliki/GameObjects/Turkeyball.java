package GameObjects;

import Polandball_pliki.Others.GetConstans;



import static Polandball_pliki.Others.GetConstans.TurkeyBallString;

/**
 * Klasa reprezentujaca wroga typu Turkeyball
 */
public class Turkeyball extends Enemy {
    /**
     * Konstruktor klasy Turkeyball
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public Turkeyball(int x,int y){
        super();
        x_=x;
        y_=y;
        velX_=0;
        velY_=0;
        name_class_object=TurkeyBallString;
        buffImage_= GetConstans.TurkeyBallBuff;
        distance_from_elevation_walls=6/80;
        distance_from_azimuth_walls=6/80;
    }
}
