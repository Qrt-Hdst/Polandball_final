package Polandball_pliki.GameObjects;

import Polandball_pliki.Collision;

import java.util.ArrayList;
import java.util.Random;
import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.Collision.*;

/**
 * Klasa przodek dla przeciwnikow
 */
public class Enemy extends LivingObject implements Runnable {
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
        velX_=Monsterspeed;
        velY_=Monsterspeed;
        buffImage_=null;
    }

    /**
     * konstruktor kopiujacy
     */
    public Enemy(Enemy enemy_second){
        super();
        this.x_=enemy_second.getX();
        this.y_=enemy_second.getY();
        this.velX_=enemy_second.get_velX();
        this.velY_=enemy_second.get_velY();
        this.buffImage_=enemy_second.getBuffImage();
    }

    public Enemy getEnemy(){
        return this;
    }

    @Override
    public void  run(){
        try {
            Thread.sleep(1000);
            //System.out.println(1);

            boolean can_I_go_East = new Collision(this.getEnemy(),"enemy").Collision_East();
            boolean can_I_go_West = new Collision(this.getEnemy(),"enemy").Collision_West();
            boolean can_I_go_North = new Collision(this.getEnemy(),"enemy").Collision_North();
            boolean can_I_go_South = new Collision(this.getEnemy(),"enemy").Collision_South();
            ArrayList<Integer> value_of_directions = new ArrayList<Integer>();

            //Sprawdzenie czy potwór może iśc w danym kierunku, w zależności od możliwego kierunku dodawana jest określona liczba
            //z której będziemy potem losować nastepny kierunek potwora
            //0 - Wschód, 1 -Zachód, 2 -Północ, 3 -Południe

          //  if (can_I_go_East == true) {
                value_of_directions.add(0);
         //   } else if (can_I_go_West == true) {
                value_of_directions.add(1);
         //   } else if (can_I_go_North == true) {
                value_of_directions.add(2);
           // } else if (can_I_go_South == true) {
                value_of_directions.add(3);
         //   }

            //losowanie liczb z tablicy dynamicznej
            Random rand = new Random();
            int kierunek = rand.nextInt(value_of_directions.size());
            System.out.println("wylosowana liczba" + kierunek);
            //ustawienie predkosci w zaleznosci od kierunku
            if (kierunek == 0) {
                System.out.println(0 + "dziendobry");
                if (can_I_go_East==true){
                this.change_velX(Monsterspeed * 1);
                this.change_velY(0);}
            } else if (kierunek == 1) {
                System.out.println(1);
                if (can_I_go_West==true){
                this.change_velX(Monsterspeed * (-1));
                this.change_velY(0);}
            } else if (kierunek == 2) {
                System.out.println(2);
                if (can_I_go_North==true){
                this.change_velY(Monsterspeed * (-1));
                this.change_velX(0);}
            } else if (kierunek == 3) {
                System.out.println(3);
                if (can_I_go_South==true){
                this.change_velY(Monsterspeed * (1));
                this.change_velX(0);}
            } else {
                this.change_velY(0);
            }

            //zmieniam

            this.changeX(this.getX() + this.get_velX());
            this.changeY(this.getY() + this.get_velY());

        }
        catch(Exception e){
            System.out.println("Blad watku wroga");
        }
    }
}
