package model;

import java.util.ArrayList;

public class PosudaZaVoce {
	// ovde uvek jedan space
	private float MaxTezina = 30;
	private ArrayList<Jabuka> vocke = new ArrayList<Jabuka>();

	// razmake ukloni svuda
	public PosudaZaVoce() {
	}

	public float getMaxTezina() {
		return MaxTezina;
	}

	public ArrayList<Jabuka> getVocke() {
		return vocke;
	}

	public void setVocke(ArrayList<Jabuka> vocke) {
		this.vocke = vocke;
	}

}
