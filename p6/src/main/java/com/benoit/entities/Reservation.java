package com.benoit.entities;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Reservation")
public class Reservation implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="identifier", sequenceName="reservation_reservation_id_seq", allocationSize=1)  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
	@Column(name="reservation_id")
	private Long id;
	
	@Column(name="numero_reservation")
	private Integer numeroReservation;
	
	@Column(name="date_reservation")
	private LocalDate dateReservation;
	
	@Column(name="statut_reservation")
	private String statutReservation;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="adherent_id")
	private Adherent adherent;
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="topo_id")
	private Topo topo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroReservation() {
		return numeroReservation;
	}

	public void setNumeroReservation(Integer numeroReservation) {
		this.numeroReservation = numeroReservation;
	}

	public LocalDate getDateReservation() {
		return dateReservation;
	}

	public void setDateReservation(LocalDate dateReservation) {
		this.dateReservation = dateReservation;
	}

	public String getStatutReservation() {
		return statutReservation;
	}

	public void setStatutReservation(String statutReservation) {
		this.statutReservation = statutReservation;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public Topo getTopo() {
		return topo;
	}

	public void setTopo(Topo topo) {
		this.topo = topo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
