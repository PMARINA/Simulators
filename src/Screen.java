//************************************************************//
// Screen.java            Author: Marie Petitjean             //
// Puts graphics JPanel onto a JFrame, where the main game is //
//************************************************************//
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFrame;


public class Screen extends JFrame {

	public DetectObjects boo;
	public Container contentPane;
	public Screen() throws IOException {
		createUI();
		boo = new DetectObjects();
		contentPane.add(boo);
	}
	public Screen(int a) throws IOException{
		createUI();
		boo = new DetectObjects(a);
		contentPane.add(boo);
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
