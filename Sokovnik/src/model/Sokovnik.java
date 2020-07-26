package model;

import java.util.ArrayList;
import java.util.Random;

import exeptionHandling.exeptionHandler;
import services.SokovnikInterface;

public class Sokovnik implements SokovnikInterface {
	private PosudaZaVoce posudaZavoce = new PosudaZaVoce();
	private Cediljka cediljka = new Cediljka();
	private int akcija = 0;
	private static Sokovnik instance = null;
	public float ukunaTezina;
	public Random rand = new Random();

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
	public void dodavanjeVoca(Jabuka voce) throws exeptionHandler {
		String poruka = "";
		if(voce.isCrvljiva()==true) {
			System.out.println("Jabuka je crvljiva!!!!!!!!!!!!!!!!!!!!");
		}else if(voce.isCrvljiva()==false) {
		if ((rand.nextInt(100 - 0 + 1) + 0) < 70) {
			if (akcija < 100) {
				akcija++;
				ArrayList<Jabuka> vocke = posudaZavoce.getVocke();
				float tezina = 0;
				for (Jabuka voce2 : vocke) {
					
					tezina =tezina+voce2.getTezina();
				}
				
				
				if ((this.ukunaTezina + voce.getTezina()) < posudaZavoce.getMaxTezina()) {
					System.out.println(this.ukunaTezina + voce.getTezina()+"ovoooo");
					this.ukunaTezina=ukunaTezina+voce.getTezina();
					vocke.add(voce);
					poruka = "Uspesno ubaceno voce";
			
				} else {
					System.out.println("cz");
					throw new exeptionHandler("Tezina bla bla");

				}
			} else{
				poruka = "Prevelik broj akcija nad sokovnikom";
			}
		} else {
			poruka = "Neuspesno dodavanje";

		}
		}
		System.out.println(ukunaTezina);
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

		String poruka = "";

		if ((rand.nextInt(100 - 0 + 1) + 0) > 30) {
			if (akcija < 100) {
				akcija++;
				double kolicinaDouble = kolicina;
				
				double kolicinaSoka =(kolicinaDouble * 0.4);

				poruka = "Uspesno cedjenje";
				System.out.println(poruka+String.valueOf(kolicinaSoka));

				cediljka.setKolicinaVoca(cediljka.getKolicinaVoca()+kolicinaSoka);
				posudaZavoce.setVocke(new ArrayList<Jabuka>());
			} else {
				poruka = "Prevelik broj akcija nad sokovnikom";
				System.out.println(poruka);
			}
		} else {

			poruka = "Neuspesno cedjenje";
			System.out.println(poruka);
		}
		
	}

}
