package me.darrionat.schedulemaster;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.darrionat.schedulemaster.statics.Bootstrapper;

/**
 * Schedule Master is a program to assist businesses in creating the most
 * efficient schedule for their employees. Schedule Master is to help businesses
 * spend less time on the complex process of creating schedules and to start
 * spending more time on the things they enjoy. This project created on October
 * 18 2020.
 * 
 * @author Darrion Thornburgh
 */
public class ScheduleMaster {

	public static void main(String[] args) {
		Bootstrapper bootstrapper = Bootstrapper.getBootstrapper();
		bootstrapper.initialize(new ScheduleMaster());
		// new ScheduleMaster().createWindow();
	}

	@SuppressWarnings("unused")
	private void createWindow() {
		JFrame frame = new JFrame("Simple GUI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel textLabel = new JLabel("I'm a label in the window", SwingConstants.CENTER);
		textLabel.setPreferredSize(new Dimension(500, 100));

		frame.getContentPane().add(textLabel, BorderLayout.CENTER);
		// Display the window
		frame.setLocationRelativeTo(null);

		// iconURL is null when not found
		ImageIcon icon = new ImageIcon("res/icon.png");
		frame.setIconImage(icon.getImage());
		// ImageIcon icon = new ImageIcon("res/icon.png");
		// frame.setIconImage(ImageIO.read(new File("res/icon.png")));

		frame.pack();
		frame.setVisible(true);

		JPanel mainPanel = new JPanel();
		mainPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO:
			}
		});
		// add mainPanel to the JFrame...
	}
}