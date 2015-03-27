package com.master.zueira.client.zueira;

public class ZueiraFactory {

	public static Zueira getZueira(final String codigo) {
		if (codigo == null) {
			return new ZueiraBeep();
		}
		if (codigo.equals("1")) {

		}
		return new ZueiraBeep();
	}

}
