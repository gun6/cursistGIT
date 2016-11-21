package be.vdab.services;

import java.util.List;

import be.vdab.entities.Soorten;
import be.vdab.repositories.SoortenRepository;

public class SoortenService extends AbstractService {
	private final SoortenRepository soortenRepository = new SoortenRepository();
	
	public List<Soorten> findAllOfLand(int land){
		return soortenRepository.findAllOfLand(land);
	}
	
	public Soorten read(int id){
		return soortenRepository.read(id);
	}

}
