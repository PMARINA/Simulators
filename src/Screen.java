//************************************************************//
// Screen.java            Author: Marie Petitjean             //
// Puts graphics JPanel onto a JFrame, where the main game is //
//************************************************************//
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Screen extends JFrame {

	public DetectObjects boo;
	public JButton refresh;
	public Container contentPane;
	public Screen() throws IOException {
		createUI();
		boo = new DetectObjects();
		contentPane.add(boo);
	}
	public Screen(final int a) throws IOException{
		createUI();
		boo = new DetectObjects(a);
		contentPane.add(boo);

		refresh = new JButton("Refresh");
		refresh.setBounds(400,400,100,50);
		refresh.setVisible(true);
		this.add(refresh);
		refresh.addActionListener(
		        new ActionListener() 
		        {
		           public void actionPerformed( ActionEvent event )
		           
		              {			
		        	   dispose();
		        	   try {
						new Screen(a);
					} catch (IOException e) {
						e.printStackTrace();
					}
								
							
		           }
		           
		        } 

		     ); 
	
	}
	
	public void createUI() throws IOException{
		contentPane = getContentPane();
		setLayout(null);
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setSize(500,500);
		setVisible(true);
		setResizable(false);
		
	}
	
}
