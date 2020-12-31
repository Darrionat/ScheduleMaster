package me.darrionat.schedulemaster.ui.animations;

import java.util.Timer;
import java.util.TimerTask;

import me.darrionat.schedulemaster.ui.UiContainer;
import me.darrionat.schedulemaster.ui.components.UiComponent;

public abstract class UiAnimation {

	private Timer timer;
	private long delay = 0;
	private long period = 0;

	private UiContainer container;
	protected UiComponent component;

	public UiAnimation(UiContainer container, UiComponent uiComponent) {
		this.container = container;
		this.component = uiComponent;
	}

	public UiAnimation(UiContainer container, UiComponent uiComponent, long delay, long period) {
		this.container = container;
		this.component = uiComponent;
		this.delay = delay;
		this.period = period;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public void setPeriod(long period) {
		this.period = period;
	}

	public void startAnimation() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				animate();
				container.repaint();
			}
		}, delay, period);
	}

	/**
	 * Animate the UiComponent. Ran on the timer
	 */
	protected abstract void animate();
}