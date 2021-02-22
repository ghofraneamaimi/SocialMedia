package models;
import javax.persistence.*;
import java.lang.reflect.Member;
import java.util.Enumeration;


@Entity
public class MemberShip {
    @EmbeddedId
    private MemberShipID membershipId;

    @Column(name = "statuInvitation")
    StatusInvitation statuInvitation;


    @Override
    public String toString() {
        return "MemberShip{" +
                "membershipId=" + membershipId +
                ", statuInvitation=" + statuInvitation +
                '}';
    }



    public MemberShipID getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(MemberShipID membershipId) {
        this.membershipId = membershipId;
    }

    public StatusInvitation getStatuInvitation() {
        return statuInvitation;
    }

    public void setStatuInvitation(StatusInvitation statuInvitation) {
        this.statuInvitation = statuInvitation;
    }

    public MemberShip(Long sourceMembre, Long targetMembre) {
        this.membershipId = new MemberShipID(sourceMembre,targetMembre);
    }


}
