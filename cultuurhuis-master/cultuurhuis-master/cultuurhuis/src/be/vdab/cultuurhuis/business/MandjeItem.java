package be.vdab.cultuurhuis.business;

import java.io.Serializable;

public class MandjeItem implements Serializable
{
	private static final long serialVersionUID = 1L;
	private Voorstelling voorstelling;
	private int plaatsen;
	
	public MandjeItem(Voorstelling voorstelling, int plaatsen) 
	{
		setVoorstelling(voorstelling);
		setPlaatsen(plaatsen);
	}
	
	public Voorstelling getVoorstelling() 
	{
		return voorstelling;
	}
	
	public void setVoorstelling(Voorstelling voorstelling) 
	{
		this.voorstelling = voorstelling;
	}
	
	public int getPlaatsen() 
	{
		return plaatsen;
	}
	
	public void setPlaatsen(int plaatsen) 
	{
		this.plaatsen = plaatsen;
	}
}
