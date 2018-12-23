package Game_figures;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Algorithms.ShortestPathAlgo;
import Algorithms.createGameFromCsv;
import Coords.Map;
import Geom.Point3D;

public class Game 
{
	final int COLUMNS_NUM = 7 ;
	public ArrayList<Packman> pacmanSet = new ArrayList();
	public ArrayList<Fruit> fruitSet =new  ArrayList();
	createGameFromCsv cgf ;
	
	public Game()
	{
		
	}
	
	public Game(String filePath)   /*בדקתי ועובד טוב*/
	{
		Map map = new Map() ;
		File game = new File(filePath) ;
		Point3D pixels ;
		Point3D coordinates ;
		FileReader fr;
		String[] elements = new String[COLUMNS_NUM] ;
		int id ;
		int altitude ;
		double latitude ;
		double longtitude ;
		int speed ;
		if(filePath.length() > 0)
		{
			try 
			{
				fr = new FileReader(game);
				BufferedReader br = new BufferedReader(fr);
				
					String line = br.readLine() ;
					while((line = br.readLine()) != null)
					{
						elements = line.split(",");
						id = Integer.parseInt(elements[1]) ;
						latitude = Double.parseDouble(elements[2]);
						longtitude = Double.parseDouble(elements[3]);		
						altitude = Integer.parseInt(elements[4]) ;
						speed = Integer.parseInt(elements[5]) ;
						coordinates = new Point3D(latitude , longtitude);
						pixels = new Point3D(map.coordsToPixels(coordinates)) ; /*המרת הנקודה מקורדינטות לפיקסלים*/
						
						if(elements[0].equals("P"))
						{
							System.out.println("pacman\t" +id +"\tX-acsis -->\t " +  pixels.ix()  + "\t   Y-acsis -->\t " +pixels.iy() + "\taltitude\t" + altitude+ "\tlongtitude\t"+longtitude );
							System.out.println();
							pacmanSet.add(new Packman(id, latitude, longtitude, altitude,speed,pixels )) ;
						}
						
						if(elements[0].equals("F"))
						{
//					System.out.println("fruit\t" +id +" \tX-acsis -->\t" + pixels.ix() + "\t   Y-acsis -->\t" +pixels.iy() + "\taltitude\t" + altitude +  "\tlongtitude\t"+longtitude);
							System.out.println();
							fruitSet.add(new Fruit(id, latitude, longtitude, altitude,pixels , ""));
						}
						
					}	 
					
			} 	
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void playGame()
	{
		ShortestPathAlgo spa = new ShortestPathAlgo(this);
		double distanceToFruit ;
		int fruitId ;
		Thread thread = new Thread() ;
		for (Packman pacman : pacmanSet) /*הדפסת מסלול של כל פקמן*/
		{
			System.out.println(	 "PACMAN "+pacmanSet.get(pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ pacmanSet.get(pacmanSet.indexOf(pacman)).getP_Path().toString());
			while(pacman.getP_Path().fruitsPath.size()>0)
			{
//				System.out.println( "fruit  " +pacman.getP_Path().fruitsPath.get(0) );
				 distanceToFruit =
						 pacman.PointAtoPointB(pacman.getP_Path().fruitsPath.get(0));
//						 try {
//							thread.sleep(1000);
//						} catch (InterruptedException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
				 if( distanceToFruit < 1 )
				 {
						System.out.println(	 "PACMAN "+pacmanSet.get(pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ pacmanSet.get(pacmanSet.indexOf(pacman)).getP_Path().toString());

					 System.out.println("fruit "  +pacman.getP_Path().fruitsPath.get(0).getId() + "was eaten!!!" );
//					 try {
//						thread.sleep(4000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					 pacman.getP_Path().getFruitsPath().remove(0);
					 
				 }
			}
		}
	}
	
	public static void main(String[] args) 
	{
		System.out.println("started");
		Game game = new Game("C:\\Users\\Avihu\\Desktop\\מטלה 3 מונחה עצמים\\data\\game_1543684662657.csv") ;
		game.playGame();
	}
}
