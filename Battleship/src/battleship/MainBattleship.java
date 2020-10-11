package battleship;

import java.awt.EventQueue;

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
		
		Ship ship = new Ship(2);
		//Ship ship2 = new Ship(4);
		ship.setShipPosition(6, 3, false);
		//ship2.setShipPosition(10, 10, false);
		System.out.println("Barco 1");
		printMatrix(ship.getPosition());
		//System.out.println("Barco 2");
		//printMatrix(ship2.getPosition());
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
