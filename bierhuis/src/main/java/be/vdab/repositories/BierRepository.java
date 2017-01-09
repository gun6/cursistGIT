package be.vdab.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Bier;
import be.vdab.entities.Brouwer;

public interface BierRepository extends JpaRepository<Bier, Long> {
	List<Bier> findByBrouwerOrderByNaam(Brouwer brouwer);
}
