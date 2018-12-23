package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Date;

public class csv2kml 
{
	final static int NUM_OF_COLUMNS = 11 ; 
	public static void readCsv(String filepath)
	{
		try 
		{
			FileReader fl = new FileReader("C:\\Users\\Avihu\\Desktop\\csv2kml\\WigleWifi_20171203085618.csv");
			FileWriter fw = new FileWriter("C:\\kml\\gps.kml") ;
			BufferedReader br = new BufferedReader(fl) ;
			BufferedWriter bw = new BufferedWriter(fw) ;
			StringBuilder sb = new StringBuilder();
			
			
			String line = "" ;
			String splitstr[] = new String[NUM_OF_COLUMNS] ;
			Date date = new Date() ;
			sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\r\n" + "");
			line = br.readLine() ;
			splitstr = line.split(",") ;
			while((line = br.readLine()) != null)
			{
			
				splitstr = line.split(",") ;
				sb.append("\n<Placemark>\n") ;
				sb.append("<name>" ); 
				sb.append("<![CDATA[" + splitstr[1] + "]]>" );
				sb.append("</name>\n");
				sb.append("<description>" + "<![CDATA[BSSID: <b>" + splitstr[0] +"</b><br/>Capabilities: <b>" + splitstr[2] + "</b><br/>Frequency: <b> 2437" + "</b><br/>Timestamp: <b>" + date.getTime() +"</b><br/>Date: <b>" + date.toString() + "</b>]]>" + "</description><styleUrl>#red</styleUrl>"  );
				sb.append("\n<Point>\n") ;
				sb.append("<coordinates>" + splitstr[7] + "," + splitstr[6] + "," + splitstr[8]);
				sb.append("</coordinates>");
				sb.append("</Point>\n");
				sb.append("</Placemark>") ;
				
			}
			sb.append("\n</Folder>\n");
			sb.append("</Document></kml>") ;
			
			bw.write(sb.toString());
			bw.close();
			br.close();
			
			
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) 
	{
		csv2kml h = new csv2kml();
		h.readCsv("C:\\Users\\Avihu\\Desktop\\csv2kml\\WigleWifi_20171203085618.csv");
		
	}
}