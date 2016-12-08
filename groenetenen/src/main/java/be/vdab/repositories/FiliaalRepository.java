package be.vdab.repositories;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Filiaal;

public interface FiliaalRepository {
	void create(Filiaal filiaal);
	Optional<Filiaal> read(Long id);
	void update(Filiaal filiaal);
	void delete(long id);
	List<Filiaal> findAll();
	long findAantalFilialen();
	long findAantalWerknemers(long id);
}
