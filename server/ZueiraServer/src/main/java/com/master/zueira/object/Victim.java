package com.master.zueira.object;

public class Victim {

	private String address;

	private String name;

	private int service;

	public Victim(final String name, final String address, final int service) {
		this.name = name;
		this.address = address;
		this.service = service;
	}

	public String getAddress() {
		return this.address;
	}

	public String getName() {
		return this.name;
	}

	public int getService() {
		return this.service;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setService(final int service) {
		this.service = service;
	}

}
