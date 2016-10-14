package be.vdab.services;

import java.util.List;

import be.vdab.entities.Campus;
import be.vdab.repositories.CampusRespository;

public class CampusService extends AbstractService {
	private final CampusRespository campusRespository = new CampusRespository();
	
	public List<Campus> findByGemeente(String gemeente){
		return campusRespository.findByGemeente(gemeente);
	}
	
	public List<Campus> findAll() {
		return campusRespository.findAll();
	}
	
	public Campus read(long id) {
		return campusRespository.read(id);
	}
}
