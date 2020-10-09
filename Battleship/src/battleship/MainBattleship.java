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
	}

}
