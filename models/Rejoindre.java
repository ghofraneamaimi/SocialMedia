package models;

import database.Connexion;

import javax.persistence.*;

@Entity
public class Rejoindre {

    @EmbeddedId
     RejoindreID rejoindreID;

    @ManyToOne
    Membre membreG;

    @ManyToOne
    Groupe groupe;

    public Rejoindre(long membreId, long groupeId) {
        this.rejoindreID = new RejoindreID(membreId,groupeId);
        this.membreG= (Membre) Connexion.find(Membre.class, membreId);
        this.groupe= (Groupe) Connexion.find(Groupe.class,groupeId);
    }

    public RejoindreID getRejoindreID() {
        return rejoindreID;
    }

    public void setRejoindreID(RejoindreID rejoindreID) {
        this.rejoindreID = rejoindreID;
    }

    public Membre getMembre() {
        return membreG;
    }

    public void setMembre(Membre membre) {
        this.membreG = membre;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Rejoindre{" +
                "rejoindreID=" + rejoindreID +
                ", membre=" + membreG +
                ", groupe=" + groupe +
                '}';
    }
}
