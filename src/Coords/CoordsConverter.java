package Coords;

import java.awt.Point;

import Geom.Point3D;
/**
 * 
 * @author Orel Bracha Avihu Oshri
 * This is the class which responsible for converting different values and 3D Points
 * as well as making the whole calculations that we need to have
 * 
 */
public class CoordsConverter implements coords_converter
{
	
	final double RADIAN_ROUND = 2 * Math.PI ;
	final double LON_NORM = 0.847091174 ;
	final int EARTH_RADIUS = 6371000  ;
	final int DEGREES_ROUND = 360     ;
	final int METER_CONVERTION = 1000 ;
	

	/** computes a new point which is the gps point transformed by a 3D vector (in meters)*/

	public CoordsConverter()
	{
		
	}
	/**
	 * This class computes a new point which is the gps point transformed by a 3D vector (in meters)
	 * @param addAltitude
	 * @param xTodegrees
	 * @param yTodegrees
	 * @param
	 * @param
	 *  
	 */
	public  Point3D add(Point3D gps, Point3D local_vector_in_meter)
	{
		
		double addAltitude = local_vector_in_meter.z() + gps.z();
		
		Point3D addVector ;
		double xTodegrees = Math.asin(local_vector_in_meter.x() / EARTH_RADIUS);
	    xTodegrees= Math.toDegrees(xTodegrees);
		double yTodegrees =  Math.asin((local_vector_in_meter.y() / (EARTH_RADIUS * LON_NORM))); ;
		
		xTodegrees += gps.x() ;
		yTodegrees += gps.y() ;
		
		
		addVector = new Point3D(xTodegrees , yTodegrees , addAltitude ) ;
		return addVector ;
		
	}
	/** computes the 3D distance (in meters) between the two gps points that we have received */

	public double distance3d(Point3D gps0, Point3D gps1)
	{
		
		double toMetterLat = diff(gps0.x() , gps1.x());		
		double toMetterLon = diff(gps0.y() , gps1.y());
		
		 	   toMetterLat = diff_radian(toMetterLat);
		 	   toMetterLon = diff_radian(toMetterLon);
		 	   
		 	   toMetterLat = toMeter(toMetterLat);
		 	   toMetterLon = toMeter(toMetterLon) * LON_NORM ;
		 	   
		 	   toMetterLat = Math.pow(toMetterLat, 2) ;
		 	   toMetterLon = Math.pow(toMetterLon, 2) ;
		 	   
		 	   return Math.sqrt(toMetterLat + toMetterLon );
		
	}

