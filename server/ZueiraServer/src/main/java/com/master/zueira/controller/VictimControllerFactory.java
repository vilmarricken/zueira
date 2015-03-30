package com.master.zueira.controller;

public class VictimControllerFactory {

	private static final VictimControllerFactory INSTANCE = new VictimControllerFactory();

	public static VictimControllerFactory getInstance() {
		return INSTANCE;
	}

	private VictimController controller = new VictimControllerImpl();

	public VictimController getController() {
		return this.controller;
	}

	public void zuar(String zueira) {
		controller.zuar(zueira);
	}

}
