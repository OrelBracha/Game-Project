package Threads;

import java.util.ArrayList;

import Algorithms.ShortestPathAlgo;
import GUI.MyFrame;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Packman;
/**
 * 
 * @author Avihu Oshri Orel Bracha
 * The thread class - the using of thread enable us to 
 * run the different pacmans that we have in parallel.
 * the thread initialize every time the class myframe amd the game is started
 * as the thread runs.
 */
public class ThreadPaint extends Thread {
	MyFrame myFrame ;
	public ThreadPaint(MyFrame myFrame)
	{
		this.myFrame = myFrame ;
	}
	
	public void run()
	{
		Game game = myFrame.getDrawGame() ;
		Play(game) ;
	}
	
	/**
	 * 
	 * @param game - receive a game to play, when we can initialize it 
	 * as the thread runs. by the game rules, it enables the pacman to remove 
	 * the fruit after it eats him.
	 * the game finished when there are no fruits on the map
	 */
	public void  Play(Game game)
	{

		ShortestPathAlgo spa = new ShortestPathAlgo(game);
		double distanceToFruit ;
		int fruitId ;
		Thread thread = new Thread() ;
		int counter = 0 ; /*לא לשכוח למחוק*/
		for (Packman pacman : myFrame.getDrawGame().pacmanSet) /*הדפסת מסלול של כל פקמן*/
		{
//			System.out.println(	 "PACMAN "+myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_Path().toString());
//			System.out.println("PACMAN "+myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getPixels_Location());
		
		} /*************************************לא למחוק!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!***************************************/
		for (Packman pacman : myFrame.getDrawGame().pacmanSet) /*הדפסת מסלול של כל פקמן*/
		{
//			System.out.println(	 "PACMAN "+myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_Path().toString());
			while(pacman.getP_Path().getFruitsPath().size()>0)
			{
				Fruit fruit =pacman.getP_Path().getFruitsPath().get(0) ;
				 distanceToFruit = pacman.PointAtoPointB(fruit );
						System.out.println("distance = " +distanceToFruit );
				 if( distanceToFruit < 1 )
				 {
					
//						System.out.println(	 "PACMAN "+myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_Path().toString());
						counter++;
					    System.out.println("fruit "  +pacman.getP_Path().getFruitsPath().get(0).getId() + "was eaten!!!" );
					
						if(fruit.getId() == pacman.getP_Path().getFruitsPath().get(0).getId())
						{
							game.fruitSet.remove(fruit);
							
						}
						
					
					    pacman.getP_Path().getFruitsPath().remove(0);					
						System.out.println(	 "PACMAN "+myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_id() +"PATH : "+ myFrame.getDrawGame().pacmanSet.get(myFrame.getDrawGame().pacmanSet.indexOf(pacman)).getP_Path().toString());
						 try {
								sleep(500);;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						myFrame.repaint();

					}
				
				 }
			myFrame.repaint();

			}
	}
}
