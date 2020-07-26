package services;

import exeptionHandling.prepunaKorpa;
import model.Jabuka;
import model.Voce;

public interface SokovnikInterface {
	public void dodavanjeVoca(Jabuka jabuka) throws prepunaKorpa; //Jabuka dv ? -> Jabuka jabuka
	public void cedjenje(float voce); //todo //izbrisi razmak

}
