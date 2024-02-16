package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entitet u bazi podataka/domenska klasa dobavljaca
 *
 * Koristi se da predstavi vezu sa tabelom "suplier" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o dobavljacu to jest suplierID, naziv, adresa i broj telefona.
 *
 * @author Luka
 */
@Entity
public class Suplier {
    /**
     * Automatski generisan identifikator dobavljaca koji je unikatan i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long suplierId;
    /**
     * Naziv dobavljaca koji je tipa String.
     */
    private String name;
    /**
     * Adresa dobavljaca koja je tipa String.
     */
    private String adress;
    /**
     * Broj telefona dobavljaca koji je tipa String.
     */
    private String phoneNumber;
    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase Suplier.
     */
    public Suplier() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Suplier sa zadatim vrednostima
     *
     * @param suplierId ID dobavljaca
     * @param name naziv dobavljaca
     * @param adress adresa dobavljaca
     * @param phoneNumber broj telefona dobavljaca
     */
    public Suplier(long suplierId, String name, String adress, String phoneNumber) {
        setSuplierId(suplierId);
        setName(name);
        setAdress(adress);
        setPhoneNumber(phoneNumber);
    }
    /**
     * Vraca ID dobavljaca.
     * @return suplierID kao long.
     */
    public long getSuplierId() {
        return suplierId;
    }
    /**
     * Postavlja suplierID na zadatu vrednost.
     *
     * @param suplierId ID servisa.
     * @throws IllegalArgumentException ako je suplierID manji od 1.
     */
    public void setSuplierId(long suplierId) {
        if(suplierId>0) {
            this.suplierId = suplierId;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }
    }
    /**
     * Vraca naziv dobavljaca.
     * @return name kao String,.
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja naziv dobavljaca na zadatu vrednost.
     *
     * @param name naziv dobavljaca.
     * @throws IllegalArgumentException ako je naziv prazan String ili null.
     */
    public void setName(String name) {
        if(name!=null && !name.isEmpty() ) {
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Ime ne moze biti prazno");
        }
    }
    /**
     * Vraca adaresu dobavljaca.
     * @return adress kao String,.
     */
    public String getAdress() {
        return adress;
    }
    /**
     * Postavlja adresu dobavljaca na zadatu vrednost.
     *
     * @param adress adresa dobavljaca.
     * @throws IllegalArgumentException ako je adresa prazan String ili null.
     */
    public void setAdress(String adress) {
        if(adress!=null &&!adress.isEmpty() ) {
            this.adress = adress;
        }
        else{
            throw new IllegalArgumentException("Adresa ne moze biti prazna");
        }
    }
    /**
     * Vraca broj telefona dobavljaca.
     * @return phoneNumber kao String,.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Postavlja broj telefona dobavljaca na zadatu vrednost.
     *
     * @param phoneNumber adresa dobavljaca.
     * @throws IllegalArgumentException ako je broj telefona prazan String ili null.
     */
    public void setPhoneNumber(String phoneNumber) {
        if(phoneNumber!=null &&!phoneNumber.isEmpty() ) {
            this.phoneNumber = phoneNumber;
        }else{
            throw new IllegalArgumentException("Broj telefona ne moze biti prazan");
        }
    }
    /**
     * Poredi dva dobavljaca po ID-ju.
     * @param o koji predstavlja dobavljaca.
     * @return
     * <ul>
     *      <li> true  - ako je suplierId oba dobavljaca isti ili ako je unet isti objekat </li>
     *      <li> false - ako je unet null objekat ili ako nije klase Suplier</li>
     * </ul>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Suplier suplier = (Suplier) o;
        return suplierId == suplier.suplierId;
    }
    /**
     * Izracunava hash code na osnovu suplierId.
     *
     * @return hash code na osnovu suplierId.
     */
    @Override
    public int hashCode() {
        return Objects.hash(suplierId);
    }
    /**
     * toString metoda
     * @return vrednosti atributa dobavljaca kao String.
     */
    @Override
    public String toString() {
        return "Suplier{" +
                "name='" + name + '\'' +
                ", adress='" + adress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
