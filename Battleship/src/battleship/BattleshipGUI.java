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
	public static final String shootImg = "src/images/shoot.png";
	public static final String shipImg = "src/images/";
	public static final String seaImg = "src/images/sea.png";
	
	private Listener listener;
	
	private BufferedImage shoot = null;
	private BufferedImage sea = null;
	private BufferedImage ship1 = null;
	private BufferedImage ship2 = null;
	private BufferedImage ship2H = null;
	private BufferedImage ship3 = null;
	private BufferedImage ship3H = null;
	private BufferedImage ship4 = null;
	private BufferedImage ship4H = null;
	private BufferedImage subImage = null;
	
	private Board controlBoard1, controlBoard2;
	private Titles title;
	private Titles ships;
	private JLabel tableroPosicion;
	private JLabel tableroPrincipal;
	private BoardGUI board1, board2;
	private JButton exit, showBoard;
	
	private Font font;
	
	public BattleshipGUI() {
		
		try {
			shoot = ImageIO.read(new File(shootImg));
			sea = ImageIO.read(new File(seaImg));
			ship1 = ImageIO.read(new File(shipImg + "1.png"));
			ship2 = ImageIO.read(new File(shipImg + "2.png"));
			ship3 = ImageIO.read(new File(shipImg + "3.png"));
			ship4 = ImageIO.read(new File(shipImg + "4.png"));
			ship2H = ImageIO.read(new File(shipImg + "2H.png"));
			ship3H = ImageIO.read(new File(shipImg + "3H.png"));
			ship4H = ImageIO.read(new File(shipImg + "4H.png"));
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se encontraron los archivos");
		}
		
		controlBoard1 = new Board();
		controlBoard2 = new Board();
		title = new Titles("BATTLESHIP", 32, Color.WHITE);
		ships = new Titles("BARCOS", 32, Color.WHITE);
		board1 = new BoardGUI();
		board2 = new BoardGUI();
		exit = new JButton("Salir");
		showBoard = new JButton("Ver tablero");
		
		initGUI();
		
		//this.setSize(1500, 1500);
		pack();
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
		for(int i = 1; i < 11; i++) {
			for(int j = 1; j < 11; j++) {
				board1.getButtons()[i][j].addMouseListener(listener);
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
		for(int i = 1; i < 11; i++) {
			for(int j = 1; j < 11; j++) {
				board1.getButtons()[i][j].setIcon(new ImageIcon(sea));
				board2.getButtons()[i][j].setIcon(new ImageIcon(sea));
			}
		}
		controlBoard1.randomShipOrganization();
		printMatrix(controlBoard1.board);
		setShipsImage();
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
	
	
	
	private void setShipsImage() {
		Ship[] ships = controlBoard1.getShips();
		BufferedImage image = null;
		
		for(int i = 1; i < 11; i++) {
			for(int j = 1; j < 11; j++) {
				board1.getButtons()[i][j].setIcon(new ImageIcon(sea));
			}
		}
		
		for(int i = 0; i < 10; i++) {
			
			Ship ship = ships[i];
			int[][] shipPosition = ship.getPosition();
			
			switch(ship.getSize()) {
			case 1:
				image = ship1;
				break;
			case 2:
				image = ship.isVertical()? ship2 : ship2H;
				break;
			case 3:
				image = ship.isVertical()? ship3 : ship3H;
				break;
			case 4:
				image = ship.isVertical()? ship4 : ship4H;
				break;
			}
			
			for(int j = 0; j < ship.getSize(); j++) {
				int x = shipPosition[j][0];
				int y = shipPosition[j][1];
				subImage = ship.isVertical()? image.getSubimage(0, 50 * j, 50, 50) : image.getSubimage(50 * j, 0, 50, 50);
				board1.getButtons()[x][y].setIcon(new ImageIcon(subImage));
			}
		}
		
		/*
		for(int i = 0; i < 4; i++) {
			board1.getButtons()[1][i + 1].setIcon(new ImageIcon(ship1));
		}
		for(int i = 0; i < 2; i++) {
			subImage = ship2.getSubimage(0, 50 * i, 50, 50);
			board1.getButtons()[i + 1][5].setIcon(new ImageIcon(subImage));
			board1.getButtons()[i + 1][6].setIcon(new ImageIcon(subImage));
			board1.getButtons()[i + 1][7].setIcon(new ImageIcon(subImage));
		}
		
		for(int i = 0; i < 3; i++) {
			subImage = ship3.getSubimage(0, 50 * i, 50, 50);
			board1.getButtons()[i + 1][8].setIcon(new ImageIcon(subImage));
			board1.getButtons()[i + 1][9].setIcon(new ImageIcon(subImage));
		}
		
		for(int i = 0; i < 4; i++) {
			subImage = ship4.getSubimage(0, 50 * i, 50, 50);
			board1.getButtons()[i + 1][10].setIcon(new ImageIcon(subImage));
		}*/
	}
	
	public static void printMatrix(int[][] matrix) {
	    for (int row = 0; row < matrix.length; row++) {
	        for (int col = 0; col < matrix[row].length; col++) {
	            System.out.printf("%4d", matrix[row][col]);
	        }
	        System.out.println();
	    }
	}
	
	private class Listener implements ActionListener, MouseListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			for(int i = 1; i < 11; i++) {
				for(int j = 1; j < 11; j++) {
					if(board2.getButtons()[i][j] == event.getSource()) {
						System.out.println("El botón " + i + ", " + j + " funciona");
						ImageIcon shootIcon = new ImageIcon(shoot);
						board2.getButtons()[i][j].setIcon(shootIcon);
					}

				}
			}
			
			for(int i = 1; i < 11; i++) {
				for(int j = 1; j < 11; j++) {
					if(board2.getButtons()[i][j] == event.getSource()) {
						System.out.println("El botón " + i + ", " + j + " funciona");
						ImageIcon shootIcon = new ImageIcon(shoot);
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
	
		Ship selectedShip = null;
		int[] mousePosition;
		@Override
		public void mousePressed(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			JButton clickedButton = (JButton) mouseEvent.getSource(); //objeto boton sobre el que se hace click
			
			int[] buttonPosition = board1.getButtonPosition(clickedButton); //position del boton en la matriz
			
			Ship ship = controlBoard1.getShipByPosition(buttonPosition[0], buttonPosition[1]); //barco en esa posicion
			selectedShip = ship;
			System.out.println(ship);
			/*
			if(ship != null) {
				selectedShip = ship;
				System.out.println(selectedShip.getSize());
			}*/
			//System.out.println("Pressed: fila: " + buttonPosition[0] + " columna: " + buttonPosition[1]);
		}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			/*
			JButton releasedButton = (JButton) mouseEvent.getSource();
			int[] relasedButtonPosition = board1.getButtonPosition(releasedButton);
			*/
			//System.out.println("Released: fila: " + mousePosition[0] + " columna: " + mousePosition[1]);
			//System.out.println(selectedShip.getSize());
			
			if(selectedShip != null && !controlBoard1.isThereAShip(selectedShip.getSize(), mousePosition[0], mousePosition[1], selectedShip.isVertical())) {
				controlBoard1.setShip(mousePosition[0], mousePosition[1], selectedShip.getShipNumber(), selectedShip.isVertical());
			}
			setShipsImage();
		}

		@Override
		public void mouseEntered(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			JButton enteredButton = (JButton) mouseEvent.getSource();
			int[] buttonPosition = board1.getButtonPosition(enteredButton);
			
			mousePosition = buttonPosition;
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
}
