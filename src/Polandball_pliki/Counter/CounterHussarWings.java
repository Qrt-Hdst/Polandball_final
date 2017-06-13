package Polandball_pliki.Counter;

import static Polandball_pliki.Panel.PanelBoard.PlayerExistence;
import static Polandball_pliki.Panel.PanelBoard.hussars_Power;
/**
 * Created by Matball on 2017-06-12.
 */
public class CounterHussarWings extends Counter {


    public CounterHussarWings() {
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
        if(current-born_time_>5000){//wykonuje sie jesli roznica wynosi wiecej niz 500
            hussars_Power=false;// ustawiam flage explozji ze nadszedl jej kres
            isStillNeed_=false;//ustawiam flage ze instancja tego licznika nie jest juz potrzebna
        }
        if(PlayerExistence==false){
            hussars_Power=false;
            isStillNeed_=false;
        }
    }

}
