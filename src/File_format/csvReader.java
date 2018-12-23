package File_format;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import GIS.MetaData;
import GIS.gisElement;
import Geom.Geom_element;
import Geom.Point3D;

public  class csvReader 
{

	/**
	 * 
	 * @author Orel Bracha Avihu Oshri 
	 * This class takes the line in the file of CSV that comes as a String, separated by commas, and gather all the information in 
	 * an array of strings in order to have the relevant information in the same place in the array
	 *
	 */
	
	double latitude ;
	double longtitude ;
	double altiude ;
	
	public gisElement CSVReader(String line)
	{

			String[] columns = line.split(",") ;
			
			Geom_element point = new Point3D(0, 0, 0) ;
			MetaData md = new MetaData(columns) ;
			gisElement ge = new gisElement(point, md);
			
			
				latitude = Double.parseDouble(columns[6]) ;
				longtitude = Double.parseDouble(columns[7]) ;
				altiude = Double.parseDouble(columns[8]) ;
				 point = new Point3D(latitude, longtitude, altiude) ;
				 md = new MetaData(columns) ;
				 ge = new gisElement(point, md);	
			
			return ge ;
			
		
		
	}
//	
//    public static void main(String[] args) 
//    {
//        String csvFile = "users2.csv";
//        String line = "";
//        String cvsSplitBy = ",";
//        PrintWriter pw = null;
//
//        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
//        {
//        	 
//        	StringBuilder sb = new StringBuilder();
//            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
//            sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>");
//            
//            while ((line = br.readLine()) != null) 
//            {
//                String[] userInfo = line.split(cvsSplitBy);
//                sb.append("<Placemark>");
//                sb.append("<name><![CDATA["+userInfo[1]+"]></name>");
//                sb.append("<description><![CDATA[BSSID: <b>"+userInfo[0]+"</b><br/>Capabilities: <b>"+userInfo[2]+"</b><br/>Frequency: <b>2412</b><br/>Timestamp: <b>1512117845000</b><br/>Date: <b>"+userInfo[3]+"</b>]]></description><styleUrl>#red</styleUrl>");
//                sb.append("<Point>");
//                sb.append("<coordinates>"+userInfo[7]+","+userInfo[6]+"</coordinates></Point>");
//            } 
//         pw.write(sb.toString());
//         pw.close();
//        }
//            catch (IOException e) 
//        {
//            e.printStackTrace();
//        }
//    }
}