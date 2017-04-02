package Polandball_pliki;

/**
 * Pole gry, plansza
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static Polandball_pliki.GetConstans.*;
//import static Polandball_pliki.LevelFrame.panelboardwidth;

public class PanelBoard extends JPanel{

    private BufferedImage bufferedImage[][];
    public PanelBoard(){PanelBoard();}

    private void PanelBoard() {

        this.setSize(panelboardwidth, panelboardheight);
        this.setLocation(0, panelinfooneheight);
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
            File DoorFile = new File(Door);
            File KeyFile = new File(Key);


            /** na razie kazda kolejna grafika przeslania poprzednia
             * , chyba bedzie trzeba zrobic 2d array BufferImage i tam te wszystkie pola powstawiac
             * i pozniej w metodzie paint je rysować */



            bufferedImage = new BufferedImage[Amountoflines][Amountofcolumns];
            for(int i=0;i<Amountoflines;i++){
                for(int j=0;j<Amountofcolumns;j++) {
                    if (field.get(i).get(j).equals("N_")) {
                     //   bufferedImage[i][j]=ImageIO.read(NothingFile);
                    } else if (field.get(i).get(j).equals("B_")) {
                        bufferedImage[i][j]=ImageIO.read(BetonFile);
                    } else if (field.get(i).get(j).equals("S_")) {
                        bufferedImage[i][j]=ImageIO.read(SkrzynkaFile);
                    } else if (field.get(i).get(j).equals("NG")) {
                        bufferedImage[i][j]=ImageIO.read(PolandBallFile);
                    } else if (field.get(i).get(j).equals("NW")) {
                        bufferedImage[i][j]=ImageIO.read(SovietBallFile);
                    } else if (field.get(i).get(j).equals("ND")) {
                        bufferedImage[i][j]=ImageIO.read(DoorFile);
                    } else if (field.get(i).get(j).equals("NK")) {
                        bufferedImage[i][j]=ImageIO.read(KeyFile);
                    }
                }

            }

        } catch(IOException e ){
            e.printStackTrace();
            System.out.println("gutentag,error");
        }

    }

    public void paint(Graphics g){
        /**3 i 4 parametr odpowiada za skale */
        g.setColor(Color.black);
        g.fillRect(0,0,panelboardwidth,panelboardheight);

        int StartDrawingX = 0;//punkt X, od ktorego zaczniemy rysowanie obiektu
        int StartDrawingY = 0;//punkt Y, od ktorego zaczniemy rysowanie obiektu
        int SizeWidthIcon = panelboardwidth/Amountofcolumns;//szerokosc obiektu graficznego, zalezna od szerokosci panelu i ilosci kolumn
        int SizeHeightIcon = panelboardheight/Amountoflines;//wysokosc obiektu graficznego, zalezna od wysokosci panelu i ilosci wierszy
        for(int i=0;i<Amountoflines;i++){
            for(int j=0;j<Amountofcolumns;j++){
                g.drawImage(bufferedImage[i][j],StartDrawingX, StartDrawingY, SizeWidthIcon, SizeHeightIcon, null);
                StartDrawingX=StartDrawingX+SizeWidthIcon;
            }
            StartDrawingX=0;
            StartDrawingY=StartDrawingY+SizeHeightIcon;
        }

    }


}
