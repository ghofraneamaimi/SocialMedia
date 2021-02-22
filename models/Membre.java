package models;
import database.Connexion;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.*;

@Entity
public class Membre   {

   /* @OneToMany
    public List<MemberShip> amis = new ArrayList<>();*/

    //la liste des amitiés demandées à cet utilisateur
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="invitationenvoye")
    public List<MemberShip> InvitationRecu ;

    //la liste des amitiés demandées par cet utilisateur
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="invitationrecu")
    public List<MemberShip> InvitationEnvoyé ;

    @Override
    public String toString() {
        return "Membre{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }

    @Id
    @GeneratedValue
    @Column(name = "memberID", unique = true, nullable = false)
    private Long id;
    @Column(name = "name", unique = false, nullable = false, length = 100)
    String nom;
    @Column(name = "prenom", unique = false, nullable = false, length = 100)
    String prenom;
    @Column(name = "mail", unique = false, nullable = false, length = 100)
    String mail;
    @Column(name = "password", unique = false, nullable = false, length = 100)
    String password;
    @Column(name = "age", unique = false, nullable = false, length = 100)
    int age;

    public Membre(String nom, String prenom, String mail, String password, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.age = age;
        this.InvitationEnvoyé = new ArrayList<MemberShip>();
        this.InvitationRecu = new ArrayList<MemberShip>();
    }

    //public Membre(Serializable ghofrane, String amaimi, String s, String s1, int i) {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Membre() { }

    /*public List<MemberShip> getAmis() {
        return amis;
    }

    public void setAmis(List<MemberShip> amis) {
        this.amis = amis;
    }*/

    public List<MemberShip> getInvitationRecu() {
        return InvitationRecu;
    }

    public void setInvitationRecu(List<MemberShip> invitationRecu) {
        InvitationRecu = invitationRecu;
    }

    public List<MemberShip> getInvitationEnvoyé() {
        return InvitationEnvoyé;
    }

    public void setInvitationEnvoyé(List<MemberShip> invitationEnvoyé) {
        InvitationEnvoyé = invitationEnvoyé;
    }

    void listeInvitationRecu()
    {

        for (int i = 0; i < this.InvitationRecu.size(); i++) {
            System.out.println(this.InvitationRecu.get(i));
        }
    }
    public void listeInvitationEnvoye()
    {
        for (int i = 0; i < this.InvitationEnvoyé.size(); i++) {
            System.out.println(this.InvitationEnvoyé.get(i));
        }
    }

    public void envoyerInvitation(String nom)
    {    boolean exist = false;
        do{
            Connexion cx = new Connexion();
            try {
                Query query = cx.getSession().createQuery("from Membre where nom = :nom");
                query.setParameter("nom", nom);
                Membre envoyé_a = (Membre) query.getSingleResult();
                System.out.println(envoyé_a);
                MemberShip m = new MemberShip(this.getId(),envoyé_a.getId());
                cx.save(m);
                this.InvitationEnvoyé.add(m);
                envoyé_a.InvitationRecu.add(m);
                cx.closeConnexion();
                System.out.println("liste des invitations recus");
                envoyé_a.listeInvitationRecu();
                exist = true;
            }
           catch (NoResultException e)
           {
               System.out.println("il n'existe pas le membre " +  nom);
               System.out.println("vous devez donner un membre existant");
           }
        }
        while(exist==false);
    }
}
