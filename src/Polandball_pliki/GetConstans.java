package Polandball_pliki;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.io.FileNotFoundException;



/**
 * Parametry pierwszego poziomu
 **/


public final class GetConstans {

	/**
	 * Sciezka do pliku konfiguracyjnego
	 **/
	public static final String Config = "src\\Polandball_pliki\\config.xml"; //poprawiłem ścieszke

	/**
	 * Wysokosc ramki
	 **/

	public static int Boardheight;

	/**
	 * Szerokosc ramki
	 **/

	public static int Boardwidth;

	/**
	 * Wysokość okna głównego - menu
	 */

	public static int MainFrameheight;

	/**
	 * Szerokość okna głównego - menu
	 */

	public static int MainFramewidth;

	/**
	 * Rozmiar okna Highscores, ramka jest kwadratowa
	 */

	public static int HighscoresFrameSize;

	/**
	 * Szybkosc gracza
	 */

	public static int SpeedPlayer;

	/**
	 * Zmienna zawierajaca ilosc sekund, po ktorym wybuchnie bomba
	 */

	public static int TimeToExplosion;

	/**
	 * Ilosc kolumn
	 **/

	public static int Amountofcolumns;

	/**
	 *Ilosc wierszy
	 **/

	public static int Amountoflines;

	/**
	 * Szybkosc potworow
	 **/

	public static int Monsterspeed;

	/**
	 * Ilosc zyc na poczatku rozgrywki
	 **/

	public static int Amountoflifes;


	/**
 	* Ilosc zwykłych bomb
 	*/

	public static int Amountofordinarybombs;

	/**
	* ilosc bomb zdalnych
	 */

	public static int Amountofremotebombs;

	/**
	 * ilosc skrzydel husarskich
	 */

	public static int Amountofhusarswings;

	/**
	 * ilosc lasrów
	 */

	public static int Amountoflasers;

	/**
	 * ilosc bomb zdalnych
	 */

	public static int Amountofkeys;

	/**
	 * Pomocnicza zmienna, okreslajaca wysokosc panelboard
	 */

	public static int panelboardheight;

	/**
	 * Pomocnicza zmienna, okeslajaca szerokosc panelboard
	 */

	public static int panelboardwidth;


	/**
	 * Pomocnicza zmienna, okreslajaca wysokosc gornego panelu
	 */

	public static int panelinfooneheight;

	/**
	 * Pomocnicza zmienna, okreslajaca wysokosc bocznego panelu
	 */

	public static int panelinfotwoheight;

	/**
	 * Pomocnicza zmienna, okreslajaca szerokosc gornego panelu
	 */

	public static int panelinfotwowidth;

	/**
	 * String przechowujcy informacje o wszystkich polach danego poziomu.
	 * W kolejnych elementach tablicy zawarte sa kolejne linie planszy
	 */

	public static String row[];
	/**
	 * Sciezka do grafiki Polandball
	 */
	public static String PolandBallString;
	/**
	 * Sciezka do grafiki TurkeyBall
	 */
	public static String TurkeyBallString;
	/**
	 * Sciezka do grafiki NaziBall
	 */
	public static String NaziBallString;
	/**
	 * Sciezka do grafiki SovietBall
	 */
	public static String SovietBallString;
	/**
	 * Sciezka do pola Skrzynka
	 */
	public static String SkrzynkaString;
	/**
	 * Sciezka do pola Beton
	 */
	public static String BetonString;
	/**
	 * Sciezka do grafiki Key
	 */
	public static String KeyString;
	/**
	 * Sciezka do gifa Normal_Bomb
	 */
	public static String Normal_BombString;
	/**
	 * Sciezka do grafiki Door
	 */
	public static String DoorString;
	/**
	 * Scieszka do gif-a z grafiką
	 */
	public static String ExplosionString;
	/**
	 * Sciezka do grafiki tła menu wejsciowego
	 */
	public static String BackgroundString;

	/**
	 * dwuwymaiora tablica, reprezentujaca wystepowanie przeszkody
	 */

	public static int StatioonaryObjectTab[][];

	/**
	 * Konstruktor klasy GetConstans, wczytywanie danych startowych z plikow
	 **/

	public GetConstans(){
		read_on_config();
		read_on_level(3);
		read_path_to_graphics();
	}

	/**
	 * Wczytanie stałych z pliku configuracyjnego
	 */

