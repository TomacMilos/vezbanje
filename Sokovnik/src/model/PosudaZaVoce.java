package model;

import java.util.ArrayList;
import java.util.List;

public class PosudaZaVoce {

	private float MaxTezina = 30;
	private List<Jabuka> vocke = new ArrayList<Jabuka>();

	public PosudaZaVoce() {
	}

	public float getMaxTezina() {
		return MaxTezina;
	}

	public List<Jabuka> getVocke() {
		return vocke;
	}

	public void setVocke(List<Jabuka> vocke) {
		this.vocke = vocke;
	}

}