package Polandball_pliki;

import Polandball_pliki.GameObjects.LivingObject;
import Polandball_pliki.GameObjects.Polandball;
import Polandball_pliki.GameObjects.StationaryObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.PanelBoard.SizeWidthIcon;
import static Polandball_pliki.PanelBoard.SizeHeightIcon;

/**
 *  Klasa sprawdzajaca kolizje
 */
public class Collision {

    /**
     * Zmienna okreslajaca, czy nie nastapila kolizja
     */
    boolean isNotCollision = true;

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

    int row_player_north;

    /**
     * Zmienna zaokraglajaca numer kolumny w dol
     */

    int column_player_west;

    /**
     * Metoda stwierdzajaca, czy zachodzi kolizja
     */
    public Collision(LivingObject livingObject) {

        //wiersz w double, w ktorym znajduje sie dany livingobject
        double_row_livingobject = (double) livingObject.getY() / (double) SizeHeightIcon;
        //kolumna w double, w ktorej znajduje sie dany livingobject
        double_column_livingobject = (double) livingObject.getX() / (double) SizeWidthIcon;
        //zaokraglenie numeru wiersza w dol
        row_player_north = (int) Math.floor(double_row_livingobject);
        //zaokraglenie kolumny w dol
        column_player_west = (int) Math.floor(double_column_livingobject);

        //sprawdzamy czy obiekt porusza sie w prawo > (wschod)
        if (livingObject.get_velX() > 0) {

            try {
                //sprawdzanie czy wyszlismy poza prawa scianke
                if (double_column_livingobject >= (double) (Amountofcolumns - 1)) {
                    //System.out.println(1);
                    isNotCollision = false;
                }
                //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko gornej krawedzi wiersza
                else if (double_row_livingobject < ((double) row_player_north + 0.15)) {
                    //System.out.println(2);
                    if (!(StatioonaryObjectTab[row_player_north][column_player_west + 1] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko dolnej krawedzi wiersza
                else if (double_row_livingobject > ((double) row_player_north + 0.85)) {
                    //System.out.println(3);
                    if (!(StatioonaryObjectTab[row_player_north + 1][column_player_west + 1] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycje y-owa obiektu, czy znajduje sie optymalnie w srodku wiersza
                else if (double_row_livingobject > ((double) row_player_north + 0.15) &&
                        double_row_livingobject < ((double) row_player_north + 0.85)) {
                    //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                    if (!(StatioonaryObjectTab[row_player_north][column_player_west + 1] == 1) &&
                            !(StatioonaryObjectTab[row_player_north + 1][column_player_west + 1] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                } else {//System.out.println(5);
                    isNotCollision = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isNotCollision = false;
                System.out.println("Blad kolizji -wschod");
            }
        }
        //sprawdzamy czy obiekt porusza sie w lewo < (zachod)
        else if (livingObject.get_velX() < 0) {
            try {
                if (double_column_livingobject < 0) {//sprawdzanie czy wyszlismy poza lewa scianke
                    //System.out.println(1);
                    isNotCollision = false;
                }
                //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko gornej krawedzi wiersza
                else if (double_row_livingobject < ((double) row_player_north + 0.15)) {
                    //System.out.println(2);
                    if (!(StatioonaryObjectTab[row_player_north][column_player_west] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycja y-wa obiektu, czy znajduje sie wystarczajaco blisko dolnej krawedzi wiersza
                else if (double_row_livingobject > ((double) row_player_north + 0.85)) {
                    //System.out.println(3);
                    if (!(StatioonaryObjectTab[row_player_north + 1][column_player_west] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycje y-owa obiektu, czy znajduje sie optymalnie w srodku wiersza
                else if (double_row_livingobject > ((double) row_player_north + 0.15) &&
                        double_row_livingobject < ((double) row_player_north + 0.85)) {
                    //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                    if (!(StatioonaryObjectTab[row_player_north][column_player_west] == 1) &&
                            !(StatioonaryObjectTab[row_player_north + 1][column_player_west] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                } else {//System.out.println(5);
                    isNotCollision = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isNotCollision = false;
                System.out.println("Blad kolizji -zachod");
            }
        }
        //sprawdzamy czy obiekt porusza sie w w gore ^ (polnoc)
        else if (livingObject.get_velY() < 0) {
            try {
                if (double_row_livingobject < 0) {//sprawdzanie czy wyszlismy poza gorna scianke
                    //System.out.println(1);
                    isNotCollision = false;
                }
                //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko lewej krawedzi kolumny
                else if (double_column_livingobject < ((double) column_player_west + 0.15)) {
                    //System.out.println(2);
                    if (!(StatioonaryObjectTab[row_player_north][column_player_west] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko prawej krawedzi kolumny
                else if (double_column_livingobject > ((double) column_player_west + 0.85)) {
                    //System.out.println(3);
                    if (!(StatioonaryObjectTab[row_player_north][column_player_west + 1] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycje x-owa obiektu, czy znajduje sie optymalnie w srodku kolumny
                else if (double_column_livingobject > ((double) column_player_west + 0.15) &&
                        double_column_livingobject < ((double) column_player_west + 0.85)) {
                    //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                    if (!(StatioonaryObjectTab[row_player_north][column_player_west] == 1) &&
                            !(StatioonaryObjectTab[row_player_north][column_player_west + 1] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                } else {//System.out.println(5);
                    isNotCollision = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isNotCollision = false;
                System.out.println("Blad kolizji -polnoc");
            }
        }
        //sprawdzamy czy obiekt porusza sie w dol V (poludnie)
        else if (livingObject.get_velY() > 0) {
            try {
                if (double_row_livingobject >= (double) (Amountoflines - 1)) {//sprawdzanie czy wyszlismy poza dolna scianke
                    //System.out.println(1);
                    isNotCollision = false;
                }
                //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko lewej krawedzi kolumny
                else if (double_column_livingobject < ((double) column_player_west + 0.15)) {
                    //System.out.println(2);
                    if (!(StatioonaryObjectTab[row_player_north + 1][column_player_west] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycja x-wa obiektu, czy znajduje sie wystarczajaco blisko prawej krawedzi kolumny
                else if (double_column_livingobject > ((double) column_player_west + 0.85)) {
                    //System.out.println(3);
                    if (!(StatioonaryObjectTab[row_player_north + 1][column_player_west + 1] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                }
                //sprawdzamy pozycje x-owa obiektu, czy znajduje sie optymalnie w srodku kolumny
                else if (double_column_livingobject > ((double) column_player_west + 0.15) &&
                        double_column_livingobject < ((double) column_player_west + 0.85)) {
                    //System.out.println(4+" "+row_player_north+" "+" "+column_player_west);
                    if (!(StatioonaryObjectTab[row_player_north + 1][column_player_west] == 1) &&
                            !(StatioonaryObjectTab[row_player_north + 1][column_player_west + 1] == 1)) {
                        isNotCollision = true;
                    } else {
                        isNotCollision = false;
                    }
                } else {//System.out.println(5);
                    isNotCollision = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isNotCollision = false;
                System.out.println("Blad kolizji -poludnie");
            }
        }
    }
}