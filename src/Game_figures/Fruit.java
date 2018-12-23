package Game_figures;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Geom.Point3D;

public class Fruit extends Point3D
{
	final int WEIGHT = 1 ;                   /************************************************** לשים לב למשקל!!!! *************************************************/
	final int NUMBER_OF_MONSTERS = 4;
	private int id ;
	private int randomIndex ;
	private Point3D fruitLocation ;
	private Point3D p_Location ;
	private File fruitFile ;
	private BufferedImage fruitImage ;
	private String[] monstersImages ;
	private Random random ;
	boolean wasEaten  ;
	

	public Fruit(int fruitId , double latitude , double longtitude ,double altitude  , Point3D pixels ,String imagePath)
	{
		super(latitude ,longtitude  ) ;
		this.id = fruitId ;	
		wasEaten = false ;
		p_Location = new Point3D(pixels) ;
		fruitLocation = new Point3D(pixels);                                      /*אמור לקבל בפיקסלים */
		if(imagePath.length() > 0)
		{
			fruitFile = new File(imagePath);
			try {
				fruitImage = ImageIO.read(fruitFile) ;
				System.out.println(" image chosen " + imagePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			{
		}
		    monstersImages = new String[NUMBER_OF_MONSTERS] ;
			monstersImages[0] = "Images/Avihu.png" ;
			monstersImages[1] = "Images/Sapir.png" ;
			monstersImages[2] = "Images/Amir.png" ;
			monstersImages[3] = "Images/yellow monster.png" ;
			Random rand = new Random() ;
			int index = rand.nextInt(4);
			fruitFile = new File(monstersImages[index]);
			try {
				fruitImage = ImageIO.read(fruitFile) ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}

	
	public Point3D getP_Location() {
		return p_Location;
	}


	public boolean getWasEaten() {
		return wasEaten;
	}

	public void setWasEaten(boolean wasEaten) {
		this.wasEaten = wasEaten;
	}
	
	public int getWEIGHT() {
		return WEIGHT;
	}

	
	public BufferedImage getFruitImage() {
		return fruitImage;
	}


	public int getId() {
		return id;
	}


	public Point3D getFruitLocation() {
		return fruitLocation;
	}
	
	
	
	
	
	
}
