package be.vdab.valueobjects;

public class ArtikelLijstObject {
	private static final long serialVersionUID = 1L;
	private String naam;
	private String artikelgroepNaam;
	
	public ArtikelLijstObject(String naam, String artikelgroepNaam) {
		this.naam = naam;
		this.artikelgroepNaam = artikelgroepNaam;
	}

	public String getNaam() {
		return naam;
	}

	public String getArtikelgroepNaam() {
		return artikelgroepNaam;
	}
	
	
	
	
}
