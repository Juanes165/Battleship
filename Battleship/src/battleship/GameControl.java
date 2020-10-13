package battleship;

import java.util.Random;

public class GameControl {
	private Random randomx;
	private Random randomy;
	private Board board1; //Players board
	private Board board2; //Computer board
	private int gameState; // 0 start, 1 player turn, 2 computer turn, 3 Player win, 4 Player loss.



	public GameControl() {
		
		gameState = 0;
		randomx = new Random();
		randomy = new Random();
		board1 = new Board();
		board2 = new Board();
		
	
	}
	
	public void emulateShoot() {	
		int x = randomx.nextInt(10);
		int y = randomy.nextInt(10);
		board1.shoot(x,y);
		
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
		board1.randomShipOrganization();
		board2.randomShipOrganization();
		setGameState(1);
		
	}


	public int getGameState() {
		return gameState;
	}

	public void setGameState(int gameState) {
		this.gameState = gameState;
	}
	
	
}