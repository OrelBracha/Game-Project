package Game_figures;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.RepaintManager;

import Coords.CoordsConverter;
import Coords.Map;
import GIS.gisElement;
import GUI.MyFrame;

import java.awt.image.BufferedImage;

import Geom.Point3D;

public class Packman 
{

	private final int p_speed = 1 ;                   /************************************************** לשים לב למהירות!!!! *************************************************/
	private final int EATTING_RADIUS = 1 ;
	private int p_id ;
	private int p_Weight ;
	private Point3D pixels_Location ;
	private Point3D c_loction ;
	private Path p_Path ;
	private File p_FileImage  ; /*להמשיך מפה*/
	CoordsConverter converter ;
	private BufferedImage p_Image ;
	Map map = new Map() ;

	/*לא לשכוח להעביר את הערכים של הנקודה של פקמן לערכים של פיקסלים*/
	public Packman(int pacmanId , double latitude , double longtitude ,double altitude , int speed  , Point3D pixels )
	{
		
		p_id = pacmanId ;
		pixels_Location = new Point3D(pixels.ix() , pixels.iy() , altitude ) ;   /*צריך להיות בפיקסלים*/
		c_loction =new Point3D(latitude , longtitude ,altitude ) ;
		converter = new CoordsConverter() ;
		p_Path = new Path() ;
		p_FileImage = new File("C:\\Users\\Avihu\\workspace2\\PACMAN\\Images\\PacmanIcon.png");
		try {
			p_Image = ImageIO.read(p_FileImage);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public double PointAtoPointB( Fruit f_Destination ) /* עובד טוב*/
	{

		double x,y,z;
		Point3D p_newPoint ;
		CoordsConverter converter = new CoordsConverter() ;
		double distanceP2F=converter.distance3d(this.c_loction, f_Destination) ;
//		System.out.println( "distance  " +distanceP2F);
		double time = distanceP2F/p_speed  ;
//		System.out.println("before --->" + this.c_loction);
		Point3D vectorInMeters = converter.vector3D(this.c_loction, f_Destination);
		x = vectorInMeters.x() * p_speed / time  ;                                                       /* הפלוס בשביל המהירות אם צריך אז למחוק*/
		y = vectorInMeters.y() * p_speed /time ;
		z = vectorInMeters.z() * p_speed / time ;
		vectorInMeters = new Point3D(x,y,z ) ;
		System.out.println("c_loction  " + c_loction);
		p_newPoint  = converter.add(this.c_loction, vectorInMeters);
//		System.out.println("after --->"+p_newPoint);
		setPoints(f_Destination);
		return distanceP2F ;
	}
	public void setPoints(Point3D coordinates) {
		Map map = new Map() ;
		pixels_Location = new Point3D(map.coordsToPixels(coordinates));
		System.out.println("coords = "+coordinates);
		System.out.println(pixels_Location);
		this.c_loction = new Point3D(coordinates);
	}
public Point3D getC_loction() {
		return c_loction;
	}

	public void setC_loction(Point3D c_loction) {
		this.c_loction = c_loction;
	}

public Point3D getPixels_Location() {
		return pixels_Location;
	}

	public void setPixels_Location(Point3D pixels_Location) {
		this.pixels_Location = pixels_Location;
	}


	public void move(Point3D point) /* פונקציה שמזיזה את הפקמן מקבלת נקודה בקורדינטות וממירה לפיקסלים*/
	{
		Map map = new Map();	 
		this.setP_Location(map.coordsToPixels(point));
	}

	public double TimeToFruit( Fruit  fruit) /*בדקתי ועובד טוב*/
	{
		double pToF_Distance = distanceToFruit(fruit) ;
		double time = pToF_Distance / this.getP_speed();
		return time ;
	}

	private double distanceToFruit(Fruit fruit)      /*בדקתי ועובד טוב*/
	{	double pythagoreanDistance ;
	int sharedXPoint = Math.abs(this.getP_Location().ix() - fruit.getFruitLocation().ix()) ;
	int sharedYPoint = Math.abs(this.getP_Location().iy() - fruit.getFruitLocation().iy()) ;

	sharedXPoint *= sharedXPoint ;
	sharedYPoint *= sharedYPoint ;
	pythagoreanDistance = Math.sqrt(  sharedXPoint + sharedYPoint ) ;
	return pythagoreanDistance ;
	}
	public void setP_Location(Point3D p_Location) 
	{
		this.pixels_Location = p_Location;
	}

	public int getP_speed() {
		return p_speed;
	}


	public int getEATTING_RADIUS() {
		return EATTING_RADIUS;
	}


	public int getP_id() {
		return p_id;
	}


	public int getP_Weight() {
		return p_Weight;
	}


	public Point3D getP_Location() {
		return pixels_Location;
	}


	public Path getP_Path() {
		return p_Path;
	}


	public File getP_File() {
		return p_FileImage;
	}


	public BufferedImage getP_Image() {
		return p_Image;
	}

	public static void main(String[] args)
	{
		CoordsConverter c = new CoordsConverter();
		Point3D p1 = new Point3D( 32.10571,35.20238);
		Point3D p2 = new Point3D(32.10186,35.21237);
		System.out.println(c.distance3d(p1, p2));//=1148.9915841381974
		Point3D pixels = new Point3D(374 , 173);
		Fruit fruit = new Fruit(1, 32.10478793, 35.20498036, 0, pixels ,"") ;
		pixels = new Point3D(1118 , 217);
		Packman pacman = new Packman(10, 32.10451344, 35.21019738 , 0,1, pixels) ;
//		System.out.println(pacman.PointAtoPointB(fruit));

	}
	
}







