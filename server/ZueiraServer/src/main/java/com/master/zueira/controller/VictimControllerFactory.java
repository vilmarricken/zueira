package com.master.zueira.controller;

public class VictimControllerFactory {

	private static final VictimControllerFactory INSTANCE = new VictimControllerFactory();

	public static VictimControllerFactory getInstance() {
		return INSTANCE;
	}

	private VictimController controller;

	public VictimController getController() {
		return this.controller;
	}

	public void setController(final VictimController controller) {
		this.controller = controller;
	}

}
