package Polandball_pliki.Counter;

import static Polandball_pliki.Panel.PanelBoard.LaserExistence;


/**
 * Created by Matball on 2017-06-16.
 */
public class CounterLaser extends Counter {
    public CounterLaser() {
        super();
        born_time_=System.currentTimeMillis();
        isStillNeed_=true;
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
        if(current-born_time_>1000){//wykonuje sie jesli roznica wynosi wiecej niz 500
            LaserExistence=false;// ustawiam flage lasera ze nadszedl jej kres
            isStillNeed_=false;//ustawiam flage ze instancja tego licznika nie jest juz potrzebna
        }
        if(LaserExistence==false){
            isStillNeed_=false;
        }
    }
}
