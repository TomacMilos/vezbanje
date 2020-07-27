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
		int ZERO = 0;
		int ONE_HUNDRED = 100;
		int ONE = 1;
		int PERCENTAGE_20 = 20;
		Random rand = new Random();
		if (rand.nextInt(ONE_HUNDRED - ZERO + ONE) + ZERO > PERCENTAGE_20) {
			this.crvljiva = false;
		} else {
			this.crvljiva = true;
		}
	}
}