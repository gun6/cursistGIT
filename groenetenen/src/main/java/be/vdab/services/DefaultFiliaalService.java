package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;

import be.vdab.entities.Filiaal;
import be.vdab.exceptions.FiliaalHeeftNogWerknemersException;
import be.vdab.mail.MailSender;
import be.vdab.repositories.FiliaalRepository;
import be.vdab.valueobjects.PostcodeReeks;

@ReadOnlyTransactionalService
class DefaultFiliaalService implements FiliaalService {
	private final FiliaalRepository filiaalRepository;
	private final MailSender mailSender;
	
	DefaultFiliaalService(FiliaalRepository filiaalRepository,MailSender mailSender) { 
		this.filiaalRepository = filiaalRepository;
		this.mailSender = mailSender;
		}
	
	@ModifyingTransactionalServiceMethod
	@Override
	public void create(Filiaal filiaal, String urlAlleFilialen) {
		filiaalRepository.save(filiaal);
		mailSender.nieuwFiliaalMail(filiaal,urlAlleFilialen + '/' + filiaal.getId());
	}

	@Override
	public Optional<Filiaal> read(long id) {
		return Optional.ofNullable(filiaalRepository.findOne(id));
	}

	@ModifyingTransactionalServiceMethod
	@Override
	public void update(Filiaal filiaal) {
		filiaalRepository.save(filiaal);

	}
	
	@ModifyingTransactionalServiceMethod
	@Override
	public void delete(long id) {
		Optional<Filiaal> optionalFiliaal = Optional.ofNullable(filiaalRepository.findOne(id));
		if (optionalFiliaal.isPresent()) {
			if ( ! optionalFiliaal.get().getWerknemers().isEmpty()) {
				throw new FiliaalHeeftNogWerknemersException();
			}
		filiaalRepository.delete(id);
		}
	}

	@Override
	public List<Filiaal> findAll() {
		return filiaalRepository.findAll(new Sort("naam"));
	}

	@Override
	public long findAantalFilialen() {
		return filiaalRepository.count();
	}
	
	@Override
	@PreAuthorize("hasAuthority('manager')")
	public List<Filiaal> findByPostcodeReeks(PostcodeReeks reeks) {
	return filiaalRepository.findByAdresPostcodeBetweenOrderByNaam(reeks.getVanpostcode(),reeks.getTotpostcode());
	}
	
	@Override
	public List<Filiaal> findNietAfgeschreven() {
	return filiaalRepository.findByWaardeGebouwNot(BigDecimal.ZERO);
	}
	
	@Override
	@ModifyingTransactionalServiceMethod
	public void afschrijven(List<Filiaal> filialen) {
		filialen.stream().forEach(filiaal -> filiaal.afschrijven());
	}

	@Override
	@Scheduled(cron = "0 0 1 * * *" /*fixedRate=60000*/)
	public void aantalFilialenMail() {
		mailSender.aantalFilialenMail(filiaalRepository.count());
		
	}
}
