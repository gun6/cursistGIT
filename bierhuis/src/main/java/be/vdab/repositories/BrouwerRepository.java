package be.vdab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Brouwer;

public interface BrouwerRepository extends JpaRepository<Brouwer, Long> {
	Brouwer findByNaam(String naam);
}
