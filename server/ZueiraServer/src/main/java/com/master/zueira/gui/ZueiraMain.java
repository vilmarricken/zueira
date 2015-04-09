package com.master.zueira.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
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

	private static ZueiraMain INSTANCE;

	/**
	 *
	 */
	private static final long serialVersionUID = 7025955466565433554L;

	public static ZueiraMain getInstance() {
		return ZueiraMain.INSTANCE;
	}

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
		ZueiraMain.INSTANCE = this;
		final JPanel p = new JPanel(new BorderLayout());
		final JScrollPane ps = new JScrollPane(this.getVictims());
		p.add(ps, BorderLayout.CENTER);
		p.add(this.getButtonsPane(), BorderLayout.SOUTH);
		this.setContentPane(p);
	}

	private Component getButtonsPane() {
		final JPanel buttons = new JPanel(new GridLayout(3, 2));
		buttons.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		buttons.add(new Zueira("1", "Mouse", new String[] { "Repetições", "Intervalos" }));
		buttons.add(new Zueira("2", "Beep", new String[] { "Repetições", "Intervalos" }));
		buttons.add(new Zueira("4", "Frozen", new String[] { "Intervalo" }));
		buttons.add(new Zueira("5", "Alt + Tab", new String[] { "Repetiçoes" }));
		buttons.add(new Zueira("6", "Ctrl + Alt + DOWN", new String[] {}));
		buttons.add(new Zueira("3", "Monster", new String[] {}));
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
