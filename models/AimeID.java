package models;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class AimeID implements Serializable {
    private long membreID;
    private long pageID;

    public AimeID(long membreID, long pageID) {
        this.membreID = membreID;
        this.pageID = pageID;
    }

    public long getMembreID() {
        return membreID;
    }

    public void setMembreID(long membreID) {
        this.membreID = membreID;
    }

    public long getPageID() {
        return pageID;
    }

    public void setPageID(long pageID) {
        this.pageID = pageID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AimeID)) return false;
        AimeID aimeID = (AimeID) o;
        return membreID == aimeID.membreID &&
                pageID == aimeID.pageID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(membreID, pageID);
    }
}
