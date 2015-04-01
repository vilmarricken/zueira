package com.master.zueira.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class ZueiraDiscover implements Runnable {

	private static final int BROADCAST_SERVICE = 8888;

	private static final String RESPONSE_MESSAGE = "Zueira on";

	private static final String SEND_IDENTITY_MESSAGE = "Zueira connected in:\t";

	private static final long WAIT_ON_ERROR = 15 * 60 * 1000;

	private static final long WAIT_RECONNECT = 15 * 60 * 1000;

	private final int service;

	public ZueiraDiscover(final int service) {
		this.service = service;
	}

	private boolean receiveResponse(final DatagramSocket client) {
		try {
			final byte[] recvBuf = new byte[150];
			final DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
			client.setSoTimeout(5000);
			client.receive(receivePacket);
			System.out.println(this.getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());
			final String returnMessage = new String(receivePacket.getData()).trim();
			if (returnMessage.equals(ZueiraDiscover.RESPONSE_MESSAGE)) {
				return true;
			}
		} catch (final IOException e) {
			e.printStackTrace();
		} finally {
			client.close();
		}
		return false;
	}

	@Override
	public void run() {
		DatagramSocket client = null;
		do {
			boolean received = false;
			try {
				System.out.println("Try discover zueira");
				client = new DatagramSocket();
				client.setBroadcast(true);
				this.sendBroadcast(client, this.service);
				received = this.receiveResponse(client);
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			try {
				if (received) {
					Thread.sleep(ZueiraDiscover.WAIT_RECONNECT);
				} else {
					Thread.sleep(ZueiraDiscover.WAIT_ON_ERROR);
				}
			} catch (final InterruptedException e) {
				e.printStackTrace();
			}
		} while (true);
	}

	private void sendBroadcast(final DatagramSocket client, final int port) throws IOException {
		final byte[] message = (ZueiraDiscover.SEND_IDENTITY_MESSAGE + port + "\t" + System.getProperty("user.name")).getBytes();
		final Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
		while (interfaces.hasMoreElements()) {
			final NetworkInterface networkInterface = interfaces.nextElement();
			if (networkInterface.isLoopback() || !networkInterface.isUp()) {
				continue;
			}
			for (final InterfaceAddress interfaceAddress : networkInterface.getInterfaceAddresses()) {
				final InetAddress broadcast = interfaceAddress.getBroadcast();
				if (broadcast == null) {
					continue;
				}
				try {
					final DatagramPacket sendPacket = new DatagramPacket(message, message.length, broadcast, ZueiraDiscover.BROADCAST_SERVICE);
					client.send(sendPacket);
				} catch (final Exception e) {
					e.printStackTrace();
				}
				System.out.println(this.getClass().getName() + ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
			}
		}
	}

}
