package GUI;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainWindow extends JFrame 
{
	File ImageFile = new File("C:\\Users\\Avihu\\workspace2\\Geographic App\\Images\\Ariel1.png");
	BufferedImage image ;

	
	public MainWindow() 
	{
		MenuBar menuBar = new MenuBar() ;
		Menu menu = new Menu("Games") ;
		MenuItem Item1 = new MenuItem("game A") ;
		MenuItem Item2 = new MenuItem("game B") ;

		menuBar.add(menu);
		menu.add(Item1) ;
		menu.add(Item2) ;
		this.setMenuBar(menuBar);
		
		try 
		{
			image = ImageIO.read(ImageFile) ;
		} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		

			this.setSize(image.getWidth()  , image.getHeight());
		}
	
	

	
	}
	
	
	
	
	
	

