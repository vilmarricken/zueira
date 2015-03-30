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

	private JTextField txValues;

	private String zueira;

	private String label;

	public Zueira(String zueira, String label) {
		this.zueira = zueira;
		this.label = label;
		this.setLayout(new GridLayout(1, 2));
		this.add(getBtZueira());
		this.add(getTxValues());
	}

	public JButton getBtZueira() {
		if (this.btZueira == null) {
			this.btZueira = new JButton();
			this.btZueira.setSize(100, 100);
			this.btZueira.setAction(getActionZueira());
		}
		return btZueira;
	}

	private Action getActionZueira() {
		return new AbstractAction(this.label) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3910212417931616053L;

			@Override
			public void actionPerformed(ActionEvent e) {
				VictimControllerFactory.getInstance().zuar(Zueira.this.zueira);
			}
		};
	}

	public JTextField getTxValues() {
		return txValues;
	}

}
