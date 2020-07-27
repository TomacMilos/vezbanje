package model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import exeptionHandling.prepunaKorpa;
import services.SokovnikInterface;

public class Sokovnik implements SokovnikInterface {
	public static int ONE_HUNDRED_ONE = 101;
	public static int PERCENTAGE_70 = 70;
	public static int PERCENTAGE_30 = 30;
	public static int PERCENTAGE_20 = 20;
	public static double PERCENTAGE_40 = 0.4;
	private PosudaZaVoce posudaZavoce = new PosudaZaVoce();
	private Cediljka cediljka = new Cediljka();
	private int akcija = 0;
	private static Sokovnik instance = null;
	public float ukunaTezinaVocaUPosudi;
	private static DecimalFormat df = new DecimalFormat("0.00");

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
		if (!vocka.isCrvljiva()) {
			Random rand = new Random();
			if (rand.nextInt(ONE_HUNDRED_ONE) < PERCENTAGE_70) {
				if (akcija < 100) {
					dodaj(vocka);
				} else {
					System.out.println("Prevelik broj akcija nad sokovnikom");
				}
			} else {
				System.out.println("Neuspesno dodavanje");
			}
		} else if (vocka.isCrvljiva()) {
			System.out.println("Jabuka je crvljiva!");
		}
		System.out.println(ukunaTezinaVocaUPosudi);
	}

	public float getUkunaTezina() {
		return ukunaTezinaVocaUPosudi;
	}

	public void setUkunaTezina(float ukunaTezina) {
		this.ukunaTezinaVocaUPosudi = ukunaTezina;
	}

	@Override
	public void cedjenje(float tezinaVoca) {
		if (mozeCedjenje() && brojAkcijaManjiOdSto()) {
			akcija++;
			double kolicinaSoka = tezinaVoca * PERCENTAGE_40;
			System.out.println("Uspesno cedjenje: " + df.format(kolicinaSoka));
			cediljka.setKolicinaSoka(cediljka.getKolicinaSoka() + kolicinaSoka);
			posudaZavoce.setVocke(Collections.emptyList());
		} else {
			System.out.println("Neuspesno cedjenje ili je dostignut maksimalan broj akcija");
		}
	}

	private boolean brojAkcijaManjiOdSto() {
		return akcija < ONE_HUNDRED_ONE;
	}

	private boolean mozeCedjenje() {
		Random rand = new Random();
		return rand.nextInt(ONE_HUNDRED_ONE) < PERCENTAGE_30;
	}

	public void dodaj(Jabuka vocka) throws prepunaKorpa {
		akcija++;
		List<Jabuka> vocke = posudaZavoce.getVocke();
		if (moguceDodavanjeJabuke(vocka)) {
			this.ukunaTezinaVocaUPosudi += vocka.getTezina();
			vocke.add(vocka);
		} else {
			throw new prepunaKorpa("Korpa je prepuna nije moguce dodati vocku");
		}
	}

	private boolean moguceDodavanjeJabuke(Jabuka vocka) {
		return this.ukunaTezinaVocaUPosudi + vocka.getTezina() < posudaZavoce.getMaxTezina();
	}
}