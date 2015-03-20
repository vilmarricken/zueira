package com.master.zueira.gui.component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.master.zueira.controller.VictimController;
import com.master.zueira.object.Victim;

public class VictimsTableModel extends AbstractTableModel implements VictimController {

	/**
	 *
	 */
	private static final long serialVersionUID = 4489852377476460408L;
	private final List<Victim> victims = new ArrayList<Victim>();

	public VictimsTableModel() {
		super();
	}

	@Override
	public void addVictim(final String name, final String address, final int service) {
		this.victims.add(new Victim(name, address, service));
		this.fireTableDataChanged();
	}

	@Override
	public Class<?> getColumnClass(final int columnIndex) {
		return String.class;
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public String getColumnName(final int column) {
		switch (column) {
		case 0:
			return "Nome";
		case 1:
			return "IP";
		}
		return "Invalid column index: " + column;
	}

	@Override
	public int getRowCount() {
		return this.victims.size();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Victim victim = this.victims.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return victim.getName();
		case 1:
			return victim.getAddress();
		}
		return "Invalid column index: " + columnIndex;
	}

	@Override
	public void removeVictim(final String address) {
		final Iterator<Victim> it = this.victims.iterator();
		while (it.hasNext()) {
			if (it.next().getAddress().equals(address)) {
				it.remove();
			}
		}
		this.fireTableDataChanged();
	}

	@Override
	public void removeVictim(final Victim victim) {
		this.removeVictim(victim.getAddress());
	}

}
