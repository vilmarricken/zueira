package com.master.zueira.controller;

import com.master.zueira.object.Victim;

public interface VictimController {

	void addVictim(String name, String address, int service);

	void removeVictim(String address);

	void removeVictim(Victim victim);

	void zuar(String zueira);

	Victim getVictim(int index);

	int count();

	void setSelected(int selectedRow);

}
