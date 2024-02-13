package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/**
 * Entitet u bazi podataka/domenska klasa servisa
 *
 * Koristi se da predstavi vezu sa tabelom "service" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o servisu to jest serviceID, naziv, adresa, broj telefpna i skup servisera servisa.
 *
 * @author Luka
 */
@Entity
public class Service {
    /**
     * Automatski generisan identifikator servisa koji je unikatan i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long serviceID;
    /**
     * Naziv servisa koji je tipa String.
     */
    private String name;
    /**
     * Adresa servisa koja je tipa String.
     */
    private String adress;
    /**
     * Broj telefona servisa koji je tipa String.
     */
    private String phoneNumber;
    /**
     * Skup servisera koji je rade u servisu.
     */
    @OneToMany
    @JsonIgnore
    private Set<Servicer> servicers;

    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase Service.
     */
    public Service() {
    }
    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Service sa zadatim vrednostima
     *
     * @param serviceID ID servisa
     * @param name naziv servisa
     * @param adress adresu servisa
     * @param phoneNumber broj telefona servisa
     * @param servicers skup servisera koji rade u servisu
     */
    public Service(long serviceID, String name, String adress, String phoneNumber, Set<Servicer> servicers) {
        setServiceID(serviceID);
        setName(name);
        setAdress(adress);
        setPhoneNumber(phoneNumber);
        setServicers(servicers);
    }

    /**
     * Vraca ID servisa.
     * @return serviceID kao long.
     */
    public long getServiceID() {
        return serviceID;
    }

    /**
     * Postavlja serviceID na zadatu vrednost.
     *
     * @param serviceID ID servisa.
     * @throws IllegalArgumentException ako je serviceID manji od 1.
     */
    public void setServiceID(long serviceID) {
        if(serviceID>0) {
            this.serviceID = serviceID;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }
    }

    /**
     * Vraca naziv servisa.
     * @return name kao String.
     */
    public String getName() {
        return name;
    }

    /**
     * Postavlja naziv servisa na zadatu vrednost.
     *
     * @param name naziv servisa.
     * @throws IllegalArgumentException ako je naziv prazan String ili null.
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
     * Vraca adresu servisa.
     * @return adress kao String.
     */
    public String getAdress() {
        return adress;
    }
    /**
     * Postavlja adresu servisa na zadatu vrednost.
     *
     * @param adress adresu servisa.
     * @throws IllegalArgumentException ako je adresa prazan String ili null.
     */
    public void setAdress(String adress) {
        if(!adress.isEmpty() && adress!=null){
        this.adress = adress;
        }else{
            throw new IllegalArgumentException("Adresa ne moze biti prazna");
        }
    }
    /**
     * Vraca broj telefona servisa.
     * @return phoneNumber kao String.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Postavlja broj telefona na zadatu vrednost.
     *
     * @param phoneNumber broj telefona servisa.
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
     * Vraca servisere koji rade u servisu.
     *
     * @return servicers kao skup servisera servisa.
     */
    public Set<Servicer> getServicers() {
        return servicers;
    }

    /**
     * Postavlja servicere na zadatu vrednost.
     *
     * @param servicers kao skup servisera servisa.
     */
    public void setServicers(Set<Servicer> servicers) {
            this.servicers = servicers;
    }

    /**
     * Poredi dva servisa po ID-ju.
     * @param o koji predstavlja servis.
     * @return
     * <ul>
     *      <li> true  - ako je servidId oba servisa isti ili ako je unet isti objekat </li>
     *      <li> false - ako je unet null objekat ili ako nije klase Service</li>
     * </ul>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Service service = (Service) o;
        return serviceID == service.serviceID;
    }

    /**
     * Izracunava hash code na osnovu servideId.
     *
     * @return hash code na osnovu servideId.
     */
    @Override
    public int hashCode() {
        return Objects.hash(serviceID);
    }
}
