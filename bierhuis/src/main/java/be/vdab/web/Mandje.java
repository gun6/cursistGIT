package be.vdab.web;

import java.util.List;

import be.vdab.valueobjects.Bestelbonlijn;

public interface Mandje {
	void addLijn(Bestelbonlijn bestelbonlijn);
	List<Bestelbonlijn> getMandje();
	void clear();
	
}
