package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class Application extends JFrame
{
	public Application()
	{
		initUI() ;
	}
	
	private void initUI()
	{
		add(new MyFrame());
		setSize(250 , 200);
		setTitle("Pacman game") ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		pack() ;
	}
	
	public static void main(String[] args) 
	{
		 EventQueue.invokeLater(() -> {
	            Application ex = new Application();
	            ex.setVisible(true);
	        });
	}
}
