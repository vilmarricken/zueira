package com.master.zueira.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ZueiraDiscover implements Runnable {

	private static final String ACCEPT_MESSAGE = "Zueira connected in: ";

	private static ZueiraDiscover INSTANCE;

	public static ZueiraDiscover getInstance() {
		if (INSTANCE == null) {
			synchronized (ACCEPT_MESSAGE) {
				if (INSTANCE == null) {
					INSTANCE = new ZueiraDiscover();
					new Thread(INSTANCE).start();
				}
			}
		}
		return INSTANCE;
	}

	public static void main(final String[] args) {
		getInstance();
	}

	private DatagramSocket socket;

	private ZueiraDiscover() {
	}

	@Override
	public void run() {
		try {
			this.socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
			this.socket.setBroadcast(true);
			while (true) {
				System.out.println(this.getClass().getName() + ">>>Ready to receive broadcast packets!");

				final byte[] recvBuf = new byte[15000];
				final DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
				this.socket.receive(packet);

				System.out.println(this.getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
				System.out.println(this.getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

				final String message = new String(packet.getData()).trim();
				if (message.startsWith(ACCEPT_MESSAGE)) {
					final byte[] sendData = "DISCOVER_FUIFSERVER_RESPONSE".getBytes();

					final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
					this.socket.send(sendPacket);

					System.out.println(this.getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
				}
			}
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

}