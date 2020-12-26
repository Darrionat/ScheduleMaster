package me.darrionat.schedulemaster.ui.animations;

import java.util.Timer;

public abstract class Animation {

	protected Timer timer;
	/**
	 * Frames per second of the animations
	 */
	protected final int fps = 144;

	public abstract void run();

	public abstract void cancel();
}