package Polandball_pliki.Counter;


import Polandball_pliki.GameObjects.Normal_Bomb;

/**
 * Created by Matball on 2017-05-19.
 */

/**
 * Klasa typu Counter odliczajaca tykniecia do wybuchu zwyklej bomby
 */
public class Counter_Normal_Bomb extends Counter {
    /**
     * Instacja klasy Normal_Bomb
     */

    Normal_Bomb normal_bomb_;


    /**
     * Konstruktor ustawiajacy czas i pocztkowe wartosci Licznika
     * @param normal_bomb bomba ktorej odlicza czas licznik
     */
    public Counter_Normal_Bomb(Normal_Bomb normal_bomb){
        super();
        born_time_=System.currentTimeMillis();// ustawiam czas kiedy powstal counter
        isStillNeed_=true; // flaga mowiaca czy counter nadal cos sensownego liczy
        normal_bomb_=normal_bomb; //przypisanie do obiektu bomba
    }

    public void checkTime(){
        checkTimeToExplosionBomb();
    }//sprawdzam czas

    public void checkTimeToExplosionBomb(){
        long current = System.currentTimeMillis();//pobieram obecny czas
        if(current-born_time_>3000){//sprawdzam czy minely 3 sekundy od podlozenia bomby, trzeba bedzie ustawic w configu
            normal_bomb_.setExplosionflag(true);//ustawienie flagi ze bomba wybucha
            isStillNeed_=false;// zaznaczam obiektowi ze nie jest juz mi do niczego potrzebny
        }
    }
}
