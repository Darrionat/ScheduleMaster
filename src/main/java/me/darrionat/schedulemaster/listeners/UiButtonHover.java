package me.darrionat.schedulemaster.listeners;

import me.darrionat.darrionGL.components.UiComponent;
import me.darrionat.darrionGL.events.ComponentEnteredEvent;
import me.darrionat.darrionGL.events.ComponentExitedEvent;
import me.darrionat.darrionGL.events.EventManager;
import me.darrionat.darrionGL.events.annotations.EventHandler;
import me.darrionat.darrionGL.events.interfaces.Listener;
import me.darrionat.schedulemaster.ui.components.UiButton;

public class UiButtonHover implements Listener {

	public UiButtonHover() {
		EventManager.registerListener(this);
	}

	@EventHandler
	public void onEnter(ComponentEnteredEvent e) {
		System.out.println("Entered");
		UiComponent component = e.getComponent();
		if (!(component instanceof UiButton) || component.getAnimation() == null)
			return;
		if (!component.getAnimation().isRunning()) {
			component.getAnimation().start();
		}
	}

	@EventHandler
	public void onExit(ComponentExitedEvent e) {
		System.out.println("Exited");
		if (!(e.getComponent() instanceof UiButton))
			return;
		if (!e.getComponent().getAnimation().isRunning()) {
			e.getComponent().getAnimation().start();
		}
	}
}