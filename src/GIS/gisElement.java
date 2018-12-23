package GIS;

import Geom.Geom_element;

import Geom.Point3D;
public class gisElement implements GIS_element 
{

	public Geom_element geom;
	public MetaData data;
	
	String mac = "" ;
	String ssid = "";
	String authMode = "";
	String firstSeen = "";
	String type = "";
	String layerTime = "" ;
	int chanel ;
	int rssi   ;
	int accuracyMeters	    ;
	int id ;
	double currentLatitude ;
	double CurrentLongitude	;
	double altitudeMeters	;
	
	public gisElement(Geom_element g , MetaData d)
	{
		geom = g ;
		data =new MetaData(d) ;
		
	}
	
	public gisElement(Geom_element g , int id)
	{
		geom = g ;
		this.id = id ;
		
	}
	
	public Geom_element getGeom() 
	{
		return geom ;
		
	}
	
	public Meta_data getData()
	{
		return data;
	}
	
	public MetaData getMetaData()
	{
		return data;
	}
	
	@Override
	public void translate(Point3D vec) 
	{
		
		
	}
	
	public String getMAC()
	{
		return data.getMac();
	}
	
	public String getSsid()
	{
		return data.getSsid();
	}
	
	public String getAuthMode()
	{
		return data.getAuthMode();
	}
	
	public String getFirsSeen()
	{
		return data.getFirstSeen();
	}
	
	public String getType()
	{
		return data.getType();
	}
	
	public int getChanel()
	{
		return data.getChanel();
	}
	
	public int getAccuracyMeters()
	{
		return data.getAccuracyMeters();
	}
	
	public int getRssi()
	{
		return data.getRssi();
	}
	
	public double getCurrentLatitude()
	{
		return data.getCurrentLatitude();
	}
	
	public double getAltitudeMeters()
	{
		return data.getAltitudeMeters();
	}
	
}
