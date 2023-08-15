package gensource.view.anotations;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;

public class TelaRelacionamentos extends JFrame{

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelacionamentos window = new TelaRelacionamentos();
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
	public TelaRelacionamentos() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		getContentPane().setBackground(new Color(255, 255, 255));
		setTitle("Relacionamento");
		setBounds(100, 100, 385, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
