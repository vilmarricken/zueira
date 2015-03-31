package com.master.zueira.client.zueira;

import java.awt.Toolkit;

public class ZueiraBeep extends ZueiraAbstract {

	public static void main(final String[] args) {
		new ZueiraBeep().run(null);
	}

	@Override
	public void run(final String value) {
		int defs[] = new int[] { 2, 200 };
		defs = this.parse(value, defs);
		final Toolkit tk = Toolkit.getDefaultToolkit();
		for (int i = 0; i < defs[0]; i++) {
			tk.beep();
			try {
				Thread.sleep(defs[1]);
			} catch (final InterruptedException e) {
			}
		}
	}
}
