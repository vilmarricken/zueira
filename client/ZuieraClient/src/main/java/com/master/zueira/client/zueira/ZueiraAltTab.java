package com.master.zueira.client.zueira;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class ZueiraAltTab extends ZueiraAbstract {

	public static void main(final String[] args) {
		new ZueiraAltTab().run(null);
	}

	public ZueiraAltTab() {
	}

	@Override
	public void run(final String value) {
		final int defs[] = new int[] { 5 };
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			for (int i = 0; i < defs[0]; i++) {
				robot.keyPress(KeyEvent.VK_TAB);
				try {
					Thread.sleep(500);
				} catch (final InterruptedException e) {
				}
			}
			robot.keyRelease(KeyEvent.VK_TAB);
			robot.keyRelease(KeyEvent.VK_ALT);
		} catch (final AWTException e1) {
		}
	}

}