	void read_on_config(){
		try {
			File file = new File(Config);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			Boardheight = Integer.parseInt(doc.getElementsByTagName("Boardheight").item(0).getTextContent());
			Boardwidth = Integer.parseInt(doc.getElementsByTagName("Boardwidth").item(0).getTextContent());
			MainFrameheight=Integer.parseInt(doc.getElementsByTagName("MainFrameheight").item(0).getTextContent());
			MainFramewidth=Integer.parseInt(doc.getElementsByTagName("MainFramewidth").item(0).getTextContent());
			HighscoresFrameSize=Integer.parseInt(doc.getElementsByTagName("HighscoresFrameSize").item(0).getTextContent());
			SpeedPlayer = Integer.parseInt(doc.getElementsByTagName("SpeedPlayer").item(0).getTextContent());
			TimeToExplosion = Integer.parseInt(doc.getElementsByTagName("TimeToExplosion").item(0).getTextContent());

		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Metoda odpowiedzialna za wczytywanie poziomu
	 * @param level parametr decydujacy ktory level zostanie wczytany
	 */

	void read_on_level(int level){
		try {

			String path_to_level=create_path_to_level(level);
			File file = new File(path_to_level);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();




			Amountofcolumns = Integer.parseInt(doc.getElementsByTagName("Amountofcolumns").item(0).getTextContent());
			Amountoflines = Integer.parseInt(doc.getElementsByTagName("Amountoflines").item(0).getTextContent());
			Monsterspeed = Integer.parseInt(doc.getElementsByTagName("Monsterspeed").item(0).getTextContent());
			Amountoflifes = Integer.parseInt(doc.getElementsByTagName("Amountoflifes").item(0).getTextContent());
			Amountofordinarybombs = Integer.parseInt(doc.getElementsByTagName("Amountofordinarybombs").item(0).getTextContent());
			Amountofremotebombs = Integer.parseInt(doc.getElementsByTagName("Amountofremotebombs").item(0).getTextContent());
			Amountofhusarswings = Integer.parseInt(doc.getElementsByTagName("Amountofhusarswings").item(0).getTextContent());
			Amountoflasers = Integer.parseInt(doc.getElementsByTagName("Amountoflasers").item(0).getTextContent());
			Amountofkeys = Integer.parseInt(doc.getElementsByTagName("Amountofkeys").item(0).getTextContent());

			row = new String[Amountoflines];
			for (int i = 0; i < Amountoflines; i++) {
				row[i] = doc.getElementsByTagName("row").item(i).getTextContent();
			}
			//wywolanie metody tworzacej statyczna tablicy do wykrywania kolzji
			MakeBoardObstacleTable();
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * Metoda wczytujaca sciezki do grafik gry
	 */

	public static void read_path_to_graphics() {
		try {
			File file = new File("src\\Polandball_pliki\\PathToGraphics.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			PolandBallString = doc.getElementsByTagName("PolandBall").item(0).getTextContent();
			TurkeyBallString = doc.getElementsByTagName("TurkeyBall").item(0).getTextContent();
			SovietBallString = doc.getElementsByTagName("SovietBall").item(0).getTextContent();
			NaziBallString = doc.getElementsByTagName("NaziBall").item(0).getTextContent();
			SkrzynkaString = doc.getElementsByTagName("Skrzynka").item(0).getTextContent();
			BetonString = doc.getElementsByTagName("Beton").item(0).getTextContent();
			DoorString = doc.getElementsByTagName("Door").item(0).getTextContent();
			KeyString = doc.getElementsByTagName("Key").item(0).getTextContent();
			Normal_BombString = doc.getElementsByTagName("Normal_bomb").item(0).getTextContent();
			ExplosionString = doc.getElementsByTagName("Explosion").item(0).getTextContent();
			BackgroundString = doc.getElementsByTagName("Background").item(0).getTextContent();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metoda tworzaca sciezke do poziomu
	 * @param level
	 * @return path - sciezka do konkretnego levela
	 */

	String create_path_to_level(int level){
		String first_part="src\\Polandball_pliki\\Level";
		String second_part=Integer.toString(level);
		String third_part=".xml";

		String path=first_part+second_part+third_part;

		//System.out.println(path);
		return path;
	}

	/**
	 * Metoda tworzaca statyczna tablice do tworzenia kolizji
	 */
	public static void MakeBoardObstacleTable(){
		try {
			//tablica potrzebna do kolizji
			StatioonaryObjectTab = new int[Amountoflines][Amountofcolumns];
			//wypelnienie stacjonarnej tablicy zerami lub jedynkami
			for (int k = 0; k < Amountoflines; k++) {
				String bufor[] = row[k].split(" ");
				for (int j = 0; j < Amountofcolumns; j++) {
					//System.out.print(bufor[j]+ " ");
					//tworzy 1 tam gdzie jest skrzynka/beton/skrzynka z drzwiami//skrzynka z kluczem// skrzynka z  innymi itemem
					if (bufor[j].equals("S_") || bufor[j].equals("B_")) {
						//System.out.print(bufor[j]+ " ");
						//System.out.print(bufor[j]+ " ");
						StatioonaryObjectTab[k][j] = 1;
					} else if (bufor[j].equals("SD") || bufor[j].equals("SK") || bufor[j].equals("SI")) {
						StatioonaryObjectTab[k][j] = 1;
					} else {
						StatioonaryObjectTab[k][j] = 0;
					}
				}
				System.out.println();

			}
		}
		catch(NullPointerException e) {
			System.out.println("W metodzie MakeBoardObstaclesTable wystapil blad typu NullPointerException");
		}
	}
}
