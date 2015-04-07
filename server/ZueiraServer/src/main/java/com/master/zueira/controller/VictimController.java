package com.master.zueira.controller;

import com.master.zueira.object.Victim;

public interface VictimController {

	void addVictim(String name, String address, int service);

	int count();

	Victim getSelectedVictim();

	Victim getVictim(int index);

	void removeVictim(String address);

	void removeVictim(Victim victim);

	void setListener(final VictimControllerListener listener);

	void setSelected(int selectedRow);

	void zuar(String zueira, final String[] labels, String[] values);

}
