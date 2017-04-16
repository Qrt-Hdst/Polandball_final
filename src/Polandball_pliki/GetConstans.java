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
	 * Wysokość okna głównego - menu
	 */

	public static int MainFrameheight;

	/**
	 * Szerokość okna głównego - menu
	 */

	public static int MainFramewidth;

	/**
	 * Wysokosc ramki
	 **/

	public static int Boardheight;

	/**
	 * Szerokosc ramki
	 **/

	public static int Boardwidth;

	/**
	 * Szybkosc potworow
	 **/

	public static int Monsterspeed;

	/**
	 * Ilosc zyc na poczatku rozgrywki
	 **/

	public static int Amountoflifes;

	/**
	 * Ilosc kolumn
	 **/

	public static int Amountofcolumns;

	/**
	 *Ilosc wierszy
	 **/

	public static int Amountoflines;

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
	 * Rozklad elementow planszy
	 **/

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
	 * Sciezka do grafiki Door
	 */
	public static String DoorString;

	/**
	 * Wczytywanie danych startowych z plikow
	 **/

	public GetConstans(){
		read_on_config();
		read_on_level(1);
		read_path_to_graphics();
		panelboardheight =(int)(0.75*Boardheight);
		panelboardwidth =(int)(0.8*Boardwidth);
		panelinfooneheight = (int)(0.2*Boardheight);
		panelinfotwoheight =(int)(0.75*Boardheight);
		panelinfotwowidth = (int)(0.2*Boardwidth);
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
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * @param level parametr decydujacy ktory level zostanie wczytany
	 * wczytanie pliku
	 */

	void read_on_level(int level){
		try {

			String path_to_level=create_path_to_level(level);
			File file = new File(path_to_level);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
			doc.getDocumentElement().normalize();

			Monsterspeed = Integer.parseInt(doc.getElementsByTagName("Monsterspeed").item(0).getTextContent());
			Amountoflifes = Integer.parseInt(doc.getElementsByTagName("Amountoflifes").item(0).getTextContent());
			Amountofcolumns = Integer.parseInt(doc.getElementsByTagName("Amountofcolumns").item(0).getTextContent());
			Amountoflines = Integer.parseInt(doc.getElementsByTagName("Amountoflines").item(0).getTextContent());
			Amountofordinarybombs = Integer.parseInt(doc.getElementsByTagName("Amountofordinarybombs").item(0).getTextContent());
			Amountofremotebombs = Integer.parseInt(doc.getElementsByTagName("Amountofremotebombs").item(0).getTextContent());
			Amountofhusarswings = Integer.parseInt(doc.getElementsByTagName("Amountofhusarswings").item(0).getTextContent());
			Amountoflasers = Integer.parseInt(doc.getElementsByTagName("Amountoflasers").item(0).getTextContent());
			Amountofkeys = Integer.parseInt(doc.getElementsByTagName("Amountofkeys").item(0).getTextContent());

			row = new String[Amountoflines];
			for (int i = 0; i < Amountoflines; i++) {
				row[i] = doc.getElementsByTagName("row").item(i).getTextContent();
			}
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}

	void read_path_to_graphics() {
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

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	String create_path_to_level(int level){
		String first_part="src\\Polandball_pliki\\Level";
		String second_part=Integer.toString(level);
		String third_part=".xml";

		String path=first_part+second_part+third_part;

		System.out.println(path);
		return path;
	}

}
