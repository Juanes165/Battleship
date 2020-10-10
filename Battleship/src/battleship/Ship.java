package battleship;

public class Ship {
	private int[][] position;
	private int[] damagedParts;
	private int size;
	
	public Ship(int size) {
		
		this.position = new int[size][2];
		this.damagedParts = new int[size];
		this.size = size;
		
	}
	
	public void setShipPosition(int x, int y, boolean isVertical) {
		
		if(isVertical) {
			if(size + y > 10) {
				y = 11 - size;
			}
			//y
			int n = y;
			for(int i = 0; i < size; i++) {
				position[i][1] = n;
				n++;
			}
			//x
			for(int i = 0; i < size; i++) {
				position[i][0] = x;
			}
		}
		else {
			if(size + x > 10) {
				x = 11 - size;
			}
			//x
			int n = x;
			for(int i = 0; i < size; i++) {
				position[i][0] = n;
				n++;
			}
			//y
			for(int i = 0; i < size; i++) {
				position[i][1] = y;
			}
		}
		
	}
	
	
	public int[][] getPosition() {
		return position;
	}
}
