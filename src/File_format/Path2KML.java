package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import GUI.MyFrame;
import Game_figures.Fruit;
import Game_figures.Game;
import Coords.Map;
import Game_figures.Packman;
import Game_figures.Path;
import Geom.Point3D;
/**
 * 
 * @author Avihu Oshri Orel Bracha
 * This class responsible for tranforming the game we have to kml file and to represent the input in 
 * Google Earth.
 *
 */
public class Path2KML 
{
	String date;
	String[]colorSelected= {"3f0407","3f043c","24043f","040f3f",
			"04323f","043f21","043f08","3f3904","3f2504",
			"3f0f04","ea1b16","2fea16","16e8ea","eae716"};

	//ArrayList<ArrayList<Point3D>>Pathset = new ArrayList<ArrayList<Point3D>>();
	ArrayList<Point3D>OnePath = new ArrayList<Point3D>();
	 ArrayList<ArrayList<Point3D>>fullPath = new ArrayList<ArrayList<Point3D>>();
	Path path = new Path(OnePath);
	Game game = new Game();
	
	int indexOfPacman = 0;
	public Path2KML()
	{
		
	}
	
	
	
	
	public String getTime() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss ").format(Calendar.getInstance().getTime());
		timeStamp=timeStamp.replace("_", "T").replace(" ", "Z");
		date=timeStamp;
		return timeStamp;
	                         }
	/**
	 * 
	 * @param game - game that was played in the app		
	 * 
	 * @param mf - the Frame which was introduced
	 */
	public  void readPath(Game game,MyFrame mf)
	{
		
	try 
	{
		
		FileWriter fw = new FileWriter("KML/PackmanPath.kml") ;
		
		BufferedWriter bw = new BufferedWriter(fw) ;
		
		StringBuilder sb = new StringBuilder();
		collectPoint(game,mf);
		//String line = "" ;//
		Date dateCurrent = new Date() ;
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n" + "");
	
		for(Packman pacman : game.pacmanSet)
		{
			
			Iterator<Point3D> pacmanIt = OnePath.iterator();
            while(pacmanIt.hasNext())
            {
            	Point3D point = pacmanIt.next();
            	
            	String time = getTime();
			sb.append("<Placemark>\n") ;
			sb.append("<name>"+"Pacman"+"</name>\n");
			sb.append("<TimeStamp>\n");
			sb.append("<when>"+time+"</when>\n");
			sb.append("</TimeStamp>");
			sb.append("<description>\n");
			sb.append("</description>\n");
			sb.append("<Point>\n") ;
			sb.append("<coordinates>"+" "+point.y()+","+point.x()+","+point.z()+"</coordinates>\n");
			sb.append("</Point>\n");
			sb.append("</Placemark>\n") ;
//		System.out.println("the point is "+point.x()+" "+point.y());
            }
            
            Iterator <Packman>secondIterator = game.pacmanSet.iterator();
			int colorNumber =0;
			while(secondIterator.hasNext()) {
				Packman Pacman = secondIterator.next();
				String setPoints="";
				for(int i=0;i<OnePath.size();i++) {
					setPoints+=""+OnePath.get(i)+"\n";
				}
				sb.append(""+"<Placemark>\n");
			    sb.append("<name>\n"+"Selected Path\n"+"</name>\n");
				sb.append("<description>\n"+"Path\n"+"</description>\n");
			    sb.append("<LineString>\n");
				sb.append("<coordinates>\n"+setPoints+"</coordinates>\n");
				sb.append("</LineString>\n");
				sb.append("</Placemark>\n");
				
				if(colorNumber!=colorSelected.length-1)
					colorNumber++;
				else
					colorNumber=0;
			}

		}
		sb.append("\n</Folder>\n");
		sb.append("</Document></kml>") ;
		bw.write(sb.toString());
		bw.close();
	
	} 
	catch (IOException e) {
		e.printStackTrace();
	}

}
	private void collectPoint(Game game, MyFrame mf)
	{
		Map map= new Map();
		
		Iterator<Packman> pacmans = game.pacmanSet.iterator();
		Iterator<Fruit>fruits = game.fruitSet.iterator();
		while(pacmans.hasNext())
		{
			Packman p1 = pacmans.next();
			Point3D convert =map.pixelToCoords((int)p1.getP_Location().y(),(int) p1.getP_Location().x(),mf.getWidth(),mf.getHeight());
			System.out.println(convert.y());
			OnePath.add(convert);
		}
		while(fruits.hasNext())
		{
			Fruit p1 = fruits.next();
			Point3D convert =map.pixelToCoords((int)p1.getFruitLocation().y(),(int) p1.getFruitLocation().x(),mf.getWidth(),mf.getHeight());
			System.out.println(convert.y());
			OnePath.add(convert);	
		}
	}
	}