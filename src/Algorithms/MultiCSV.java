package Algorithms;



import java.io.File;
import java.util.ArrayList;

import File_format.KmlFile;
import GIS.gisProject;
import GIS.GIS_layer;
import GIS.GIS_project;
import GIS.gisLayer;
import java.io.File;
import GIS.GIS_project;
import GIS.gisLayer;
/**
 *  
 * @author Avihu Oshri and Orel bracha
 *This class responsible for taking a path of folder and find the CSV Files in it.
 *As we find the CSV Files we just add them to a GIS_Project which includes set of 
 *CSV Files to get data from
 */
public class MultiCSV 
{

	  gisProject project ; 
	  KmlFile kFile = new KmlFile() ;
	  ArrayList<String> csvPathes = new ArrayList<>() ;

	  /**
		 * 
		 * @param FolderPath - the path in the computer to the exact folder we need to examine
		 * This function activates The function of searchCSV
		 */
	public void projectConverter(String FolderPath)
	{
		  project = searchCsv(FolderPath) ;
	}
	
	/**
	 * 
	 * @param FolderPath  - the path in the computer to the exact folder we need to examine
	 * @return A set of GIS Layers that includes all if the CSV Files we have just found
	 */
	public gisProject searchCsv(String FolderPath)
	{
		  System.out.println("inside " + FolderPath);
		  gisProject layerSet = new gisProject();
		  File[] fileArray = new File(FolderPath).listFiles();
		
		for(File fIndex : fileArray)
		{
			if(fIndex.isDirectory())
			{
				String newPath = FolderPath + "\\" + fIndex.getName() ;
				searchCsv(newPath);
			}
			if(fIndex.getName().contains(".csv"))
			{	
				 gisLayer layer = new gisLayer(fIndex) ;
				 layerSet.add(layer) ;
				 csvPathes.add(fIndex.getAbsolutePath());			//Add the file path to the array list for later
			}			
		}

		 kFile.kmlFileCreator(csvPathes , FolderPath);

		return layerSet ;
		
	}
	
	/**
	 * 
	 * @param args - Our main class with the exact path to be checked
	 */
	public static void main(String[] args) {
		MultiCSV mul = new MultiCSV() ;
		String filePath = "C:\\Users\\Avihu\\Desktop\\csv2kml" ;
		mul.projectConverter(filePath);
	}
}
/*"C:\\Users\\Avihu\\Desktop\\csv2kml"*/