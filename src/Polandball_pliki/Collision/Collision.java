package Polandball_pliki.Collision;

/**
 * Created by Matball on 2017-05-20.
 */

/**
 * Klasa "Protoplasta" wszystkich kolizji
 */
public class Collision {

    /**
     * Zmienna okreslajaca, czy nie nastapila kolizja
     */
    boolean isNotCollision;

    /**
     *  Konstruktor Klasy Collizja
     */
    public Collision(){
        isNotCollision=true;
    }

    /**
     * Metoda zwracająca parametr isNotCollision
     * @return zwraca wartosc logiczna typu booolean z informacja czy zaszła kolizja
     */
    public boolean getIsNotCollision(){return isNotCollision;}
}
