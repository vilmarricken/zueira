package com.master.zueira.client.zueira;

import java.awt.Toolkit;

public class ZueiraDefault implements Zueira {

	public static void main(final String[] args) {
		new ZueiraDefault().run(null);
	}

	@Override
	public void run(final String value) {
		int vezes = 2;
		int intervalo = 200;
		if (value != null) {
			final String values[] = value.split(" ");
			if (values.length == 2) {
				try {
					vezes = Integer.valueOf(values[0].trim());
					intervalo = Integer.valueOf(values[1].trim());
				} catch (final Exception e) {
				}
			}
		}
		for (int i = 0; i < vezes; i++) {
			Toolkit.getDefaultToolkit().beep();
			try {
				Thread.sleep(intervalo);
			} catch (final InterruptedException e) {
			}
		}
	}
}
