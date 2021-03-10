package models;

import javax.persistence.*;

@Entity

public class Aimes {
    @EmbeddedId
    AimeID aimeid;

    @ManyToOne
    Membre membre;

    @ManyToOne
    Page page;

    public Aimes (Long membreID, long pageID )
    {
        aimeid = new AimeID(membreID, pageID);
    }

    @Override
    public String toString() {
        return "Aimes{" +
                "aimeid=" + aimeid +
                ", membre=" + membre +
                ", page=" + page +
                '}';
    }
}
