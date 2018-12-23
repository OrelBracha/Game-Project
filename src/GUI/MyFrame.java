package GUI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import Algorithms.SaveGameToCsv;
import Algorithms.ShortestPathAlgo;
import Coords.Map;
import File_format.Path2KML;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Packman;
import Geom.Point3D;
import Threads.ThreadPaint;
/**
 * 
 * @author Avihu Oshri Orel Bracha
 * The main GUI class used to introduce the whole project.
 * We will use here methods related to JFrame which will allow us to demonstrate 
 * all of the features of the class as well as using different files 
 */
public class MyFrame extends JFrame implements ComponentListener{
	public	 int WIDTH = 1300 ;/*1433*/
	public  int HEIGHT = 630 ; /*642*/
	final int DEFULT_SPEED = 1 ;

	File mapFile    = new File("Images/Ariel1.png");
	File fruit1File = new File("Images/orange monster.png");
	String pacmanImage         ="Images/boaz.png" ;
	String amirFigure   ="Images/Amir.png" ;
	String sapirFigure="Images/Sapir.png" ;
	String avihuFigure     ="Images/Avihu.png" ;
	String yellowMonsterImage   ="Images/yellow monster.png";

	BufferedImage arielMap ;
	BufferedImage pacmanOrFruitImage ;
	Dimension dimension ;
	Game drawGame = new Game();
	Path2KML kml_path = new Path2KML() ;
    int GameWidth = this.WIDTH;
    int GameHeight = this.HEIGHT;
	int pacmanIdCounter = 1 ;
	int fruitIdCounter = 1 ;
	int pacmanClicked = 0 ;
	int fruitClicked = 0 ;
	boolean pacmanOn   = false ;
	boolean fruit_1_On = false ;
	boolean fruit_2_On = false ;
	boolean fruit_3_On = false ;
	boolean fruit_4_On = false ;
	boolean drawMenuOn = false ;
	double widthRatio  ;
	double heightRatio ;
	int mapHeight ;
	int mapWidth ;
	 int save_counter = 1 ;
	MenuBar menuBar  ;
	Menu openOrSave  ;		
	
	MenuItem openItem     ;
	MenuItem saveItem     ;

	Menu drawMenu ;
	MenuItem fruitItem_1 ;
	MenuItem fruitItem_2;
	MenuItem fruitItem_3 ;
	MenuItem fruitItem_4 ;
	MenuItem pacmenItem  ;
	
	ImageIcon pacmanIcon ;
	ImageIcon fruitIcon   ;

