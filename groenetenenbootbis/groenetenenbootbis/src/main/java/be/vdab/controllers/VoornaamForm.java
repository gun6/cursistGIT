package be.vdab.controllers;

import org.hibernate.validator.constraints.NotBlank;

class VoornaamForm {
	@NotBlank
	private String voornaam;

	public String getVoornaam() {
		return voornaam;
	}

	public void setVoornaam(String voornaam) {
		this.voornaam = voornaam;
	}
	
	
}
