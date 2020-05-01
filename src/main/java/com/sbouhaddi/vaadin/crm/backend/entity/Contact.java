package com.sbouhaddi.vaadin.crm.backend.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends AbstractEntity implements Cloneable {

	@NotNull
	@NotEmpty
	private String firstName = "";
	@NotNull
	@NotEmpty
	private String lastName = "";
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;
	@Enumerated(EnumType.STRING)
	@NotNull
	private Status status;
	@Email
	@NotNull
	@NotEmpty
	private String email = "";
}
