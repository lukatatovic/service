package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
@Entity
public class Service {

    @Id
    @GeneratedValue
    private long serviceID;
    private String name;
    private String adress;
    private String phoneNumber;
    @OneToMany
    @JsonIgnore
    private Set<Servicer> servicers;

    public Service() {
    }

    public Service(long serviceID, String name, String adress, String phoneNumber, Set<Servicer> servicers) {
        setServiceID(serviceID);
        setName(name);
        setAdress(adress);
        setPhoneNumber(phoneNumber);
        setServicers(servicers);
    }

    public long getServiceID() {
        return serviceID;
    }

    public void setServiceID(long serviceID) {
        if(serviceID>0) {
            this.serviceID = serviceID;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 00");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(!name.isEmpty() && name!=null) {
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Ime ne moze biti prazno");
        }
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        if(!adress.isEmpty() && adress!=null){
        this.adress = adress;
        }else{
            throw new IllegalArgumentException("Adresa ne moze biti prazna");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(!phoneNumber.isEmpty() && phoneNumber!=null) {
            this.phoneNumber = phoneNumber;
        }else{
            throw new IllegalArgumentException("Broj telefona ne moze biti prazan");
        }
    }

    public Set<Servicer> getServicers() {
        return servicers;
    }

    public void setServicers(Set<Servicer> servicers) {
        if(servicers!=null) {
            this.servicers = servicers;
        }else{
            throw new IllegalArgumentException("Serviseri ne mogu biti null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return serviceID == service.serviceID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceID);
    }
}
