package gensource.view.anotations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

public class TelaColumn extends JFrame{

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaColumn window = new TelaColumn();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaColumn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 360, 324);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
	}

}
