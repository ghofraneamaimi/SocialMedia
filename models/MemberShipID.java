package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MemberShipID implements Serializable {


//l'utilisateur qui a demandé l'amitié

    private Long sourceMembre;

    //l'utilisateur auquel l'amitié a été demandée

    private Long targetMembre;



    public MemberShipID() {
    }
     public MemberShipID(Long sourceMembre, Long targetMembre) {
        this.sourceMembre=sourceMembre;
        this.targetMembre=targetMembre;
     }

     public Long getSourceMembre() {
         return sourceMembre;
     }

     public Long getTargetMembre() {
         return targetMembre;
     }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberShipID)) return false;
        MemberShipID that = (MemberShipID) o;
        return sourceMembre.equals(that.sourceMembre) &&
                targetMembre.equals(that.targetMembre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceMembre, targetMembre);
    }

    @Override
    public String toString() {
        return "MemberShipID{" +
                "sourceMembre=" + sourceMembre +
                ", targetMembre=" + targetMembre +
                '}';
    }
}