    public MyFrame() {

        initUI();
    }
    /**
     * Initializing the window of the game, creating the menu by different buttons
     * used to be menu buttons. we have options to open or save game, as well as create game on our own.
     * We will also define what will be going when we press on the mouse on those buttons.
     * 
     */
    private void initUI()  /*פה מאותחלים המשתנים*/
    {
    
    	MenuBar menuBar = new MenuBar() ;
		Menu openOrSave = new Menu("Open / Save") ;		
		ImageIcon pacmanIcon  = new ImageIcon("Pacman-icon .png");
		ImageIcon fruitIcon   = new ImageIcon("orange monster.png");
		MenuItem openItem    = new MenuItem("Open" ) ;
		MenuItem saveItem    = new MenuItem("Save") ;
		
		Menu drawMenu = new Menu("Draw figures");
		MenuItem pacmenItem  = new MenuItem("Pacman");
		MenuItem fruitItem_1 = new MenuItem("Fruit 1");
		MenuItem fruitItem_2 = new MenuItem("Fruit 2");
		MenuItem fruitItem_3 = new MenuItem("Fruit 3");
		MenuItem fruitItem_4 = new MenuItem("Fruit 4");

		Menu play = new Menu("Play menu");
		MenuItem playGame = new MenuItem("Play game");

		 int mapWidth ;
    	 int mapHeight ;
		openOrSave.add(openItem);
		openOrSave.add(saveItem);
		drawMenu.add(pacmenItem);
		drawMenu.add(fruitItem_1);
		drawMenu.add(fruitItem_2);
		drawMenu.add(fruitItem_3);
		drawMenu.add(fruitItem_4);
		play.add(playGame) ;
		menuBar.add(openOrSave);
		menuBar.add(drawMenu);
		menuBar.add(play);
		try {
			arielMap = ImageIO.read(mapFile);
			mapWidth = arielMap.getWidth() ;
			mapHeight = arielMap.getHeight() ;
			 setPreferredSize(new Dimension(WIDTH, HEIGHT));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
		
		openItem.addActionListener(e ->
		{
			JFileChooser fc = new JFileChooser() ; 
			JButton open = new JButton() ;
			fc.setCurrentDirectory(new java.io.File("C:\\Users\\Avihu\\Desktop\\מטלה 3 מונחה עצמים\\data"));
			if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION )
			{				
			}
			String gamePath = fc.getSelectedFile().getAbsolutePath();
			drawGame = new Game(gamePath);
			repaint();
		});
		playGame.addActionListener(e ->
		{
			play();
		});
		saveItem.addActionListener(e ->
		{
			SaveGameToCsv saveGame =new SaveGameToCsv(drawGame , save_counter);
			kml_path.readPath(drawGame, this);
			save_counter++;
		});
		 pacmenItem.addActionListener(e ->{
			 fruit_1_On = false ;
			 fruit_2_On = false ;
			 fruit_3_On = false ;
			 fruit_4_On = false ;
			drawMenuOn = false ;
			if(pacmanOn == true)
			{
				pacmanOn = false ;
			}
			else if(pacmanOn == false)
			{
				pacmanOn = true ;
			}
			repaint();
		
    	 });
		 fruitItem_1.addActionListener(e ->{
			 pacmanOn = false ;
			 fruit_2_On = false ;
			 fruit_3_On = false ;
			 fruit_4_On = false ;
			drawMenuOn = false ;
			if(fruit_1_On == true)
			{
				fruit_1_On = false ;
			}
			else if(fruit_1_On == false)
			{
				fruit_1_On = true ;
			}
    	 });
		 fruitItem_2.addActionListener(e ->{

				pacmanOn = false ;
				fruit_1_On = false ;
				 fruit_3_On = false ;
				 fruit_4_On = false ;
				drawMenuOn = false ;
				if(fruit_2_On == true)
				{
					fruit_2_On = false ;
				}
				else if(fruit_2_On == false)
				{
					fruit_2_On = true ;
				}
    	 });
		 fruitItem_3.addActionListener(e ->{

				pacmanOn = false ;
				fruit_1_On = false ;
				 fruit_2_On = false ;
				 fruit_4_On = false ;
				drawMenuOn = false ;
				if(fruit_3_On == true)
				{
					fruit_3_On = false ;
				}
				else if(fruit_3_On == false)
				{
					fruit_3_On = true ;
				}
    	 });
		 fruitItem_4.addActionListener(e ->{
			 pacmanOn = false ;
				fruit_1_On = false ;
				 fruit_2_On = false ;
				 fruit_3_On = false ;
				drawMenuOn = false ;
				if(fruit_4_On == true)
				{
					fruit_4_On = false ;
				}
				else if(fruit_4_On == false)
				{
					fruit_4_On = true ;
				}
    	 });
		 
		 getContentPane().addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					Map map = new Map() ;
					Point3D pixelPoint = new Point3D(e.getX()+5, e.getY()+40) ;
					                                                
					Point3D coordPoint = new Point3D(map.pixelToCoords(e.getX(), e.getY(),GameWidth,GameHeight)) ;
					
					System.out.println("x="+e.getX() + ",   y=" +e.getY());
					if(pacmanOn == true)
					{
						
						Packman pacman = new Packman(pacmanIdCounter,coordPoint.x() , coordPoint.y() ,coordPoint.z() ,DEFULT_SPEED,pixelPoint) ;
						System.out.println("new pacman added with ID :  "+pacmanIdCounter);
						pacmanIdCounter++;
						drawGame.pacmanSet.add(pacman);			
						System.out.println("pacman number "+ pacmanIdCounter+ " was added" );
						repaint();
					}
					
					if(fruit_1_On == true)
					{
						
						Fruit fruit = new Fruit(fruitIdCounter , coordPoint.x() , coordPoint.y() ,coordPoint.z() ,pixelPoint , amirFigure) ;
						System.out.println("new fruit added with ID :  "+fruitIdCounter +"  added in point  " + pixelPoint);
						
						fruitIdCounter++;
						drawGame.fruitSet.add(fruit);					
						repaint();
					}
					
					if(fruit_2_On == true)
					{
						Fruit fruit = new Fruit(fruitIdCounter , coordPoint.x() , coordPoint.y() ,coordPoint.z() ,pixelPoint , sapirFigure) ;
						System.out.println("new fruit added with ID :  "+fruitIdCounter);
						fruitIdCounter++;
						drawGame.fruitSet.add(fruit);					
						repaint();
					}
					
					if(fruit_3_On == true)
					{
						Fruit fruit = new Fruit(fruitIdCounter , coordPoint.x() , coordPoint.y() ,coordPoint.z() ,pixelPoint , avihuFigure ) ;
						System.out.println("new fruit added with ID :  "+fruitIdCounter);
						fruitIdCounter++;
						drawGame.fruitSet.add(fruit);					
						repaint();
					}
					
					if(fruit_4_On == true)
					{
						Fruit fruit = new Fruit(fruitIdCounter , coordPoint.x() , coordPoint.y() ,coordPoint.z() ,pixelPoint , yellowMonsterImage) ;
						System.out.println("new fruit added with ID :  "+fruitIdCounter + "on x = " + e.getX() + ", y = " + e.getY());
						fruitIdCounter++;
						drawGame.fruitSet.add(fruit);					
						repaint();
					}
					
					if(drawMenuOn = true )
					{
						repaint();
					}
				}
			});
			
		this.setMenuBar(menuBar);	
       

        setResizable(true);
        pack();
        
        setTitle("Pacman");    
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        
    }
    /**
     * paint has the responsibility to make the different changes when it comes to check
     * what has been done in the game window so far. It is used as repaint any time we need to
     * paint new window.
     */
   
    public void paint(Graphics g) {
    	int x ;
    	int y ;
    	double xRatio;
    	double yRatio;           /*10,30*/
		g.drawImage(arielMap,10 ,30, this.getWidth()-17, this.getHeight()-38, this) ;
	
		try {
			pacmanOrFruitImage = ImageIO.read(fruit1File) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(Fruit fruit : drawGame.fruitSet)
		{								
//			x = fruit.getgetP_Location().ix() ;
//			y =pIndex.getP_Location().iy() ;
			g.drawImage(fruit.getFruitImage(),(int) (fruit.getFruitLocation().ix()*this.getWidth()/WIDTH) ,  (int) (fruit.getFruitLocation().iy()*this.getHeight()/HEIGHT), (int)(40*this.getWidth()/WIDTH) ,  (int)(40*this.getHeight()/HEIGHT), this) ;
		}
		for(Packman pIndex : drawGame.pacmanSet)
		{
			x = pIndex.getPixels_Location().ix() ;
			y =pIndex.getPixels_Location().iy() ;
//			xRatio = x/mapWidth ;
//			yRatio = y/mapHeight ;
			
			g.drawImage(pIndex.getP_Image(),(int) (x*this.getWidth()/WIDTH), (int) (y*this.getHeight()/HEIGHT),(int)(40*this.getWidth()/WIDTH) ,  (int)(60*this.getHeight()/HEIGHT), this) ;
		}
		widthRatio = this.getWidth()/WIDTH ;
		heightRatio = this.getHeight()/HEIGHT ;
	}



public static void main(String[] args) 
{
//	MyFrame window = new MyFrame();	
//	window.setVisible(true);
	  EventQueue.invokeLater(() -> {
          JFrame ex = new MyFrame();
          ex.setVisible(true);
      });
	
}
/**
 * here we will begin to play our game. We will initialize the Thread that will enable us to see 
 * how the pacmans can run freely towards the fruits.	
 */
public void play()
{
		ThreadPaint tp = new ThreadPaint(this) ;
		tp.start();
}
		
		@Override
		public void componentResized(ComponentEvent e)
		{
		 widthRatio   = e.getComponent().getWidth() /WIDTH  ;
		 heightRatio  = e.getComponent().getHeight() /HEIGHT ;
		 repaint();
		 WIDTH =  e.getComponent().getWidth() ;
		 HEIGHT = e.getComponent().getHeight() ;
		 System.out.println("width-->" + dimension.getWidth() + "height-->" + dimension.getHeight());
		}


public Game getDrawGame() {
		return drawGame;
	}

@Override
public void componentMoved(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void componentShown(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}
@Override
public void componentHidden(ComponentEvent e) {
	// TODO Auto-generated method stub
	
}

}
