package services;

import exeptionHandling.prepunaKorpa;
import model.Jabuka;
import model.Voce;

public interface SokovnikInterface {
	public void dodavanjeVocke(Jabuka jabuka) throws prepunaKorpa;
	public void cedjenje(float voce);
}