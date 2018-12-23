package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import File_format.csvReader;
import Geom.Geom_element;
import Geom.Point3D;


/**
 * 
 * @author Avihu Oshri and Orel Bracha 
 * This class represent gis layer - which the main constructor of this method takes a file 
 * that we get , read that file and adds every line with it's contents to A set of the object GIS_element
 *
 */
public  class gisLayer implements GIS_layer
{
	MetaData metadata ;
	Set<GIS_element> gisSet ;
	
	/**
	 * As mentioned, the main constructor, receives a file, use different methods in order to read it, 
	 * and with a while loop reads and takes every line with the information included and adds it as an object of the kind 
	 * GIS_element to the set that we have created. 
	 */
	public gisLayer(File csvFile)
	{	
	try 
	{
		FileReader fr     ;
		BufferedReader br ;
		String line = ""  ; 
		csvReader cr = new csvReader();
	    gisSet = new HashSet<>() ;
		Date date =new Date()     ;
		String time ;
		
		fr = new FileReader(csvFile) ;
		br = new BufferedReader(fr)  ;
		line = br.readLine() ;
		line = br.readLine() ;
		
		
		while((line = br.readLine()) != null)
		{
			gisSet.add(cr.CSVReader(line)); // Add GIS elements to the layer set
		}
		
		br.close();
		time = date.toString() ;
		
		metadata = new MetaData(time)  ;
	} 
	catch (FileNotFoundException e) 
	{
		e.printStackTrace();
	}
	catch (IOException e) 
	{
		e.printStackTrace();
	}

	}
	
	public Set<GIS_element> getLayer()
	{
		return gisSet ;
	}
	
	
	public Meta_data get_Meta_data()
	{
		return metadata ;
	}
	
	
	public MetaData getMetadata()
	{
		return metadata ;
	}
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return gisSet.size();
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return gisSet.isEmpty();
	}
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return gisSet.contains(o);
	}
	@Override
	public Iterator<GIS_element> iterator() {
		// TODO Auto-generated method stub
		return gisSet.iterator();
	}
	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return gisSet.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return gisSet.toArray(a);
	}
	@Override
	public boolean add(GIS_element e) {
		// TODO Auto-generated method stub
		return gisSet.add(e);
	}
	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return gisSet.remove(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gisSet.containsAll(c);
	}
	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		// TODO Auto-generated method stub
		return gisSet.addAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gisSet.retainAll(c);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return gisSet.removeAll(c);
	}
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
}
