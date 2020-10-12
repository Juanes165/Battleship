package battleship;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

public class BattleshipGUI extends JFrame {
	//un game control
	//jugadores
	//clase de tablero GUI
	public static final String shoot = "src/images/shoot.png";
	public static final String ship = "src/images/";
	
	private Listener listener;
	
	private BufferedImage bufferedImage = null;
	private BufferedImage ship1 = null;
	private BufferedImage ship2 = null;
	private BufferedImage ship3 = null;
	private BufferedImage ship4 = null;
	
	private Titles title;
	private Titles ships;
	private JLabel tableroPosicion;
	private JLabel tableroPrincipal;
	private BoardGUI board1, board2;
	private JButton exit, showBoard;
	
	private Font font;
	
	public BattleshipGUI() {
		
		try {
			bufferedImage = ImageIO.read(new File(shoot));
			ship1 = ImageIO.read(new File(ship + "1.png"));
			ship2 = ImageIO.read(new File(ship + "2.png"));
			ship3 = ImageIO.read(new File(ship + "3.png"));
			ship4 = ImageIO.read(new File(ship + "4.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se encontraron los archivos");
		}
		
		title = new Titles("BATTLESHIP", 32, Color.WHITE);
		ships = new Titles("BARCOS", 32, Color.WHITE);
		board1 = new BoardGUI();
		board2 = new BoardGUI();
		exit = new JButton("Salir");
		showBoard = new JButton("Ver tablero");
		
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
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				board1.getButtons()[i][j].addActionListener(listener);
				board2.getButtons()[i][j].addActionListener(listener);
			}
		}
		
		//botones y labels
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridwidth=3;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.BOTH;
		add(title, constraints);
		
		ships.setPreferredSize(200, 50);
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=3;
		constraints.fill=GridBagConstraints.BOTH;
		add(ships, constraints);
		
		board1.setBorder(new TitledBorder("Tus barcos"));
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		add(board1, constraints);

		board2.setBorder(new TitledBorder("Barcos enemigos"));
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		add(board2, constraints);
		
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.PAGE_END;
		add(exit, constraints);
		
		constraints.anchor=GridBagConstraints.LAST_LINE_END;
		add(showBoard, constraints);
	}
	
	public class Listener extends MouseAdapter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			for(int i = 1; i < 11; i++) {
				for(int j = 1; j < 11; j++) {
					if(board2.getButtons()[i][j] == event.getSource()) {
						System.out.println("El botón " + i + ", " + j + " funciona");
						ImageIcon shootIcon = new ImageIcon(bufferedImage);
						board2.getButtons()[i][j].setIcon(shootIcon);
					}

				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			JButton actionButton = (JButton) mouseEvent.getSource();
			
		}
	
	}
}
