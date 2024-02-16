package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;
/**
 * Entitet u bazi podataka/domenska klasa servisera.
 *
 * Koristi se da predstavi vezu sa tabelom "servicer" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o serviseru to jest servicerID, ime, prezime, servis u kome radi i skup izvrsenih popravki.
 *
 * @author Luka
 */
@Entity
public class Servicer {
    /**
     * Automatski generisan identifikator servisera koji je unikatan i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long servicerID;
    /**
     * Ime servisera koje je tipa String.
     */
    private String name;
    /**
     * Prezime servisera koje je tipa String.
     */
    private String surname;
    /**
     * Servise u kojem serviser radi koji je tipa Service.
     */
    @ManyToOne
    private Service service;
    /**
     * Skup popravki koje je serviser izvrsio.
     */
    @OneToMany
    @JsonIgnore
    private Set<Repair> repairs;
    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase Servicer.
     */
    public Servicer() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Servicer sa zadatim vrednostima
     *
     * @param name ime servisera
     * @param surname prezime servisera
     */
    public Servicer(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Servicer sa zadatim vrednostima
     *
     * @param servicerID ID servisera
     * @param name ime servisera
     * @param surname prezime servisera
     * @param service servis u kojem serviser radi
     * @param repairs skup popravki koje je serviser izvrsio
     */
    public Servicer(long servicerID, String name, String surname, Service service, Set<Repair> repairs) {
        setServicerID(servicerID);
        setName(name);
        setSurname(surname);
        setService(service);
        setRepairs(repairs);
    }
    /**
     * Vraca ID servisera.
     * @return servicerID kao long.
     */
    public long getServicerID() {
        return servicerID;
    }
    /**
     * Postavlja servicerID na zadatu vrednost.
     *
     * @param servicerID ID servisa.
     * @throws IllegalArgumentException ako je servicerID manji od 1.
     */
    public void setServicerID(long servicerID) {
        if( servicerID>0) {
            this.servicerID = servicerID;
        }
        else{
            throw new IllegalArgumentException("ID servisera mora biti veci od 0");
        }
    }
    /**
     * Vraca ime servisera.
     * @return name kao String.
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja ime servisera na zadatu vrednost.
     *
     * @param name ime servisera.
     * @throws IllegalArgumentException ako je ime prazan String ili null.
     */
    public void setName(String name) {
        if(name!=null &&!(name.isEmpty())  ){
            this.name = name;
        }else {
            throw new IllegalArgumentException("Ime ne sme biti prazno");
        }
    }
    /**
     * Vraca prezime servisera.
     * @return surname kao String.
     */
    public String getSurname() {
        return surname;
    }
    /**
     * Postavlja prezime servisera na zadatu vrednost.
     *
     * @param surname ime servisera.
     * @throws IllegalArgumentException ako je prezime prazan String ili null.
     */
    public void setSurname(String surname) {
        if(surname!=null && !surname.isEmpty() ){
            this.surname = surname;
        }else{
            throw new IllegalArgumentException("Prezime ne sme biti prazno");
        }

    }

    /**
     * Vraca servis u kojem serviser radi.
     * @return service kao Service.
     */
    public Service getService() {
        return service;
    }

    /**
     * Postavlja servis u kojem serviser radi na zadatu vrednost.
     *
     * @param service servis u kojem serviser radi
     */
    public void setService(Service service) {
            this.service = service;
    }

    /**
     * Vraca popravke koje je serviser izvrsio.
     * @return repairs kao skup popravki.
     */
    public Set<Repair> getRepairs() {
        return repairs;
    }

    /**
     * Postavlja popravke na zadaty vrednost.
     * @param repairs skup popravki koje je serviser izvrsio.
     */
    public void setRepairs(Set<Repair> repairs) {
            this.repairs = repairs;
    }
    /**
     * Poredi dva servisera po ID-ju.
     * @param o koji predstavlja servisera.
     * @return
     * <ul>
     *      <li> true  - ako je servicerId oba servisera isti ili ako je unet isti objekat </li>
     *      <li> false - ako je unet null objekat ili ako nije klase Servicer</li>
     * </ul>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Servicer servicer = (Servicer) o;
        return servicerID == servicer.servicerID;
    }
    /**
     * Izracunava hash code na osnovu servicerId.
     *
     * @return hash code na osnovu servicerId.
     */
    @Override
    public int hashCode() {
        return Objects.hash(servicerID);
    }
    /**
     * toString metoda
     * @return vrednosti atributa servisera kao String.
     */
    @Override
    public String toString() {
        return "Servicer{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
