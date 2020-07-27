package model;

import java.util.Random;

public class Jabuka extends Voce {
	private boolean crvljiva;
	public Jabuka(String naziv, int tezina) {
		super(naziv, tezina);
	}
	public boolean isCrvljiva() {
		return crvljiva;
	}
	public void setCrvljiva() {
		int ONE_HUNDRED_ONE = 101;
		int PERCENTAGE_20 = 20;
		Random rand = new Random();
		if (rand.nextInt(ONE_HUNDRED_ONE)< PERCENTAGE_20) {
			this.crvljiva = true;
		} else {
			this.crvljiva = false;
		}
	}
}