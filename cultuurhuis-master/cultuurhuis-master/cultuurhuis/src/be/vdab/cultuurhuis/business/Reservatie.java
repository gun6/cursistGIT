package be.vdab.cultuurhuis.business;

import java.io.Serializable;

public class Reservatie implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int reservatieId;
	private int klantId;
	private int voorstellingsId;
	private int plaaten;
	
	public Reservatie(int klantId, int voorstellingsId, int plaatsen)
	{
		setKlantId(klantId);
		setVoorstellingsId(voorstellingsId);
		setPlaaten(plaatsen);
	}
	
	public Reservatie(int reservatieId, int klantId, int voorstellingsId, int plaatsen)
	{
		setReservatieId(reservatieId);
		setKlantId(klantId);
		setVoorstellingsId(voorstellingsId);
		setPlaaten(plaatsen);
	}

	public int getReservatieId() {
		return reservatieId;
	}

	public void setReservatieId(int reservatieId) {
		this.reservatieId = reservatieId;
	}

	public int getKlantId() {
		return klantId;
	}

	public void setKlantId(int klantId) {
		this.klantId = klantId;
	}

	public int getVoorstellingsId() {
		return voorstellingsId;
	}

	public void setVoorstellingsId(int voorstellingsId) {
		this.voorstellingsId = voorstellingsId;
	}

	public int getPlaaten() {
		return plaaten;
	}

	public void setPlaaten(int plaaten) {
		this.plaaten = plaaten;
	}
}
