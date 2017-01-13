package be.vdab.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import be.vdab.valueobjects.Bestelbonlijn;

@Component
@SessionScope
class MandjeImpl implements Serializable, Mandje {
	private static final long serialVersionUID = 1L;
	private List<Bestelbonlijn> lijnen;

	@Override
	public void addLijn(Bestelbonlijn bestelbonlijn) {
		if (lijnen == null) {
			lijnen = new ArrayList<>();
		}
		lijnen.add(bestelbonlijn);

	}

	@Override
	public List<Bestelbonlijn> getMandje() {
		return lijnen;
	}

	@Override
	public void clear() {
		lijnen.clear();
	}

	@Override
	public void removeLijn(Bestelbonlijn bestelbonlijn) {
		lijnen.remove(bestelbonlijn);
	}

	@Override
	public boolean isEmpty() {
		if (lijnen == null) {
			return true;
		}
		return false;
	}

}
