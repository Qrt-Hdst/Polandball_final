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

import static Polandball_pliki.GetConstans.*;

public class PanelBoard extends JPanel{

    /**
     * bufor, do którego ładowana jest grafika poziomu
     */

    private BufferedImage bufferedImage[][];

    /**
     * punkt X, od ktorego zaczniemy rysowanie obiektu
     */

    private int StartDrawingX;

    /**
     * punkt Y, od ktorego zaczniemy rysowanie obiektu
     */
    private int StartDrawingY;

    /**
     * szerokosc obiektu graficznego, zalezna od szerokosci panela i ilosci kolumn
     */
    private int SizeWidthIcon;

    /**
     * wysokosc obiektu graficznego, zalezna od wysokosci panela i ilosci wierszy
     */

    private int SizeHeightIcon;

    public PanelBoard(){PanelBoard();}

    private void PanelBoard() {

        /**
         * parametry ogólne planszy
         */
        this.setSize(panelboardwidth, panelboardheight);
        this.setLocation(0, panelinfooneheight);
        this.setBackground(Color.BLACK);


        try{
            /**
             * wczytywanie grafik na podstawie ściezek z pliku konfiguracyjnego
             */
            File PolandBallFile = new File(PolandBall);
            File SovietBallFile = new File(SovietBall);
            File NaziBallFile = new File(NaziBall);
            File TurkeyBallFile = new File(TurkeyBall);
            File BetonFile = new File(Beton);
            File SkrzynkaFile = new File(Skrzynka);
            File DoorFile = new File(Door);
            File KeyFile = new File(Key);
            /**
             * dwuwymiarowa tablica, w której zawarte są kody poszczególnych pól planszy, wczytywane z pliku konfiguracyjnego
             */
            ArrayList<ArrayList<String>> field = new ArrayList<>();
            for (int i=0;i<Amountoflines;i++) {
                field.add(new ArrayList<>());
                String bufor_string[]=row[i].split(" ");
                for(int j=0;j<Amountofcolumns;j++) {
                    field.get(i).add(bufor_string[j]);
                }
            }

            /**
             * dodanie grafik do bufora - tablicy dwuwymiarowej, reprezentujcej układ planszy
             */

            bufferedImage = new BufferedImage[Amountoflines][Amountofcolumns];
            for(int i=0;i<Amountoflines;i++){
                for(int j=0;j<Amountofcolumns;j++) {
                    if (field.get(i).get(j).equals("N_")) {
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
            System.out.println("Blad wczytywania planszy");
        }

    }

    /**
     * funkcja rysująca mape poziomu
     * @param g
     */
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,panelboardwidth,panelboardheight);

        StartDrawingX = 0;
        StartDrawingY = 0;
        SizeWidthIcon = panelboardwidth/Amountofcolumns;
        SizeHeightIcon = panelboardheight/Amountoflines;

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
