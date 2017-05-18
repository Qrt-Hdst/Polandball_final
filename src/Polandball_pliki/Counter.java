package Polandball_pliki;

import Polandball_pliki.GameObjects.Bomb;
import static Polandball_pliki.GetConstans.*;

/**
 * Klasa liczaca czas zycia obiektow
 */

public class Counter implements  Runnable{

    /**
     * Instacja klasy Bomb
     */

    Bomb bomba;

    /**
     * Konstruktor licznika
     */

    public Counter(Bomb bomb){
        bomba=bomb;
    }

    /**
     * Cialo watku, liczace od zadanej wartosci do 0 (sekundy)
     */
    @Override
    public void run(){
            long current = System.currentTimeMillis();
            int i = TimeToExplosion;
            while(i >= 0) {
                if(System.currentTimeMillis() - current > 1000){
                    //System.out.println(i--);
                    i--;//odliczenie co 1 sekunda
                    current = System.currentTimeMillis();
                }
            }bomba.setExplosionflag(true);//gdy czas minie, flaga explosionflag_ na true
    }
}
