package be.vdab.entities;

import be.vdab.entities.Cursus;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@DiscriminatorValue ("I")
public class IndividueleCursus extends Cursus implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private int duurtijd;
   
}
