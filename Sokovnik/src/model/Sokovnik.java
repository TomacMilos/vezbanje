package model;

import java.util.ArrayList;
import java.util.Random;

import exeptionHandling.prepunaKorpa;
import services.SokovnikInterface;

public class Sokovnik implements SokovnikInterface {
	// ovde enter
	private PosudaZaVoce posudaZavoce = new PosudaZaVoce();
	private Cediljka cediljka = new Cediljka();
	private int akcija = 0;
	private static Sokovnik instance = null;
	public float ukunaTezina;
	public Random rand = new Random();

	public static Sokovnik getInstance() {
		// ok
		if (instance == null) {
			instance = new Sokovnik();
		}
		return instance;
	}
	//

	public PosudaZaVoce getPosudaZavoce() {
		return posudaZavoce;
	}

	public Cediljka getCediljka() {
		return cediljka;
	}

	@Override
	// ovaj parametar Jabuka voce, da li je intuitivan ?
	// vise smisla ima da se zove vocka ?
	public void dodavanjeVoca(Jabuka vocka) throws prepunaKorpa {
		// ovo mi se ne svidja, razbij sve ovo u metode, i imas u intelij desni klik na
		// projekat pa reformat code
		// da ti sve lepo formatira
		// druga stvar, u kode sve konstante tipa 100,0 , 70 izbaci u private static
		// final Long nesto = 70
		// kad gledam kod nemam pojma sta znaci 70 i akcija manja od 100l
		String poruka = ""; //ne treba ti poruka, dole longove nisi izdvojio u konstante

		//prvi if se pise voce.isCrvljiva(), ne treba ==true, jasno mi je da ce uci tu ako je true
		if(vocka.isCrvljiva()==false) { // if !vocka.isCrvljiva ????
			// sve u ovom ifu izdvoj u metodu tipa dodajVocku ili kako vec
			dodaj(vocka);
			if ((rand.nextInt(100 - 0 + 1) + 0) < 70) {
				if (akcija < 100) {

				} else {
					poruka = "Prevelik broj akcija nad sokovnikom";
				}
			} else {
				poruka = "Neuspesno dodavanje";

			}
		} else {
			System.out.println("Jabuka je crvljiva!!!!!!!!!!!!!!!!!!!!");
		}
		System.out.println(ukunaTezina); //nisu formatirana ova dva printa
		System.out.println(poruka);
	}

	public float getUkunaTezina() {
		return ukunaTezina;
	}

	public void setUkunaTezina(float ukunaTezina) {
		this.ukunaTezina = ukunaTezina;
	}

	@Override
	public void cedjenje(float kolicina) {
		// ovi uvuceni iffovi su isto losa praksa

		if ((rand.nextInt(100 - 0 + 1) + 0) > 30) {// opet izdvoj u konstante sve ??
			if (akcija < 100) {
				akcija++;
				double kolicinaDouble = kolicina; //kolicinaDouble ??? sta to govori ?  ne vidim da se igde koristi dalje, tako da ti ova linija ni ne treba ?

				double kolicinaSoka = (kolicinaDouble * 0.4); //zagrade su visak ????

				System.out.println("Uspesno cedjenje " + String.valueOf(kolicinaSoka)); //opet formatiraj sa %d ili kako vec ide za double iskoristi round ako treba

				cediljka.setKolicinaVoca(cediljka.getKolicinaVoca() + kolicinaSoka);
				posudaZavoce.setVocke(new ArrayList<Jabuka>());
			} else {
				;// sta ce ovo ovde ?
				System.out.println("Prevelik broj akcija nad sokovnikom");
			}
		} else {
//opet razmak
			System.out.println("Neuspesno cedjenje");
		}
	}

	public void dodaj(Jabuka vocka) throws prepunaKorpa {
		String poruka = ""; //izbrisi
		akcija++;
		ArrayList<Jabuka> vocke = posudaZavoce.getVocke();
		float tezina = 0; //gde uopste ovo koristis ???
		/*
		 * vocke.forEach.(vocka -> { tvoja logika neka }
		 */
		//
		for (Jabuka voce : vocke) {// koristi java stream
			// tezina = tezina + voce2.getTezine();
			// ovako treba da se pise, druga stvar imena promenljivih tipa voce1 ili voce2
			// su losa praksa, navedi smislenije nekako
			tezina = tezina + voce.getTezina();
		}

		if ((this.ukunaTezina + vocka.getTezina()) < posudaZavoce.getMaxTezina()) { //ukunaTezina ????
			System.out.println(this.ukunaTezina + vocka.getTezina() + "ovoooo");
			this.ukunaTezina = ukunaTezina + vocka.getTezina(); // jako je nepregledno kad je sve slepljeno
			vocke.add(vocka);
			poruka = "Uspesno ubaceno voce"; //gde se koristi ova poruka ????

		} else {
			System.out.println("cz");
			throw new prepunaKorpa("Tezina bla bla");

		}
	}

}
