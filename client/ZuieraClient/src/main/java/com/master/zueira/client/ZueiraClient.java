package com.master.zueira.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class ZueiraClient {

	public static void main(final String[] args) throws IOException {
		new ZueiraClient().execute();
	}

	public ZueiraClient() {
	}

	public void execute() throws IOException {
		final ServerSocket ss = this.initService();
		final int service = ss.getLocalPort();
		new Thread(new ZueiraDiscover(service)).start();
		while (true) {
			final Socket s = ss.accept();
			new Thread(new ZueiraRun(s)).start();
		}
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
