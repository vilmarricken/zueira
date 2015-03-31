package com.master.zueira.controller;

public class VictimControllerFactory {

	private static final VictimControllerFactory INSTANCE = new VictimControllerFactory();

	public static VictimControllerFactory getInstance() {
		return VictimControllerFactory.INSTANCE;
	}

	private final VictimController controller = new VictimControllerImpl();

	public VictimController getController() {
		return this.controller;
	}

	public void zuar(final String zueira, final String value) {
		this.controller.zuar(zueira, value);
	}

}
