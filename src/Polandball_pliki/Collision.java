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

public class Collision {

    /**
     * zmienna okreslajaca, czy nastapila kolizja
     */
    boolean isNotCollision=false;

    double double_column_player=0;
    double double_row_player=0;

    /**
     *  Metoda stwierdzajaca, czy zachodzi kolizja
     */
    public Collision(LivingObject livingObject) {
        double double_row_player =((double) livingObject.getY() / (double)SizeHeightIcon)  ;
        double double_column_player = (double)livingObject.getX() /(double) SizeWidthIcon ;

        //System.out.println(double_row_player +"row");
        //System.out.println("column_player="+double_column_player+ "livingObject.getX()="+livingObject.getX()+"+ SizeWidthIcon" +SizeWidthIcon);
        ///------------
        if(livingObject.get_velX()>0) {
            int column_player = (int) Math.floor(double_column_player);
            int row_player = (int) Math.floor(double_row_player);
            //wypluj_dane(1, row_player, double_row_player, column_player, double_column_player);
            try {
                if (column_player < Amountofcolumns - 1) {
                    //wypluj_dane(2, row_player, double_row_player, column_player, double_column_player);
                    if (!(StatioonaryObjectTab[row_player][column_player + 1] == 1)) {
                        //wypluj_dane(3, row_player, double_row_player, column_player, double_column_player);
                        if (livingObject.getX() + livingObject.get_velX() < SizeWidthIcon * (column_player) + 2 * SizeWidthIcon
                                && livingObject.getX() + livingObject.get_velX() < (Amountofcolumns - 1) * SizeWidthIcon) {
                            //wypluj_dane(4, row_player, double_row_player, column_player, double_column_player);
                            isNotCollision = true;
                        }
                    }
                } else if (column_player == Amountofcolumns - 1) {
                    //wypluj_dane(5, row_player, double_row_player, column_player, double_column_player);
                    if (livingObject.getX() + SizeWidthIcon < SizeWidthIcon * (column_player) + 1 * SizeWidthIcon) {
                        //wypluj_dane(6, row_player, double_row_player, column_player, double_column_player);
                        isNotCollision = true;
                    }
                } else {
                    isNotCollision = false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isNotCollision= false;
                System.out.println("Blad kolizji -wschod");
            }
        } else if(livingObject.get_velX()<0) {
            int column_player = (int) Math.floor(double_column_player);
            int row_player = (int) Math.floor(double_row_player);
            //wypluj_dane(1, row_player, double_row_player, column_player, double_column_player);
            try {
                if (column_player > 0) {
                    //wypluj_dane(2, row_player, double_row_player, column_player, double_column_player);
                    //System.out.println(StatioonaryObjectTab[row_player][column_player - 1]);
                    if (!(StatioonaryObjectTab[row_player][column_player - 1] == 1)) {
                       // wypluj_dane(3, row_player, double_row_player, column_player, double_column_player);
                        //System.out.println(livingObject.getX() + livingObject.get_velX() + " przerwa " + (SizeWidthIcon * column_player - 2 * SizeWidthIcon));
                        if (livingObject.getX() + livingObject.get_velX() > SizeWidthIcon * column_player - 2 * SizeWidthIcon &&
                                livingObject.getX() + livingObject.get_velX() > 0) {

                            //wypluj_dane(4, row_player, double_row_player, column_player, double_column_player);
                            isNotCollision= true;
                        }
                    }
                } else if (column_player == 0) {
                    //System.out.println(5 + "column_player " + column_player + "double column_ " + double_column_player);
                    if (livingObject.getX() + livingObject.get_velX() > 0) {
                        //System.out.println(6 + "column_player " + column_player + "double column_ " + double_column_player);
                        isNotCollision=true;
                    }
                } else {
                    isNotCollision= false;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                isNotCollision=false;
                System.out.println("Blad kolizji -zachod");
            }
        }
        else if (livingObject.get_velY() > 0) {
            int column_player=(int)Math.floor(double_column_player);

            int row_player=(int)Math.floor(double_row_player);
            //wypluj_dane(1,row_player,double_row_player,column_player,double_column_player);
            try {
                if (row_player < Amountoflines-1) {
                    //wypluj_dane(2,row_player,double_row_player,column_player,double_column_player);
                    //System.out.println(StatioonaryObjectTab[row_player+1][column_player]);
                    if (!(StatioonaryObjectTab[row_player+1][column_player] == 1)) {
                        //wypluj_dane(3,row_player,double_row_player,column_player,double_column_player);
                        //System.out.println(livingObject.getY()+livingObject.get_velY()+SizeHeightIcon+" przerwa "+ (SizeHeightIcon * (row_player)+2*SizeHeightIcon));
                        if (livingObject.getY()+SizeHeightIcon+livingObject.get_velY()< SizeHeightIcon * (row_player)+3*SizeHeightIcon
                                && livingObject.getY()+livingObject.get_velY()<(Amountoflines-1)* SizeHeightIcon) {
                          //  wypluj_dane(4,row_player,double_row_player,column_player,double_column_player);
                            isNotCollision = true;
                        }
                    }
                } else if (column_player == Amountoflines-1) {
                    wypluj_dane(5,row_player,double_row_player,column_player,double_column_player);
                    if (livingObject.getY()+SizeHeightIcon< SizeHeightIcon * (column_player)+1*SizeHeightIcon) {
                       // wypluj_dane(6,row_player,double_row_player,column_player,double_column_player);
                        isNotCollision = true;
                    }
                }  else {isNotCollision=false;}
            }
            catch(ArrayIndexOutOfBoundsException e ) {
                isNotCollision=false;
                System.out.println("Blad kolizji -poludnie");
            }
        }
        else if(livingObject.get_velY()<0){
            int column_player=(int)Math.floor(double_column_player);
            int row_player=(int)Math.floor(double_row_player);
            //wypluj_dane(1,row_player,double_row_player,column_player,double_column_player);
            try {
                if (row_player > 0) {
               //     wypluj_dane(2,row_player,double_row_player,column_player,double_column_player);
                    System.out.println(StatioonaryObjectTab[row_player-1][column_player] );
                    if (!(StatioonaryObjectTab[row_player-1][column_player] == 1)) {
                  //      wypluj_dane(3,row_player,double_row_player,column_player,double_column_player);
                   //     System.out.println(livingObject.getY()+livingObject.get_velY()+" przerwa "+ (SizeHeightIcon * row_player -2*SizeHeightIcon));
                        if (livingObject.getY()+livingObject.get_velY()> SizeHeightIcon * row_player -2*SizeHeightIcon &&
                                livingObject.getY()+livingObject.get_velY()>0) {
                  //          wypluj_dane(4,row_player,double_row_player,column_player,double_column_player);
                            isNotCollision = true;
                        }
                    }
                }
                else if ( row_player == 0 ){
                   // System.out.println(5+"column_player " + column_player +"double column_ "+double_column_player );
                    if (livingObject.getY()+livingObject.get_velY() > 0) {
                  //      System.out.println(6+"column_player " + column_player +"double column_ "+double_column_player );
                        isNotCollision = true;
                    }
                }else {isNotCollision=false;}
            }
            catch(ArrayIndexOutOfBoundsException e ) {
                isNotCollision=false;
                System.out.println("Blad kolizji -polnoc");
            }
        }
    }



    void wypluj_dane(int x,int row_player,double double_row_player,int column_player,double double_column){
        System.out.println(x+") "+"row_player " + row_player +"double row_ "+double_row_player +" column_player " +column_player +"double_column "+double_column);
    }


}
