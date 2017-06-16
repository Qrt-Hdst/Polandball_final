package Polandball_pliki.Counter;

/**
 * Klasa Protoplasta - liczaca czas zycia obiektow
 */
public class Counter {
    /**
     *
     */
    long born_time_;
    /**
     * zmienna okreslajaca czy licznik nadal jest potrzebny
     */
    boolean isStillNeed_;

    /**
     * Konstruktor licznika
     */

    public Counter(){
        born_time_=System.currentTimeMillis();
        isStillNeed_=true;
    }

    /**
     * funkcja wywolujaca u potomkow czy aby nie przekroczyly zdefiniowanego czasu trwania obiektu
     */
    public void checkTime(){

    }

    /**
     * zwraca flage czy konstruktor nadal jest potrzebny
     * @return zwrcaca flage czy konstruktor nadal jest potrzebny
     */

    public boolean getisStillNeed(){return isStillNeed_;}


}
