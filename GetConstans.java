package GetConstans;

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
		* Scie�ka do pliku konfiguracyjnego
	**/
	public static final String Config = "config.xml";
	
	/**
		* Wysoko�� planszy
	**/
	
	public static int Boardheigh;
	
	/**
		* Szeroko�� planszy
	**/
	
	public static int Boardwidth;
	
	/**
		* Pozycja X Polandballa
	**/
	
	public static int Polandballpositionx;
	
	/**
		* Pozycja Y Polandballa
	**/
	
	public static int Polandballpositiony;
	
	/**
		* Ilo�� potwor�w
	**/
	
	public static int Amountofmonsters;
	
	/**
		* szybko�� potwor�w
	**/
	
	public static int Monsterspeed;
	
	/**
		* Ilo�� �y� na pocz�tku rozgrywki
	**/
	
	public static int Amountoflifes;
	
	/**
		* Rozk�ad element�w planszy
	**/
	
	public static String row;
	
	static{
		parseConfig();
	}
	
	private GetConstans(){};
	
	/**
		* Wczytywanie p�l z pliku konfiguracyjnego
	**/
	
	public static void parseConfig(){
		try{
			File file = new File(Config);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
			
			Boardheigh=Integer.parseInt(doc.getElementsByTagName("Boardheigh").item(0).getTextContent());
			Boardwidth=Integer.parseInt(doc.getElementsByTagName("Boardwidth").item(0).getTextContent());
			Polandballpositionx=Integer.parseInt(doc.getElementsByTagName("Polandballpositionx").item(0).getTextContent());
			Polandballpositiony=Integer.parseInt(doc.getElementsByTagName("Polandballpositiony").item(0).getTextContent());
			Amountofmonsters=Integer.parseInt(doc.getElementsByTagName("Amountofmonsters").item(0).getTextContent());
			Monsterspeed=Integer.parseInt(doc.getElementsByTagName("Monsterspeed").item(0).getTextContent());
			Amountoflifes=Integer.parseInt(doc.getElementsByTagName("Amountoflifes").item(0).getTextContent());
			row=doc.getElementsByTagName("row").item(0).getTextContent();




		}
		catch(FileNotFoundException e){
            e.printStackTrace();
			}
        catch(Exception e){
            e.printStackTrace();
			}
	}

	/**
	 * tak
	 */
	
	
}
