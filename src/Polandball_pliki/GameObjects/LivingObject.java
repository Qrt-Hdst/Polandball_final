package Polandball_pliki.GameObjects;

/**
 * Klasa przodek dla wszystkich poruszającyhc/żyjących obiektow
 */
public class LivingObject extends GameObject {
    /**
     * prędkośc obiektu na osi x
     */
    int velX_;
    /**
     * prędkośc obiektu na osi y
     */
    int velY_;
    /**
     * przerwa miedzy punktami skrajnymi na zachodzie i wschodzie balla a krawedziami bocznymi grafiki
     */
    double distance_from_elevation_walls;
    /**
     * przerwa miedzy punktami skrajnymi na północy i południu balla a krawedziami sufitu i podłogi grafiki
     */
    double distance_from_azimuth_walls;
    /**
     * konstruktor obiektu będącego poruszającym się/żyjącym obiektem
     * @param x obecne polozenie obiektu na osi x
     * @param y obecne polozenie obiektu na osi y
     */
    public LivingObject(int x,int y){
        super();
        x_=x;
        y_=y;
        velX_=0;
        velY_=0;
        name_class_object=null;
        buffImage_=null;
        distance_from_azimuth_walls=0;
        distance_from_elevation_walls=0;
    }

    /**
     * konstruktor bezparametrowy
     */
    public LivingObject(){
        super();
        x_=0;
        y_=0;
        velX_=0;
        velY_=0;
        buffImage_=null;
        distance_from_azimuth_walls=0;
        distance_from_elevation_walls=0;
    }
    /**
     * Zmienia wartosc predkosci na osi X
     * @param velX nowa wartosc predkosci
     */
    public void change_velX(int velX){
        velX_=velX;
    }

    /**
     * Zmienia wartosc predksci na osi Y
     * @param velY nowa wartosc prędkosci na osi Y
     */
    public void change_velY(int velY){
        velY_=velY;
    }

    /**
     * zwraca dotychczasowa predkosc na osi X
     * @return predkosc obiektu na osi x
     */
    public int get_velX(){return velX_;}
    /**
     * zwraca dotychczasowa predkosc na osi Y
     * @return predkosc obiektu na osi y
     */
    public int get_velY(){return velY_;}

    public double getDistance_from_elevation_walls(){return distance_from_elevation_walls;}

    public double getDistance_from_azimuth_walls(){return distance_from_azimuth_walls;}
}
