import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class mainTest {

	 static void additionMethod (String name) {
		
		String path = name + ".xml";
    	File f = new File (path);
    	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    	try {
    		DocumentBuilder builder = dbf.newDocumentBuilder(); 
    		Document doc = builder.newDocument();
    		Element root = doc.createElement("StudentList");
    		Element student = doc.createElement("Student");
    		
    		Element studentID = doc.createElement("StudentID");
    		Text idValue = doc.createTextNode("A001");
    		studentID.appendChild(idValue);
    		

    		Element studentName = doc.createElement("StudentName");
    		Text NameValue = doc.createTextNode("Josh");
    		studentName.appendChild(NameValue);
    		

    		Element mark = doc.createElement("Mark");
    		Text markValue = doc.createTextNode("20");
    		mark.appendChild(markValue);
    		
    		student.appendChild(studentID);
    		student.appendChild(studentName);
    		student.appendChild(mark);
    		
    		root.appendChild(student);
    		
    		doc.appendChild(root);
    		
    		DOMSource source = new DOMSource(doc);
    		
    		Result result = new StreamResult(f);
    		TransformerFactory transformerfactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerfactory.newTransformer();
    		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
    		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
    		transformer.transform(source, result);
    		System.out.println("Done");
    		
    	}
    	catch(ParserConfigurationException ex) {
    		
    	} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	 
	 static void readMethod (String name) {
		 String FILENAME = name + ".xml";
		 DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		 try {
			   dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
			   DocumentBuilder db = dbf.newDocumentBuilder();
			   Document doc = db.parse(new File(FILENAME));
			   doc.getDocumentElement().normalize();

			   System.out.println("Root Element :" + doc.getDocumentElement().getNodeName());
		       System.out.println("------");

		       NodeList list = doc.getElementsByTagName("Student");
			   
		       for (int temp = 0; temp < list.getLength(); temp++) {

		              Node node = list.item(temp);

		              if (node.getNodeType() == Node.ELEMENT_NODE) {

		                  Element element = (Element) node;

		                  // get staff's attribute
		                  //String id = element.getAttribute("id");

		                  // get text
		                  String firstname = element.getElementsByTagName("StudentID").item(0).getTextContent();
		                  String lastname = element.getElementsByTagName("StudentName").item(0).getTextContent();
		                  String nickname = element.getElementsByTagName("Mark").item(0).getTextContent();

		                 // NodeList salaryNodeList = element.getElementsByTagName("salary");
		                 // String salary = salaryNodeList.item(0).getTextContent();

		                  // get salary's attribute
		                //  String currency = salaryNodeList.item(0).getAttributes().getNamedItem("currency").getTextContent();

		                  System.out.println("Current Element :" + node.getNodeName());              
		                  System.out.println("StudentID : " + firstname);
		                  System.out.println("StudentName : " + lastname);
		                  System.out.println("Mark : " + nickname);
		                 // System.out.printf("Salary [Currency] : %,.2f [%s]%n%n", Float.parseFloat(salary), currency);

		              }
		          }
		       
			   
			   	
		 }catch(Exception dad) {
			 
		 }
		 
		 
	 }

	 static void editMethod(String name, String ID) throws IOException, ParserConfigurationException, SAXException, TransformerException {

		 	File inputFile = new File(name + ".xml");
	         DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
	         Document doc = docBuilder.parse(inputFile);
	         
	         Node StudentList = doc.getFirstChild();
	         Node Student = doc.getElementsByTagName("Student").item(0);
	         
	         NodeList list = Student.getChildNodes();
	         
	         for (int temp = 0; temp < list.getLength(); temp++) {
	             Node node = list.item(temp);
	             if (node.getNodeType() == Node.ELEMENT_NODE) {
	                Element eElement = (Element) node;
	                if ("StudentID".equals(eElement.getNodeName())) {
	                	 eElement.setTextContent(ID);
	                }
	             }
	          }
	         
	         	        
	         
	         	DOMSource source = new DOMSource(doc);
	    		Result result = new StreamResult(inputFile);
	    		TransformerFactory transformerfactory = TransformerFactory.newInstance();
	    		Transformer transformer = transformerfactory.newTransformer();
	    		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
	    		transformer.transform(source, result);
	    		
	    		
	         
	 }
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
    	
    	Scanner sc = new Scanner(System.in);
    	
    	System.out.println("Name");
    	String name = sc.nextLine();
    	
    	System.out.println("[1] Add [2] Edit [3] Read");
    	String type = sc.nextLine();
    	
    	if(type.equals("1")) {
    		additionMethod(name);
    	}
    
    	if(type.equals("3")) {
    		readMethod(name);
    	}
    	if(type.equals("2")) {
    		System.out.println("Enter new StudentID");
        	String ID = sc.nextLine();
    		editMethod(name, ID);
    	}

   }
    
       
    }
    
    
   
    

    
