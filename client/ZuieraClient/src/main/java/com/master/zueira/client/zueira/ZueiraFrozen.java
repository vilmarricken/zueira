package com.master.zueira.client.zueira;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class ZueiraFrozen extends ZueiraAbstract {

	public static void main(final String[] args) {
		new ZueiraFrozen().run(null);
	}

	public ZueiraFrozen() {
	}

	@Override
	public void run(final String value) {
		int defs[] = new int[] { 60 };
		defs = this.parse(value, defs);
		final JWindow win = new JWindow();
		try {
			final Toolkit tk = Toolkit.getDefaultToolkit();
			final Dimension screenSize = tk.getScreenSize();
			System.out.println(screenSize);
			final Rectangle screenRect = new Rectangle(screenSize);
			Robot robot;
			robot = new Robot();
			final BufferedImage screenCapturedImage = robot.createScreenCapture(screenRect);
			final ImageIcon icon = new ImageIcon(screenCapturedImage);
			final JLabel label = new JLabel(icon);
			win.getContentPane().add(label, BorderLayout.CENTER);
			win.pack();
			win.setVisible(true);
			Thread.sleep(defs[0] * 1000);
		} catch (final AWTException e) {
			e.printStackTrace();
		} catch (final InterruptedException e) {
			e.printStackTrace();
		}
		win.setVisible(false);
		win.dispose();
	}

}
