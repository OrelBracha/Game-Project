package File_format;
import java.util.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import java.io.File;
import java.io.IOException;

import GIS.GIS_layer;
import GIS.MetaData;
import GIS.gisElement;
import GIS.gisLayer;
import GIS.gisProject;

public class KmlFile  
{
	final  int NUM_OF_COLUMNS = 11;

	public void kmlFileCreator(ArrayList<String> projectLayers , String projectPath)
	{

			try 
			{
				String colors[] = {"red" , "green" , "blue" , "yellow" , "orange" , "purple"};
				int i = 0 ;
				
				String layer= "";
				
				layer = projectLayers.get(0);
				
				FileReader fl = new FileReader(layer);
				FileWriter fw = new FileWriter(projectPath +"\\gps.kml") ;
				BufferedReader  br = new BufferedReader(fl) ;
				BufferedWriter bw = new BufferedWriter(fw) ;  
				StringBuilder sb = new StringBuilder();
				sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
				sb.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\">"
						+ ""
						+ "<Document>"
						+ "		<Style id=\"red\">"
						+ "								<IconStyle>"
						+ "														<Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>"
						+ "														</Icon>"
						+ "							   </IconStyle>"
						+ "</Style>"
						+ "<Style id=\"yellow\">"
						+ "										<IconStyle>"
						+ "														<Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href>"
						+ "														</Icon>"
						+ "										</IconStyle>"
						+ "</Style>"
						+ "<Style id=\"green\">"
						+ "									<IconStyle><Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href>"
						+ "														   </Icon>"
						+ "									</IconStyle>"
						+ "</Style>"

						+ "<Style id=\"blue\">"
						+ "									<IconStyle><Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/blue-dot.png</href>"
						+ "														   </Icon>"
						+ "									</IconStyle>"
						+ "</Style>"

						+ "<Style id=\"yellow\">"
						+ "									<IconStyle><Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href>"
						+ "														   </Icon>"
						+ "									</IconStyle>"
						+ "</Style>"
						+ "<Style id=\"pink\">"
						+ "									<IconStyle><Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/pink-dot.png</href>"
						+ "														   </Icon>"
						+ "									</IconStyle>"
						+ "</Style>"
						+ "<Style id=\"orange\">"
						+ "									<IconStyle><Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/orange-dot.png</href>"
						+ "														   </Icon>"
						+ "									</IconStyle>"
						+ "</Style>"
						+ "<Style id=\"purple\">"
						+ "									<IconStyle><Icon>"
						+ "																	<href>http://maps.google.com/mapfiles/ms/icons/purple-dot.png</href>"
						+ "														   </Icon>"
						+ "									</IconStyle>"
						+ "</Style>"
						+ "<Folder>"
						+ "<name>Wifi Networks</name>\r\n" + "");
				for(String layers : projectLayers)
				{
					
			         fl = new FileReader(layers);
					 br = new BufferedReader(fl) ;
					 bw = new BufferedWriter(fw) ;
					 String line = "" ;
					 String splitstr[] = new String[NUM_OF_COLUMNS] ;
				     Date date = new Date() ;
					 csvReader cr = new csvReader();

					line = br.readLine() ;
					line = br.readLine() ;
					splitstr = line.split(",") ;
							while((line = br.readLine()) != null)
							{	
								if(i >(colors.length -1 ) )
								{
									i=0 ;
								}
								String chooseColor = colors[i];
								
								gisElement ge =  cr.CSVReader(line) ;
								
								splitstr = line.split(",") ;
								sb.append("\n<Placemark>\n") ;
								sb.append("<name>" ); 
								sb.append("<![CDATA[" + splitstr[1] + "]]>" );
								sb.append("</name>\n");
								sb.append("<description>" + "<![CDATA[BSSID: <b>" + splitstr[0] +"</b><br/>Capabilities: <b>" + splitstr[2] + "</b><br/>Frequency: <b> 2437" + "</b><br/>Timestamp: <b>" + date.getTime() +"</b><br/>Date: <b>" + date.toString() + "</b><br/>Meta Data : <b>"
										+ "		 </br>MAC : " +ge.getMAC()  + "</b>"
										+ "		 </br>SSID : " +ge.getSsid()  +"</b>"
										+ "		 </br>AuthMode : " + ge.getAuthMode()  + "</b>"
										+ "		 </br>FirsSeen : " + ge.getFirsSeen()  + "</b>"
										+ "		 </br>Type : " + ge.getType()  + "</b>"
										+ "		 </br>Chanel : " + ge.getChanel()  + "</b>"
										+ " 		 </br>AccuracyMeters : " + ge.getAccuracyMeters()  + "</b>"
										+ "	     </br>Rssi : " + ge.getRssi()  + "</b>"
										+ "	     </br>CurrentLatitude : " + ge.getCurrentLatitude()  + "</b>"
										+ "	     </br>AltitudeMeters : " + ge.getAltitudeMeters()  + "</b>"
										+ "	   ]]>" + "</description><styleUrl>#"+ chooseColor +"</styleUrl>"  );
								sb.append("\n<Point>\n") ;
								sb.append("<coordinates>" + splitstr[7] + "," + splitstr[6] + "," + splitstr[8]);
								sb.append("</coordinates>");
								sb.append("</Point>\n");
								sb.append("</Placemark>") ;	
								i++ ;
							}
				} 
				sb.append("\n</Folder>\n");
				sb.append("</Document></kml>\n") ;
				bw.write(sb.toString());			

				bw.close();
				br.close();	
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}
		
	}
	
	
	


