package battleship;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class Titles extends JLabel {
	//Atributtes.
	//Metodos
	public Titles(String text, int size, Color colorFonto) {
			this.setText(text);
			Font font = new Font(Font.SERIF,Font.BOLD+Font.ITALIC,size);
			this.setFont(font);
			this.setBackground(colorFonto);
			this.setForeground(Color.black);
			this.setHorizontalAlignment(JLabel.CENTER);
	}
}
