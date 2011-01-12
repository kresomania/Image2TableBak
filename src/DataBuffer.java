import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class DataBuffer {

    // variables declaration
    private File xmlFile = new File("table.html");
    private Document xmlDocument;
    private Element rootElement;
    private ArrayList<String> pixList;
    private int width;


    public DataBuffer(ArrayList<String> pixList, int width) {
    	this.pixList = pixList;
    	this.width = width;
    	saveToXml();
    }
    
    public void saveToXml() {
        rootElement = new Element("html");
        xmlDocument = new Document();
        
        Format xmlFormat = Format.getPrettyFormat();
//        xmlFormat.setEncoding("ISO-8859-1");
//        xmlFormat.setOmitEncoding(false);
        xmlFormat.setOmitDeclaration(true);
        
        XMLOutputter outputter = new XMLOutputter(xmlFormat);
        
        //structure
        Element headElement = new Element("head");
        Element styleElement = new Element("style");
        styleElement.setText("table {border: none; border-collapse: collapse; cellspacing: 0; cellpadding: 0;} td {width: 1px; height: 1px;}");
        Element bodyElement = new Element("body");
        Element tableElement = new Element("table");
        
        headElement.addContent(styleElement);
        bodyElement.addContent(tableElement);
        rootElement.addContent(headElement).addContent(bodyElement);
        
        try {
        	for (int i=0;i<(pixList.size()/width);i++) {
        		
        		Element tr = new Element("tr");
        		
        		for (int j=0;j<width;j++) {
        			
        			Element td = new Element("td");
        			Attribute bgcolor = new Attribute("style", "background:#" 
        					+ pixList.get(i*width+j).substring(2) + ";");
        			td.setAttribute(bgcolor);
        			tr.addContent(td);
        		}
        		
        		tableElement.addContent(tr);
        		
        	}
     
            xmlDocument.setRootElement(rootElement);
            outputter.output(xmlDocument, new FileOutputStream(xmlFile));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fehler beim Speichern!",
                    "Achtung Fehler!",JOptionPane.ERROR_MESSAGE);
        }
    }

}