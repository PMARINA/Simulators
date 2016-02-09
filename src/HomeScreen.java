//***************************************************************//
// HomeScreen.java            Author: Marie Petitjean            //
//JFrame where you are able to select the level you want to play //
//***************************************************************//
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class HomeScreen extends JFrame{
	Container contentPane;
	
	public JButton play,easy,medium,hard,random;
	public JButton[] buttons = {play,medium,hard,random};
	public HomeScreen(){
		contentPane = getContentPane();
		
		play = new JButton("Easy");
		play.setBounds(0,100,100,100);
		contentPane.add(play);
		play.addActionListener(
		        new ActionListener() 
		        {
		           public void actionPerformed( ActionEvent event )
		           
		              {
		           			try {
							new Screen(0);
							//new Code();
								dispose();
							} catch (IOException e) { 
								System.out.println("nerp");
							}
		           			setVisible(false);
		           }
		           
		        } 

		     ); 
		medium = new JButton("Medium");
		medium.setBounds(200,100,100,100);
		contentPane.add(medium);
		medium.addActionListener(
		        new ActionListener() 
		        {
		           public void actionPerformed( ActionEvent event )
		           
		              {
		           			try {
							new Screen(1);
						//	new Code();
								dispose();
							} catch (IOException e) {
								System.out.println("nerp");
							}
		           			setVisible(false);
		           }
		           
		        } 

		     ); 
		
		hard = new JButton("Hard");
		hard.setBounds(400,100,100,100);
		contentPane.add(hard);
		hard.addActionListener(
		        new ActionListener() 
		        {
		           public void actionPerformed( ActionEvent event )
		           
		              {
		           			try {
							new Screen(2);
							//new Code();
								dispose();
							} catch (IOException e) {
								System.out.println("nerp");
							}
		           			setVisible(false);
		           }
		           
		        } 

		     ); 

		random = new JButton("Random");
		random.setBounds(300,300,100,100);
		contentPane.add(random);
		random.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent even){
						
						try{
							new Screen(3);
							dispose();
						}
						catch(IOException e){
							System.out.println("nope");
						}
						
					}
					
			}
				);
		
		setLayout(null);
		setBackground(Color.GREEN);
		setVisible(true);
		setSize(500,500);
	}

}
