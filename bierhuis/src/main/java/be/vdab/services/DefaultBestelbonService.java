package be.vdab.services;

import org.springframework.stereotype.Service;

import be.vdab.entities.Bestelbon;
import be.vdab.repositories.BestelbonRepository;

@Service
class DefaultBestelbonService implements BestelbonService {
	private final BestelbonRepository bestelbonRepository;

	public DefaultBestelbonService(BestelbonRepository bestelbonRepository) {
		this.bestelbonRepository = bestelbonRepository;
	}
	
	@Override
	public void create(Bestelbon bestelbon) {
		bestelbonRepository.save(bestelbon);
	}

}
