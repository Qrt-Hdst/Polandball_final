package Polandball_pliki.Collision;

import Polandball_pliki.GameObjects.GameObject;
import Polandball_pliki.GameObjects.Terrain;

import static Polandball_pliki.Others.GetConstans.Amountofcolumns;
import static Polandball_pliki.Others.GetConstans.Amountoflines;
import static Polandball_pliki.Panel.PanelBoard.BetonIsNotHere;
import static Polandball_pliki.Panel.PanelBoard.SizeHeightIcon;
import static Polandball_pliki.Panel.PanelBoard.SizeWidthIcon;


/**
 * Created by Matball on 2017-06-16.
 */
public class CollisionPlayerWithLaser extends Collision{

    /**
     * Wiersz w typie double, w ktorym znajduje sie dany livingobject
     */

    double double_row_livingobject;

    /**
     * Kolumna w typie double, w ktorej znajduje sie dany livingobject
     */

    double double_column_livingobject;

    /**
     * Zmienna zaokraglajaca numer wiersza w dol
     */

    int row_livingobject_north;

    /**
     * Zmienna zaokraglajaca numer kolumny w dol
     */

    int column_livingobject_west;


    public CollisionPlayerWithLaser(int xLaser,int yLaser,int heightLaser,int widthLaser,GameObject gameObject){
        super();
        System.out.println(((xLaser+widthLaser)<gameObject.getX())+" 1");
        System.out.println((xLaser>(gameObject.getX()+SizeWidthIcon))+" 2");
        System.out.println(((yLaser+heightLaser)<gameObject.getY())+" 3");
        System.out.println( (yLaser >(gameObject.getY())+SizeHeightIcon)+" 4");
        if(


                (xLaser+widthLaser)<gameObject.getX()
                ||
                xLaser>(gameObject.getX()+SizeWidthIcon)
                ||
                (yLaser+heightLaser)<gameObject.getY()
                ||
                yLaser >(gameObject.getY()+SizeHeightIcon))
        {
            isNotCollision=true;
        }
        else {
            isNotCollision=false;
        }

    }

    public CollisionPlayerWithLaser(int positionY,int positionX) {
        super(); //wywoluje konstruktor klasy rodzica

        //wiersz w double, w ktorym znajduje sie dany livingobject
        double_row_livingobject = (double) positionY / (double) SizeHeightIcon;
        //kolumna w double, w ktorej znajduje sie dany livingobject
        double_column_livingobject = (double) positionX / (double) SizeWidthIcon;
        //zaokraglenie numeru wiersza w dol
        row_livingobject_north = (int) Math.floor(double_row_livingobject);
        //zaokraglenie kolumny w dol
        column_livingobject_west = (int) Math.floor(double_column_livingobject);


    }

