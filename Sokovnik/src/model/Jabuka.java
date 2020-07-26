package model;

import java.util.Random;

public class Jabuka extends Voce {
	//razmak
	private boolean crvljiva;


	public Jabuka(String naziv, int tezina, boolean crvljiva) {
		super(naziv, tezina);
		this.crvljiva = crvljiva;
	}

	public boolean isCrvljiva() {
		return crvljiva;
	}

	public void setCrvljiva() {
		//random moze ovde
		Random rand = new Random(); //da li random treba da bude deo Jabuke ?
		//mozda da napravis public constante u application.java pa da ih i ovde koristis
		if (rand.nextInt(100 - 0 + 1) + 0 > 20) {
			this.crvljiva = false;
		} else {
			this.crvljiva = true;
		}

	}
}
