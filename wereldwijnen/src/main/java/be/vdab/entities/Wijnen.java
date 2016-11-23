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
	private int id;

	private byte beoordeling;

	private int inBestelling;

	private int jaar;

	private BigDecimal prijs;

	private int versie;

	//bi-directional many-to-one association to Soorten
	@ManyToOne
	@JoinColumn(name="soortid")
	private Soorten soorten;

	public Wijnen() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

}