package com.master.zueira.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.master.zueira.client.zueira.Zueira;
import com.master.zueira.client.zueira.ZueiraFactory;

public class ZueiraRun implements Runnable {

	private static String OPTION_RUN = "1";

	private final Socket socket;

	public ZueiraRun(final Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			final InputStream in = this.socket.getInputStream();
			final byte[] b = new byte[1024];
			int t = -1;
			final ByteArrayOutputStream out = new ByteArrayOutputStream(512);
			while ((t = in.read(b)) > 0) {
				out.write(b, 0, t);
			}
			in.close();
			try {
				final String value = out.toString();
				final String[] values = value.split("#");
				if (values[0].equals(ZueiraRun.OPTION_RUN)) {
					final Zueira z = ZueiraFactory.getZueira(values[1]);
					z.run(values[2]);
				}
			} catch (final Exception e) {
				final Zueira z = ZueiraFactory.getZueira(null);
				z.run(null);
			}
			this.socket.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
