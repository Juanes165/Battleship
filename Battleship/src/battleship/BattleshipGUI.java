package battleship;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class BattleshipGUI extends JFrame {
	//un game control
	//jugadores
	//clase de tablero GUI
	private Listener listener;
	
	private JLabel title;
	private JLabel ships;
	private JLabel tableroPosicion;
	private JLabel tableroPrincipal;
	
	private Font font;
	
	public BattleshipGUI() {
		
		initGUI();
		
		this.setSize(500, 500);
		this.setTitle("Battleship");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initGUI() {
		//container y layout
		
		//elementos de control y escucha
		listener = new Listener();
		
		//botones y labels
		font = new Font("Helvetica", Font.PLAIN, 20);
		title = new JLabel("BATTLESHIP");
		title.setFont(font);
		add(title, BorderLayout.NORTH);
		
		ships = new JLabel("Aqui van los barcos");
		ships.setFont(font);
		add(ships, BorderLayout.WEST);
		
		tableroPosicion = new JLabel(".          Aqui van los barcos aliados");
		tableroPosicion.setFont(font);
		add(tableroPosicion, BorderLayout.CENTER);
		
		tableroPrincipal = new JLabel("Aqui van los disparos");
		tableroPrincipal.setFont(font);
		add(tableroPrincipal, BorderLayout.EAST);
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
