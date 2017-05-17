package Polandball_pliki.GameObjects;

import Polandball_pliki.Collision;

import java.util.ArrayList;
import java.util.Random;
import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.PanelBoard.SizeWidthIcon;
import static Polandball_pliki.PanelBoard.SizeHeightIcon;
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

    /**
     * Metoda zwracaja danego wroga
     */
    public Enemy getEnemy(){
        return this;
    }

    /**
     * Metoda run() watku wroga, losoujaca kierunek ruchu, wywolujaca metdoy sprawdzajace kolizje i zmieniajaca pozycje
     * wrogow po sprawdzeniu warunkow kolizji
     */

    @Override
    public void  run(){
       synchronized (this) {
           try {
               while (true) {//petla nieskonczona, wrogowie caly czas chodza
                   Thread.sleep(50);//usypianie watku na 1000 milisekund, wszystko wyliczamy co ten czas
                   //System.out.println(1);
                   //wywowlanie odpowiednik metod koloizji, sprawdzenie w ktorych kierunku moze isc dany wrod
                   boolean can_I_go_East = new Collision(this.getEnemy(), "enemy").Collision_East();
                   boolean can_I_go_West = new Collision(this.getEnemy(), "enemy").Collision_West();
                   boolean can_I_go_North = new Collision(this.getEnemy(), "enemy").Collision_North();
                   boolean can_I_go_South = new Collision(this.getEnemy(), "enemy").Collision_South();

                   //losowanie liczby z przedzialu 0-3, z zaleznosci od wylosowanej liczby nadaje pozniej potworowi
                   //odpowiedni kierunek
                   //0 - Wschód, 1 -Zachód, 2 -Północ, 3 -Południe
                   Random rand = new Random();
                   int kierunek = rand.nextInt(4);

                   // System.out.println("wylosowana liczba" + kierunek);

                   //ustawienie predkosci w zaleznosci od kierunku
                   //po kazdym losowaniu kierunku jest sprawdzenie czy przypadkiem nie wykryto kolizji
                   if (kierunek == 0) {
                        //System.out.println(0);
                       if (can_I_go_East == true) {
                           this.change_velX(1);
                           this.change_velY(0);
                       }
                   } else if (kierunek == 1) {
                      // System.out.println(1);
                       if (can_I_go_West == true) {
                           this.change_velX(-1);
                           this.change_velY(0);
                       }
                   } else if (kierunek == 2) {
                       //System.out.println(2);
                       if (can_I_go_North == true) {
                           this.change_velY(-1);
                           this.change_velX(0);
                       }
                   } else if (kierunek == 3) {
                      // System.out.println(3);
                       if (can_I_go_South == true) {
                           this.change_velY(1);
                           this.change_velX(0);
                       }
                   } else {
                       this.change_velX(0);
                       this.change_velY(0);
                   }

                   //zmiana pozycji wroga, w zaleznosci od mozliwego kierunku
                   this.changeX(this.getX() + this.get_velX());
                   this.changeY(this.getY() + this.get_velY());
               }

           } catch (Exception e) {
               System.out.println("Blad watku wroga");
           }
       }
    }
}