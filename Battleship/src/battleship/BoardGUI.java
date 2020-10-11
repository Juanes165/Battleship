package battleship;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardGUI extends JPanel {

	//attributes
	private int row, column;
	private JButton[][] buttons;
	//private JPanel board;
	private JLabel title;
	//private MatrixButtons[][] MatrixButtons = new JButton [DimensionX][DimensionY];
	
	
	//Methods
	
	public BoardGUI() {
		
	buttons = new JButton[11][11];
	initGUI();
	
	
	//Set default panel config
/*	this.setTitle("Board");
	this.setSize(300,200);
	this.setVisible(true);
	this.setLocationRelativeTo(null);
	this.setResizable(false);
	
	*/
	}
	private void initGUI() {
		//Layout
		this.setLayout(new GridLayout(11,11));
		for(int i = 0; i < 11;i++) {
			for(int j = 0; j < 11;j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(30,30));
				add(buttons[i][j]);
			}
		}
		//add(buttons, BorderLayout.CENTER);
		
	}
}
