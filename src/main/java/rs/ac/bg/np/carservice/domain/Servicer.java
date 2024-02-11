package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Servicer {
    @Id
    @GeneratedValue
    private long servicerID;

    private String name;
    private String surname;
    @ManyToOne
    private Service service;

    @OneToMany
    @JsonIgnore
    private Set<Repair> repairs;
    public Servicer() {
    }

    public Servicer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Servicer(long servicerID, String name, String surname, Service service, Set<Repair> repairs) {
        setServicerID(servicerID);
        setName(name);
        setSurname(surname);
        setService(service);
        setRepairs(repairs);
    }

    public long getServicerID() {
        return servicerID;
    }

    public void setServicerID(long servicerID) {
        if( servicerID>0) {
            this.servicerID = servicerID;
        }
        else{
            throw new IllegalArgumentException("ID servisera mora biti veci od 0");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!(name.isEmpty()) && name !=null ){
            this.name = name;
        }else {
            throw new IllegalArgumentException("Ime ne sme biti prazno");
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(!surname.isEmpty() && surname !=null){
            this.surname = surname;
        }else{
            throw new IllegalArgumentException("Prezime ne sme biti prazno");
        }

    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
            this.service = service;
    }
    public Set<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(Set<Repair> repairs) {
        if(repairs!=null) {
            this.repairs = repairs;
        }else {
            throw new IllegalArgumentException("Popravke ne mogu biti null");
        }
    }
}
