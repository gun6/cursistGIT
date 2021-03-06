package be.vdab.repositories;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertNotEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import be.vdab.datasource.TestDataSourceConfig;
import be.vdab.entities.Filiaal;
import be.vdab.valueobjects.Adres;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDataSourceConfig.class,TestRepositoriesConfig.class})
@Transactional
public class JpaFiliaalRepositoryTest {
	@Autowired
	public FiliaalRepository filiaalRepository;

	@Test
	public void create() {
		Filiaal filiaal = new Filiaal("TestNaam", true, BigDecimal.ONE,LocalDate.now(), new Adres("Straat", "HuisNr", 1000, "Gemeente"));
		filiaalRepository.save(filiaal);
		assertNotEquals(0, filiaal.getId());
	}

}
