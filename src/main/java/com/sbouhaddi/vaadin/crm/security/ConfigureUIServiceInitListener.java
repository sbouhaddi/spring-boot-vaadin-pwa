package com.sbouhaddi.vaadin.crm.security;

import org.springframework.stereotype.Component;

import com.sbouhaddi.vaadin.crm.ui.view.login.LoginView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;

@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void serviceInit(ServiceInitEvent event) {
		event.getSource().addUIInitListener(uiEvent -> {
			final UI ui = uiEvent.getUI();
			ui.addBeforeEnterListener(this::beforeEnter);
		});
	}

	/**
	 * Reroutes the user if they're not authorized to access the view.
	 *
	 * @param event before navigation event with event details
	 */
	private void beforeEnter(BeforeEnterEvent event) {
		if (!LoginView.class.equals(event.getNavigationTarget()) && !SecurityUtils.isUserLoggedIn()) {
			event.rerouteTo(LoginView.class);
		}
	}
}