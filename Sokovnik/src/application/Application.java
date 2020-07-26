package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.util.Random;

import exeptionHandling.exeptionHandler;
import model.Jabuka;
import model.PosudaZaVoce;
import model.Sokovnik;

public class Application {
	public static Sokovnik sokovnik = Sokovnik.getInstance();

	public static void main(String[] args) throws IOException, exeptionHandler {

		glavniMeni();

	}

	public static void glavniMeni() throws IOException, exeptionHandler {
		System.out.println("Dobrodosli u sokovnik");
		System.out.println("Izaberite opciju :");
		System.out.println("1) Dodavanje voca");
		System.out.println("2) Cedjenje");
		System.out.println("3) Kolicina voca u posudi");
		System.out.println("4) Kolicina napravljenog soka");
		System.out.println("--------------------------");

		// Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		// Reading data using readLine
		String name = reader.readLine();

		if (name.equals("1")) {
			try {
				dodavanje();
			} catch (exeptionHandler e) {
				System.out.println(e);
			}

		} else if (name.equals("2")) {
			float tezina = 0;
			for (Jabuka jabuka : sokovnik.getPosudaZavoce().getVocke()) {
				System.out.println(jabuka.getNaziv() + "=" + jabuka.getTezina());

				tezina = tezina + jabuka.getTezina();
			}

			sokovnik.cedjenje(tezina);
			glavniMeni();
		} else if (name.equals("3")) {
			float tezina = 0;
			System.out.println("---------------------------------------------------------");
			for (Jabuka jabuka : sokovnik.getPosudaZavoce().getVocke()) {
				System.out.println(jabuka.getNaziv() + "=" + jabuka.getTezina());

				tezina = tezina + jabuka.getTezina();
			}
			System.out.println("---------------------------------------------------------");
			System.out.println("Sum= " + tezina + "kg");
			glavniMeni();
		} else if (name.equals("4")) {
			System.out.println("Ukupna kolicina napravljenog soka = "
					+ String.valueOf(sokovnik.getCediljka().getKolicinaVoca()) + "l");
			glavniMeni();

		} else {
			System.out.println("Pogresna opcija!!!!!!!!!!");
			glavniMeni();
		}
	}

	public static void dodavanje() throws IOException, exeptionHandler {
		System.out.println("--------------------------");
		System.out.println("x) Za povratak u glavni meni ");
		System.out.println("Dodavanje jabuke u korpu");
		System.out.println("Uneti tezinu jabuke (tezina moze biti u rasponu 1-3)");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String tezina = reader.readLine();
		System.out.println("Uneti naziv jabuke");
		BufferedReader reader1 = new BufferedReader(new InputStreamReader(System.in));
		String naziv = reader1.readLine();

		boolean crvljiva;
		Random rand = new Random();
		if ((rand.nextInt((100 - 0 + 1) + 0) <= 20)) {
			crvljiva = true;
		} else {
			crvljiva = false;
		}
		if (tezina != "x") {
			int tezina1 = Integer.valueOf(tezina);
			if (tezina1 == 1 || tezina1 == 2 || tezina1 == 3) {
				Jabuka jabuka = new Jabuka(naziv, tezina1, crvljiva);
				sokovnik.dodavanjeVoca(jabuka);
				glavniMeni();
			}
		} else if (tezina == "x") {
			glavniMeni();
		} else {

			System.out.println("pogresna vrednost!!!!!!!!!!!!!!!!!!!!!!!!!");
			dodavanje();
		}
	}
}
