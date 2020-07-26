package exeptionHandling;

public class prepunaKorpa extends Exception {

	//ova klasa treba da se zove kao specifican error koji treba da bacis
	//npr prepunaKorpa.. a ne exceptionHandler, mora biti nesto specificnije
	public prepunaKorpa(String message) {
		System.out.println("Prepuna korpa program se prekida");
	}
}
