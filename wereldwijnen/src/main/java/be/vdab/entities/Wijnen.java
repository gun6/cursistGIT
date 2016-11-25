package be.vdab.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@NamedEntityGraph(name = "Wijnen.metSoortenEnLanden",attributeNodes = @NamedAttributeNode(value = "soorten",subgraph = "metLanden"),subgraphs = @NamedSubgraph(name = "metLanden",attributeNodes = @NamedAttributeNode("landen")))
public class Wijnen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private byte beoordeling;

	private int inBestelling;

	private int jaar;

	private BigDecimal prijs;
	
	@Version
	private int versie;

	//bi-directional many-to-one association to Soorten
	@ManyToOne
	@JoinColumn(name="soortid")
	private Soorten soorten;

	public Wijnen() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte getBeoordeling() {
		return this.beoordeling;
	}

	public void setBeoordeling(byte beoordeling) {
		this.beoordeling = beoordeling;
	}

	public int getInBestelling() {
		return this.inBestelling;
	}

	public void setInBestelling(int inBestelling) {
		this.inBestelling = inBestelling;
	}

	public int getJaar() {
		return this.jaar;
	}

	public void setJaar(int jaar) {
		this.jaar = jaar;
	}

	public BigDecimal getPrijs() {
		return this.prijs;
	}

	public void setPrijs(BigDecimal prijs) {
		this.prijs = prijs;
	}

	public int getVersie() {
		return this.versie;
	}

	public void setVersie(int versie) {
		this.versie = versie;
	}

	public Soorten getSoorten() {
		return this.soorten;
	}

	public void setSoorten(Soorten soorten) {
		this.soorten = soorten;
	}

	@Override
	public String toString() {
		return "Wijn [jaar=" + jaar + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Wijnen))
			return false;
		Wijnen other = (Wijnen) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	
	
	

}