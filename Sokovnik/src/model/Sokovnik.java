package model;

import java.util.ArrayList;
import java.util.Random;

import exeptionHandling.prepunaKorpa;
import services.SokovnikInterface;

public class Sokovnik implements SokovnikInterface {
	private PosudaZaVoce posudaZavoce = new PosudaZaVoce();
	private Cediljka cediljka = new Cediljka();
	private int akcija = 0;
	private static Sokovnik instance = null;
	public float ukunaTezina;
	private static int ONE_HUNDRED = 100;
	private static int ZERO = 0;
	private static int PERCENTAGE_70 = 70;
	private static int PERCENTAGE_30 = 30;
	private static int ONE = 1;
	private static double PERCENTAGE40= 0.4;
	public static Sokovnik getInstance() {
		if (instance == null) {
			instance = new Sokovnik();
		}
		return instance;
	}
	public PosudaZaVoce getPosudaZavoce() {
		return posudaZavoce;
	}
	public Cediljka getCediljka() {
		return cediljka;
	}
	@Override
	public void dodavanjeVocke(Jabuka vocka) throws prepunaKorpa {
		if (vocka.isCrvljiva() == false) {
			dodaj(vocka);
			Random rand = new Random();
			if ((rand.nextInt(ONE_HUNDRED - ZERO + ONE) + ZERO) < PERCENTAGE_70) {
				if (akcija < 100) {
				} else {
					System.out.println("Prevelik broj akcija nad sokovnikom");
				}
			} else {
				System.out.println("Neuspesno dodavanje");
			}
		} else {
			System.out.println("Jabuka je crvljiva!!!!!!!!!!!!!!!!!!!!");
		}
		System.out.println(ukunaTezina);
	}
	public float getUkunaTezina() {
		return ukunaTezina;
	}
	public void setUkunaTezina(float ukunaTezina) {
		this.ukunaTezina = ukunaTezina;
	}
	@Override
	public void cedjenje(float kolicina) {
		Random rand = new Random();
		if ((rand.nextInt(ONE_HUNDRED - ZERO + ONE) + ZERO) > PERCENTAGE_30) {
			if (akcija < ONE_HUNDRED) {
				akcija++;
				double kolicinaSoka = kolicina * PERCENTAGE40;
				System.out.format("Uspesno cedjenje %d", kolicinaSoka);
				cediljka.setKolicinaVoca(cediljka.getKolicinaVoca() + kolicinaSoka);
				posudaZavoce.setVocke(new ArrayList<Jabuka>());
			} else {
				System.out.println("Prevelik broj akcija nad sokovnikom");
			}
		} else {
			System.out.println("Neuspesno cedjenje");
		}
	}
	public void dodaj(Jabuka vocka) throws prepunaKorpa {
		akcija++;
		ArrayList<Jabuka> vocke = posudaZavoce.getVocke();
		if ((this.ukunaTezina + vocka.getTezina()) < posudaZavoce.getMaxTezina()) { // ukunaTezina ????
			this.ukunaTezina = ukunaTezina + vocka.getTezina();
			vocke.add(vocka);

		} else {
			System.out.println("cz");
			throw new prepunaKorpa("Tezina bla bla");
		}
	}
}