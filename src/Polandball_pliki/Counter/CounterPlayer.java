package Polandball_pliki.Counter;

import Polandball_pliki.GameObjects.Polandball;
import static Polandball_pliki.LevelFrame.PanelBoard.PlayerExistence;
/**
 * Created by Matball on 2017-05-22.
 */

/**
 * Klasa odpowiedzialna za odliczanie czasu do respawnu Player-a
 */
public class CounterPlayer extends Counter {

    /**
     * Instnacja klasy Polandball
     */
    private Polandball player_;


    /**
     * Konstruktor klasy CounterPlayer
     * @param player obiekt gracza
     */

    public CounterPlayer(Polandball player){
        super();//wywolanie konstruktora rodzica ( Counter )
        born_time_=System.currentTimeMillis();//ustawienie czasu od ktorego liczymy
        isStillNeed_=true;//ustawienie flagi przydatnosci licznika na true
        player_=player;//przypisanie licznika do konkretnego obiektu eksplozji
    }

    /**
     * funkcja wywolujaca metode checkTimeToRespawn
     */
    public void checkTime(){
        checkTimeToRespawn();
    }//sprawdzenia czasu1

    /**
     * Funckcja sprawdzajaca czy nastapil juz czas respawnu gracza po wczesniejszej smierci
     */
    public void checkTimeToRespawn(){
        long current = System.currentTimeMillis();//obecny czas komputera
        if(current-born_time_>3000){//wykonuje sie jesli roznica wynosi wiecej niz 500
            PlayerExistence=true;//zmieniam wartosc flagi w zmiennej statycznej PanelBoard, na true. Jest to sygnał zeby program zrespawnował gracza w miejscu startowm
            isStillNeed_=false;//ustawiam flage ze instancja tego licznika nie jest juz potrzebna
        }
    }

}
