package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.List;
import java.util.Random;

import exeptionHandling.prepunaKorpa;
import model.Jabuka;
import model.PosudaZaVoce;
import model.Sokovnik;

public class Application {
	public static Sokovnik sokovnik = Sokovnik.getInstance();
	private static int do100 = 100;
	private static int od0 =0;
	private static int percentage20 =20;

	public static void main(String[] args) throws IOException, prepunaKorpa {

		glavniMeni();

	}

	public static void glavniMeni() throws IOException, prepunaKorpa {
		//ovi svi printevi idu u metodu posebnu tipa printMeni()
		printMain();
		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Reading data using readLine
		String name = reader.readLine(); //name -> meniNumber ili tako nesto ?


		//ovo nije dobra praksa, ako je name null, dobices null pointer, treba pisati prvo konstantu
		//("1").equals(name) tako izbegavas hendlanje null pountera
		if (("1").equals(name)) { // "1" isto u private static final String ONE, znaci svi stringovi su konstante, nema potrebe da ista budu zakucano u kodu
			//cim vidim da se zeleni u intellij nije dobro, sem system.out.println("nesto");
			try {
				dodavanje();
			} catch (prepunaKorpa e) {
				System.out.println(e);
			}

			//2.equals......
		} else if (("2").equals(name)) {
			// ovo neka bude metoda cedjenje kao sto si gore uradio metod dodavanje
			cedjenje();
		} else if (name.equals("3")) {
			//ovo isto u metod getKolicinaVocaUKorpi
			getVoceUKorpi();
			
		} else if (name.equals("4")) {
			//ne treba ti string.valueOf mislim da kad radis konkatenaciju string + nesto, java compiler zna da treba da evaluira nesto u string

			System.out.println("Ukupna kolicina napravljenog soka = "
					+ String.valueOf(sokovnik.getCediljka().getKolicinaVoca()) + "l");
			glavniMeni();

		} else {
			System.out.println("Pogresna opcija!!!!!!!!!!");
			glavniMeni();
		}
	}
	public static void printMain() {
		//ovo neka bude private metoda
		System.out.println("Dobrodosli u sokovnik");
		System.out.println("Izaberite opciju :");
		System.out.println("1) Dodavanje voca");
		System.out.println("2) Cedjenje");
		System.out.println("3) Kolicina voca u posudi");
		System.out.println("4) Kolicina napravljenog soka");
		System.out.println("--------------------------");
//ne radi ti taj rofmater dobro,
	}//oivde ide razmak isto
	public static void cedjenje() throws IOException, exeptionHandler {
		float tezina = 0;
		//sokivnig.getPosudaZavoce().getVocke().forEach(vocka -> {})
		//ovo gore ti je java 8 feature, to nauci, bar za for petlju kako se koristi
		//to ti je veliki plus, moze i ovako da prodje, al onako ce videti da si ozbiljan baja
		//znaci java streams izguglaj
		for (Jabuka jabuka : sokovnik.getPosudaZavoce().getVocke()) {
			System.out.println(jabuka.getNaziv() + "=" + jabuka.getTezina());

			tezina = tezina + jabuka.getTezina();
		}

		sokovnik.cedjenje(tezina);
		glavniMeni();
	}

	public static void getVoceUKorpi() throws IOException, prepunaKorpa {
		float tezina = 0;
		System.out.println("---------------------------------------------------------");
		for (Jabuka jabuka : sokovnik.getPosudaZavoce().getVocke()) {
			System.out.println(jabuka.getNaziv() + "=" + jabuka.getTezina());

			tezina = tezina + jabuka.getTezina();
		}
		System.out.println("---------------------------------------------------------");
		System.out.println("Sum= " + tezina + "kg");// razmake dodaj, lepo formatiraj taj string, nz
		//nesto tipa System.out.println("Sum= %d tezina", tezina);
		//zaboravio sam kako ide tacno formatiranje, ali tako treba, mada ok je i konkatenacija obicna,
		//al ovo ti sve pisem da znas kako pravilno izgleda clean code
		glavniMeni();
	}

	public static void dodavanje() throws IOException, prepunaKorpa {
		//printevi u metod poseban
		printeviDodavanje();

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String tezina = reader.readLine(); // da li mozes odmah da castujes u long ili int ?, sta ce ti tezina kao string ? ?????
		System.out.println("Uneti naziv jabuke");
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		String naziv = reader1.readLine();
		//ovo sa bufferedReaderom isto sve u poseban metod tipa ucitajNazivJabuke i ucitajTezinuJabuke

		boolean crvljiva;
		Random rand = new Random();
		if ((rand.nextInt((do100 - od0 + 1) + od0) <= percentage20)) {//opet sve izbaci u konstante, nemam pojma sta tebi znaci 20
			//ime konstante se obavezno pise sa CAPS LOCK znaci PERCENTAGE_20
			//DO 100 i od0, opet prilicno neintuitivno, nazovi samo ONE_HUNDRED i ZERO
			crvljiva = true;
		} else {
			crvljiva = false;
		}
		if (!tezina.equals("x")) { //sta ovaj if radi ? !tezina.equals('x') poredis stringove ?? obrnuto "x".equals(tezina) zbog nulpointera
			int tezina1 = Integer.valueOf(tezina);
			//ovo mi se ne svidja, to je isto konstanta
			// znaci private static final ArrayList<Long> DOZVOLJENE_TEZINE = new Arrays.of(1,2,3);
			int[] dozvoljeneTezine= {1,2,3};
			//ovde onda imas if DOZVOLJENE_TEZINE.contains(tezina);
			//znaci tezina1 i tezina2 su lose prakse, ime svake promenljive mora biti jasno
			//mozda ti to deluje glupo jer si ti radio i ti sve znas kako tvoj program radi
			//ali meni koji gledam ovaj tvoj kod
			//to samo zvuci zbunjujuce
			if (tezina1 == dozvoljeneTezine[0] || tezina1 ==dozvoljeneTezine[1] || tezina1 ==dozvoljeneTezine[2]) { //opet konstante..
				//ovo se cini ok, mozda bih dodao da sve bude inline
				//sokovnik.dodavanjeVoca(new Jabuke(bla,blab,lab))
				//reimenovao bih ovu metodu da bude dodavanjeVocke jer dodajes jednu vocku, a ne voce kao mnozinu
				Jabuka jabuka = new Jabuka(naziv, tezina1, crvljiva);
				sokovnik.dodavanjeVoca(jabuka);
				glavniMeni();
			}
		} else if (tezina == "x") {//x.equals(tezina)
			glavniMeni();
		} else {
//razmak ???
			System.out.println("pogresna vrednost!!!!!!!!!!!!!!!!!!!!!!!!!");
			dodavanje();
		}
	}
	public static void printeviDodavanje() {
		System.out.println("--------------------------");
		System.out.println("x) Za povratak u glavni meni ");
		System.out.println("Dodavanje jabuke u korpu");
		System.out.println("Uneti tezinu jabuke (tezina moze biti u rasponu 1-3)");
	}
}
