package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;


@Entity
@Table(name = "managers")
public class Manager implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String voornaam;
	private String familienaam;
	@OneToOne(fetch = FetchType.LAZY,mappedBy = "manager")
	private Campus campus;

	public Manager() {
	}

	public long getId() {
		return id;
	}

	public String getVoornaam() {
		return voornaam;
	}

	public String getFamilienaam() {
		return familienaam;
	}

	public Campus getCampus() {
		return campus;
	}
	
	
   
}
