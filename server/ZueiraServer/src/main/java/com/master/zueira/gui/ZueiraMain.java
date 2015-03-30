package com.master.zueira.gui;

import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;

import com.master.zueira.controller.VictimControllerFactory;
import com.master.zueira.gui.component.VictimsTableModel;

public class ZueiraMain extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 7025955466565433554L;

	private JTable victims;

	public ZueiraMain() throws HeadlessException {
		super("Zueira");
		JPanel p = new JPanel(new GridLayout(1, 2));
		JScrollPane ps = new JScrollPane(getVictims());
		p.add(ps);
		
		this.setContentPane(p);
	}

	public JTable getVictims() {
		if (this.victims == null) {
			this.victims = new JTable(this.getVictimsModel());
			this.victims.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent event) {
					VictimControllerFactory.getInstance().getController().setSelected(victims.getSelectedRow());
				}
			});
		}
		return this.victims;
	}

	private TableModel getVictimsModel() {
		final VictimsTableModel model = new VictimsTableModel();
		return model;
	}

	public static void main(String[] args) {
		ZueiraMain main = new ZueiraMain();
		main.pack();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
	}

}
