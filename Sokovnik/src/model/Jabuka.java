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
		Random rand = new Random();
		if (rand.nextInt(Sokovnik.ONE_HUNDRED_ONE) < Sokovnik.PERCENTAGE_20) {
			this.crvljiva = true;
		} else {
			this.crvljiva = false;
		}
	}
}