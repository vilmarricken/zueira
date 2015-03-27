package com.master.zueira.client.zueira;

import java.awt.Toolkit;

public class ZueiraBeep implements Zueira {

	public static void main(final String[] args) {
		new ZueiraBeep().run(null);
	}

	@Override
	public void run(final String value) {
		final int values[] = new int[] { 2, 200 };
		final Toolkit tk = Toolkit.getDefaultToolkit();
		for (int i = 0; i < values[0]; i++) {
			tk.beep();
			try {
				Thread.sleep(values[1]);
			} catch (final InterruptedException e) {
			}
		}
	}
}
