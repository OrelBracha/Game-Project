package GIS;

import java.nio.channels.Channel;
import java.util.ArrayList;

import Geom.Point3D;

public class MetaData implements Meta_data {

	String mac = "" ;
	String ssid = "";
	String authMode = "";
	String firstSeen = "";
	String type = "";
	String layerTime = "" ;
	int chanel ;
	int rssi   ;
	int accuracyMeters	    ;
	int id;
	double currentLatitude ;
	double CurrentLongitude	;
	double altitudeMeters	;
	
	public MetaData()
	{
		
	}
	
	public MetaData(String[] line)
	{
		mac  = line[0] ;
		ssid = line[1] ;
		authMode = line[2] ;
		firstSeen = line[3];
		chanel = Integer.parseInt(line[4]);
		rssi= Integer.parseInt(line[5]);
		currentLatitude= Double.parseDouble(line[6]);
		CurrentLongitude= Double.parseDouble(line[7]);
		altitudeMeters= Double.parseDouble(line[8]);
		accuracyMeters= Integer.parseInt(line[9]);
		type = line[10];	
	}
	public MetaData(MetaData c)
	{
		 mac = "" ;
		 ssid = "";
		 authMode = "";
		 firstSeen = "";
		 type = "";
		 layerTime = "" ;
		 chanel = c.chanel;
		 rssi  = c.rssi  ;
		 accuracyMeters = c.accuracyMeters	    ;
		 id = c.id;
		 currentLatitude = c.currentLatitude ;
		 CurrentLongitude = c.CurrentLongitude	;
		 altitudeMeters	=  c.altitudeMeters;
	}
	
	public MetaData(int id)
	{
		this.id = id ;
	}
	public MetaData(String time)
	{
		layerTime = time ;
	}
	
	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getAuthMode() {
		return authMode;
	}

	public void setAuthMode(String authMode) {
		this.authMode = authMode;
	}

	public String getFirstSeen() {
		return firstSeen;
	}

	public void setFirstSeen(String firstSeen) {
		this.firstSeen = firstSeen;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLayerTime() {
		return layerTime;
	}

	public void setLayerTime(String layerTime) {
		this.layerTime = layerTime;
	}

	public int getRssi() {
		return rssi;
	}

	public void setRssi(int rssi) {
		this.rssi = rssi;
	}

	public int getAccuracyMeters() {
		return accuracyMeters;
	}
	
	
	public void setAccuracyMeters(int accuracyMeters) {
		this.accuracyMeters = accuracyMeters;
	}
	public int getId() {
		return id;
	}
	
	public double getCurrentLatitude() {
		return currentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) {
		this.currentLatitude = currentLatitude;
	}

	public double getCurrentLongitude() {
		return CurrentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) {
		CurrentLongitude = currentLongitude;
	}

	public double getAltitudeMeters() {
		return altitudeMeters;
	}

	public void setAltitudeMeters(double altitudeMeters) {
		this.altitudeMeters = altitudeMeters;
	}

	public void setChanel(int chanel) {
		this.chanel = chanel;
	}

	public int getChanel()
	{
		return chanel ;
	}
	
	@Override
	public long getUTC() 
	{
		
		Long utc =Long.parseLong(firstSeen.substring(11, 19)) ;
		return utc ;	
	}
	public String toString()
	{
		String mdString = "<b>MAC = " + mac + "</br>" +  "<b> SSID = " + ssid + "</br>"+" AuthMode = " + authMode +  "\n"+" FirstSeen = " + firstSeen 
				          + "\n"+ " Channel = " + chanel +  "\n"+" RSSI = " + rssi +  "\n"+" CurrentLatitude = " +  currentLatitude +   
				          "\n"+ "CurrentLongitude = " + CurrentLongitude + "\n"+" AltitudeMeters = " + altitudeMeters +  "\n"+" AccuracyMeters" 
				          + accuracyMeters + " Type = " + type ;
		
		return mdString ;			
	}
	
	public String toString(long time)
	{
		String mdstr = "Layer set was created on " + layerTime ;
		return mdstr ;
	}
	@Override
	public Point3D get_Orientation() 
	{
		
		return null;
	}

}
