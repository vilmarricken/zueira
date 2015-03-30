package com.master.zueira.controller;

import java.util.Iterator;
import java.util.Vector;

import com.master.zueira.object.Victim;

public class VictimControllerImpl implements VictimController {

	private final Vector<Victim> victims = new Vector<Victim>();

	private int selected = -1;

	public VictimControllerImpl() {
	}

	public void setSelected(int selected) {
		this.selected = selected;
	}

	@Override
	public void addVictim(final String name, final String address, final int service) {
		this.victims.add(new Victim(name, address, service));
	}

	@Override
	public void removeVictim(final String address) {
		final Iterator<Victim> it = this.victims.iterator();
		while (it.hasNext()) {
			if (it.next().getAddress().equals(address)) {
				it.remove();
			}
		}
	}

	@Override
	public int count() {
		return this.victims.size();
	}

	@Override
	public void removeVictim(final Victim victim) {
		this.removeVictim(victim.getAddress());
	}

	@Override
	public void zuar(String zueira) {
		
	}

	@Override
	public Victim getVictim(int index) {
		return this.victims.get(index);
	}

}
