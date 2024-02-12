package rs.ac.bg.np.carservice.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class RepaitItem {
    @Id
    @GeneratedValue
    private long repaiItemId;
    @ManyToOne
    private Repair repair;
    @OneToOne
    private Part part;

    public RepaitItem() {
    }

    public RepaitItem(long repaiItemId, Repair repair, Part part) {
        setRepaiItemId(repaiItemId);
        setRepair(repair);
        setPart(part);
    }

    public long getRepaiItemId() {
        return repaiItemId;
    }

    public void setRepaiItemId(long repaiItemId) {
        if(repaiItemId>0) {
            this.repaiItemId = repaiItemId;
        }else {
            throw new IllegalArgumentException("Id mora biti veci od 0");
        }
    }


    public Repair getRepair() {
        return repair;
    }

    public void setRepair(Repair repair) {
        if(repair!=null) {
            this.repair = repair;
        }else {
            throw new IllegalArgumentException("Popravka ne moze biti null");
        }
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        if(part!=null) {
            this.part = part;
        }else {
            throw new IllegalArgumentException("Deo ne moze biti null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepaitItem that = (RepaitItem) o;
        return repaiItemId == that.repaiItemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(repaiItemId);
    }
}
