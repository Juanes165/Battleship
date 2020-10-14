package battleship;

import java.util.Random;

public class Board {
	private Ship[] ships;
	private int[][] board;
	
	public Board() {
		board = new int[11][11];
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				board[i][j] = 0;
			}
		}
		
		ships = new Ship[10];
		for(int i = 0; i < 10; i ++) {
			ships[i] = new Ship(i);
		}
		
		for(int i = 1; i < 11; i++) {
			this.setShip(1, i, i - 1, true);
		}
	}
	
	
	
	public void setShip(int x, int y, int shipNumber, boolean isVertical) {
		Ship ship = ships[shipNumber];
		int[][] shipPosition = ship.getPosition();
		
		if(thereIsAShip(ship, x, y, isVertical)) {
			System.out.println("Hay un barco");
		}
		else {
			for(int i = 0; i < ship.getSize(); i++) {
				board[shipPosition[i][0]][shipPosition[i][1]] = 0;
			}
			ship.setShipPosition(x, y, isVertical);
			for(int i = 0; i < ship.getSize(); i++) {
				board[shipPosition[i][0]][shipPosition[i][1]] = 1;
			}
		}
		
	}
	
	
	
	public boolean thereIsAShip(Ship ship, int x, int y, boolean isVertical) {
		
		boolean thereIsAShip = false;
		if(isVertical) {
			if(ship.getSize() + x > 10) {
				x = 11 - ship.getSize();
			}
			for(int i = 0; i < ship.getSize(); i++) {
				if(board[x + i][y] == 1) {
					if(getShipByPosition(x + i, y) != ship) {
						thereIsAShip = true;
					}
				}
			}
		}
		else {
			if(ship.getSize() + y > 10) {
				y = 11 - ship.getSize();
			}
			for(int i = 0; i < ship.getSize(); i++) {
				if(board[x][y + i] == 1) {
					if(getShipByPosition(x, y + i) != ship) {
						thereIsAShip = true;
					}
				}
			}
		}
		return thereIsAShip;
	}
	
	
	
	public void randomShipOrganization() {
		Random random = new Random();
		int randomX;
		int randomY;
		int vertical;
		
		for(int i = 0; i < 10; i++) {
			Ship ship = ships[i];
			randomX = random.nextInt(10) + 1;
			randomY = random.nextInt(10) + 1;
			vertical = random.nextInt(1);

			while(thereIsAShip(ship, randomX, randomY, vertical == 1)) {
				randomX = random.nextInt(10) + 1;
				randomY = random.nextInt(10) + 1;
				vertical = random.nextInt(2);
			}
			
			this.setShip(randomX, randomY, i, vertical == 1);
		}
	}
	
	
	
	public boolean shoot(int x, int y) {
		if(board[x][y] == 1) {
			for(int i = 0; i < 10; i++) {
				if(ships[i].shoot(x, y)) {
					board[x][y] = 3;
					return ships[i].isSunk();
				}
			}
		}
		else if(board[x][y] == 0) {
			board[x][y] = 2;
		}
		return false;
	}
	
	
	
	public boolean allShipsSank() {
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				if(board[i][j] == 1) {
					return false;
				}
				
			}
		}
		return true;
	}
	
	
	
	public Ship[] getShips(){
		return ships;
	}
	
	
	
	public Ship getShipByPosition(int x, int y) {
		if(board[x][y] == 1 || board[x][y] == 3) {
			for(int i = 0; i < 10; i++) {
				Ship ship = ships[i];
				for(int j = 0; j < ship.getSize(); j++) {
					if(ship.getPosition()[j][0] == x && ship.getPosition()[j][1] == y) {
						return ship;
					}
				}
			}
		}
		return null;
	}
	
	
	
	public int[][] getBoard() {
		return board;
	}
}
