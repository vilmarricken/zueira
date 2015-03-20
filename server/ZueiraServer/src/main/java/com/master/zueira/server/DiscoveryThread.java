package com.master.zueira.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class DiscoveryThread implements Runnable {

	private static class DiscoveryThreadHolder {
		private static final DiscoveryThread INSTANCE = new DiscoveryThread();
	}

	public static DiscoveryThread getInstance() {
		return DiscoveryThreadHolder.INSTANCE;
	}

	public static void main(final String[] args) {
		new Thread(getInstance()).start();
	}

	private DatagramSocket socket;

	@Override
	public void run() {
		try {
			// Keep a socket open to listen to all the UDP trafic that is
			// destined for this port
			this.socket = new DatagramSocket(8888, InetAddress.getByName("0.0.0.0"));
			this.socket.setBroadcast(true);

			while (true) {
				System.out.println(this.getClass().getName() + ">>>Ready to receive broadcast packets!");

				// Receive a packet
				final byte[] recvBuf = new byte[15000];
				final DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);
				this.socket.receive(packet);

				// Packet received
				System.out.println(this.getClass().getName() + ">>>Discovery packet received from: " + packet.getAddress().getHostAddress());
				System.out.println(this.getClass().getName() + ">>>Packet received; data: " + new String(packet.getData()));

				// See if the packet holds the right command (message)
				final String message = new String(packet.getData()).trim();
				if (message.equals("DISCOVER_FUIFSERVER_REQUEST")) {
					final byte[] sendData = "DISCOVER_FUIFSERVER_RESPONSE".getBytes();

					// Send a response
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