package battleship;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class BattleshipGUI extends JFrame {
	//un game control
	//jugadores
	//clase de tablero GUI
	private Listener listener;
	
	private JLabel title;
	private JLabel ships;
	private JLabel tableroPosicion;
	private JLabel tableroPrincipal;
	private BoardGUI board1, board2;
	private JButton exit, showBoard;
	
	private Font font;
	
	public BattleshipGUI() {
		
		initGUI();
		
		this.setSize(1500, 1500);
		this.setTitle("Battleship");
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	private void initGUI() {
		//container y layout
		this.getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		//elementos de control y escucha
		listener = new Listener();
		
		//botones y labels
		font = new Font("Helvetica", Font.PLAIN, 20);
		title = new JLabel("BATTLESHIP");
		title.setFont(font);
		title.setPreferredSize(new Dimension(500, 100));
		title.setBackground(new Color(25,25,25));
		title.setOpaque(true);
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=3;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH;
		add(title, constraints);
		
		ships = new JLabel("Aqui van los barcos");
		ships.setFont(font);
		ships.setBackground(new Color(100, 100, 100));
		ships.setForeground(new Color(255, 255, 0));
		ships.setHorizontalAlignment(JLabel.CENTER);
		ships.setOpaque(true);
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=3;
		constraints.fill=GridBagConstraints.BOTH;
		add(ships, constraints);
		
		board1 = new BoardGUI();
		board1.setPreferredSize(new Dimension(500, 500));
		board1.setBorder(new TitledBorder("Tus barcos"));
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		add(board1, constraints);
		
		board2 = new BoardGUI();
		board2.setPreferredSize(new Dimension(500, 500));
		board2.setBorder(new TitledBorder("Barcos enemigos"));
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		add(board2, constraints);
		
		exit = new JButton("Salir");
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.PAGE_END;
		add(exit, constraints);
		
		showBoard = new JButton("Ver tablero");
		constraints.anchor=GridBagConstraints.LAST_LINE_END;
		add(showBoard, constraints);
	}
	
	private class Listener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
