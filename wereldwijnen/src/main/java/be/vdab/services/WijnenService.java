package be.vdab.services;

import java.util.List;

import be.vdab.entities.Wijnen;
import be.vdab.repositories.WijnenRepository;

public class WijnenService {
	private final WijnenRepository wijnenRepository = new WijnenRepository();
	
	public List<Wijnen> findAllOfSoort(int soort){
		return wijnenRepository.findAllOfSoort(soort);
	}
	
	public Wijnen getWijnMetSoortEnLand(int id){
		return wijnenRepository.getWijnMetSoortEnLand(id);
	}

}
