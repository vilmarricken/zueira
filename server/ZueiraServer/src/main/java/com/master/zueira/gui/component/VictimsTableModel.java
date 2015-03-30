package com.master.zueira.gui.component;

import javax.swing.table.AbstractTableModel;

import com.master.zueira.controller.VictimControllerFactory;
import com.master.zueira.object.Victim;

public class VictimsTableModel extends AbstractTableModel {

	/**
	 *
	 */
	private static final long serialVersionUID = 4489852377476460408L;

	public VictimsTableModel() {
		super();
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
		return VictimControllerFactory.getInstance().getController().count();
	}

	@Override
	public Object getValueAt(final int rowIndex, final int columnIndex) {
		final Victim victim = VictimControllerFactory.getInstance().getController().getVictim(rowIndex);
		switch (columnIndex) {
		case 0:
			return victim.getName();
		case 1:
			return victim.getAddress();
		}
		return "Invalid column index: " + columnIndex;
	}

}
