package battleship;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WindowHelp extends JFrame {

	public static final String shootImg = "src/images/shoot.png";
	public static final String shipShootedImg = "src/images/shipShooted.png";
	public static final String shipImg = "src/images/";
	public static final String seaImg = "src/images/sea.png";
	
	private JButton comeback;
	private BoardGUI board; // Tablero enemigo.
	private Listeners listener;
	private JFrame battleshipgui;
	private GameControl gameControl;
	private Board controlBoard2;
	

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
	
	
	public WindowHelp(JFrame battleshipgui) {
		try {
		
		sea = ImageIO.read(new File(seaImg));
		ship1 = ImageIO.read(new File(shipImg + "1.png"));
		ship2 = ImageIO.read(new File(shipImg + "2.png"));
		ship3 = ImageIO.read(new File(shipImg + "3.png"));
		ship4 = ImageIO.read(new File(shipImg + "4.png"));
		ship1H = ImageIO.read(new File(shipImg + "1H.png"));
		ship2H = ImageIO.read(new File(shipImg + "2H.png"));
		ship3H = ImageIO.read(new File(shipImg + "3H.png"));
		ship4H = ImageIO.read(new File(shipImg + "4H.png"));
			
		this.battleshipgui=battleshipgui;
	
		gameControl =  battleshipgui.getGameControl();
		controlBoard = .getBoard2();
	
		initGUI();
		board = new BoardGUI();
		
		//Default window.
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setUndecorated(true);
		this.setVisible(true);
		pack();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "No se encontraron los archivos");

		}
	}
	private void setShipsImage() {
		Ship[] ships = controlBoard.getShips();
		BufferedImage image = null;
		
		for(int i = 1; i < 11; i++) {
			for(int j = 1; j < 11; j++) {
				board.getButtons()[i][j].setIcon(new ImageIcon(sea));
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
				board.getButtons()[x][y].setIcon(new ImageIcon(subImage));
			}
		}
	}


	private void initGUI(){
		
		//window container and layout.
		
		//Create listener.
		listener = new Listeners();
		
		//Create Gui.
		//controlBoard2 = gameControl.getBoard2();
		Titles title = new Titles ("Flota enemiga", 27, Color.black);
		add(title, BorderLayout.NORTH);
		//setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		comeback = new JButton("Regresar");
		comeback.addActionListener(listener);
		add(comeback,BorderLayout.SOUTH);
		
		
	}
	private class Listeners implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			//event button comeback.
			battleshipgui.setEnabled(true);
			setVisible(false);
		}
		
	}
}