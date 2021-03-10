package models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Page {
    @ManyToOne
    private Membre membre;

    @OneToMany(mappedBy = "page")
    private List<Aimes> Fans = new ArrayList<>();

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long idPage;

    @Column( name = "pageName", unique = false, nullable = false, length = 100 )
    private String namePage;

    @Column( name = "pageGenre", unique = false, nullable = false, length = 100 )
    private Genre genrePage;

    @Column(name = "dateCreation", length = 100)
    String date;

    public Page(Membre membre, String namePage, Genre genrePage, String date) {
        this.membre = membre;
        this.namePage = namePage;
        this.genrePage = genrePage;
        this.date = date;
    }
    public Page() {
    }

    public Genre getGenrePage() {
        return genrePage;
    }

    public void setGenrePage(Genre genrePage) {
        this.genrePage = genrePage;
    }


    public Membre getMembre() {
        return membre;
    }

    public void setMembre(Membre membre) {
        this.membre = membre;
    }

    public Long getId() {
        return idPage;
    }
    public String getNamePage() {
        return namePage;
    }

    public void setNamePage(String namePage) {
        this.namePage = namePage;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Page{" +
                "membre=" + membre +
                ", id=" + idPage +
                ", namePage='" + namePage + '\'' +
                ", genrePage=" + genrePage +
                ", date='" + date + '\'' +
                '}';
    }
}
