package be.vdab.services;

import java.util.List;

import org.springframework.stereotype.Service;

import be.vdab.entities.Bier;
import be.vdab.entities.Brouwer;
import be.vdab.repositories.BierRepository;

@Service
class DefaultBierService implements BierService {
	private final BierRepository bierRepository;

	public DefaultBierService(BierRepository bierRepository) {
		this.bierRepository = bierRepository;
	}

	@Override
	public List<Bier> findByBrouwerOrderByName(Brouwer brouwer) {
		return bierRepository.findByBrouwerOrderByNaam(brouwer);
	}

	@Override
	public long findAantalBieren() {
		return bierRepository.count();
	}

	@Override
	public Bier findByNaam(String naam) {
		return bierRepository.findByNaam(naam);
	}

	@Override
	public Bier read(long id) {
		return bierRepository.findOne(id);
	}

}
