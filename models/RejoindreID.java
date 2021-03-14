package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RejoindreID implements Serializable {

    private long membreID;
    private long groupeID;

    public RejoindreID(long membreID, long groupeID) {
        this.membreID = membreID;
        this.groupeID = groupeID;
    }

    public long getMembreID() {
        return membreID;
    }

    public void setMembreID(long membreID) {
        this.membreID = membreID;
    }

    public long getGroupeID() {
        return groupeID;
    }

    public void setGroupeID(long groupeID) {
        this.groupeID = groupeID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RejoindreID that = (RejoindreID) o;
        return membreID == that.membreID && groupeID == that.groupeID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(membreID, groupeID);
    }
}
