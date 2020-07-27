package model;

import java.util.ArrayList;

public class PosudaZaVoce {

	private float MaxTezina = 30;
	private ArrayList<Jabuka> vocke = new ArrayList<Jabuka>();
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