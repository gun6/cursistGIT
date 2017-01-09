package be.vdab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Soort;

public interface SoortRepository extends JpaRepository<Soort, Long> {

}
