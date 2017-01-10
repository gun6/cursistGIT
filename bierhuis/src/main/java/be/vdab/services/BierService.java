package be.vdab.services;

import java.util.List;

import be.vdab.entities.Bier;
import be.vdab.entities.Brouwer;

public interface BierService {
	List<Bier> findByBrouwerOrderByName(Brouwer brouwer);
	long findAantalBieren();
	Bier findByNaam(String naam);
}
