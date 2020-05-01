package com.sbouhaddi.vaadin.crm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sbouhaddi.vaadin.crm.backend.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}
