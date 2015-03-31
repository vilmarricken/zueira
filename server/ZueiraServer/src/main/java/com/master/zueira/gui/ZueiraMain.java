package com.master.zueira.gui;

import java.awt.BorderLayout;
import java.awt.Component;
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
import com.master.zueira.server.ZueiraDiscover;

public class ZueiraMain extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 7025955466565433554L;

	public static void main(final String[] args) {
		final ZueiraMain main = new ZueiraMain();
		main.pack();
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setVisible(true);
		ZueiraDiscover.getInstance();
	}

	private JTable victims;

	public ZueiraMain() throws HeadlessException {
		super("Zueira");
		final JPanel p = new JPanel(new BorderLayout());
		final JScrollPane ps = new JScrollPane(this.getVictims());
		p.add(ps, BorderLayout.CENTER);
		p.add(this.getButtonsPane(), BorderLayout.SOUTH);
		this.setContentPane(p);
	}

	private Component getButtonsPane() {
		final JPanel buttons = new JPanel(new GridLayout(2, 2));
		buttons.add(new Zueira("1", "Mouse"));
		buttons.add(new Zueira("2", "Beep"));
		return buttons;
	}

	public JTable getVictims() {
		if (this.victims == null) {
			this.victims = new JTable(this.getVictimsModel());
			this.victims.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				@Override
				public void valueChanged(final ListSelectionEvent event) {
					VictimControllerFactory.getInstance().getController().setSelected(ZueiraMain.this.victims.getSelectedRow());
				}
			});
		}
		return this.victims;
	}

	private TableModel getVictimsModel() {
		final VictimsTableModel model = new VictimsTableModel();
		VictimControllerFactory.getInstance().getController().setListener(model);
		return model;
	}

}