	/**
	 *  computes the polar representation of the 3D vector which will be received after calculationof the two points: gps0-->gps1 
	 * Note: this method should return an azimuth (aka yaw), elevation (pitch), and distance*/
    /*Formulas from this site : https://www.omnicalculator.com/other/azimuth*/
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) 
	{
		double[] aziElevDist = new double[3];
		double azimuth   ;
		double elevation ; 
		double distance  ;
		double diffLon =  Math.sin(Math.toDegrees(gps1.y()-gps0.y()));
		double latInitialPoint = Math.toDegrees(gps0.x());
		double latFinalPoint   = Math.toDegrees(gps1.x());
		
		double a = diffLon* Math.cos(latFinalPoint);
		double b =  Math.sin(latFinalPoint) * Math.cos(latInitialPoint)- Math.cos(latFinalPoint) * Math.sin(latInitialPoint)* Math.cos(diffLon);
		
				
	    distance = distance3d(gps0,gps1);
	    
	    aziElevDist[2] = distance ;
	    
	    azimuth = Math.atan2(a,b);
	    
	    aziElevDist[0] = azimuth < 0 ? (Math.toDegrees(azimuth)+360) : Math.toDegrees(azimuth);
	    
	    elevation =Math.toDegrees( Math.asin((gps1.z() - gps0.z())/distance))  ;//lambda = asin{ (elev2 - elev1) / d }
	    aziElevDist[1] = elevation ;
	    
	    return aziElevDist ;

	}
	//Formula:	azimuth = atan2( sin Î”Î» â‹… cos Ï†2 , cos Ï†1 â‹… sin Ï†2 âˆ’ sin Ï†1 â‹… cos Ï†2 â‹… cos Î”Î» )
	//          ATAN2(COS(lat1)*SIN(lat2)-SIN(lat1)*COS(lat2)*COS(lon2-lon1) ,  SIN(lon2-lon1)*COS(lat2)) 
	
	/** computes the 3D vector (in meters) between two gps points gps0 and gps1 */

	public Point3D vector3D(Point3D gps0, Point3D gps1)
	{
		Point3D metterVectore ;
		double toMetterLat = diff(  gps1.x() ,gps0.x());		
		double toMetterLon = diff(gps1.y() , gps0.y());
		double toMetterAlt = diff(gps1.z() , gps0.z());
		 	   toMetterLat = diff_radian(toMetterLat);
		 	   toMetterLon = diff_radian(toMetterLon);
		 	   
		 	   toMetterLat = toMeter(toMetterLat);
		 	   toMetterLon = toMeter(toMetterLon) * LON_NORM ;
		 	   
		 	   metterVectore = new Point3D(toMetterLat , toMetterLon , toMetterAlt);
		 	   return metterVectore ;
	}
	/**
	 * 
	 * this function returns 3D point that represent a 2d vector in meter
	 */
	public Point3D twoPointsToMetter(Point3D point1 , Point3D point2) /*òåáã èåá áååãàåú*/
	{
		Point3D p ;
		double toMetterLat = diff(point1.x() , point2.x());		
		double toMetterLon = diff(point1.y() , point2.y());
		      
		       toMetterLat = diff_radian(toMetterLat);
	 	       toMetterLon = diff_radian(toMetterLon);
	 	       
	 	       toMetterLat = toMeter(toMetterLat);
		 	   toMetterLon = toMeter(toMetterLon) * LON_NORM ;
		 	   
		 	   p = new Point3D(toMetterLat , toMetterLon ) ;
		 	   return p ;
		 	   
	}
	/********************************PRIVATE METHODES***********************************/
	private double diff(double endpoint , double statpoint)
	{
		return endpoint - statpoint ;
	}
	
	private double diff_radian(double diff)
	{
		return (Math.PI * diff) / 180 ;
	}
	
	private double toMeter(double diffR)
	{
		
		return Math.sin(diffR) * EARTH_RADIUS;
	}
	
	
	/**

	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf]

	 * @param p - The point that we will receive

	 * @return - an answer true if this is a valid point else if not

	 */

	public boolean isValid_GPS_Point(Point3D p)
	{
		boolean isValid = true ;
		
		if(p.x() < -90 || p.x() > 90)
		{
			isValid = false ;
		}
		else if(p.y() < -180 || p.y() > 180)
		{
			isValid = false ;
		}
		else if (p.z() < -450 )
		{
			isValid = false ;
		}
		return isValid ;
	}

	
	
	
	public static void main(String[] args) {
CoordsConverter coords = new CoordsConverter();	
Point3D gps0 = new Point3D(32.103315 ,35.209039 , 60 );
Point3D gps1 = new Point3D(32.106352,35.205225,40);
System.out.println("s"+Math.toDegrees(337));

//System.out.println("AZIMUTH IS   ---->  "+coords.azimuth_elevation_dist(gps0, gps1)[0] + "Â°" );
//System.out.println("ELEVATION IS ---->  "+coords.azimuth_elevation_dist(gps1, gps0)[1] + "Â°");
//System.out.println("DISTANCE IS  ---->  "+coords.azimuth_elevation_dist(gps0, gps1)[2]);
	System.out.println(coords.vector3D(gps0, gps1));	


		
		

	}

	
}
