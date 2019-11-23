package application;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import controllers.DatabaseController;
import models.Page;

public class Driver {

	private static String title;
	private static String text;
	private static ArrayList<Page> pages = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\Kevin\\Desktop\\test.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		NodeList pageList;
		DatabaseController db = new DatabaseController();

		doc.getDocumentElement().normalize();
		pageList = doc.getElementsByTagName("page");

		printAllNodeNames(pageList);
		
		for(Page page : pages) {
			db.savePage(page);
		}

	}

	private static void printAllNodeNames(NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);

			if (node.getNodeName().equalsIgnoreCase("title")) {
				title = node.getTextContent();
			} else if (node.getNodeName().equalsIgnoreCase("text")) {
				text = node.getTextContent();
				pages.add(new Page(title, text));
			}

			if (node.getNodeType() != Node.TEXT_NODE) {

				if (node.getChildNodes().getLength() > 1) {
					printAllNodeNames(node.getChildNodes());
				}
			}
		}
	}

}
