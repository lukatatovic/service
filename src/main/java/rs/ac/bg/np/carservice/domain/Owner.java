package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
/**
 * Entitet u bazi podataka/domenska klasa vlasnika.
 *
 * Koristi se da predstavi vezu sa tabelom "owner" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o servisu to jest ownerID, ime, prezime, broj telefpna i skup automobila vlasnika.
 *
 * @author Luka
 */
@Entity
public class Owner {
    /**
     * Automatski generisan identifikator vlasnika koji je unikatana i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long ownerID;
    /**
     * Ime vlasnika koje je tipa String.
     */
    private String name;
    /**
     * Prezime vlasnika koje je tipa String.
     */
    private String surname;
    /**
     * Broj telefona vlasnika koji je tipa String.
     */
    private String phoneNumber;
    /**
     * Skup automobila koje vlasnik poseduje.
     */
    @JsonIgnore
    @OneToMany
    private Set<Car> cars;
    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase Owner.
     */
    public Owner() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Owner sa zadatim vrednostima
     *
     * @param ownerID ID vlasnika
     * @param name ime vlasnika
     * @param surname prezime vlasnika
     * @param phoneNumber broj telefona vlasnika
     * @param cars skup automobila koje vlasnik poseduje
     */
    public Owner(long ownerID, String name, String surname, String phoneNumber, Set<Car> cars) {
        setOwnerID(ownerID);
        setName(name);
        setSurname(surname);
        setPhoneNumber(phoneNumber);
        setCars(cars);
    }
    /**
     * Vraca ID vlasnika.
     * @return ownerID kao long.
     */
    public long getOwnerID() {
        return ownerID;
    }
    /**
     * Postavlja ownerID na zadatu vrednost.
     *
     * @param ownerID ID vlasnika.
     * @throws IllegalArgumentException ako je ownerID manji od 1.
     */
    public void setOwnerID(long ownerID) {
        if(ownerID>0) {
            this.ownerID = ownerID;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }

    }
    /**
     * Vraca ime vlasnika.
     * @return name kao String,.
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja ime vlasnika na zadatu vrednost.
     *
     * @param name ime vlasnika.
     * @throws IllegalArgumentException ako je ime prazan String ili null.
     */
    public void setName(String name) {
        if(!name.isEmpty() && name!=null) {
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Ime ne moze biti prazno");
        }

    }
    /**
     * Vraca prezime vlasnika.
     * @return surname kao String,.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Postavlja prezime vlasnika na zadatu vrednost.
     *
     * @param surname prezime vlasnika.
     * @throws IllegalArgumentException ako je prezime prazan String ili null.
     */
    public void setSurname(String surname) {
        if(!surname.isEmpty() && surname!=null) {
            this.surname = surname;
        }
        else{
            throw new IllegalArgumentException("Prezime ne moze biti prazno");
        }
    }
    /**
     * Vraca broj telefona vlasnika.
     * @return phoneNumber kao String,.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Postavlja broj telefona vlasnika na zadatu vrednost.
     *
     * @param phoneNumber prezime vlasnika.
     * @throws IllegalArgumentException ako je broj telefona prazan String ili null.
     */
    public void setPhoneNumber(String phoneNumber) {
        if(!phoneNumber.isEmpty() && phoneNumber!=null) {
            this.phoneNumber = phoneNumber;
        }else{
            throw new IllegalArgumentException("Broj telefona ne moze biti prazan");
        }

    }
    /**
     * Vraca automobile koje vlasnik poseduje.
     *
     * @return cars kao skup automobila vlasnika.
     */
    public Set<Car> getCars() {
        return cars;
    }
    /**
     * Postavlja automobile na zadatu vrednost.
     *
     * @param cars kao skup automobila vlasnika.
     * @throws IllegalArgumentException ako je skup automobila null.
     */
    public void setCars(Set<Car> cars) {
        if(cars==null) {
            this.cars = cars;
        }else{
            throw new IllegalArgumentException("Vlasnik mora da ima bar 1 automobil");
        }

    }
}
