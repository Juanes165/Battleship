package battleship;

import java.awt.EventQueue;
import java.util.Arrays;

public class MainBattleship {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				BattleshipGUI window = new BattleshipGUI();
			}

		});
		
		
		Ship ship = new Ship(4);
		//Ship ship2 = new Ship(4);
		ship.setShipPosition(3, 8, false);
		ship.shoot(3, 7);
		ship.shoot(3, 8);
		ship.shoot(3, 9);
		//ship.shoot(3, 10);
		
		//ship.setDamagedPart(7, 3);
		//ship.setDamagedPart(8, 3);
		//ship.setDamagedPart(9, 3);
		//ship2.setShipPosition(10, 10, false);
		System.out.println("Barco 1");
		printMatrix(ship.getPosition());
		System.out.println("Partes dañadas");
		System.out.println(Arrays.toString(ship.damagedParts));
		System.out.println("Está hundido: " + ship.isSunk);
		//System.out.println("Barco 2");
		//printMatrix(ship2.getPosition());
		
		
		Board board = new Board();
		board.setShip(9, 3, 8, false);
		board.setShip(8, 5, 8, true);
		board.shoot(5, 5);
		board.shoot(10, 10);
		printMatrix(board.board);
	}

	public static void printMatrix(int[][] matrix) {
	    for (int row = 0; row < matrix.length; row++) {
	        for (int col = 0; col < matrix[row].length; col++) {
	            System.out.printf("%4d", matrix[row][col]);
	        }
	        System.out.println();
	    }
	}
}
