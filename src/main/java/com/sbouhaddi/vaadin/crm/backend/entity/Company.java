package com.sbouhaddi.vaadin.crm.backend.entity;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Company extends AbstractEntity {

	private String name;
	@OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
	private List<Contact> employees = new LinkedList<>();

	public Company(String name) {
		super();
		this.name = name;
	}

}
