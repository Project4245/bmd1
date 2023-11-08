package com.bmd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "appointment_table")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String patientName;

	@Column(nullable = false)
	private int patientAge;

	@Column(nullable = false)
	private String gender;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String mobileNumber;

	@ManyToOne
	private Users users;
}
