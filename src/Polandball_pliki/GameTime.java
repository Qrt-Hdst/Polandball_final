package Polandball_pliki;

import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.PanelBoard.PauseActive;

/**
 * Klasa odpowiedzialna za czas gry
 */
public class GameTime implements Runnable{

    /**
     * Metoda zwracaja obiekt klasy Gametime
     * @return obiekt klasy GameTime
     */
    public GameTime getGameTime(){
        return this;
    }

    /**
     * Metoda watku odliczajaca czas
     */
    @Override
    public void run() {
        try {
            int i = LevelTime;
            while (i >= 0 ) {
                synchronized (this) {
                    Thread.sleep(1000);//odliczmay co 1 sekunde
                    if(PauseActive==false){
                        //super dziwna formula, jak to dziala? trzeba spytac wrozki
                        if ((LevelTime - (Math.floor(LevelTime / 60)) * 60) >= 10) {
                            PanelInfoOne.TimeLabel2.setText(Integer.toString((int) Math.floor(LevelTime / 60)) + ":"
                                    + Integer.toString((int) (LevelTime - (Math.floor(LevelTime / 60)) * 60)));
                            LevelTime--;//zmniejszenie czasu o 1 sekunde
                            i--;//petelka sie konczy na 0
                        } else if ((LevelTime - (Math.floor(LevelTime / 60)) * 60) < 10) {
                            PanelInfoOne.TimeLabel2.setText(Integer.toString((int) Math.floor(LevelTime / 60)) + ":0"
                                    + Integer.toString((int) (LevelTime - (Math.floor(LevelTime / 60)) * 60)));
                            LevelTime--;//zmniejszenie czasu o 1 sekunde
                            i--;//petelka sie konczy na 0
                        }
                    }
                }
            }
        } catch(Exception e){System.out.println(e+"Blad klasy GameTime");
        }
    }
}
