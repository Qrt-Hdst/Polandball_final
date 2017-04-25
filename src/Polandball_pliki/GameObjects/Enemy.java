package Polandball_pliki.GameObjects;

/**
 * klasa przodek dla przeciwników
 */
public class Enemy extends LivingObject {
    /**
     * konstruktor obiektu wrog
     * @param x polozenie na osi x
     * @param y polozenie na osi y
     */
    public Enemy(int x,int y){
        super();
        x_=x;
        y_=y;
        velX_=0;
        velY_=0;
        buffImage_=null;
    }

    /**
     * konstruktor bezparametrowy
     */
    public Enemy(){
        super();
        x_=0;
        y_=0;
        velX_=0;
        velY_=0;
        buffImage_=null;
    }
}
