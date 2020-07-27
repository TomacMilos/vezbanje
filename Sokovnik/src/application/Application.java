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
import model.Voce;

public class Application {

	public static Sokovnik sokovnik = Sokovnik.getInstance();
	public static final String OPTION1 = "1";
	public static String BACKDUGME = "x";
	public static String OPTION2 = "2";
	public static String OPTION3 = "3";
	public static String OPTION4 = "4";
	public static int ZERO = 0;
	public static final ArrayList<Integer> DOZVOLJENE_TEZINE = new ArrayList<Integer>();
	public static Long tezina = 0L;

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
		return reader.readLine();
	}

	public static void glavniMeni() throws IOException, prepunaKorpa {
		printMain();
		String opcija = getOpcija();
		if (OPTION1.equals(opcija)) {
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
			System.out.format("Ukupna kolicina napravljenog soka= %.2fl \n", sokovnik.getCediljka().getKolicinaSoka());
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
		ispisiCrtice();
	}

	public static void cedjenje() throws IOException, prepunaKorpa {
		sokovnik.getPosudaZavoce().getVocke().forEach(vocka -> {
			System.out.format(vocka.getNaziv() + "= %f\n", vocka.getTezina());
			tezina = (long) (tezina + vocka.getTezina());
		});
		sokovnik.cedjenje(tezina);
		restartovanjeTezina();
		glavniMeni();
	}

	public static void getTezinaVocaUPosudi() throws IOException, prepunaKorpa {
		ispisiCrtice();
		sokovnik.getPosudaZavoce().getVocke().forEach(jabuka -> {
			System.out.format(jabuka.getNaziv() + "= %f \n", jabuka.getTezina());
			tezina = (long) (tezina + jabuka.getTezina()); // Skontati long
		});
		ispisiCrtice();

		System.out.format("Sum= %d%nkg \n", tezina);
		restartovanjeTezina();
		glavniMeni();
	}

	private static void restartovanjeTezina() {
		tezina = 0L;
	}

	private static void ispisiCrtice() {
		System.out.println("--------------------------------");
	}

	private static String ucitajNaziv() throws IOException {
		System.out.println("Uneti naziv jabuke");
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		return reader1.readLine();
	}

	private static String ucitajTezinu() throws IOException {
		System.out.println("Uneti tezinu jabuke (tezina moze biti u rasponu 1-3)");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		return reader.readLine();
	}

	public static void dodavanje() throws IOException, prepunaKorpa {
		printeviDodavanje();
		String naziv = ucitajNaziv();
		String unosZaTezinu = ucitajTezinu();

		if (!pritisnutX(unosZaTezinu)) {
			int tezinaUnos = parsiranjeUnosaZaTezinu(unosZaTezinu);

			if (!DOZVOLJENE_TEZINE.contains(tezinaUnos)) {
				System.out.println("Pogresna vrednost");
				dodavanje();
			} else {
				Jabuka jabuka = new Jabuka(naziv, tezinaUnos); // obrnuto
				jabuka.setCrvljiva();
				sokovnik.dodavanjeVocke(jabuka);
				glavniMeni();
			}
		} else if (pritisnutX(unosZaTezinu)) {
			glavniMeni();
		} else {
			System.out.println("pogresna vrednost!");
			dodavanje();
		}
	}

	private static boolean pritisnutX(String unosZaTezinu) {
		return BACKDUGME.equals(unosZaTezinu);
	}

	private static int parsiranjeUnosaZaTezinu(String unosZaTezinu) throws IOException, prepunaKorpa {
		int tezinaUnos = ZERO;
		try {
			tezinaUnos = Integer.parseInt(unosZaTezinu);
		} catch (NumberFormatException e) {
			System.out.println("Pogresna vrednost");
			dodavanje();
		}
		return tezinaUnos;
	}

	public static void printeviDodavanje() {
		System.out.println("--------------------------");
		System.out.println("x) Za povratak u glavni meni ");
		System.out.println("   Dodavanje jabuke u korpu");

	}
}