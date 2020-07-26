package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import exeptionHandling.prepunaKorpa;
import model.Jabuka;
import model.PosudaZaVoce;
import model.Sokovnik;

public class Application {
	public static Sokovnik sokovnik = Sokovnik.getInstance();
	private static int ONE_HUNDRED = 100;
	private static int ZERO = 0;
	private static int PERCENTAGE_20 = 20;
	private static int ONE = 1;
	private static final String ONESTRING = "1";
	private static String BACKDUGME = "x";
	private static String OPTION2 = "2";
	private static String OPTION3 = "3";
	private static String OPTION4 = "4";
	private static final ArrayList<Integer> DOZVOLJENE_TEZINE = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException, prepunaKorpa {
		dodavanjeVrednosti();
		glavniMeni();
	}
	public static void dodavanjeVrednosti() {
		DOZVOLJENE_TEZINE.add(1);
		DOZVOLJENE_TEZINE.add(2);
		DOZVOLJENE_TEZINE.add(3);
	}
	public static String getOpcija() throws IOException {
		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// Reading data using readLine
		String opcija = reader.readLine();
		return opcija;
	}

	public static void glavniMeni() throws IOException, prepunaKorpa {
		printMain();
		String opcija = getOpcija();
		if (ONESTRING.equals(opcija)) {
			try {
				dodavanje();
			} catch (prepunaKorpa e) {
				System.out.println(e);
			}
		} else if (OPTION2.equals(opcija)) {
			cedjenje();
		} else if (OPTION3.equals(opcija)) {
			getVoceUKorpi();
		} else if (OPTION4.equals(opcija)) {
			System.out.println("Ukupna kolicina napravljenog soka = " + sokovnik.getCediljka().getKolicinaVoca() + "l");
			glavniMeni();
		} else {
			System.out.println("Pogresna opcija!!!!!!!!!!");
			glavniMeni();
		}
	}
 
	private static void printMain() {
		System.out.println("Dobrodosli u sokovnik");
		System.out.println("Izaberite opciju :");
		System.out.println("1) Dodavanje voca");
		System.out.println("2) Cedjenje");
		System.out.println("3) Kolicina voca u posudi");
		System.out.println("4) Kolicina napravljenog soka");
		System.out.println("--------------------------");
	}

	public static void cedjenje() throws IOException, prepunaKorpa {
		float tezina = 0;
		// sokivnig.getPosudaZavoce().getVocke().forEach(vocka -> {})
		// ovo gore ti je java 8 feature, to nauci, bar za for petlju kako se koristi
		// to ti je veliki plus, moze i ovako da prodje, al onako ce videti da si
		// ozbiljan baja
		// znaci java streams izguglaj
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
		System.out.println("Sum= " + tezina + "kg");
		glavniMeni();
	}

	private static String ucitajNaziv() throws IOException {
		System.out.println("Uneti naziv jabuke");
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		String naziv = reader1.readLine();
		return naziv;
	}

	private static String ucitajTezinu() throws IOException {
		System.out.println("Uneti tezinu jabuke (tezina moze biti u rasponu 1-3)");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String tezina = reader.readLine();
		return tezina;
	}

	public static void dodavanje() throws IOException, prepunaKorpa {
		printeviDodavanje();
		String naziv = ucitajNaziv();
		String tezina = ucitajTezinu();
		boolean crvljiva;
		Random rand = new Random();
		if ((rand.nextInt((ONE_HUNDRED - ZERO + ONE) + ZERO) <= PERCENTAGE_20)) {
			crvljiva = true;
		} else {
			crvljiva = false;
		}
		if (!tezina.equals(BACKDUGME)) {
			int tezina1 = Integer.valueOf(tezina);
			if (DOZVOLJENE_TEZINE.contains(tezina1)) {
				Jabuka jabuka = new Jabuka(naziv, tezina1, crvljiva);
				sokovnik.dodavanjeVocke(jabuka);
				glavniMeni();
			}
		} else if (BACKDUGME.equals(tezina)) {
			glavniMeni();
		} else {
			System.out.println("pogresna vrednost!!!!!!!!!!!!!!!!!!!!!!!!!");
			dodavanje();
		}
	}

	public static void printeviDodavanje() {
		System.out.println("--------------------------");
		System.out.println("x) Za povratak u glavni meni ");
		System.out.println("Dodavanje jabuke u korpu");

	}
}
