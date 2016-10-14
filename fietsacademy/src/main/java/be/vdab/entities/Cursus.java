package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
@Table (name = "cursussen")
@DiscriminatorColumn (name = "Soort")
public class Cursus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	private String naam;
	
	@Override
	public String toString(){
		return naam;
	}

}
