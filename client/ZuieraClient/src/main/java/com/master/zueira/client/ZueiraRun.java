package com.master.zueira.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ZueiraRun implements Runnable {

	private Socket socket;

	public ZueiraRun(final Socket socket) {
	}

	@Override
	public void run() {
		try {
			final InputStream in = this.socket.getInputStream();
			final byte[] b = new byte[1024];
			int t = -1;
			final StringBuilder s = new StringBuilder();
			while ((t = in.read(b)) > 0) {
				s.append(new String(b, 0, t));
			}
			in.close();
			this.socket.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
