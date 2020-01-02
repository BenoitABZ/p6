package com.benoit.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="Commentaire")
public class Commentaire implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="identifier", sequenceName="commentaire_commentaire_id_seq", allocationSize=1)  
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
	@Column(name="commentaire_id")
	private Long id;
	
	@Column(name="date_commentaire")
	private LocalDate dateCommentaire;
	
	@Column(name="contenu_commentaire")
	private String contenuCommentaire;
	
	//@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="adherent_id")
	private Adherent adherent;
	
	//@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="site_escalade_id")
	private SiteEscalade siteEscalade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getDateCommentaire() {
		return dateCommentaire;
	}

	public void setDateCommentaire(LocalDate dateCommentaire) {
		this.dateCommentaire = dateCommentaire;
	}

	public String getContenuCommentaire() {
		return contenuCommentaire;
	}

	public void setContenuCommentaire(String contenuCommentaire) {
		this.contenuCommentaire = contenuCommentaire;
	}

	public Adherent getAdherent() {
		return adherent;
	}

	public void setAdherent(Adherent adherent) {
		this.adherent = adherent;
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