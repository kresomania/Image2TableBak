import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.w3c.dom.css.RGBColor;


public class TableMachine {

	// variables declaration
	private BufferedImage myImage;
	private int height;
	private int width;
	private ArrayList<String> pixList;
	
	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new TableMachine();
	}
	
	/**
	 * constructor
	 */
	public TableMachine() {
		pixList = new ArrayList<String>();
		readImage();
		
//		System.out.println(height + "*" + width);
		
		
		convIt();
		saveToXml();
		
		
		
//		int rgb = myImage.getRGB(23 , 0);
//		System.out.println(Integer.toHexString(rgb));
//		System.out.println(height + " * " + width);

	}
	
	
	public void convIt() {
		for (int i=0;i<height;i++) {			
			for (int j=0;j<width;j++) {
				int rgbColor = myImage.getRGB(j,i);
				String hexColor = Integer.toHexString(rgbColor);
				pixList.add(hexColor);
				int ga = i + 1;
				int ag = j + 1;
				System.out.println("Pixel " + ga + "*" + ag + " " + hexColor);
			}
		}
	}
	
	
//	public void convIt() {
//		for (int i=0;i<height;i++) {			
//			for (int j=0;j<width;j++) {
//				int rgbColor = myImage.getRGB(i,j);
//				String hexColor = Integer.toHexString(rgbColor);
//				pixList.add(hexColor);
//				int ga = i + 1;
//				int ag = j + 1;
//				System.out.println("Pixel " + ga + "*" + ag + " " + Integer.toHexString(myImage.getRGB(i,j)));
//			}
//		}
//	}
	
	
	public void saveToXml() {
		new DataBuffer(pixList, width);
	}
	
	/**
	 * reads the image
	 */
	public void readImage() {
		try {
			myImage = ImageIO.read(new File("pic.png"));
		} catch (IOException e) {
			System.out.println("IOExeption!!!");
		}
		
		width = myImage.getWidth();
		height = myImage.getHeight();
	}

}
