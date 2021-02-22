package tansactions;
import database.Connexion;
import models.Membre;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class MemberTr extends  Membre {
    protected SessionFactory sessionFactory = null;

    public MemberTr() {
    }

    public boolean inscrire(String nom, String prenom, String email, String pwd, int age) {
        Connexion cx = new Connexion();
        Membre user = new Membre(nom, prenom, email, pwd, age);
        cx.save(user);
        cx.closeConnexion();
        return false;

    }


    Membre authentifier(String mail, String pwd) {
        Connexion cx = new Connexion();

        Query query = cx.getSession().createQuery("from Membre where mail = :mail");
        query.setParameter("mail", mail);
        Membre user = (Membre) query.getSingleResult();
        cx.closeConnexion();
        System.out.println("hi");
        return user;
    }
}





