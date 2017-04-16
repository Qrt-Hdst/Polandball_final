package Polandball_pliki.GameObjects;

/**
 * Created by Matball on 2017-04-16.
 */
public class Enemy extends GameObject {
    public Enemy(int x,int y){
        super();
        x_=x;
        y_=y;
        velX_=0;
        velY_=0;
        buffImage_=null;
    }
    public Enemy(){
        super();
        x_=0;
        y_=0;
        velX_=0;
        velY_=0;
        buffImage_=null;
    }
}
