package com.benoit.entities;

import java.io.Serializable;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Topo")
public class Topo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="identifier", sequenceName="topo_topo_id_seq", allocationSize=1)  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
	@Column(name="topo_id")
	private Long id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="date_parution")
	private LocalDate dateParution;
	
	@Column(name="description")
	private String description;
	
	@Column(name="disponibilite")
	private String disponibilite;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="adherent_id")
	private Adherent adherent;
	
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy="topo", fetch = FetchType.EAGER)
	private Set<Reservation> reservations = new HashSet<Reservation>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LocalDate getDateParution() {
		return dateParution;
	}

	public void setDateParution(LocalDate dateParution) {
		this.dateParution = dateParution;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(String disponibilite) {
		this.disponibilite = disponibilite;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public Set<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
