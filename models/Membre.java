package models;
import com.sun.org.apache.xalan.internal.xsltc.dom.StepIterator;
import database.Connexion;
import org.hibernate.query.Query;
import javax.persistence.*;
import javax.transaction.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Membre   {
    Date date = new Date();
    @OneToMany(mappedBy="membre",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    private List<Page> pages = new ArrayList<Page>();

    @OneToMany(mappedBy = "page",fetch = FetchType.LAZY)
    private List<Aimes> pagesAimees = new ArrayList<>();

    @OneToMany
    public List<Membre> amis = new ArrayList<>();

    //la liste des amitiés demandées à cet utilisateur
    @OneToMany(mappedBy = "sourceMembre",fetch = FetchType.LAZY)
    public List<MemberShip> InvitationEnvoye = new ArrayList<>() ;

    //la liste des amitiés demandées par cet utilisateur
    @OneToMany(mappedBy = "targetMembre",fetch = FetchType.LAZY)
    public List<MemberShip> InvitationRecu  = new ArrayList<>();


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
  /*  @Column(name = "amis", unique = false, nullable = false)
    */

    public Membre(String nom, String prenom, String mail, String password, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.age = age;
        this.InvitationEnvoye = new ArrayList<MemberShip>();
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

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }
    public List<Aimes> getPagesAimees() {
        return pagesAimees;
    }

    public void setPagesAimees(List<Aimes> pagesAimees) {
        this.pagesAimees = pagesAimees;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Membre> getAmis() {
        return amis;
    }

    public void setAmis(List<Membre> amis) {
        this.amis = amis;
    }

    public void setInvitationEnvoye(List<MemberShip> invitationEnvoye) {
        InvitationEnvoye = invitationEnvoye;
    }


    public Membre() { }


    /*public List<MemberShip> getAmis() {
        return amis;
    }

    public void setAmis(List<MemberShip> amis) {
        this.amis = amis;
    }*/

    public List<MemberShip> getInvitationEnvoye() {
        return InvitationEnvoye;
    }
    public List<MemberShip> getInvitationRecu() {
        return InvitationRecu;
    }

    public void setInvitationRecu(List<MemberShip> invitationRecu) {
        InvitationRecu = invitationRecu;
    }

    public List<MemberShip> getInvitationEnvoyé() {
        return InvitationEnvoye;
    }

    public void setInvitationEnvoyé(List<MemberShip> invitationEnvoyé) {
        InvitationEnvoye = invitationEnvoyé;
    }



    @Transactional
    public void listeInvitationRecu()
    {

        for (int i = 0; i < this.getInvitationRecu().size(); i++) {
            System.out.println(this.getInvitationRecu().get(i));
        }
    }
    @Transactional
    public void listeInvitationEnvoye()
    {
       for (int i = 0; i < this.InvitationEnvoye.size(); i++) {
           System.out.println(InvitationEnvoye.get(i));
       }
    }

    @Transactional
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
                cx.save(this);
                envoyé_a.InvitationRecu.add(m);
                this.InvitationEnvoye.add(m);
                cx.getSession().update(envoyé_a);
                System.out.println("liste des invitations recus pour " + envoyé_a.getNom());
                envoyé_a.listeInvitationRecu();
                System.out.println("liste des invitations envoyées par " + this.getNom());
                System.out.println(this.getInvitationEnvoyé());
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
  @Transactional
   public  void accepterInvitation(Membre m )
   {
      this.getAmis().add(m);
       System.out.println("liste d'amis \n");
       this.getAmis();
   }

    @Transactional
    public  void listePage()
    {
        for (int i = 0; i < this.getPages().size(); i++) {
            System.out.println(getPages().get(i));
        }
    }
    @Transactional
    public boolean creePage (String nom , String  genre)
    {
        Connexion cx = new Connexion();
        Page p = new Page();
        Genre gr = Genre.valueOf(genre);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        p.setMembre(this);
        p.setDate(formatter.format(date));
        p.setNamePage(nom);
        p.setGenrePage(gr);
        pages.add(p);
        cx.save(p);
        cx.closeConnexion();
        return false;
    }

    @Transactional
    void listePagesAimées()
    {
        for (int i = 0; i < this.getPagesAimees().size(); i++) {
            System.out.println(getPagesAimees().get(i));
        }
    }
    @Transactional
     public void aimerPage(String nom) {
        boolean exist = false;
        do {
            Connexion cx = new Connexion();
            try {
                Query query = cx.getSession().createQuery("from Page where namePage = :nom");
                query.setParameter("nom", nom);
                Page p = (Page) query.getSingleResult();
                System.out.println(p);
                Aimes m = new Aimes(this.getId(), p.getId());
                cx.save(m);
                //cx.save(this);
                cx.getSession().update(p);
                this.getPagesAimees().add(m);

            } catch (NoResultException e) {
                System.out.println("page n'existe pas  " + nom);
                System.out.println("vous devez donner une page existant");
            }
            cx.closeConnexion();

        }
        while (exist == false);
    }

}
