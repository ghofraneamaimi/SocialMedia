package models;

import database.Connexion;
import org.hibernate.Criteria;
import org.hibernate.transform.Transformers;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Groupe {
    @ManyToOne
      Membre membre;

    //liste des membres qui ont rejoint le groupe
    @OneToMany (mappedBy = "groupe")
    List<Rejoindre> membreRejoint = new ArrayList<>();

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idGroupe;

    @Column( name = "groupeName", unique = false, nullable = false, length = 100 )
    private String nameGroupe;

    @Column( name = "privacy", unique = false, nullable = false, length = 100 )
    private Privacy privacyGroupe;

    @Column(name = "dateCreation", length = 100)
    String date;

    public Groupe() {
    }

    public Groupe(Membre membre, List<Rejoindre> membreRejoint, Long idGroupe, String nameGroupe, Privacy privacyGroupe, String date)
    {
        this.membre = membre;
        this.membreRejoint = membreRejoint;
        this.idGroupe = idGroupe;
        this.nameGroupe = nameGroupe;
        this.privacyGroupe = privacyGroupe;
        this.date = date;
    }

    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public List<Rejoindre> getMembreRejoint() {
        return membreRejoint;
    }

    public void setMembreRejoint(List<Rejoindre> membreRejoint) {
        this.membreRejoint = membreRejoint;
    }

    public Long getIdGroupe() {
        return idGroupe;
    }

    public void setIdGroupe(Long idGroupe) {
        this.idGroupe = idGroupe;
    }

    public String getNameGroupe() {
        return nameGroupe;
    }

    public void setNameGroupe(String nameGroupe) {
        this.nameGroupe = nameGroupe;
    }

    public Privacy getPrivacyGroupe() {
        return privacyGroupe;
    }

    public void setPrivacyGroupe(Privacy privacyGroupe) {
        this.privacyGroupe = privacyGroupe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static List<Groupe> listeGroupeDisponible()
    {
        Query crit = Connexion.getSession().createQuery("from Groupe");
         List<Groupe> results = crit.getResultList();
         return results;
       // return Connexion.getSession().createQuery("from Groupe").getResultList().get(0);

    }
}
