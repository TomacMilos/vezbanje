package model;

public class Voce {

	private String naziv;
	private float tezina;
	public Voce(String naziv, int tezina) {
		super();
		this.naziv = naziv;
		this.tezina = tezina;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public float getTezina() {
		return tezina;
	}
	public void setTezina(int tezina) {
		this.tezina = tezina;
	}
}