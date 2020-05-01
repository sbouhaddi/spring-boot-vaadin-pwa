package com.sbouhaddi.vaadin.crm.backend.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.sbouhaddi.vaadin.crm.backend.entity.Company;
import com.sbouhaddi.vaadin.crm.backend.repository.CompanyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyService {

	private final CompanyRepository companyRepository;

	public List<Company> findAll() {
		return companyRepository.findAll();
	}

	public Map<String, Integer> getStats() {
		HashMap<String, Integer> stats = new HashMap<>();
		findAll().forEach(company -> stats.put(company.getName(), company.getEmployees().size()));
		return stats;
	}

}
