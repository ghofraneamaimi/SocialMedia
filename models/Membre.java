package models;
import database.Connexion;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Membre   {
    @Transient
    Date d= new Date();

    //liste de ses propres groupes(celui est l'admin)
    @OneToMany
    private List<Groupe> myGroupes = new ArrayList<>();

    //liste des groupes qui le membre a rejoint
    @OneToMany (mappedBy = "membreG",fetch = FetchType.LAZY)
    List<Rejoindre> groupeJoint = new ArrayList<>();


    @OneToMany(mappedBy="membre",fetch = FetchType.EAGER,cascade = CascadeType.ALL )
    private List<Page> pages = new ArrayList<Page>();

    @OneToMany(mappedBy = "page",fetch = FetchType.LAZY)
    private List<Aimes> pagesAimees = new ArrayList<>();

    @OneToMany
    public List<Membre> amis = new ArrayList<Membre>();

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

    public Membre(String nom, String prenom, String mail, String password, int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.password = password;
        this.age = age;
        this.InvitationEnvoye = new ArrayList<MemberShip>();
        this.InvitationRecu = new ArrayList<MemberShip>();
    }

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
        return d;
    }

    public void setDate(Date d) {
        this.d = d;
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

    public List<Groupe> getMyGroupes() {
        return myGroupes;
    }

    public void setMyGroupes(List<Groupe> myGroupes) {
        this.myGroupes = myGroupes;
    }

    public List<Rejoindre> getGroupeJoint() {
        return groupeJoint;
    }

    public void setGroupeJoint(List<Rejoindre> groupeJoint) {
        this.groupeJoint = groupeJoint;
    }

    public Membre() { }

    public List<MemberShip> getInvitationEnvoye() {
        return InvitationEnvoye;
    }

    public List<MemberShip> getInvitationRecu() {
        return InvitationRecu;
    }

    public void setInvitationRecu(List<MemberShip> invitationRecu) {
        InvitationRecu = invitationRecu;
    }

    public void listeInvitationRecu()
    {
        for (int i = 0; i < this.InvitationRecu.size(); i++) {
            System.out.println(this.InvitationRecu.get(i));
        }
    }

    public void listeInvitationEnvoye()
    {
       for (int i = 0; i < this.InvitationEnvoye.size(); i++) {
           System.out.println(InvitationEnvoye.get(i));
       }
    }

    public void envoyerInvitation(String nom)
    {    boolean exist = false;
        do{
            try {
                String q= "from Membre where nom =: nom";
                Query query = Connexion.getSession().createQuery("from Membre where nom =: nom");
                query.setParameter("nom", nom);
                Membre envoye_a = (Membre) query.getSingleResult();
                System.out.println(envoye_a);
                MemberShip m = new MemberShip(this.getId(),envoye_a.getId());
                Connexion.save(m);
                Connexion.save(this);
                envoye_a.InvitationRecu.add(m);
                this.InvitationEnvoye.add(m);
                Connexion.getSession().update(envoye_a);
                System.out.println("liste des invitations recus pour " + envoye_a.getNom());
                envoye_a.listeInvitationRecu();
                System.out.println("liste des invitations envoyées par " + this.getNom());
                System.out.println(this.getInvitationEnvoye());
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

    public  void listeAmies()
    {
        for (int i = 0; i < this.getAmis().size(); i++) {
            System.out.println(getAmis().get(i));
        }
    }

    public  void accepterInvitation(Membre m, int x  )
    {
           Transaction tx =Connexion.getSession().beginTransaction();
           this.getAmis().add(m);
           this.getInvitationRecu().remove(x);
           System.out.println("liste d'amis \n");
           this.listeAmies();
           tx.commit();
       }

    public  void listePage()
    {
        for (int i = 0; i < this.getPages().size(); i++) {
            System.out.println(getPages().get(i));
        }
    }

    public boolean creePage (String nom , String  genre)
    {
        Page p = new Page();
        Genre gr = Genre.valueOf(genre);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        p.setMembre(this);
        p.setDate(formatter.format(d));
        p.setNamePage(nom);
        p.setGenrePage(gr);
        pages.add(p);
        Connexion.save(p);
        return false;
    }

    void listePagesAimees()
    {
        for (int i = 0; i < this.getPagesAimees().size(); i++) {
            System.out.println(getPagesAimees().get(i));
        }
    }

     public void aimerPage(String nom)
     {
        boolean exist = false;
        do {
            try {
                Query query = Connexion.getSession().createQuery("from Page where namePage = :nom");
                query.setParameter("nom", nom);
                Page p = (Page) query.getSingleResult();
                System.out.println(p);
                Aimes m = new Aimes(this.getId(), p.getId());
                Connexion.save(m);
                Connexion.getSession().update(p);
                this.getPagesAimees().add(m);

            } catch (NoResultException e) {
                System.out.println("page n'existe pas  " + nom);
                System.out.println("vous devez donner une page existant");
            }

        }
        while (exist == false);
    }

    public boolean creeGroupe (String nom , String  privacy)
    {
        Groupe p = new Groupe();
        Privacy gr = Privacy.valueOf(privacy);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        p.setMembre(this);
        p.setDate(formatter.format(d));
        p.setNameGroupe(nom);
        p.setPrivacyGroupe(gr);
        myGroupes.add(p);
        Connexion.save(p);
        return false;
    }


}
