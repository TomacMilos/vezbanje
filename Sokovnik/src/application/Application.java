package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import exeptionHandling.prepunaKorpa;
import model.Jabuka;
import model.Sokovnik;

public class Application {

	public static Sokovnik sokovnik = Sokovnik.getInstance();
	private static final String ONESTRING = "1";
	private static String BACKDUGME = "x";
	private static String OPTION2 = "2";
	private static String OPTION3 = "3";
	private static String OPTION4 = "4";
	private static int ZERO = 0;
	private static final ArrayList<Integer> DOZVOLJENE_TEZINE = new ArrayList<Integer>();
	private static long tezina = 0;

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
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
			getTezinaVocaUPosudi();
		} else if (OPTION4.equals(opcija)) {
			System.out.format("Ukupna kolicina napravljenog soka= %.2fl \n", sokovnik.getCediljka().getKolicinaVoca());
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
		sokovnik.getPosudaZavoce().getVocke().forEach(vocka -> {
			System.out.format(vocka.getNaziv() + "= %f\n", vocka.getTezina());
			tezina = (long) (tezina + vocka.getTezina());
		});
		sokovnik.cedjenje(tezina);
		tezina = (long) ZERO;
		glavniMeni();
	}

	public static void getTezinaVocaUPosudi() throws IOException, prepunaKorpa {
		System.out.println("---------------------------------------------------------");
		sokovnik.getPosudaZavoce().getVocke().forEach(jabuka -> {
			System.out.format(jabuka.getNaziv() + "= %f \n", jabuka.getTezina());
			tezina = (long) (tezina + jabuka.getTezina());
		});
		System.out.println("---------------------------------------------------------");

		System.out.format("Sum= %d%nkg \n", tezina);
		tezina = ZERO;
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

		if (!BACKDUGME.equals(tezina)) {
			int tezinaUnos = ZERO;
			try {
				tezinaUnos = Integer.parseInt(tezina);
			} catch (NumberFormatException e) {
				System.out.println("Pogresna vrednost");
				dodavanje();
			}

			if (!DOZVOLJENE_TEZINE.contains(tezinaUnos)) {
				System.out.println("Pogresna vrednost");
				dodavanje();
			} else if (DOZVOLJENE_TEZINE.contains(tezinaUnos)) {
				Jabuka jabuka = new Jabuka(naziv, tezinaUnos);
				jabuka.setCrvljiva();
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