package battleship;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;

public class BattleshipGUI extends JFrame {
	public static final String shootImg = "src/images/shoot.png";
	public static final String shipShootedImg = "src/images/shipShooted.png";
	public static final String shipImg = "src/images/";
	public static final String seaImg = "src/images/sea.png";
	public static final String sunkShipImg = "src/images/sunkShip.png";
	
	private Listener listener;
	
	private BufferedImage shoot = null;
	private BufferedImage shipShooted = null;
	private BufferedImage sunkShip = null;
	private BufferedImage sea = null;
	private BufferedImage ship1 = null;
	private BufferedImage ship1H = null;
	private BufferedImage ship2 = null;
	private BufferedImage ship2H = null;
	private BufferedImage ship3 = null;
	private BufferedImage ship3H = null;
	private BufferedImage ship4 = null;
	private BufferedImage ship4H = null;
	private BufferedImage subImage = null;
	
	private GameControl gameControl;
	private Board controlBoard1, controlBoard2;
	private Titles title;
	private Titles ships;
	private BoardGUI board1, board2;
	private JButton exit, showBoard, randomOrganization, play;
	private JFrame Myself = this;
	private WindowHelp help = new WindowHelp(Myself);
	
	private Font font;
	
	public BattleshipGUI() {
		
		try {
			
			shoot = ImageIO.read(new File(shootImg));
			shipShooted = ImageIO.read(new File(shipShootedImg));
			sunkShip = ImageIO.read(new File(sunkShipImg));
			sea = ImageIO.read(new File(seaImg));
			ship1 = ImageIO.read(new File(shipImg + "1.png"));
			ship2 = ImageIO.read(new File(shipImg + "2.png"));
			ship3 = ImageIO.read(new File(shipImg + "3.png"));
			ship4 = ImageIO.read(new File(shipImg + "4.png"));
			ship1H = ImageIO.read(new File(shipImg + "1H.png"));
			ship2H = ImageIO.read(new File(shipImg + "2H.png"));
			ship3H = ImageIO.read(new File(shipImg + "3H.png"));
			ship4H = ImageIO.read(new File(shipImg + "4H.png"));
		
			gameControl = new GameControl();
			controlBoard1 = gameControl.getBoard1();
			controlBoard2 = gameControl.getBoard2();
			title = new Titles("BATTLESHIP", 32, Color.WHITE);
			ships = new Titles("BARCOS", 32, Color.WHITE);
			board1 = new BoardGUI();
			board2 = new BoardGUI();
			exit = new JButton("Salir");
			showBoard = new JButton("Ver tablero");
			randomOrganization = new JButton("¡Al azar!");
			play = new JButton("Jugar");
			
			initGUI();
			
			pack();
			this.setTitle("Battleship");
			this.setResizable(true);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.setVisible(true);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se encontraron los archivos");
		}
		
		
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
		exit.addActionListener(listener);
		showBoard.addActionListener(listener);
		randomOrganization.addActionListener(listener);
		play.addActionListener(listener);
		
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
		add(showBoard, constraints);
		
		constraints.anchor=GridBagConstraints.LAST_LINE_END;
		add(exit, constraints);
		
		
		constraints.anchor=GridBagConstraints.LAST_LINE_START;
		add(randomOrganization, constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridheight=1;
		constraints.anchor=GridBagConstraints.LAST_LINE_START;
		add(play, constraints);
	}
	
	
	public GameControl getGameControl() {
		return gameControl;
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
				image = ship.isVertical()? ship1 : ship1H;
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
	}
	
	public static void printMatrix(int[][] matrix) {
	    for (int row = 0; row < matrix.length; row++) {
	        for (int col = 0; col < matrix[row].length; col++) {
	            System.out.printf("%4d", matrix[row][col]);
	        }
	        System.out.println();
	    }
	}
	
	
	
	private void overlapIcon(JButton button) {

		BufferedImage newIcon = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
		ImageIcon initialIcon = (ImageIcon) button.getIcon();
		BufferedImage initialImage = (BufferedImage) initialIcon.getImage();
		
		Graphics g = newIcon.getGraphics();
		
		g.drawImage(initialImage, 0, 0, null);
		g.drawImage(shipShooted, 0, 0, null);
		g.dispose();
		
		button.setIcon(new ImageIcon(newIcon));
	}
	
	
	
	private void setSunkShipImg(int x, int y, Board controlBoard, BoardGUI board) {
		Ship ship = controlBoard.getShipByPosition(x, y);
		int[][] shipPosition = ship.getPosition();
		JButton[][] buttons = board.getButtons();
		
		for(int i = 0; i < ship.getSize(); i++) {
			buttons[shipPosition[i][0]][shipPosition[i][1]].setIcon(new ImageIcon(sunkShip));
		}
	}
	
	
	
	private boolean playerShoot(int x, int y) {
		int[] shot;

		if(controlBoard2.getBoard()[x][y] == 0) {
			//configuracion del icono de disparo
			ImageIcon shootIcon = new ImageIcon(shoot);
			board2.getButtons()[x][y].setIcon(shootIcon);
			//control del disparo
			controlBoard2.shoot(x, y);
			return false;
		}
		else if(controlBoard2.getBoard()[x][y] == 1) {
			//configuracion del icono de disparo
			ImageIcon shootIcon = new ImageIcon(shipShooted);
			board2.getButtons()[x][y].setIcon(shootIcon);
			
			//control del disparo y retorna verdadero si se ha hundido un barco
			if(controlBoard2.shoot(x, y)) {
				setSunkShipImg(x, y, controlBoard2, board2);
				return true;
			}
			
			return false;
		}
		return false;
	}


	
	
	private class Listener implements ActionListener, MouseListener {

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			
			if(event.getSource() == exit) {
				System.exit(0);
			}
			if(event.getSource() == showBoard) {
				help.setVisible(true);
				Myself.setEnabled(false);
				
			}
			if(event.getSource() == randomOrganization) {
				controlBoard1.randomShipOrganization();
				setShipsImage();
			}
			if(event.getSource() == play) {
				gameControl.startGame();
				printMatrix(gameControl.getBoard2().getBoard());
				randomOrganization.setVisible(false);
			}
			
			int[] shot;
			for(int i = 1; i < 11; i++) {
				for(int j = 1; j < 11; j++) {
					if(board2.getButtons()[i][j] == event.getSource() && gameControl.getGameState() == 1) {
						playerShoot(i, j);
						/*
						if(controlBoard2.getBoard()[i][j] == 0) {
							ImageIcon shootIcon = new ImageIcon(shoot);
							board2.getButtons()[i][j].setIcon(shootIcon);
							controlBoard2.shoot(i, j);
							
							printMatrix(gameControl.getBoard2().getBoard());
							System.out.println();
							
							gameControl.setGameState(2);
							shot = gameControl.emulateShoot();
							//controlBoard1.shoot(shot[0], shot[1]);
							
							if(controlBoard1.getBoard()[shot[0]][shot[1]] == 3) {
								overlapIcon(board1.getButtons()[shot[0]][shot[1]]);
								//board1.getButtons()[shot[0]][shot[1]].setIcon(new ImageIcon(shipShooted));
								gameControl.setGameState(1);
							}
							else {
								board1.getButtons()[shot[0]][shot[1]].setIcon(new ImageIcon(shoot));
								gameControl.setGameState(1);
							}
						}
						else if(controlBoard2.getBoard()[i][j] == 1) {
							ImageIcon shootIcon = new ImageIcon(shipShooted);
							board2.getButtons()[i][j].setIcon(shootIcon);
							printMatrix(gameControl.getBoard2().getBoard());
							System.out.println();
							
							if(controlBoard2.shoot(i, j)) {
								gameControl.setGameState(1);
							}
							else {
								gameControl.setGameState(2);
								shot = gameControl.emulateShoot();
								controlBoard1.shoot(shot[0], shot[1]);
								
								if(controlBoard1.getBoard()[shot[0]][shot[1]] == 3) {
									board1.getButtons()[shot[0]][shot[1]].setIcon(new ImageIcon(shipShooted));
									gameControl.setGameState(1);
								}
								else {
									board1.getButtons()[shot[0]][shot[1]].setIcon(new ImageIcon(shoot));
									gameControl.setGameState(1);
								}
							}
						}	*/	
					}
				}
			}
		}

		@Override
		public void mouseClicked(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			JButton actionButton = (JButton) mouseEvent.getSource();
			if(mouseEvent.getButton() == 3) {
				
				JButton clickedButton = (JButton) mouseEvent.getSource(); //objeto boton sobre el que se hace click
				
				int[] buttonPosition = board1.getButtonPosition(clickedButton); //position del boton en la matriz
				
				Ship ship = controlBoard1.getShipByPosition(buttonPosition[0], buttonPosition[1]); //barco en esa posicion
				
				if(ship != null && !controlBoard1.thereIsAShip(ship, mousePosition[0], mousePosition[1], ship.isVertical())) {
					controlBoard1.setShip(mousePosition[0], mousePosition[1], selectedShip.getShipNumber(), !selectedShip.isVertical());
				}
				setShipsImage();
			}
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
		}

		@Override
		public void mouseReleased(MouseEvent mouseEvent) {
			// TODO Auto-generated method stub
			if(selectedShip != null && !controlBoard1.thereIsAShip(selectedShip, mousePosition[0], mousePosition[1], selectedShip.isVertical())) {
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
