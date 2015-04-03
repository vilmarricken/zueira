package com.master.zueira.client.zueira;

import java.awt.BorderLayout;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class ZueiraMonster implements Zueira {

	public static void main(final String[] args) {
		new ZueiraMonster().run(null);
	}

	public ZueiraMonster() {
	}

	private URL getURLImage() {
		return this.getClass().getClassLoader().getResource("exorcista.jpg");
	}

	@Override
	public void run(final String value) {
		final JWindow w = new JWindow();
		final JLabel lbl = new JLabel(new ImageIcon(this.getURLImage()));
		w.getContentPane().add(lbl, BorderLayout.CENTER);
		w.pack();
		w.setLocationRelativeTo(null);
		w.setVisible(true);
		try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
		}
		w.setVisible(false);
		w.dispose();
	}

}
