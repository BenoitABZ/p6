
CREATE SEQUENCE adresse_adresse_id_seq_1;

CREATE TABLE Adresse (
                adresse_id INTEGER NOT NULL DEFAULT nextval('adresse_adresse_id_seq_1'),
                numero_voie VARCHAR NOT NULL,
                libelle_voie VARCHAR NOT NULL,
                nom_voie VARCHAR NOT NULL,
                code_postal VARCHAR NOT NULL,
                ville VARCHAR NOT NULL,
                CONSTRAINT adresse_id PRIMARY KEY (adresse_id)
);


ALTER SEQUENCE adresse_adresse_id_seq_1 OWNED BY Adresse.adresse_id;

CREATE SEQUENCE adherent_adherent_id_seq;

CREATE TABLE Adherent (
                adherent_id INTEGER NOT NULL DEFAULT nextval('adherent_adherent_id_seq'),
                membre BOOLEAN NOT NULL,
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                mail VARCHAR NOT NULL,
                telephone VARCHAR NOT NULL,
                adresse_id INTEGER NOT NULL,
                CONSTRAINT adherent_id PRIMARY KEY (adherent_id)
);


ALTER SEQUENCE adherent_adherent_id_seq OWNED BY Adherent.adherent_id;

CREATE SEQUENCE site_escalade_site_escalade_id_seq_2;

CREATE TABLE Site_escalade (
                site_escalade_id INTEGER NOT NULL DEFAULT nextval('site_escalade_site_escalade_id_seq_2'),
                lieu VARCHAR NOT NULL,
                label VARCHAR,
                adherent_id INTEGER NOT NULL,
                CONSTRAINT site_escalade_id PRIMARY KEY (site_escalade_id)
);


ALTER SEQUENCE site_escalade_site_escalade_id_seq_2 OWNED BY Site_escalade.site_escalade_id;

CREATE SEQUENCE secteur_secteur_id_seq;

CREATE TABLE Secteur (
                secteur_id INTEGER NOT NULL DEFAULT nextval('secteur_secteur_id_seq'),
                numero_secteur VARCHAR NOT NULL,
                site_escalade_id INTEGER NOT NULL,
                CONSTRAINT secteur_id PRIMARY KEY (secteur_id)
);


ALTER SEQUENCE secteur_secteur_id_seq OWNED BY Secteur.secteur_id;

CREATE SEQUENCE voie_voie_id_seq;

CREATE TABLE Voie (
                voie_id INTEGER NOT NULL DEFAULT nextval('voie_voie_id_seq'),
                equipee BOOLEAN NOT NULL,
                cotation_voie VARCHAR,
                secteur_id INTEGER NOT NULL,
                CONSTRAINT voie_id PRIMARY KEY (voie_id)
);


ALTER SEQUENCE voie_voie_id_seq OWNED BY Voie.voie_id;

CREATE SEQUENCE longueur_longueur_id_seq;

CREATE TABLE Longueur (
                longueur_id INTEGER NOT NULL DEFAULT nextval('longueur_longueur_id_seq'),
                cotation_longueur VARCHAR NOT NULL,
                taille_longueur VARCHAR,
                voie_id INTEGER NOT NULL,
                CONSTRAINT longueur_id PRIMARY KEY (longueur_id)
);


ALTER SEQUENCE longueur_longueur_id_seq OWNED BY Longueur.longueur_id;

CREATE SEQUENCE topo_topo_id_seq;

CREATE TABLE Topo (
                Topo_id INTEGER NOT NULL DEFAULT nextval('topo_topo_id_seq'),
                nom VARCHAR NOT NULL,
                date_parution VARCHAR NOT NULL,
                description VARCHAR NOT NULL,
                disponibilite VARCHAR NOT NULL,
                adherent_id INTEGER NOT NULL,
                CONSTRAINT topo_id PRIMARY KEY (Topo_id)
);


ALTER SEQUENCE topo_topo_id_seq OWNED BY Topo.Topo_id;

CREATE SEQUENCE commentaire_commentaire_id_seq;

CREATE TABLE Commentaire (
                commentaire_id INTEGER NOT NULL DEFAULT nextval('commentaire_commentaire_id_seq'),
                date_commentaire VARCHAR NOT NULL,
                contenu_commentaire VARCHAR NOT NULL,
                adherent_id INTEGER NOT NULL,
                site_escalade_id INTEGER NOT NULL,
                CONSTRAINT commentaire_id PRIMARY KEY (commentaire_id)
);


ALTER SEQUENCE commentaire_commentaire_id_seq OWNED BY Commentaire.commentaire_id;

CREATE SEQUENCE reservation_reservation_id_seq;

CREATE TABLE Reservation (
                reservation_id INTEGER NOT NULL DEFAULT nextval('reservation_reservation_id_seq'),
                numero_reservation VARCHAR NOT NULL,
                date_reservation VARCHAR NOT NULL,
                statut_reservation VARCHAR NOT NULL,
                adherent_id INTEGER NOT NULL,
                Topo_id INTEGER NOT NULL,
                CONSTRAINT numero_reservation PRIMARY KEY (reservation_id)
);


ALTER SEQUENCE reservation_reservation_id_seq OWNED BY Reservation.reservation_id;

ALTER TABLE Adherent ADD CONSTRAINT adresse_adherent_fk
FOREIGN KEY (adresse_id)
REFERENCES Adresse (adresse_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Reservation ADD CONSTRAINT adherent_reservation_fk
FOREIGN KEY (adherent_id)
REFERENCES Adherent (adherent_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Commentaire ADD CONSTRAINT adherent_commentaire_fk
FOREIGN KEY (adherent_id)
REFERENCES Adherent (adherent_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Topo ADD CONSTRAINT adherent_topo_fk
FOREIGN KEY (adherent_id)
REFERENCES Adherent (adherent_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Site_escalade ADD CONSTRAINT adherent_site_escalade_fk
FOREIGN KEY (adherent_id)
REFERENCES Adherent (adherent_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Secteur ADD CONSTRAINT site_escalade_secteur_fk
FOREIGN KEY (site_escalade_id)
REFERENCES Site_escalade (site_escalade_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Commentaire ADD CONSTRAINT site_escalade_commentaire_fk
FOREIGN KEY (site_escalade_id)
REFERENCES Site_escalade (site_escalade_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (secteur_id)
REFERENCES Secteur (secteur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Longueur ADD CONSTRAINT voie_longueur_fk
FOREIGN KEY (voie_id)
REFERENCES Voie (voie_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Reservation ADD CONSTRAINT topo_reservation_fk
FOREIGN KEY (Topo_id)
REFERENCES Topo (Topo_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
