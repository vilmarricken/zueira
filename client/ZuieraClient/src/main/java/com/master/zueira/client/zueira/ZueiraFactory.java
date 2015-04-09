package com.master.zueira.client.zueira;

public class ZueiraFactory {

	public static Zueira getZueira(final String codigo) {
		if (codigo == null) {
			return new ZueiraBeep();
		}
		if (codigo.equals("1")) {
			return new ZueiraMouse();
		}
		if (codigo.equals("2")) {
			return new ZueiraBeep();
		}
		if (codigo.equals("3")) {
			return new ZueiraMonster();
		}
		if (codigo.equals("4")) {
			return new ZueiraFrozen();
		}
		if (codigo.equals("5")) {
			return new ZueiraAltTab();
		}
		if (codigo.equals("6")) {
			return new ZueiraCtrlAltDown();
		}
		return new ZueiraBeep();
	}

}
