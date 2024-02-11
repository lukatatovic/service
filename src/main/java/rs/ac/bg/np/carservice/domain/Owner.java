package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Owner {
    @Id
    @GeneratedValue
    private long ownerID;
    private String name;
    private String surname;
    private String phoneNumber;
    @JsonIgnore
    @OneToMany
    private Set<Car> cars;

    public Owner() {
    }

    public Owner(long ownerID, String name, String surname, String phoneNumber, Set<Car> cars) {
        setOwnerID(ownerID);
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
        setCars(cars);
    }

    public long getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(long ownerID) {
        if(ownerID>0) {
            this.ownerID = ownerID;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 0");
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(!surname.isEmpty() && surname!=null) {
            this.surname = surname;
        }
        else{
            throw new IllegalArgumentException("Prezime ne moze biti prazno");
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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        if(cars==null) {
            this.cars = cars;
        }else{
            throw new IllegalArgumentException("Vlasnik mora da ima bar 1 automobil");
        }

    }
}
