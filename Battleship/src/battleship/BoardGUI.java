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

	//Atributos
	private JButton[][] buttons;
	private final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};

	
	public BoardGUI() {
		buttons = new JButton[11][11];
		initGUI();
	}
	
	
	
	private void initGUI() {
		//Layout
		this.setLayout(new GridLayout(11,11));
		//botones
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {			
				buttons[i][j] = new JButton();
				buttons[i][j].setPreferredSize(new Dimension(50, 50));
				buttons[i][j].setBackground(new Color(100, 255, 255));
				buttons[i][j].setBorder(new LineBorder(Color.BLACK));
				add(buttons[i][j]);
			}
		}
		
		for(int i = 1; i < 11; i++) {
			buttons[0][i].setText(letters[i - 1]);
			buttons[0][i].setBackground(new Color(255, 255, 255));
			buttons[i][0].setText(Integer.toString(i));
			buttons[i][0].setBackground(new Color(255, 255, 255));
			buttons[0][0].setBackground(new Color(255, 255, 255));
		}
	}
	
	
	
	public int[] getButtonPosition(JButton button) {
		int[] position = {0, 0};
		for(int i = 0; i < 11; i ++) {
			for(int j = 0; j < 11; j ++) {
				if(buttons[i][j] == button) {
					position[0] = i;
					position[1] = j;
					return position;
				}
			}
		}
		return position;
	}
	
	
	
	public JButton[][] getButtons() {
		return buttons;
	}
}
