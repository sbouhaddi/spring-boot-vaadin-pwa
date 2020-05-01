package com.sbouhaddi.vaadin.crm.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sbouhaddi.vaadin.crm.backend.entity.Contact;
import com.sbouhaddi.vaadin.crm.backend.repository.ContactRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactService {

	private final ContactRepository contactRepository;

	public List<Contact> findAll() {
		return contactRepository.findAll();
	}

	public List<Contact> findAll(String stringFilter) {
		if (stringFilter == null || stringFilter.isEmpty()) {
			return contactRepository.findAll();
		} else {
			return contactRepository.search(stringFilter);
		}
	}

	public long count() {
		return contactRepository.count();
	}

	public void delete(Contact contact) {
		contactRepository.delete(contact);
	}

	public void save(Contact contact) {
		if (contact == null) {
			log.error("Contact is null. Are you sure you have connected your formto the application?");
			return;
		}
		contactRepository.save(contact);
	}

}
