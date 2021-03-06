package be.vdab.services;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import be.vdab.entities.Brouwer;
import be.vdab.repositories.BrouwerRepository;

@Service
class DefaultBrouwerService implements BrouwerService {
	private final BrouwerRepository brouwerRepository;


	public DefaultBrouwerService(BrouwerRepository brouwerRepository) {
		this.brouwerRepository = brouwerRepository;
	}

	@Override
	public List<Brouwer> findAll() {
		return brouwerRepository.findAll(new Sort("naam"));
	}

	@Override
	public Brouwer findByNaam(String naam) {
		return brouwerRepository.findByNaam(naam);
	}

}
