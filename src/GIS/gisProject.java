package GIS;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 
 * @author Orel Bracha Avihu Oshri
 * The class uses a main constructor responsible for taking GIS layers and add them to 
 * a set that is configured in the beginning of the class.
 * It has also a field of the requested folderpath we need to use.
 * The class converts every file to a  kml file which is applicable for representing information in GoogleEarth
 */


public class gisProject implements GIS_project
{
	Set<GIS_layer>	layerSet = new HashSet<>() ;
	
	
	String folderPath = "C:\\Users\\Avihu\\Desktop\\csv2kml" ;
	
	
	
	
	
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return layerSet.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return layerSet.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return layerSet.contains(o);
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		// TODO Auto-generated method stub
		return layerSet.iterator();
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return layerSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return layerSet.toArray(a) ;
	}

	@Override
	public boolean add(GIS_layer e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return layerSet.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return layerSet.containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		// TODO Auto-generated method stub
		return layerSet.addAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return layerSet.retainAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return layerSet.removeAll(c);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Meta_data get_Meta_data() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
