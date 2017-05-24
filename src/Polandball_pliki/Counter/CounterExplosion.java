package Polandball_pliki.Counter;

import Polandball_pliki.GameObjects.Explosion;
import Polandball_pliki.GameObjects.Normal_Bomb;

import static Polandball_pliki.GetConstans.TimeToExplosion;

/**
 * Created by Matball on 2017-05-19.
 */

/**
 * Klasa typu Counter odliczajaca czas do konca eksplozji ( i znikniecia jej grafiki na mapie
 */
public class CounterExplosion extends Counter  {

    /**
     * Instacja klasy Eksplozji
     */

    Explosion explosion_;


    /**
     * Konstruktor ustawiajacy czas i pocztkowe wartosci Licznika
     * @param explosion eksplozja ktorej odlicza czas trwania
     */

    public CounterExplosion(Explosion explosion){
        super();//wywolanie konstruktora rodzica ( Counter )
        born_time_=System.currentTimeMillis();//ustawienie czasu od ktorego liczymy
        isStillNeed_=true;//ustawienie flagi przydatnosci licznika na true
        explosion_=explosion;//przypisanie licznika do konkretnego obiektu eksplozji
    }

    /**
     * funkcja wywolujaca metode checkTimeToEndOfExplosion
     */
    public void checkTime(){
        checkTimeToEndOfExplosion();
    }//sprawdzenia czsu1

    /**
     * Funckcja sprawdzajaca czy nie minal juz czas trwania wybuchu
     */
    public void checkTimeToEndOfExplosion(){
        long current = System.currentTimeMillis();//obecny czas komputera
        if(current-born_time_>500){//wykonuje sie jesli roznica wynosi wiecej niz 500
            explosion_.set_end_of_explosion(true);// ustawiam flage explozji ze nadszedl jej kres
            isStillNeed_=false;//ustawiam flage ze instancja tego licznika nie jest juz potrzebna
        }
    }

}
