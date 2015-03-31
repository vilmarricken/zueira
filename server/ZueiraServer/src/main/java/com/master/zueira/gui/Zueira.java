package com.master.zueira.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.master.zueira.controller.VictimControllerFactory;

public class Zueira extends JPanel {

	/**
	 *
	 */
	private static final long serialVersionUID = 3689270434238404237L;

	private JButton btZueira;

	private final String label;

	private JTextField txValues;

	private final String zueira;

	public Zueira(final String zueira, final String label) {
		this.zueira = zueira;
		this.label = label;
		this.setLayout(new GridLayout(2, 1));
		this.add(this.getBtZueira());
		this.add(this.getTxValues());
	}

	private Action getActionZueira() {
		return new AbstractAction(this.label) {

			/**
			 *
			 */
			private static final long serialVersionUID = -3910212417931616053L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				VictimControllerFactory.getInstance().zuar(Zueira.this.zueira, Zueira.this.getTxValues().getText());
			}
		};
	}

	public JButton getBtZueira() {
		if (this.btZueira == null) {
			this.btZueira = new JButton();
			// this.btZueira.setSize(30, 30);
			this.btZueira.setAction(this.getActionZueira());
		}
		return this.btZueira;
	}

	public JTextField getTxValues() {
		if (this.txValues == null) {
			this.txValues = new JTextField(20);
		}
		return this.txValues;
	}

}
