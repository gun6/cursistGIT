package be.vdab.services;

import java.util.List;

import org.springframework.stereotype.Service;

import be.vdab.entities.Werknemer;
import be.vdab.repositories.WerknemerRepository;

@Service
class DefaultWerknemerService implements WerknemerService {
	private final WerknemerRepository werknemerRepository;
	
	public DefaultWerknemerService(WerknemerRepository werknemerRepository) {
		this.werknemerRepository = werknemerRepository;
		}

	@Override
	public List<Werknemer> findByVoornaam(String voornaam) {
		return werknemerRepository.findByVoornaam(voornaam);
	}

}
