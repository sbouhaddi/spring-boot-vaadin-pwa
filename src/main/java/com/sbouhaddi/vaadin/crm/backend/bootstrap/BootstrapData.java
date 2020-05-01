package com.sbouhaddi.vaadin.crm.backend.bootstrap;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.sbouhaddi.vaadin.crm.backend.entity.Company;
import com.sbouhaddi.vaadin.crm.backend.entity.Contact;
import com.sbouhaddi.vaadin.crm.backend.entity.Status;
import com.sbouhaddi.vaadin.crm.backend.repository.CompanyRepository;
import com.sbouhaddi.vaadin.crm.backend.repository.ContactRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

	private final CompanyRepository companyRepository;
	private final ContactRepository contactRepository;

	@Override
	public void run(String... args) throws Exception {
		populateTestData();
	}

	public void populateTestData() {
		if (companyRepository.count() == 0) {
			companyRepository.saveAll(Stream.of("Path-Way Electronics", "E-Tech Management", "Path-E-Tech Management")
					.map(name -> new Company(name)).collect(Collectors.toList()));

		}
		

		if (contactRepository.count() == 0) {
			Random r = new Random(0);
			List<Company> companies = companyRepository.findAll();
			contactRepository.saveAll(Stream
					.of("Gabrielle Patel", "Brian Robinson", "Eduardo Haugen", "Koen Johansen", "Alejandro Macdonald",
							"Angel Karlsson", "Yahir Gustavsson", "Haiden Svensson", "Emily Stewart", "Corinne Davis",
							"Ryann Davis", "Yurem Jackson", "Kelly Gustavsson", "Eileen Walker", "Katelyn Martin",
							"Israel Carlsson", "Quinn Hansson", "Makena Smith", "Danielle Watson", "Leland Harris",
							"Gunner Karlsen", "Jamar Olsson", "Lara Martin", "Ann Andersson", "Remington Andersson",
							"Rene Carlsson", "Elvis Olsen", "Solomon Olsen", "Jaydan Jackson", "Bernard Nilsen")
					.map(name -> {
						String[] split = name.split(" ");
						Contact contact = new Contact();
						contact.setFirstName(split[0]);
						contact.setLastName(split[1]);
						contact.setCompany(companies.get(r.nextInt(companies.size())));
						contact.setStatus(Status.values()[r.nextInt(Status.values().length)]);
						String email = (contact.getFirstName() + "." + contact.getLastName() + "@"
								+ contact.getCompany().getName().replaceAll("[\\s-]", "") + ".com").toLowerCase();

						contact.setEmail(email);
						return contact;
					}).collect(Collectors.toList()));
		}
	}

}
