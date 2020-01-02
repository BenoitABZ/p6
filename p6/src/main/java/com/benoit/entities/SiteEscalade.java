package com.benoit.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="site_escalade")
public class SiteEscalade implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="identifier", sequenceName="site_escalade_site_escalade_id_seq_2", allocationSize=1)  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
	@Column(name="site_escalade_id")
	private Long id;
	
	@Column(name="departement")
	private String departement;
	
	@Column(name="commune")
	private String commune;

	@Column(name="label")
	private String label;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="description")
	private String description;

	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy="siteEscalade", fetch = FetchType.EAGER)
	private Set<Commentaire> commentaires = new HashSet<Commentaire>();
	
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@OneToMany(mappedBy="siteEscalade", fetch = FetchType.EAGER)
	private Set<Secteur> secteurs = new HashSet<Secteur>();
	
	//@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="adherent_id")
	private Adherent adherent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}
	
	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Commentaire> getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(Set<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}

	public Set<Secteur> getSecteurs() {
		return secteurs;
	}

	public void setSecteurs (Set<Secteur> secteurs) {
		this.secteurs = secteurs;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
