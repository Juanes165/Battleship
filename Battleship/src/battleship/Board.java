package battleship;

public class Board {
	private Ship[] ships;
	public int[][] board;
	
	public Board() {
		board = new int[11][11];
		for(int i = 0; i < 11; i++) {
			for(int j = 0; j < 11; j++) {
				board[i][j] = 0;
			}
		}
		
		ships = new Ship[10];
		ships[0] = new Ship(1);
		ships[1] = new Ship(1);
		ships[2] = new Ship(1);
		ships[3] = new Ship(1);
		ships[4] = new Ship(2);
		ships[5] = new Ship(2);
		ships[6] = new Ship(2);
		ships[7] = new Ship(3);
		ships[8] = new Ship(3);
		ships[9] = new Ship(4);
	}
	
	
	public void setShip(int ship, int x, int y, boolean isVertical) {
		ships[ship].setShipPosition(x, y, isVertical);
		for(int i = 0; i < ships[ship].getSize(); i++) {
			board[ships[ship].getPosition()[i][0]][ships[ship].getPosition()[i][1]] = 1;
		}
	}
	
	
	
	public void shoot(int x, int y) {
		for(int i = 0; i < 10; i++) {
			ships[i].shoot(x, y);
		}
		board[x][y] = 2;
	}
}
