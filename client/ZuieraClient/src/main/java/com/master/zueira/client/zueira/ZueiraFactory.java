package com.master.zueira.client.zueira;

public class ZueiraFactory {

	public static Zueira getZueira(final String codigo) {
		if (codigo.equals("1")) {

		}
		return new ZueiraDefault();
	}

}
