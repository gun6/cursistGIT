package be.vdab.entities;

import be.vdab.entities.Cursus;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


@Entity
@DiscriminatorValue ("G")
public class GroepsCursus extends Cursus implements Serializable {

	
	private static final long serialVersionUID = 1L;
	@Temporal (TemporalType.DATE)
	private Date van;
	@Temporal (TemporalType.DATE)
	private Date tot;
	
	
}
