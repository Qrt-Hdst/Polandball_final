package Polandball_pliki;

/**
 * Pole gry, plansza
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static Polandball_pliki.GetConstans.*;
//import static Polandball_pliki.LevelFrame.panelboardwidth;

public class PanelBoard extends JPanel{
    private BufferedImage bufferedImage;
    public PanelBoard(){PanelBoard();}

    private void PanelBoard() {

        this.setSize(panelboardwidth,panelboardheight);
        this.setLocation(0,panelinfooneheight);
        this.setBackground(Color.BLACK);

        try{
            /**tablica dwuwymiarowa w Javie, zalecana przez internety*/
            ArrayList<ArrayList<String>> field = new ArrayList<ArrayList<String>>();
            for (int i=0;i<Amountoflines;i++) {
                /**dodaje wiersz do tablicy field*/
                field.add(new ArrayList<String>());
                /**tablica stringa w ktorej rozbijam wiersz na komórki*/
                String bufor_string[]=row[i].split(" ");
                /**wrzucam elementy bufor_stringa, do pól naszej tablicy*/
                for(int j=0;j<Amountofcolumns;j++) {
                    field.get(i).add(bufor_string[j]);
                }
            }



            File PolandBallFile = new File(PolandBall);
            File SovietBallFile = new File(SovietBall);
            File NaziBallFile = new File(NaziBall);
            File TurkeyBallFile = new File(TurkeyBall);
            File BetonFile = new File(Beton);
            File NothingFile = new File(Nothing);
            File SkrzynkaFile = new File(Skrzynka);

            /** na razie kazda kolejna grafika przeslania poprzednia
             * , chyba bedzie trzeba zrobic 2d array BufferImage i tam te wszystkie pola powstawiac
             * i pozniej w metodzie paint je rysować */

            for(int i=0;i<Amountoflines;i++){
                for(int j=0;j<Amountofcolumns;j++) {
                    if (field.get(i).get(j).equals("N_")) {
                        /**ImageIO zwraca BufferImage*/
                        bufferedImage= ImageIO.read(NothingFile);
                        //StartDrawingX = StartDrawingX + SizeWidthIcon;
                    } else if (field.get(i).get(j).equals("B_")) {
                        bufferedImage=ImageIO.read(BetonFile);
                        //StartDrawingX = StartDrawingX + SizeWidthIcon;
                    } else if (field.get(i).get(j).equals("S_")) {
                        bufferedImage=ImageIO.read(NaziBallFile);
                        //StartDrawingX = StartDrawingX + SizeWidthIcon;
                    } else if (field.get(i).get(j).equals("NG")) {
                        bufferedImage=ImageIO.read(PolandBallFile);
                        //StartDrawingX = StartDrawingX + SizeWidthIcon;
                    } else if (field.get(i).get(j).equals("NW")) {
                        bufferedImage=ImageIO.read(TurkeyBallFile);
                        //StartDrawingX = StartDrawingX + SizeWidthIcon;
                    }
                }
            }

        } catch(IOException e ){
            e.printStackTrace();
            System.out.println("gutentag,error");
        }
        //repaint();
    }

    public void paint(Graphics g){
        /**3 i 4 parametr odpowiada za skale */
        g.drawImage(bufferedImage,100 , 100, 100, 100, null);
    }


}
