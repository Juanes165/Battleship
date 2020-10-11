package battleship;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class BoardGUI extends JPanel {

	//attributes
	private int row, column;
	private JButton[][] buttons;
	private JButton button;
	//private JPanel board;
	private JLabel title;
	//private MatrixButtons[][] MatrixButtons = new JButton [DimensionX][DimensionY];
	
	
	//Methods
	
	public BoardGUI() {
		
	buttons = new JButton[11][11];
	initGUI();

	
	}
	private void initGUI() {
		//Layout
		this.setLayout(new GridLayout(11,11));
		for(int i = 0; i < 11;i++) {
			for(int j = 0; j < 11;j++) {
				//button = new JButton();
				//button.setPreferredSize(new Dimension(10, 10));
				buttons[i][j] = new JButton();
				buttons[i][j].setBackground(new Color(100, 255, 255));
				buttons[i][j].setBorder(new LineBorder(Color.BLACK));
				add(buttons[i][j]);
				//add(button);
			}
		}
		for(int i = 1; i < 11;i++) {
			buttons[0][i].setText(Integer.toString(i));
			buttons[0][i].setBackground(new Color(255, 255, 255));
			buttons[i][0].setText(Integer.toString(i));
			buttons[i][0].setBackground(new Color(255, 255, 255));
			buttons[0][0].setBackground(new Color(255, 255, 255));
		}
	}
}
