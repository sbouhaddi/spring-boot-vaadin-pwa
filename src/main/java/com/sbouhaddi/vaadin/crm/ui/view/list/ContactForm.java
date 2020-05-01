package com.sbouhaddi.vaadin.crm.ui.view.list;

import java.util.List;

import com.sbouhaddi.vaadin.crm.backend.entity.Company;
import com.sbouhaddi.vaadin.crm.backend.entity.Contact;
import com.sbouhaddi.vaadin.crm.backend.entity.Status;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.shared.Registration;

public class ContactForm extends FormLayout {

	private static final long serialVersionUID = 1L;
	public TextField firstName = new TextField("First name");
	public TextField lastName = new TextField("Last name");
	public EmailField email = new EmailField("Email");
	public ComboBox<Status> status = new ComboBox<>("Status");
	public ComboBox<Company> company = new ComboBox<>("Company");

	public Button save = new Button("Save");
	public Button delete = new Button("Delete");
	public Button close = new Button("Cancel");

	public Binder<Contact> binder = new BeanValidationBinder<>(Contact.class);

	public ContactForm(List<Company> companies) {
		addClassName("contact-form");

		binder.bindInstanceFields(this);
		status.setItems(Status.values());
		company.setItems(companies);
		company.setItemLabelGenerator(Company::getName);

		add(firstName, lastName, email, status, company, createButtonsLayout());
	}

	public void setContact(Contact contact) {
		binder.setBean(contact);
	}

	private Component createButtonsLayout() {
		save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
		delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
		close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

		save.addClickShortcut(Key.ENTER);
		close.addClickShortcut(Key.ESCAPE);

		save.addClickListener(click -> validateAndSave());
		delete.addClickListener(click -> fireEvent(new DeleteEvent(this, binder.getBean())));
		close.addClickListener(click -> fireEvent(new CloseEvent(this)));

		binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

		return new HorizontalLayout(save, delete, close);
	}

	private void validateAndSave() {
		if (binder.isValid()) {
			fireEvent(new SaveEvent(this, binder.getBean()));
		}
	}

	// Events
	public static abstract class ContactFormEvent extends ComponentEvent<ContactForm> {

		private static final long serialVersionUID = 1L;
		private Contact contact;

		protected ContactFormEvent(ContactForm source, Contact contact) {
			super(source, false);
			this.contact = contact;
		}

		public Contact getContact() {
			return contact;
		}
	}

	public static class SaveEvent extends ContactFormEvent {
		private static final long serialVersionUID = 1L;

		SaveEvent(ContactForm source, Contact contact) {
			super(source, contact);
		}
	}

	public static class DeleteEvent extends ContactFormEvent {

		private static final long serialVersionUID = 1L;

		DeleteEvent(ContactForm source, Contact contact) {
			super(source, contact);
		}

	}

	public static class CloseEvent extends ContactFormEvent {
		private static final long serialVersionUID = 1L;

		CloseEvent(ContactForm source) {
			super(source, null);
		}
	}

	public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
			ComponentEventListener<T> listener) {
		return getEventBus().addListener(eventType, listener);
	}
}
