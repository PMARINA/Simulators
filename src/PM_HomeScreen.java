import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Choice;
import java.awt.GridLayout;
import javax.swing.JDesktopPane;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * [PROMPT]
 * @author PMARINA
 * @version Feb 2, 2016
 */
public class PM_HomeScreen {
	@SuppressWarnings("unused")
	private static JFrame frame = new JFrame("Test");

	/**
	 * Blah Blah Blah
	 * @params param1 does this
	 * @returns returns stuff
	 * @throws noInputError no input given by the user
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
		Choice difficulty = new Choice();
		difficulty.add("Easy");
		difficulty.add("Medium");
		difficulty.add("Hard");
		difficulty.add("Random");
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JToolBar toolBar = new JToolBar();
		frame.getContentPane().add(toolBar);
		
		frame.getContentPane().add(difficulty);

		
		JButton btnStart = new JButton("START");
		frame.getContentPane().add(btnStart);
	}
}
