package Polandball_pliki;

/**
 * Pole gry, plansza
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import Polandball_pliki.Collision.*;
import Polandball_pliki.Counter.*;
import Polandball_pliki.GameObjects.*;

import static Polandball_pliki.GetConstans.*;
import static Polandball_pliki.MainFrame.startPanel;
import static Polandball_pliki.SetConnection.ServerMode;



public class PanelBoard extends JPanel implements ActionListener,KeyListener{
    /**
     * Sprawdza czy gracz ma włączone skrzydła hussarskie
     */
    public static boolean hussars_Power= false;
    /**
     * Zmienna okreslajaca czy gracz jest w poblizu drzwi
     */
    public static boolean player_stay_on_door=false;
    /**
     * Tablica obiektow przeciwnikow
     */
    public static ArrayList<Enemy> enemy=new ArrayList<>();

    /**
     * Tablica obiektow typu gracz
     */
    public static  ArrayList<Polandball> player=new ArrayList<>();
    /**
     * Tablica obiektow zdalnych bomb
     */
    public static Remote_Bomb remote_bomb=null;

    /**
     * Tablica obiektow zwykle bomby
     */
    public static ArrayList<Normal_Bomb> normal_bomb=new ArrayList<>();

    /**
     *  Tablica obiektow eksplozji
     */
    public static ArrayList<Explosion> explosions =new ArrayList<>();

    /**
     * Tablica licznikow jakie sa w tej chwili w bazie danych
     */
    public static ArrayList<Counter> counters=new ArrayList<>();

    /**
     *  Tablica obiektow bedacych elementami terenu
     */
    public static ArrayList<Terrain> terrains=new ArrayList<>();

    /*
     * Tablica do przechowywania drzwi
     *

    public static ArrayList<Item> doors=new ArrayList<>();
    */
    /*
     * Tablica do przechowywania kluczy
     *

    public static ArrayList<Item> keys=new ArrayList<>();
    */
    /**
     *  Tablica itemow (elementow do zebrania/uruchomienia interakcji przez gracza)
     */
    public static ArrayList<Item> items=new ArrayList<>();

    /**
     * Tablica znakow(pol), na ktorej jest zapisana plansza
     */
    public static ArrayList<ArrayList<String>> field = new ArrayList<>();
    /**
     * Tablica watkow wrogow
     */
    public static ArrayList<Thread> TableOfEnemyThreads = new ArrayList<>();
    /**
     * Tablica, do ktorej beda rozdzielane ciagi znakow z pliku konfiguracyjnego
     */
    public String bufor_string[];

    /**
     * Zmienna okreslajaca, czy player zyje
     */
    public static boolean PlayerExistence=false;


    /**
     * Zmienna okreslajaca, czy gra znajduje sie w stanie pauzy
     */
    public static boolean PauseActive = false;

    /**
     * Zmienna okreslajaca, czy mozemy postawic kolejna zdalna bombe
     */

    public static boolean PermissionForRemoteBomb=true;

    /**
     * Watek zegara gry
     */
    public static Thread timethread;

    /**
     * szerokosc obiektu graficznego, zalezna od szerokosci panela i ilosci kolumn
     */

    public static int SizeWidthIcon = ((int)(0.8*Boardwidth))/Amountofcolumns;

    /**
     * wysokosc obiektu graficznego, zalezna od wysokosci panela i ilosci wierszy
     */

    public static int SizeHeightIcon = ((int)(0.8*Boardheight))/Amountoflines;

    /**
     * konstruktor panelu głównego, zawierający funkcję PanelBoard
     */

    public PanelBoard(){PanelBoard();}


    /**
     * konstruktor zawirający parametry planszy, okreslenie liczby i polozenie GameObject jakie sie znajdą w grze
     */

    private void PanelBoard() {
        panelboardheight =(int)(0.8*Boardheight);
        panelboardwidth =(int)(0.8*Boardwidth);
        panelinfooneheight = (int)(0.2*Boardheight);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //ogólne parametry planszy
        this.setSize(panelboardwidth, panelboardheight);
        this.setLocation(0, panelinfooneheight);
        this.setBackground(Color.BLACK);

            //dwuwymiarowa tablica, w której zawarte są kody poszczególnych pól planszy, wczytywane z pliku konfiguracyjnego
            for (int i=0;i<Amountoflines;i++) {
                field.add(new ArrayList<>());
                bufor_string=row[i].split(" ");
                for(int j=0;j<Amountofcolumns;j++) {
                    field.get(i).add(bufor_string[j]);
                }
            }


            //wypelnienie dynamicznej tablicy elementami, w zaleznosci od wczytanej konfiguracji
            for(int i=0;i<Amountoflines;i++){
                for(int j=0;j<Amountofcolumns;j++) {
                    if (field.get(i).get(j).equals("N_")) {
                    } else if (field.get(i).get(j).equals("B_")) {
                        terrains.add(new Beton(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie betonu w pole j,i
                    } else if (field.get(i).get(j).equals("S_")) {
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie skrzynki w pole j,i
                    } else if (field.get(i).get(j).equals("NG")) {
                        player.add(new Polandball(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie playera w polu j,i
                        PlayerExistence=true;
                    } else if (field.get(i).get(j).equals("NW")) {
                        enemy.add(lottery_of_enemies(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie wroga w  pole j,i
                    } else if (field.get(i).get(j).equals("SD")) {
                        items.add(new Door(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie itemu-Drzwi w polu j,i
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    } else if (field.get(i).get(j).equals("SK")) {
                        items.add(new Key(SizeWidthIcon*(j),SizeHeightIcon*(i)));//dodanie itemu-Klucz w polu j,i
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    } else if (field.get(i).get(j).equals("SI")) {
                        items.add(lottery_of_items(SizeWidthIcon*(j),SizeHeightIcon*(i)));
                        terrains.add(new Skrzynka(SizeWidthIcon*(j),SizeHeightIcon*(i)));//zakrywam item skrzynka
                    }
                    //do zrobienia jeszcze if z itemami typu skrzydla husarskie czy laser sprawiedliwosci
                }
            }
        createThreadsForEnemy();//tworzenie watkow wrogow
        GameTime gametime = new GameTime();//ustawienie zegara gry,utworzenie obiektu zegara
        timethread =new Thread(gametime.getGameTime());//stworzenie watku dla zegara
        timethread.start();//uruchomienie watku zegara
        //------------->TRZEBA ZABIC TEN WATEK PRZY ZAMKNIECIU OKNA LUB PRZY NASTEPNYM POZIOMIE<----------------------
    }


    /**
     *Funkcja losujaca item, jaki ma sie pojawic pod skrzynka w danym miejscu
     */
    public Item lottery_of_items(int position_itemX,int position_itemY){
        Item item_;

        Random randomGenerator =new Random();

        int fate= randomGenerator.nextInt(5);

        if(fate==0){
            item_=new WingsOfHussar(position_itemX,position_itemY);
        }
        else if(fate==1){
            item_=new ChestOfGold(position_itemX,position_itemY);
        }
        else if(fate==2){
            item_=new BoxOfBomb(position_itemX,position_itemY);
        }
        else if(fate==3){
            item_=new GunLaser(position_itemX,position_itemY);
        }
        else if(fate==4){
            item_=new Heart(position_itemX,position_itemY);
        }
        else {
            item_=null;
        }

        return item_;

    }


    /**
     * Funkcja losujaca typ potwora
     */
    public Enemy lottery_of_enemies(int position_enemyX,int position_enemyY){
        //instancja klasy enemy do ktorej w zaleznosci od losu przypisze konkretny enemyballa
        Enemy enemy_;
        //inicjuje zmienna losujaca
        Random randomGenerator=new Random();
        //losuje liczbe od 0 do 2
        int fate= randomGenerator.nextInt(3);

        if(fate==0){//kiedy wylosuje zero losuje turkey balla
            enemy_=new Turkeyball(position_enemyX,position_enemyY);
        }else if(fate==1){//kiedy wylosuje jeden losuje naziballa
            enemy_=new Naziball(position_enemyX,position_enemyY);
        }else if(fate==2){//kiedy wylosuje dwa losuje sovietballa
            enemy_=new Sovietball(position_enemyX,position_enemyY);
        }else {
            enemy_=null;//zwraca null w innych przypadkach, jakis blad w losowaniu
        }
        return enemy_;//zwracam wylosowanego wroga

    }

    /**
     * metoda wywolywana przy wywołaniu jakiejś akcji (np. przesuniecia obiektu z punktu a do b)
     * @param e parametr przchowujacy informacje na temat zmian w programie
     */
    public void actionPerformed(ActionEvent e) {
        if(PauseActive==false) {
            movePlayer();//meteda odpowiedzialna za poruszenie gracza
            moveEnemy();//metoda odpowiedzialna za poruszanie wrogow
            checkPlayerHasCollectedItem();//metoda sprawdzajaca czy gracz zebrał którys item
            checkNormalBombStatus();//sprawdzanie na bieżąco czy bomba nie ma juz wybuchnąć
            checkExplosionStatus();//sprawdzanie czy przypadkiem eksplozja nie powinna sie juz skonczyc
            checkRespawnPlayer(); // sprawdzanie czy w razie gdyby player zginal nie nastapil czas jego respawnu
            checkCounter();//funkcja sprawdzajaca stan licznik w grze
            repaint();//odmalowywanie gracza po kazdym wykrytym zdarzeniu
        }
    }


    /**
     * Metoda sprawdzajaca czy gracz nie zebral ktoregos elementu
     */
    void checkPlayerHasCollectedItem() {
        if (items.size() > 0 && player.size() > 0) {

            boolean Player_not_take_a_item = true;//zmienna okreslajaca czy player schwycil item
            for (Iterator<Item> iteratoItems = items.iterator(); iteratoItems.hasNext(); ) {//iteratoItems wskazuje na kolejne elementy z kolekcji items
                Item itemInstance = iteratoItems.next();// przypisuje obiekt item na obiekt na który wskazuje iteraItems
                    Player_not_take_a_item = new CollisionPlayerWithItem(player.get(0), itemInstance).getIsNotCollision();
                    //wykonuje sie sie kiedy player wykonal kolizje z itemem i w omawianym miejscu nie ma zadnej skrzynki
                    if (!Player_not_take_a_item && StatioonaryObjectTab[itemInstance.getRowY()][itemInstance.getColumnX()] == 0) {
                        System.out.println("Zlapalem " + itemInstance.getNameClassObject());
                        // Amountofpoints=ChangeInfoStatus(Amountofpoints,PointsForItem);//punty za itemek
                        // PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//wyswietlenie w labelu
                        if(!itemInstance.getNameClassObject().equals(DoorString)){
                            iteratoItems.remove();//usuwam element jesli kolizja wykryla zetkniecie sie itema i gracza
                        }

                        //sprawdzamy, ktory itemek podnieslismy
                        if (itemInstance.getNameClassObject().equals("src//GameGraphics//Winge_of_hussars.png")) {
                            Amountofhusarswings = ChangeInfoStatus(Amountofhusarswings, 1);//dodanie skrzydel do ekwipunku
                            PanelInfoTwo.Iloscskrzydelhusarskich.setText(Integer.toString(Amountofhusarswings));//wyswietlenie w labelu
                            Amountofpoints = ChangeInfoStatus(Amountofpoints, PointsForItem);//punty za itemek
                            PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//wyswietlenie w labelu
                        } else if (itemInstance.getNameClassObject().equals("src//GameGraphics//ChestOfGold.png")) {
                            Amountofpoints = ChangeInfoStatus(Amountofpoints, PointsForChestOfGold);//punty za skrzynke zlota
                            PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//wyswietlenie w labelu
                        } else if (itemInstance.getNameClassObject().equals("src//GameGraphics//Gun_laser.png")) {
                            Amountoflasers = ChangeInfoStatus(Amountoflasers, 1);//dodanie lasera do ekwipunku
                            PanelInfoTwo.Ilosclaserow.setText(Integer.toString(Amountoflasers));//wyswietlenie w labelu
                            Amountofpoints = ChangeInfoStatus(Amountofpoints, PointsForItem);//punty za itemek
                            PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//wyswietlenie w labelu
                        } else if (itemInstance.getNameClassObject().equals("src//GameGraphics//Heart.png")) {
                            Amountoflifes = ChangeInfoStatus(Amountoflifes, 1);//dodanie zycia
                            PanelInfoOne.LifeLabel2.setText(Integer.toString(Amountoflifes));//wyswietlenie w labelu
                            Amountofpoints = ChangeInfoStatus(Amountofpoints, PointsForItem);//punty za itemek
                            PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//wyswietlenie w labelu
                        } else if (itemInstance.getNameClassObject().equals("src//GameGraphics//box_of_bombs.png")) {
                            Amountofremotebombs = ChangeInfoStatus(Amountofremotebombs, 1);//dodanie zdalnej bomby
                            PanelInfoTwo.Iloscbombzdalnych.setText(Integer.toString(Amountofremotebombs));//wyswietlenie w labelu
                            Amountofordinarybombs = ChangeInfoStatus(Amountofordinarybombs, 2);//dodanie zwyklej bomby
                            PanelInfoTwo.Iloscbombzwyklych.setText(Integer.toString(Amountofordinarybombs));//wyswietlenie w labelu
                            Amountofpoints = ChangeInfoStatus(Amountofpoints, 3 * PointsForItem);//punty za itemek
                            PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//wyswietlenie w labelu
                        } else if (itemInstance.getNameClassObject().equals("src//GameGraphics//Key.png")) {
                            Amountofkeys = ChangeInfoStatus(Amountofkeys, 1);
                            PanelInfoTwo.Ilosckluczy.setText(Integer.toString(Amountofkeys));//wyswietlenie w labelu
                        } else if (itemInstance.getNameClassObject().equals(DoorString) &&  Amountofkeys>0){
                                player_stay_on_door=true;
                        }
                    }
                    else {
                        player_stay_on_door=false;
                    }
                }
            }
        }
    /**
     * Metoda sprawdzają
     */

    /**
     * metoda do wywołania zmiany polozenia polandballa
     */

    void movePlayer() {
        if(player.size()>0) {
            //zmienna przechowujaca informacje czy player nie zachacza o instancje jakiegos terenu
            boolean I_can_go = new CollisionLivingObjectWithTerrain(player.get(0), "player").getIsNotCollision();
            boolean Enemy_didnt_caught_player= checkPotentialCollisionWithEnemy();
            if (I_can_go && Enemy_didnt_caught_player) {//jesli jest I_can_go oraz Enemy_didnt_caught_player jest true to player moze sie poruszyc
                player.get(0).changeX(player.get(0).getX() + player.get(0).get_velX());
                player.get(0).changeY(player.get(0).getY() + player.get(0).get_velY());
            }
        }
    }

    /**
     * Sprawdza potencjalne kolizje Gracza z wrogami i zwraca odpowiednia wartosc true/false.
     */
    boolean checkPotentialCollisionWithEnemy(){
        boolean isNotCollision=true;
        for(Iterator<Enemy> iteratoEnemy = enemy.iterator(); iteratoEnemy.hasNext();){
            Enemy enemy1=iteratoEnemy.next();
            isNotCollision=new CollisionPlayerWithEnemy(player.get(0),enemy1).getIsNotCollision();
            if(isNotCollision==false){
                PlayerExistence=false;
                counters.add(new CounterPlayer(player.get(0)));
                player.remove(0); //usuwa playera z mapy
                Amountoflifes=ChangeInfoStatus(Amountoflifes,-1);//zmniejszenie zyc o 1
                PanelInfoOne.LifeLabel2.setText(Integer.toString(Amountoflifes));//aktualizacja wyswietlenia ilosci zyc
                checkPlayerLifes();//metoda sprawdzajaca czy graczowi nei skonczyly sie zycia
                System.out.println(Amountoflifes);
                return isNotCollision;
            }
        }
        return isNotCollision;

    }

    /**
     * Metoda tworzaca watki dla wrogow
     */

    void createThreadsForEnemy(){
        try {
            for (int i = 0; i < enemy.size(); i++) {
                Thread EnemyThread = new Thread(enemy.get(i).getEnemy());//.start();//(new Enemy(enemy.get(i).getEnemy())).start();
                EnemyThread.start();
                TableOfEnemyThreads.add(EnemyThread);
            }

        } catch(Exception e){
            System.out.println("Blad w watku");
            System.out.println(e);
        }
    }
    /**
     * Metoda poruszajaca wrogow
     */

    void moveEnemy(){
        for(int i=0;i<enemy.size();i++){
        //dla kazdego worga kierunek ruchu jest wyliczany w niezaleznym watku
        int kierunek = enemy.get(i).getEnemydirection();
            //zmiana predkosci (poruszania sie) wroga, w zaleznosci od kierunku
            switch(kierunek) {
                case 0:
                    enemy.get(i).change_velX(1);
                    enemy.get(i).change_velY(0);
                break;
                case 1:
                    enemy.get(i).change_velX(-1);
                    enemy.get(i).change_velY(0);
                break;
                case 2:
                    enemy.get(i).change_velY(-1);
                    enemy.get(i).change_velX(0);
                break;
                case 3:
                    enemy.get(i).change_velY(1);
                    enemy.get(i).change_velX(0);
                break;
            }
            enemy.get(i).changeX(enemy.get(i).getX() + enemy.get(i).get_velX());
            enemy.get(i).changeY(enemy.get(i).getY() + enemy.get(i).get_velY());
        }
    }

    /**
     * Metoda sprawdzajaca, czy bomba juz wybuchla
     */

    private void checkNormalBombStatus(){
        //sprawdzam wszystkie bomby po kolei
        for(int i=0;i<normal_bomb.size();i++) {
            if (normal_bomb.get(i).getExplosionflag() == true){//wywoluje sie gdy flaga bomby informujaca ze ma teraz eksplozje jest true
                createExplosion(normal_bomb.get(i)); // tworze eksplozje na ekranie
                normal_bomb.remove(i);//usuniecie danej bomby z tablicy bomb, jesli wybuchla(czas sie skonczyl)
            }
        }
    }

    /**
     * Metoda tworzaca eksplozja w okreslonym obszarze
     * @param bomb  bomba ktora wybucha i zostawia eksplozje
     */
    private void createExplosion(Bomb bomb){

        explosions.add(new Explosion(bomb.getX()-SizeWidthIcon,bomb.getY()-SizeHeightIcon));//dodaje do tablicy obiekt typu eksplozja
        // UWAGAbedzie wymiarow 3 na 3 wiec przesuwam go o jedna kolumne na zachod i o wiersz na polnoc
        checkTerrainToDestroyByExplosion();//wywoluje funkcje sprawdzajaca czy w wyniku ekplozji nie powinno sie zniszczyc jakiejs skrzynki
        counters.add(new CounterExplosion(explosions.get(explosions.size()-1) ) );//dodaje licznik liczacy czas trwania eksplozji
    }

    /**
     * Metoda Sprawdza czy eksplozja nie zniszczyla jakichs skrzynek, jesli tak to je niszczy
     */
    public void checkTerrainToDestroyByExplosion(){

        boolean notDestroy=true;//zmienna przechowujaca informacje czy aby napewno dochodzi do jakiegoś zniszczenia obiektu

        for(Iterator<Terrain> iteratoTerrain =terrains.iterator();iteratoTerrain.hasNext();) {// dokonuje iteracji po calym zbiorze terrain za pomoca iteratorow, 1) tworze iterator 2) ustawiam go na pierwszy element tej kolejkcji
            Terrain ter = iteratoTerrain.next();//tworze instancje obiektu w petli na podstawie obiektu na ktory wskazuje iterator
            if (ter.getNameClassObject().equals(SkrzynkaString)) {//jesli element skrzynka zwroci parametr name_class_object taki sam jak SkrzynkaString, to wykona sie if
                notDestroy = new CollisionExplosionWithSkrzynki( ter, explosions.get(explosions.size() - 1) ).getIsNotCollision();//sprawdzam czy ta konkretne skrzynke moze zniszczyc eksplozja
                if (!notDestroy) {//jesli eksplozja pokazuje ze mamy zniszczyc obiekt wykonuje sie if
                    StatioonaryObjectTab[ter.getRowY()][ter.getColumnX()]=0;//zmieniam wartosc w tablicy stworzonej w GetConstan apropo pol w ktore mogą wejsc living object
                    iteratoTerrain.remove();//usun obiekt wskazywany przez iterator ( a wlasciwie to pozbadz sie wszelkich wskaznikow na niego wskazujacych )
                    Amountofpoints=ChangeInfoStatus(Amountofpoints,PointsForCreate);//punkty za skrzynki
                    PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//aktualizacja wyswietlenia punktow
                }
            }
        }

    }

    /**
     * Metoda sprawdzajaca status Eksplozji ( czy sie juz skonczyla czy nie , jesli tak to usuwa obiekt eksplozja
     */
    private void checkExplosionStatus(){
        for (int i=0;i<explosions.size();i++){//iteruje po co calej kolekcji eksplozje
            if(explosions.get(i).get_end_of_explosion()==true){//jesli eksplozja ma flage oznaczajaca koniec jej istnienie to odpala sie if z usunieciem eksplozji z ekranu
                explosions.remove(i);
            }
        }
        checkPlayerToDestroy();//sprawdza czy player nie zginie w wyniku wybuchu eksplozji
        checkEnemyToDestroy();//sprawdza czy ktorys z potwoorow nie zginie w wyniku eksplozji
    }

    /**
     * Metoda sprawdza czy obiekt typu player nie zostanie zniszczonywn w wyniku eksplozjii
     */

    private void checkPlayerToDestroy(){
        if(player.size()>0) {
            boolean notDestroy = true;// flaga jednoznacznie stwierdzajaca czy obiekt typu player powinnien zostac usuniety
            for (Iterator<Explosion> iteratoExplosion = explosions.iterator(); iteratoExplosion.hasNext(); ) {//iteruje po kolekcji explosion 1) tworze iterator dla kolekcji ekplozjii 2) przypisuje go do do pierwszego elementu kolekcji
                Explosion explInstance = iteratoExplosion.next();//tworze instancje obiektu typu eksplozja na podstawie obiektu na ktory wskazuje w danej chwili iterator
                notDestroy = new CollisionLivingObjectWithExplosion(player.get(0), explInstance).getIsNotCollision();//sprawdzam czy player napewno nie ucierpi w wyniku eksplozji
                if (!notDestroy) {//jesli w wyniku oblicazen wyszlo ze gracz ucierpi w wyniku eksplozji
                    Amountoflifes=ChangeInfoStatus(Amountoflifes,-1);//zmniejszenie zyc o 1
                    PanelInfoOne.LifeLabel2.setText(Integer.toString(Amountoflifes));//aktualizacja wyswietlenia ilosci zyc
                    PlayerExistence=false;
                    counters.add(new CounterPlayer(player.get(0)));
                    player.remove(0); //usuwa playera z mapy
                    checkPlayerLifes();//metoda sprawdzajaca czy graczowi nei skonczyly sie zycia
                }
            }
        }
    }

    /**
     * Metoda sprawdzająca czy obiekt typu Enemy nie zostanie zniszczony w wyniku eksplozji
     */

    private void checkEnemyToDestroy(){
        boolean notDestroy=true;// flaga jednoznacznie stwierdzajaca czy obiekt typu enemy powinnien zostac usuniety

        for(Iterator<Explosion> iteratoExplosion = explosions.iterator();iteratoExplosion.hasNext();) {//przelatuje po kolekcji explosion
            Explosion explInstance=iteratoExplosion.next();// przypisuje do zminnej insntanceExplosion obiekt na ktory wskazuje iterator
            int i=0;
            for (Iterator<Enemy> iteratoEnemy = enemy.iterator(); iteratoEnemy.hasNext(); ) {//iteruje po kolekcji enemy 1) tworze iterator dla kolekcji enemy 2) przypisuje go do do pierwszego elementu kolekcji enemy
                Enemy enemInstance = iteratoEnemy.next();//tworze instancje obiektu typu enemy na podstawie obiektu na ktory wskazuje w danej chwili iterator
                    notDestroy = new CollisionLivingObjectWithExplosion(enemInstance, explInstance).getIsNotCollision();//sprawdzam czy enemy napewno nie ucierpi w wyniku eksplozji
                    if (!notDestroy) {//jesli w wyniku obliczen wyszlo ze potwor ucierpi w wyniku eksplozji
                        TableOfEnemyThreads.get(i).stop();
                        TableOfEnemyThreads.remove(i);
                        iteratoEnemy.remove();//usuwa enemy wskazywany przez iterator
                        Amountofpoints=ChangeInfoStatus(Amountofpoints,PointsForMonster);//punty za zabicie potwora
                        PanelInfoOne.PointLabel2.setText(Integer.toString(Amountofpoints));//wyswietlenie w labelu
                    }
                i++;
            }
        }
    }

    /**
     * Metoda sprawdza czy istnieje w tej chwili na planszy player, jesli nie (a powinnien bo ma np. trzy życia i jedno przed chwilą stracił w wyniku wybuchu) ustawia playera w polu startowy
     */
    public void checkRespawnPlayer(){

        if(PlayerExistence==true && player.size()==0) {
            setRespawnPolandball();//wywolanie metody ustawiajacej gracza w polu startowym
        }
    }

    /**
     * Ustawienie Polandballa w miejscu startowym po respawnie
     */
    public static void setRespawnPolandball() {
        for (int i = 0; i < Amountoflines; i++) {
            for (int j = 0; j < Amountofcolumns; j++) {
                if (field.get(i).get(j).equals("NG")) {
                    player.add(new Polandball(SizeWidthIcon * (j), SizeHeightIcon * (i)));//dodanie player w polu j,i
                }
            }
        }
    }

    /**
     * metoda sprawdzajaca czy ktorys z licznikow nie wykryl konca odliczania i jesli tak to go usunie z tablicy
     */
    void checkCounter(){
        for(int i=0;i<counters.size();i++){//iteruje po kolekcji liczniki
            counters.get(i).checkTime();//sprawdzam czy aby i-ty element kolekcji liczniki nie powinien juz zakonczyc swojego dzialania
            if(counters.get(i).getisStillNeed()==false){//sprawdzam czy licznik w ogole jeszcze jest potrzebny
                counters.remove(i);//usuwam niepotrzebny licznik
            }
        }
    }
    /**
     * metoda reagujaca na nacisniecie klawisza
     * @param e wydarzenie przechowujace informacje na temat wcisnietego klawisza
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        if(PauseActive==false) { // sprawdzam czy gra nie jest w stanie pauzy
            if (player.size() > 0) {
                int factor_mode=1;
                if(hussars_Power==true){
                    factor_mode=2;
                }
                if (c == KeyEvent.VK_LEFT) {
                    player.get(0).change_velX(SpeedPlayer * -1*(factor_mode));
                    player.get(0).change_velY(0);
                } else if (c == KeyEvent.VK_RIGHT) {
                    player.get(0).change_velX(SpeedPlayer * 1*(factor_mode));
                    player.get(0).change_velY(0);
                } else if (c == KeyEvent.VK_DOWN) {
                    player.get(0).change_velY(SpeedPlayer * 1*(factor_mode));
                    player.get(0).change_velX(0);
                } else if (c == KeyEvent.VK_UP) {
                    player.get(0).change_velY(SpeedPlayer * (-1)*factor_mode);
                    player.get(0).change_velX(0);
                }
                //stawianie bomby
                else if (c == KeyEvent.VK_SPACE) {
                    if (Amountofordinarybombs > 0) {//sprawdzenie czy mamy jeszcze bomby
                        normal_bomb.add(new Normal_Bomb(player.get(0).getX(), player.get(0).getY()));//dodanie dodani bomby do tablicy
                        counters.add(new CounterNormalBomb(normal_bomb.get(normal_bomb.size() - 1)));//ustawienie licznika dla danego obiekty typu bomba
                        Amountofordinarybombs = ChangeInfoStatus(Amountofordinarybombs, -1);//zmniejszenie ilosci bomb
                        PanelInfoTwo.Iloscbombzwyklych.setText(Integer.toString(Amountofordinarybombs));//aktualizacja ilosci bomb
                        // znajdujacego sie w tablicy
                    } else {
                        System.out.println("Nie masz wiecej bomb");
                    }
                } else if (c == KeyEvent.VK_Z /*&& normal_bomb.size() < 3*/ && PermissionForRemoteBomb != false) {
                    System.out.println("remote " + (Amountofremotebombs > 0));
                    if (Amountofremotebombs > 0) {
                        remote_bomb = new Remote_Bomb(player.get(0).getX(), player.get(0).getY());//dodanie bomby do tablicy
                        Amountofremotebombs = ChangeInfoStatus(Amountofremotebombs, -1);//zmniejszenie ilosci bomb
                        PanelInfoTwo.Iloscbombzdalnych.setText(Integer.toString(Amountofremotebombs));//aktualizacja ilosci bomb
                        //znajdujace sie w tablicy
                        PermissionForRemoteBomb = false;//nie mozemy postawic nastepnej zdalnej bomby
                    }
                } else if (remote_bomb != null && c == KeyEvent.VK_X) {
                    createExplosion(remote_bomb); // tworze eksplozje na ekranie
                    remote_bomb = null; //zwracam referencje na obiekt null, z powrotem moge stawiac bombe
                    PermissionForRemoteBomb = true;//po zdetonowaniu zdalnej bomby mozemy postawic nastepna
                } else if (c == KeyEvent.VK_P) {
                    PauseActive = true;

                } else if (c == KeyEvent.VK_E) {
                    if (player_stay_on_door == true && Amountofkeys > 0) {
                        PauseActive = true;//zatrzymanie gry
                        NextLevelInfo nextlevelinfo = new NextLevelInfo();
                        nextlevelinfo.setVisible(true);
                    } else if (player_stay_on_door == true && Amountofkeys == 0) {
                        System.out.println("Brak kluczy do otworzenia drzwi");
                    } else if (player_stay_on_door == false && Amountofkeys == 1) {
                        System.out.println("Brak drzwi które mozna otworzyc");
                    }
                }
                else if(c == KeyEvent.VK_W) {
                    if (Amountofhusarswings > 0 && hussars_Power == false) {
                        hussars_Power = true;
                        Amountofhusarswings = ChangeInfoStatus(Amountofhusarswings, -1);
                        PanelInfoTwo.Iloscskrzydelhusarskich.setText(Integer.toString(Amountofhusarswings));//aktualizacja ilosci bomb
                        System.out.println("Hussarball, Active!");
                        counters.add(new CounterHussarWings());
                    }
                }
            }
        }
        else if( c==KeyEvent.VK_P ) {
            PauseActive=false;
        }

    }

    /**
     * funkcja wymuszona przez intefejs, nieuzywana
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e){

    }

    /**
     * Metoda opisujaca co sie dzieje gdy nie jest wciskany zaden klawisz
     * @param e zmienna przechowujaca informacje o dzialaniach w danej chwili na klawiszach
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if(player.size()>0) {
            player.get(0).change_velY(0);
            player.get(0).change_velX(0);
        }
    }


    /**
     * funkcja rysująca mape poziomu
     * @param g grafika na której "malujemy" elementy
     */

    public void paint(Graphics g){
        //System.out.println("Size of players "+ player.size());
        g.setColor(Color.black);
        g.fillRect(0,0,(int)(0.8*Boardwidth),(int)(0.8*Boardheight)); // rysuje czarny kwadrat bedacy tlem dla naszych grafik
        drawItem(g); //rysuje itemy na planszy
        drawBomb(g); //rysuje bomby na planszy
        drawPlayerObject(g);//rysuje playera
        drawEnemyObject(g);//rysuje wrogow
        drawTerrain(g);//rysuje elementy terenu - skrzynki, beton itd
        drawExplosion(g);//rysuje eksplozje
    }
    /**
     * funkcja rysujaca itemy na grafice
     * @param g grafika na ktorej jest namalowywana obiekty
     */
    public void drawItem(Graphics g){
        for(int i=0;i<items.size();i++) {
            g.drawImage(items.get(i).getBuffImage(), items.get(i).getX(), items.get(i).getY(), SizeWidthIcon, SizeHeightIcon, null);
        }
        /*//rysowanie drzwi
        for(int i =0;i<doors.size();i++){
            g.drawImage(doors.get(i).getBuffImage(), doors.get(i).getX(), doors.get(i).getY(), SizeWidthIcon, SizeHeightIcon, null);
        }
        //rysowanie kluczy
        for(int i =0;i<keys.size();i++){
            g.drawImage(keys.get(i).getBuffImage(), keys.get(i).getX(), keys.get(i).getY(), SizeWidthIcon, SizeHeightIcon, null);
        }*/
    }
    /**
     * funkcja rysujaca bomby na grafice
     * @param g grafika na ktorej jest namalowywana obiekty
     */
    public void drawBomb(Graphics g){
        //rysowanie wszystkich bomb na ekran
        for(int i=0;i<normal_bomb.size();i++){
                    g.drawImage(normal_bomb.get(i).getGIF(), normal_bomb.get(i).getX(), normal_bomb.get(i).getY(), SizeWidthIcon, SizeHeightIcon, this);
        }
        if(remote_bomb!=null){
            g.drawImage(remote_bomb.getBuffImage(),remote_bomb.getX(),remote_bomb.getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }

    /**
     * funkcja rysujaca eksplozji na grafice
     * @param g grafika na którje jest namalowywane obiekty
     */
    public void drawExplosion(Graphics g) {
        //rysowanie wszystkich eksplozji na ekran
        for (int i = 0; i < explosions.size(); i++) {
            {
                if(PauseActive==false) {
                    g.drawImage(explosions.get(i).getGIF(), explosions.get(i).getX(), explosions.get(i).getY(), 3 * SizeWidthIcon, 3 * SizeHeightIcon, this);
                }
                else{
                    g.drawImage(explosions.get(i).getGIF(), explosions.get(i).getX(), explosions.get(i).getY(), 3 * SizeWidthIcon, 3 * SizeHeightIcon, null);
                }
            }
        }
    }

    /**
     * funkcja rysujaca gracza
     * @param g grafika na która jest namalowywana ombiekty
     */
    public void drawPlayerObject(Graphics g) {
        //System.out.println("Size of players "+ player.size());
        if(player.size()>0) {
            if(hussars_Power==false) {
                g.drawImage(player.get(0).getBuffImage(), player.get(0).getX(), player.get(0).getY(), SizeWidthIcon, SizeHeightIcon, null);
            }else if(hussars_Power==true) {
                if(player.get(0).get_velX()>0) {
                    g.drawImage(player.get(0).getRightHussar(), player.get(0).getX(), player.get(0).getY(), SizeWidthIcon, SizeHeightIcon, null);
                }else if(player.get(0).get_velX()<0){
                    g.drawImage(player.get(0).getLeftHussar(), player.get(0).getX(), player.get(0).getY(), SizeWidthIcon, SizeHeightIcon, null);
                }else {
                    g.drawImage(player.get(0).getRightHussar(), player.get(0).getX(), player.get(0).getY(), SizeWidthIcon, SizeHeightIcon, null);
                }
            }
        }
    }
    /**
     * funkcja rysujaca wrogow
     * @param g grafika na która jest namalowywana obiekty
     */

    public void drawEnemyObject(Graphics g){
        for(int i=0;i<enemy.size();i++){
                g.drawImage(enemy.get(i).getBuffImage(), enemy.get(i).getX(), enemy.get(i).getY(), SizeWidthIcon, SizeHeightIcon,  null);

        }
    }

    /**
     * funkcja rysujaca obiekty terenowe
     * @param g grafika na której są namalowywane obiekty
     */

    public void drawTerrain(Graphics g){
        for(int i=0;i<terrains.size();i++) {
            g.drawImage(terrains.get(i).getBuffImage(),terrains.get(i).getX(),terrains.get(i).getY(),SizeWidthIcon,SizeHeightIcon,null);
        }
    }

    /**
     * Metoda zmieniajaca okreslony parametr
     * @param whattochange informuje jaka rzecz chcemy zmienic
     * @param howmuchtochange o ile chemy ja zmienic
     */
    public static int ChangeInfoStatus(int whattochange, int howmuchtochange){
        whattochange=whattochange+howmuchtochange;
        return whattochange;
    }

    /**
     * Metoda sprawdzajaca, czy gra nie powinna sie juz skonczyc, gdy gracz straci zycia
     */
    private void checkPlayerLifes(){
        if(Amountoflifes==0){
            GameOver gameover = new GameOver();
            gameover.setVisible(true);
            PauseActive=true;
        }
    }

    /**
     * Metoda przywracajaca ustawienia sprzed rozpoczecia rozgrywki
     */

    public static void MakeDefaultOption(int level){
        try{
            timethread.stop();//zatrzymanie watku licznika
            if(startPanel.MakeSocket()!=null && ServerMode==true){
                SetConnection.GetLevelConfig(startPanel.MakeSocket(), level);//pobranie danych pierwszego poziomu na poczatku gry
                GetConstans.MakeBoardObstacleTable();//zaladowanie jeszcze raz tablicy kolizji
            }else{
                GetConstans.read_on_level(level);//wczytanie konkretnego poziomu
                GetConstans.MakeBoardObstacleTable();//zaladowanie jeszcze raz tablicy kolizji
            }
            SizeHeightIcon = (int)((0.8*(double)Boardheight))/Amountoflines;//ustawienie pożadanych rozmiarów pól
            SizeWidthIcon = ((int)(0.8*(double)Boardwidth))/Amountofcolumns;
            KillEnemyThreads();//zabijane watkow wrogow
            ClearArraylists();//wywolanie nizej zdefiniowanej metody odpowiedzialnej za czyscienie buforw dynamicznych
        }catch(Exception e){
            System.out.println(e+"Blad metody panelboarda - PanelBoard, MakeDefaultOption()");
        }
    }

    /**
     * Metoda czyszczaca wszystki dynamiczne tablice
     */

    public static void ClearArraylists(){
        //czyszczenie wszystkich buforow
        enemy.clear();
        player.clear();
        remote_bomb=null;
        normal_bomb.clear();
        explosions.clear();
        counters.clear();
        terrains.clear();
        items.clear();
        field.clear();
        TableOfEnemyThreads.clear();
        PauseActive=false;
        PermissionForRemoteBomb=true;
        hussars_Power=false;
    }

    /**
     * Metoda odpowiedzialna za niszczenie watkow wrogow po zakonczeniu rogywki lub zmiany poziomu
     */
    public static void KillEnemyThreads(){
        for (int i=0; i<TableOfEnemyThreads.size();i++) {//stopowanie wszystkich watkow wroga
            TableOfEnemyThreads.get(i).stop();
        }
    }
}
