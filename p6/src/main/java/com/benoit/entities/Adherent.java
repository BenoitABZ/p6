package com.benoit.entities;

import java.io.Serializable;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.SelectBeforeUpdate;

@Entity
@Table(name="Adherent")
@SelectBeforeUpdate
public class Adherent implements Serializable{
	

	private static final long serialVersionUID = 1L;

@Id
@SequenceGenerator(name="identifier", sequenceName="adherent_adherent_id_seq", allocationSize=1)  
@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="identifier")
@Column(name="adherent_id")
private Long id;

@Column(name="membre")
private Boolean membre;

@Column(name="nom")
private String nom;

@Column(name="prenom")
private String prenom;

@Column(name="mail")
private String mail;

@Column(name="telephone")
private String telephone;

@Column(name="motdepasse")
private String motDePasse;


@Embedded
private Adresse adresse;

@Cascade(org.hibernate.annotations.CascadeType.ALL)
@OneToOne(mappedBy ="adherent", fetch = FetchType.EAGER)
private Reservation reservation; 

@Cascade(org.hibernate.annotations.CascadeType.ALL)
@OneToMany(mappedBy="adherent", fetch = FetchType.EAGER)
private Set<SiteEscalade> sitesEscalade= new HashSet<SiteEscalade>();

@Cascade(org.hibernate.annotations.CascadeType.ALL)
@OneToMany(mappedBy="adherent", fetch = FetchType.EAGER)
private Set<Topo> topos = new HashSet<Topo>();

@Cascade(org.hibernate.annotations.CascadeType.ALL)
@OneToMany(mappedBy="adherent", fetch = FetchType.EAGER)
private Set<Commentaire> commentaires = new HashSet<Commentaire>();

public Long getId() {
	return id;
}

public void setId(Long id) {
	this.id = id;
}

public Boolean getMembre() {
	return membre;
}

public void setMembre(Boolean membre) {
	this.membre = membre;
}

public String getNom() {
	return nom;
}

public void setNom(String nom) {
	this.nom = nom;
}

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getMail() {
	return mail;
}

public void setMail(String mail) {
	this.mail = mail;
}

public String getTelephone() {
	return telephone;
}

public void setTelephone(String telephone) {
	this.telephone = telephone;
}

public String getMotDePasse() {
	return motDePasse;
}

public void setMotDePasse(String motDePasse) {
	this.motDePasse = motDePasse;
}

public Adresse getAdresse() {
	return adresse;
}

public void setAdresse(Adresse adresse) {
	this.adresse = adresse;
}

public Reservation getReservation() {
	return reservation;
}

public void setReservation(Reservation reservation) {
	this.reservation = reservation;
}

public Set<Topo> getTopos() {
	return topos;
}

public void setTopos(Set<Topo> topos) {
	this.topos = topos;
}

public Set<Commentaire> getCommentaires() {
	return commentaires;
}

public void setCommentaires(Set<Commentaire> commentaires) {
	this.commentaires = commentaires;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}
}
