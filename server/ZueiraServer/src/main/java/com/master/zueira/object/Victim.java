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

	@Override
	public boolean equals(final Object obj) {
		final Victim v = (Victim) obj;
		return (v).address.equals(this.address) && v.name.equals(this.name) && v.service == this.service;
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

	@Override
	public int hashCode() {
		return this.address.hashCode();
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
