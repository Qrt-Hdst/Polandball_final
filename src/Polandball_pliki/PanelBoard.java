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
import static Polandball_pliki.GetConstans.SovietBallString;

public class PanelBoard extends JPanel{

    /**
     * bufor, do którego ładowana jest grafika poziomu
     */

    private BufferedImage bufferedImage[][];

    /**
     * konstruktor panelu głównego, zawierający funkcję PanelBoard
     */

    public PanelBoard(){PanelBoard();}

    /**
     * funkcja zawirająca parametry planszy, wczytywanie grafik do bufora
     */

    private void PanelBoard() {

        //ogólne parametry planszy
        this.setSize(panelboardwidth, panelboardheight);
        this.setLocation(0, panelinfooneheight);
        this.setBackground(Color.BLACK);


        try{
            //wczytywanie grafik na podstawie ścieżek z pliku konfiguracyjnego
            File PolandBallFile = new File(PolandBallString);
            File SovietBallFile = new File(SovietBallString);
            //File NaziBallFile = new File(NaziBall);
            //File TurkeyBallFile = new File(TurkeyBall);
            File BetonFile = new File(BetonString);
            File SkrzynkaFile = new File(SkrzynkaString);
            File DoorFile = new File(DoorString);
            File KeyFile = new File(KeyString);

            //dwuwymiarowa tablica, w której zawarte są kody poszczególnych pól planszy, wczytywane z pliku konfiguracyjnego
            ArrayList<ArrayList<String>> field = new ArrayList<>();
            for (int i=0;i<Amountoflines;i++) {
                field.add(new ArrayList<>());
                String bufor_string[]=row[i].split(" ");
                for(int j=0;j<Amountofcolumns;j++) {
                    field.get(i).add(bufor_string[j]);
                }
            }

            //dodanie grafik do bufora - tablicy dwuwymiarowej, reprezentujcej układ planszy

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
     * punkt X, od ktorego zaczniemy rysowanie obiektu
     */

    public int StartDrawingX = 0;

    /**
     * punkt Y, od ktorego zaczniemy rysowanie obiektu
     */

    public int StartDrawingY = 0;

    /**
     * szerokosc obiektu graficznego, zalezna od szerokosci panela i ilosci kolumn
     */

    public int SizeWidthIcon = panelboardwidth/Amountofcolumns;

    /**
     * wysokosc obiektu graficznego, zalezna od wysokosci panela i ilosci wierszy
     */

    public int SizeHeightIcon = panelboardheight/Amountoflines;

    /**
     * funkcja rysująca mape poziomu
     * @param g
     */

    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0,0,panelboardwidth,panelboardheight);

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
