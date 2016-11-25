package be.vdab.services;

import java.util.List;

import be.vdab.entities.Landen;
import be.vdab.repositories.LandenRepository;

public class LandenService extends AbstractService {
	private final LandenRepository landenRepository = new LandenRepository();
	
	public List<Landen> findAll() {
		return landenRepository.findAll();
	}
	
	public Landen read(long id){
		return landenRepository.read(id);
	}
}
