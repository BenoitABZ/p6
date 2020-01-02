package com.benoit.entities;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Voie")
public class Voie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="identifier", sequenceName="voie_voie_id_seq", allocationSize=1)  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
	@Column(name="voie_id")
	private Long id;
	
	@Column(name="cotation_voie")
	private Integer cotationVoie;
	
	@Column(name="nombre_longueur")
	private String nombreLongueur;

	@Column(name="hauteur")
	private String hauteur;
	
	@Column(name="equipee")
	private Boolean equipee;
	
	//@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="secteur_id")
	private Secteur secteur;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getHauteur() {
		return hauteur;
	}

	public void setHauteur(String hauteur) {
		this.hauteur = hauteur;
	}
	
	public String getNombreLongueur() {
		return nombreLongueur;
	}

	public void setNombreLongueur(String nombreLongueur) {
		this.nombreLongueur = nombreLongueur;
	}


	public Integer getCotationVoie() {
		return cotationVoie;
	}

	public void setCotationVoie(Integer cotationVoie) {
		this.cotationVoie = cotationVoie;
	}

	public Boolean getEquipee() {
		return equipee;
	}

	public void setEquipee(Boolean equipee) {
		this.equipee = equipee;
	}


	public Secteur getSecteur() {
		return secteur;
	}

	public void setSecteur(Secteur secteur) {
		this.secteur = secteur;
	}
	
}