    public boolean Collision_East(){
        try {
            //sprawdzanie czy wyszlismy poza prawa scianke
            if (double_column_livingobject >= (double) (Amountofcolumns - 1)) {
                // System.out.println("H");
                isNotCollision = false;
            }
            //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko gornej krawedzi wiersza
            else if (double_row_livingobject < ((double) row_livingobject_north + 0.15)) {
                if (BetonIsNotHere(row_livingobject_north,column_livingobject_west+1)) {
                    //System.out.println("a");
                    isNotCollision = true;
                } else {
                    //System.out.println("m");
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko dolnej krawedzi wiersza
            else if (double_row_livingobject > ((double) row_livingobject_north + 0.85)) {
                //System.out.println(3);
                if (BetonIsNotHere(row_livingobject_north+1,column_livingobject_west+1)) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycje y-owa obiektu, czy znajduje sie optymalnie w srodku wiersza
            else if (double_row_livingobject > ((double) row_livingobject_north + 0.15) &&
                    double_row_livingobject < ((double) row_livingobject_north + 0.85)) {
                //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                if ((BetonIsNotHere(row_livingobject_north,column_livingobject_west+1))&&
                        (BetonIsNotHere(row_livingobject_north+1,column_livingobject_west+1))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            } else {//System.out.println(5);
                isNotCollision = false;
            }
            return isNotCollision;
        } catch (ArrayIndexOutOfBoundsException e) {
            isNotCollision = true;
            System.out.println("Blad kolizji -wschod");
            System.out.println(e);
        }

        return isNotCollision;
    }
    //sprawdzanie kolizji w lewo <
    public boolean Collision_West(){
        try {
            if (double_column_livingobject < 0) {//sprawdzanie czy wyszlismy poza lewa scianke
                //System.out.println(1);
                isNotCollision = false;
            }
            //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko gornej krawedzi wiersza
            else if (double_row_livingobject < ((double) row_livingobject_north + 0.15)) {
                //System.out.println(2);
                if ((BetonIsNotHere(row_livingobject_north,column_livingobject_west))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko dolnej krawedzi wiersza
            else if (double_row_livingobject > ((double) row_livingobject_north + 0.85)) {
                //System.out.println(3);
                if ((BetonIsNotHere(row_livingobject_north+1,column_livingobject_west))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycje y-owa obiektu, czy znajduje sie optymalnie w srodku wiersza
            else if (double_row_livingobject > ((double) row_livingobject_north + 0.15) &&
                    double_row_livingobject < ((double) row_livingobject_north + 0.85)) {
                //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                if ((BetonIsNotHere(row_livingobject_north,column_livingobject_west))&&
                        (BetonIsNotHere(row_livingobject_north+1,column_livingobject_west))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            } else {//System.out.println(5);
                isNotCollision = false;
            }
            return isNotCollision;
        } catch (ArrayIndexOutOfBoundsException e) {
            isNotCollision = true;
            System.out.println("Blad kolizji -zachod");
            System.out.println(e);
        }

        return isNotCollision;
    }
    //sprawdzanie kolizji w gore ^
    public boolean Collision_North(){
        try {
            if (double_row_livingobject <= 0) {//sprawdzanie czy wyszlismy poza gorna scianke
                isNotCollision = false;
            }
            //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko lewej krawedzi kolumny
            else if (double_column_livingobject < ((double) column_livingobject_west + 0.15)) {
                //System.out.println(2);
                if ((BetonIsNotHere(row_livingobject_north,column_livingobject_west))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko prawej krawedzi kolumny
            else if (double_column_livingobject > ((double) column_livingobject_west + 0.85)) {
                //System.out.println(3);
                if ((BetonIsNotHere(row_livingobject_north,column_livingobject_west+1))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycje x-owa obiektu, czy znajduje sie optymalnie w srodku kolumny
            else if (double_column_livingobject > ((double) column_livingobject_west + 0.15) &&
                    double_column_livingobject < ((double)column_livingobject_west + 0.85)) {
                //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                if ((BetonIsNotHere(row_livingobject_north,column_livingobject_west)) &&
                        (BetonIsNotHere(row_livingobject_north,column_livingobject_west+1))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            } else {//System.out.println(5);
                isNotCollision = false;
            }
            return isNotCollision;
        } catch (ArrayIndexOutOfBoundsException e) {
            isNotCollision = true;
            System.out.println("Blad kolizji -polnoc");
            System.out.println(e);
        }

        return isNotCollision;
    }
    //sprawdzanie kolizji w dol V
    public boolean Collision_South(){
        try {
            if (double_row_livingobject >= (double) (Amountoflines - 1)) {//sprawdzanie czy wyszlismy poza dolna scianke
                //System.out.println(1);
                isNotCollision = false;
            }
            //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko lewej krawedzi kolumny
            else if (double_column_livingobject < ((double) column_livingobject_west + 0.15)) {
                //System.out.println(2);
                if ((BetonIsNotHere(row_livingobject_north+1,column_livingobject_west))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko prawej krawedzi kolumny
            else if (double_column_livingobject > ((double) column_livingobject_west + 0.85)) {
                //System.out.println(3);
                if ((BetonIsNotHere(row_livingobject_north+1,column_livingobject_west+1))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            }
            //sprawdzamy pozycje x-owa obiektu, czy znajduje sie optymalnie w srodku kolumny
            else if (double_column_livingobject > ((double) column_livingobject_west + 0.15) &&
                    double_column_livingobject < ((double) column_livingobject_west + 0.85)) {
                //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                if ((BetonIsNotHere(row_livingobject_north+1,column_livingobject_west)) &&
                        (BetonIsNotHere(row_livingobject_north+1,column_livingobject_west+1))) {
                    isNotCollision = true;
                } else {
                    isNotCollision = false;
                }
            } else {//System.out.println(5);
                isNotCollision = false;
            }
            return isNotCollision;
        } catch (ArrayIndexOutOfBoundsException e) {
            isNotCollision = true;
            System.out.println("Blad kolizji -poludnie");
            System.out.println(e);
        }
        return isNotCollision;
    }


}
