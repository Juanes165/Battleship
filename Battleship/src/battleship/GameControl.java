package battleship;

import java.util.Random;

public class GameControl {
	private Random random;
	private Board board1; //Players board
	private Board board2; //Computer board
	private int gameState; // 0 start, 1 player turn, 2 computer turn, 3 Player win, 4 Player loss.



	public GameControl() {
		
		gameState = 0;
		random = new Random();
		board1 = new Board();
		board2 = new Board();
		
	
	}
	
	
	
	public int[] emulateShoot() {	
		int x = random.nextInt(10) + 1;
		int y = random.nextInt(10) + 1;
		while(board1.getBoard()[x][y] == 2 || board1.getBoard()[x][y] == 3) {
			x = random.nextInt(10) + 1;
			y = random.nextInt(10) + 1;
		}
		board1.shoot(x, y);
		int[] shot = {x, y};
		return shot;
	}
	
	
	
	public void winMatch() {
		if(board1.allShipsSank() == true) {			
				setGameState(4);
			}
			if(board2.allShipsSank() == true) {			
				setGameState(3);
			}
			
		}
	
	
	
	public void startGame() {
		board2.randomShipOrganization();
		setGameState(1);	
	}


	
	public int getGameState() {
		return gameState;
	}

	
	
	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	
	
	
	public Board getBoard1() {
		return board1;
	}
	
	
	
	public Board getBoard2() {
		return board2;
	}
}