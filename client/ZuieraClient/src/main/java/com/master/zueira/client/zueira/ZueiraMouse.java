package com.master.zueira.client.zueira;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.util.Random;

public class ZueiraMouse extends ZueiraAbstract {

	public static void main(final String[] args) {
		new ZueiraMouse().run("10 500");
	}

	@Override
	public void run(final String value) {
		final Toolkit tk = Toolkit.getDefaultToolkit();
		final Dimension screen = tk.getScreenSize();
		System.out.println(screen);
		Robot robot;
		final int maxX = (int) screen.getWidth();
		final int maxY = (int) screen.getHeight();
		final Random r = new Random();
		final int defs[] = this.parse(value, new int[] { 5, 200 });
		try {
			robot = new Robot();
			for (int i = 0; i < defs[0]; i++) {
				final int x = r.nextInt(3000) % maxX;
				final int y = r.nextInt(3000) % maxY;
				// System.out.println(x + "X" + y);
				robot.mouseMove(x, y);
				Thread.sleep(defs[1]);
			}
		} catch (final AWTException e) {
			e.printStackTrace();
		} catch (final InterruptedException e) {
		}
	}

}
