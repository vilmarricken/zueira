package com.master.zueira.client.zueira;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class ZueiraCtrlAltDown implements Zueira {

	public static void main(final String[] args) {
		new ZueiraCtrlAltDown().run(null);
	}

	public ZueiraCtrlAltDown() {
	}

	@Override
	public void run(final String value) {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_DOWN);

			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_ALT);
			robot.keyRelease(KeyEvent.VK_DOWN);
		} catch (final AWTException e1) {
		}
	}

}
