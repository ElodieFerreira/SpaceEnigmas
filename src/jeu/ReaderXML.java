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
	public Document getDocument() {
		return this.documentComplet;
	}
}
