package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
@Entity
public class Repair {
    @Id
    @GeneratedValue
    private long repairID;
    @ManyToOne
    private Servicer servicer;
    @ManyToOne
    private Car car;
    private Date date;
    private int price;
    @OneToMany
    @JsonIgnore
    private Set<RepaitItem> items;



    public Repair() {
    }

    public Repair(long repairID, Servicer servicer, Car car, Date date, int price,Set<RepaitItem> items) {
        setRepairID(repairID);
        setServicer(servicer);
        setCar(car);
        setDate(date);
        setPrice(price);
        setItems(items);
    }

    public long getRepairID() {
        return repairID;
    }

    public void setRepairID(long repairID) {
        if(repairID>0) {
            this.repairID = repairID;
        }else {
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }
    }

    public Servicer getServicer() {
        return servicer;
    }

    public void setServicer(Servicer servicer) {
        if(servicer!=null){
        this.servicer = servicer;
        }else {
            throw new IllegalArgumentException("Serviser ne sme biti null");
        }

    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if(car!=null){
            this.car = car;
        }else {
            throw new IllegalArgumentException("Automobil ne sme biti null");
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if(date!=null){
            this.date = date;
        }else {
            throw new IllegalArgumentException("Datum ne sme biti null");
        }
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price>=0) {
            this.price = price;
        }else{
            throw new IllegalArgumentException("Cena mora biti pozitivna");
        }
    }

    public Set<RepaitItem> getItems() {
        return items;
    }

    public void setItems(Set<RepaitItem> items) {
        if(items!=null && !items.isEmpty()) {
            this.items = items;
        }else {
            throw new IllegalArgumentException("Mora postojati bar 1 stavka popravke");
        }
    }
}
