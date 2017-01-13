package be.vdab.valueobjects;

import javax.validation.constraints.Min;

public class AantalForm {
	@Min(1)
	private int aantal;
	private long bierId;
	
	public int getAantal() {
		return aantal;
	}
	
	public void setAantal(int aantal) {
		this.aantal = aantal;
	}
	
	public long getBierId() {
		return bierId;
	}
	
	public void setBierId(long bierId) {
		this.bierId = bierId;
	}
	
	
}
