package model;

import java.util.ArrayList;
import java.util.Random;

import exeptionHandling.exeptionHandler;
import services.SokovnikInterface;

public class Sokovnik implements SokovnikInterface {
	//ovde enter
	private PosudaZaVoce posudaZavoce = new PosudaZaVoce();
	private Cediljka cediljka = new Cediljka();
	private int akcija = 0;
	private static Sokovnik instance = null;
	public float ukunaTezina;
	public Random rand = new Random();

	public static Sokovnik getInstance() {
		//ok
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
	//ovaj parametar Jabuka voce, da li je intuitivan ?
	// vise smisla ima da se zove vocka ?
	public void dodavanjeVoca(Jabuka voce) throws exeptionHandler {
		//ovo mi se ne svidja, razbij sve ovo u metode, i imas u intelij desni klik na projekat pa reformat code
		//da ti sve lepo formatira
		//druga stvar, u kode sve konstante tipa 100,0 , 70 izbaci u private static final Long nesto = 70
		//kad gledam kod nemam pojma sta znaci 70 i akcija manja od 100l
		String poruka = "";
		//prvi if se pise voce.isCrvljiva(), ne treba ==true, jasno mi je da ce uci tu ako je true
		if(voce.isCrvljiva()==true) {
			System.out.println("Jabuka je crvljiva!!!!!!!!!!!!!!!!!!!!");
		}else if(voce.isCrvljiva()==false) {
			// sve u ovom ifu izdvoj u metodu tipa dodajVocku ili kako vec
		if ((rand.nextInt(100 - 0 + 1) + 0) < 70) {
			if (akcija < 100) {
				akcija++;
				ArrayList<Jabuka> vocke = posudaZavoce.getVocke();
				float tezina = 0;
				/*
				vocke.forEach.(vocka -> {
					tvoja logika neka
				}
				*/
				 */
				for (Jabuka voce2 : vocke) {// koristi java stream
					//tezina = tezina + voce2.getTezine();
					//ovako treba da se pise, druga stvar imena promenljivih tipa voce1 ili voce2 su losa praksa, navedi smislenije nekako
					tezina =tezina+voce2.getTezina();
				}
				
				
				if ((this.ukunaTezina + voce.getTezina()) < posudaZavoce.getMaxTezina()) {
					System.out.println(this.ukunaTezina + voce.getTezina()+"ovoooo");
					this.ukunaTezina=ukunaTezina+voce.getTezina(); //jako je nepregledno kad je sve slepljeno
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
		//ovi uvuceni iffovi su isto losa praksa

		String poruka = ""; // ne svidja mi se ovo, ne treba ti, tamo gde treba da printas, printaj string koji vec treba
		//a ne treba da ga inicijalizujes

		if ((rand.nextInt(100 - 0 + 1) + 0) > 30) {// opet izdvoj u konstante sve
			if (akcija < 100) {
				akcija++;
				double kolicinaDouble = kolicina;
				
				double kolicinaSoka =(kolicinaDouble * 0.4);

				poruka = "Uspesno cedjenje";//dodaj razmake, da taj string na ispisu bude lepo formatiran
				System.out.println(poruka+String.valueOf(kolicinaSoka));

				cediljka.setKolicinaVoca(cediljka.getKolicinaVoca()+kolicinaSoka);
				posudaZavoce.setVocke(new ArrayList<Jabuka>());
			} else {
				poruka = "Prevelik broj akcija nad sokovnikom";
				System.out.println(poruka);
			}
		} else {
//opet razmak
			poruka = "Neuspesno cedjenje";
			System.out.println(poruka);
		}
		//izbrisi ovo, koristi intellij reformac code
	}

}
