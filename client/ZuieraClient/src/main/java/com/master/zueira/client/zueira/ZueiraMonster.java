package com.master.zueira.client.zueira;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

public class ZueiraMonster implements Zueira {

	public static void main(final String[] args) {
		new ZueiraMonster().run(null);
	}

	public ZueiraMonster() {
	}

	private Dimension getDimension(final ImageIcon image) {
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final double screenWidth = screenSize.getWidth();
		final double screenHeight = screenSize.getHeight();
		final double iconWidth = image.getIconWidth();
		final double iconHeight = image.getIconHeight();
		final double rh = (iconHeight / screenHeight);
		final double rw = (iconWidth / screenWidth);
		int newWidth = 0;
		int newHeight = 0;
		if (rh > rw) {
			newHeight = (int) (iconHeight / rh);
			newWidth = (int) (iconWidth / rh);
		} else {
			newHeight = (int) (iconHeight / rw);
			newWidth = (int) (iconWidth / rw);
		}
		final Dimension d = new Dimension(newWidth, newHeight);
		return d;
	}

	private Clip getSound() throws Exception {
		final URL file = this.getClass().getClassLoader().getResource("burn.wav");
		final AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
		final Clip audioClip = AudioSystem.getClip();
		audioClip.open(audioStream);
		return audioClip;
	}

	private URL getURLImage() {
		return this.getClass().getClassLoader().getResource("exorcista.jpg");
	}

	private JWindow getWindow() {
		final JWindow w = new JWindow();
		final ImageIcon image = new ImageIcon(this.getURLImage());

		final Dimension d = this.getDimension(image);
		final Image scaledInstance = image.getImage().getScaledInstance(d.width, d.height, 100);
		image.setImage(scaledInstance);

		final JLabel lbl = new JLabel(image);
		w.getContentPane().add(lbl, BorderLayout.CENTER);
		w.setSize(d);
		w.setLocationRelativeTo(null);
		w.setAlwaysOnTop(true);
		return w;
	}

	@Override
	public void run(final String value) {
		try {
			final Clip audio = this.getSound();
			final JWindow w = this.getWindow();
			w.setVisible(true);
			audio.start();
			try {
				Thread.sleep(2000);
			} catch (final InterruptedException e) {
			}
			w.setVisible(false);
			w.dispose();
		} catch (final Exception e1) {
			e1.printStackTrace();
		}
	}

}
