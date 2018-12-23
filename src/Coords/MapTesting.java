package Coords;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

class MapTesting {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

    @Test
    public void CoordsToPixelsCheck()
    {
    	Map map = new Map();
    	Point3D p1 = new Point3D(32.102482,35.207475,0.0);
    	Point3D actual = map.coordsToPixels(p1);
    	Point3D expected  = new Point3D(731,521,0.0);
    	assertTrue(actual.x()==expected.x()&&actual.y()==expected.y()&&actual.z()==expected.z());
    }
    @Test
    public void PixelsToCoordsCheck()
    {
    	Map map = new Map();
    	Point3D p1 = new Point3D(716,389,0.0);
    	Point3D actual = map.pixelToCoords(p1.ix(),p1.iy(),map.mapWidth,map.mapHeight);
    	Point3D expected  = new Point3D(32.103634211444515,35.20573314330218,0.0);
    	assertTrue(actual.x()==expected.x()&&actual.y()==expected.y()&&actual.z()==expected.z());
    }
    @Test
    public void distPixcheck()
    {
    	Map map = new Map();
		Point3D check = new Point3D(32.103634211444515,35.20573314330218);
		Point3D p1 = new Point3D(32.102482,35.207475,0.0);
		int ExpectedDist = 301;
		double actualDist =(int) map.PixDist(check, p1);
		assertEquals(ExpectedDist, actualDist);
    }
    @Test
    public void angleBetweenCheck()
    {
    	Map map = new Map();
		Point3D check = new Point3D(32.103634211444515,35.20573314330218);
		Point3D p1 = new Point3D(32.102482,35.207475,0.0);
		int ExpectedDist = 33;
		double actualDist =(int) map.angleBetween(check, p1);
		assertEquals(ExpectedDist, actualDist);
    }
    
    
    
}

