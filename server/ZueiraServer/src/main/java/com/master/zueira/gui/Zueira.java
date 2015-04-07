package com.master.zueira.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

	private final String[] labels;

	private final JTextField[] txValues;

	private final String zueira;

	public Zueira(final String zueira, final String label, final String[] labels) {
		this.zueira = zueira;
		this.label = label;
		this.labels = labels;
		this.txValues = new JTextField[labels.length];
		this.setLayout(new GridBagLayout());
		this.build();
		this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
	}

	private void build() {
		final GridBagConstraints c = new GridBagConstraints();
		c.weightx = 0.5;
		c.weighty = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		for (int i = 0; i < this.labels.length; i++) {
			this.txValues[i] = new JTextField(6);
			c.anchor = GridBagConstraints.EAST;
			this.add(new JLabel(this.labels[i]), c);
			c.gridx = 1;
			c.anchor = GridBagConstraints.WEST;
			this.add(this.txValues[i], c);
			c.gridx = 0;
			c.gridy++;
		}
		c.anchor = GridBagConstraints.CENTER;
		c.gridwidth = 2;
		c.gridx = 0;
		c.fill = GridBagConstraints.BOTH;
		this.add(this.getBtZueira(), c);
	}

	private Action getActionZueira() {
		return new AbstractAction(this.label) {

			private static final long serialVersionUID = -3910212417931616053L;

			@Override
			public void actionPerformed(final ActionEvent e) {
				final String values[] = new String[Zueira.this.txValues.length];
				for (int i = 0; i < Zueira.this.txValues.length; i++) {
					values[i] = Zueira.this.txValues[i].getText();
					if (values[i].trim().length() == 0) {
						JOptionPane.showMessageDialog(ZueiraMain.getInstance(), "Informe o valor para o campo: " + Zueira.this.labels[i], "Zueiraaaaaa", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				VictimControllerFactory.getInstance().zuar(Zueira.this.zueira, Zueira.this.labels, values);
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

}
