package battleship;

public class Ship {
	private int[][] position;
	private boolean[] damagedParts;
	private int size;
	private boolean isVertical;
	
	public Ship(int size) {
		
		this.position = new int[size][2];
		this.damagedParts = new boolean[size];
		this.size = size;
		this.isVertical = true;
		
	}
	
	public void setShipPosition(int x, int y, boolean isVertical) {
		this.isVertical = isVertical;
		if(isVertical) {
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
		else {
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
	}
	
	
	
	public boolean shoot(int x, int y) {
		
		for(int i = 0; i < size; i++) {
			if(position[i][0] == x && position[i][1] == y) {
				damagedParts[i] = true;
				return true;
			}
		}
		
		return false;		
	}
	
	
	
	public int[][] getPosition() {
		return position;
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	
	public boolean isSunk() {
		boolean isSunk = true;
		
		for(int i = 0; i < size; i++) {
			if(!damagedParts[i]) {
				isSunk = false;
			}
		}
		return isSunk;
	}
	
	
	
	public boolean isVertical() {
		return isVertical;
	}
}
