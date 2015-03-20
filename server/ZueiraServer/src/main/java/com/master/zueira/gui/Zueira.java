package com.master.zueira.gui;

import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.master.zueira.controller.VictimControllerFactory;
import com.master.zueira.gui.component.VictimsTableModel;

public class Zueira extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 7025955466565433554L;

	private JTable victims;

	public Zueira() throws HeadlessException {
		super("Zueira");
	}

	public JTable getVictims() {
		if (this.victims == null) {
			this.victims = new JTable(this.getVictimsModel());
		}
		return this.victims;
	}

	private TableModel getVictimsModel() {
		final VictimsTableModel model = new VictimsTableModel();
		VictimControllerFactory.getInstance().setController(model);
		return model;
	}

}
