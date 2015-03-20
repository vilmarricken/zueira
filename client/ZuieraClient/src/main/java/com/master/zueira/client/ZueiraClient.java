package com.master.zueira.client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.util.Enumeration;
import java.util.Random;

public class ZueiraClient {

	private static final String BROADCAST_MASK = "255.255.0.0";

	private static final int BROADCAST_SERVICE = 8888;

	private static final String RESPONSE_MESSAGE = "Zueira on";

	private static final String SEND_IDENTITY_MESSAGE = "Zueira connected in: ";

	public static void main(final String[] args) {
		new ZueiraClient().execute();
	}

	public ZueiraClient() {
	}

	public void discover(final int port) {
		try {
			final DatagramSocket client = new DatagramSocket();
			client.setBroadcast(true);
			final byte[] message = (SEND_IDENTITY_MESSAGE + port).getBytes();
			try {
				final DatagramPacket sendPacket = new DatagramPacket(message, message.length, InetAddress.getByName(BROADCAST_MASK), BROADCAST_SERVICE);
				client.send(sendPacket);
			} catch (final Exception e) {
				e.printStackTrace();
			}
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
						final DatagramPacket sendPacket = new DatagramPacket(message, message.length, broadcast, 8888);
						client.send(sendPacket);
					} catch (final Exception e) {
						e.printStackTrace();
					}
					System.out.println(this.getClass().getName() + ">>> Request packet sent to: " + broadcast.getHostAddress() + "; Interface: " + networkInterface.getDisplayName());
				}
			}
			System.out.println(this.getClass().getName() + ">>> Done looping over all network interfaces. Now waiting for a reply!");
			final byte[] recvBuf = new byte[15000];
			final DatagramPacket receivePacket = new DatagramPacket(recvBuf, recvBuf.length);
			client.receive(receivePacket);
			System.out.println(this.getClass().getName() + ">>> Broadcast response from server: " + receivePacket.getAddress().getHostAddress());
			final String returnMessage = new String(receivePacket.getData()).trim();
			if (returnMessage.equals(RESPONSE_MESSAGE)) {
				System.out.println(receivePacket.getAddress());
			}
			client.close();
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	public void execute() {
		final ServerSocket ss = this.initService();
		final int port = ss.getLocalPort();
		this.discover(port);
	}

	private ServerSocket initService() {
		int start = 8000;
		final int amount = 1000;
		final Random r = new Random();
		ServerSocket ss = null;
		while (ss == null) {
			for (int i = 0; i < 100; i++) {
				final int port = r.nextInt(amount) + start;
				try {
					ss = new ServerSocket(port);
					System.out.println("Client port: " + port);
					break;
				} catch (final IOException e) {
					e.printStackTrace();
				}
			}
			start += amount;
		}
		return ss;
	}

}
