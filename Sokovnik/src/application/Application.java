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
import util.TezinaUtil;

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
			getVoceUKorpi();
		} else if (OPTION4.equals(opcija)) {
			System.out.format("Ukupna kolicina napravljenog soka= %fl \n", sokovnik.getCediljka().getKolicinaVoca());
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
		final TezinaUtil fw = new TezinaUtil();
		sokovnik.getPosudaZavoce().getVocke().forEach(vocka -> {
			System.out.format(vocka.getNaziv() + "= %f", vocka.getTezina());
			fw.setValue(fw.value + vocka.getTezina());
		});
		sokovnik.cedjenje(fw.getValue());
		fw.setValue(0);
		glavniMeni();
	}

	public static void getVoceUKorpi() throws IOException, prepunaKorpa {
		System.out.println("---------------------------------------------------------");
		final TezinaUtil fw = new TezinaUtil();
		sokovnik.getPosudaZavoce().getVocke().forEach(jabuka -> {
			System.out.format(jabuka.getNaziv() + "= %f \n", jabuka.getTezina());
			fw.setValue(fw.value + jabuka.getTezina());
		});
		System.out.println("---------------------------------------------------------");
		System.out.format("Sum= %fkg \n", fw.getValue());
		fw.setValue(0);
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
			int tezina1 = 0;
			boolean izbor;
			try {  
			    tezina1=Integer.parseInt(tezina);  
			    izbor= true;
			  } catch(NumberFormatException e){  
			    izbor= false;  
			  }
			
			if (!DOZVOLJENE_TEZINE.contains(tezina1)||izbor==false) {
				System.out.println("Pogresna vrednost");
				dodavanje();
			} else if (DOZVOLJENE_TEZINE.contains(tezina1)) {
				Jabuka jabuka = new Jabuka(naziv, tezina1);
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