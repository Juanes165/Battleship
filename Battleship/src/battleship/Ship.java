package battleship;

public class Ship {
	private int[][] position;
	private int[][] damagedParts;
	private int size;
	
	public Ship(int size) {
		
		position = new int[size][2];
		damagedParts = new int[size][2];
		this.size = size;
		
	}
	
	public void setShipPosition(int x, int y, boolean isVertical) {


	}
}
