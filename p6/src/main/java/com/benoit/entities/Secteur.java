package com.benoit.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Secteur")
public class Secteur implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="identifier", sequenceName="secteur_secteur_id_seq", allocationSize=1)  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
	@Column(name="secteur_id")
	private Long id;
	
	@Column(name="numero_secteur")
	private Integer numeroSecteur;
	
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy="secteur", fetch = FetchType.EAGER)
	private Set<Voie> voies = new HashSet<Voie>();
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="site_escalade_id")
	private SiteEscalade siteEscalade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumeroSecteur() {
		return numeroSecteur;
	}

	public void setNumeroSecteur(Integer numeroSecteur) {
		this.numeroSecteur = numeroSecteur;
	}

	public Set<Voie> getVoies() {
		return voies;
	}

	public void setVoies(Set<Voie> voies) {
		this.voies = voies;
	}

	public SiteEscalade getSiteEscalade() {
		return siteEscalade;
	}

	public void setSiteEscalade(SiteEscalade siteEscalade) {
		this.siteEscalade = siteEscalade;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
