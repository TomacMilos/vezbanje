package model;

import java.util.Random;

public class Jabuka extends Voce {
	private boolean crvljiva;
	public Random rand = new Random(); //da li random treba da bude deo Jabuke ?

	public Jabuka(String naziv, int tezina, boolean crvljiva) {
		super(naziv, tezina);
		this.crvljiva = crvljiva;
	}

	public boolean isCrvljiva() {
		return crvljiva;
	}

	public void setCrvljiva() {
		//random moze ovde
		if (rand.nextInt(100 - 0 + 1) + 0 > 20) {
			this.crvljiva = false;
		} else {
			this.crvljiva = true;
		}

	}
}
