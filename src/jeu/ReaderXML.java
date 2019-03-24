package jeu;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReaderXML {
	private Document documentComplet;
	
	/** Constructeur d'un ReaderXML qui aura un Document, en proprieté d'instance, obtenu après que le fichier XML, dont
	 * le nom est passé en paramètre, soit parsé
	 * @param String nomFichier
	 */
	public ReaderXML(String nomFichier) {
		 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		 Document xml = null;
		 try {
			DocumentBuilder builder = factory.newDocumentBuilder();
	        File fileXML = new File("src/data/"+nomFichier);
			xml = builder.parse(fileXML);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 this.documentComplet = xml;
	}
	/** Getter du document du ReaderXML
	 * @return Document document
	 */
	public Document getDocument() {
		return this.documentComplet;
	}
}
