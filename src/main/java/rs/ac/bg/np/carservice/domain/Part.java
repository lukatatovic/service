package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

/**
 * Entitet u bazi podataka/domenska klasa dela
 *
 * Koristi se da predstavi vezu sa tabelom "part" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o delu to jest partID, naziv, cena, marka automobila za koju je deo, model automobila za koj je deo i dobavljaca .
 *
 * @author Luka
 */
@Entity
public class Part {
    /**
     * Automatski generisan identifikator dela koji je unikatan i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long partID;
    /**
     * Naziv dela koji je tipa String.
     */
    private String name;
    /**
     * Cena dela koja je tipa int.
     */
    private int price;
    /**
     * Marka automobila za koju je deo koja je tipa String.
     */
    private String brand;
    /**
     * Model automobila za koji je deo koji je tipa String.
     */
    private String model;
    /**
     * Dobavljac od koga se nabavlja deo tip Suplier.
     */
    @OneToOne
    @JsonIgnore
    private Suplier suplier;
    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase Part.
     */
    public Part() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Part sa zadatim vrednostima
     *
     * @param partID ID dela
     * @param name naziv dela
     * @param price cena dela
     * @param brand marka automobila za koju je deo
     * @param model model automobila za koji je deo
     * @param suplier dobavljac dela
     */
    public Part(long partID, String name, int price, String brand, String model, Suplier suplier) {
        setPartID(partID);
        setName(name);
        setPrice(price);
        setBrand(brand);
        setModel(model);
        setSuplier(suplier);
    }
    /**
     * Vraca ID dela.
     * @return partID kao long.
     */
    public long getPartID() {
        return partID;
    }
    /**
     * Postavlja partID na zadatu vrednost.
     *
     * @param partID ID dela.
     * @throws IllegalArgumentException ako je partID manji od 1.
     */
    public void setPartID(long partID) {
        if(partID>0) {
            this.partID = partID;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }
    }
    /**
     * Vraca naziv dela.
     * @return name kao String.
     */
    public String getName() {
        return name;
    }
    /**
     * Postavlja naziv dela na zadatu vrednost.
     *
     * @param name naziv dela.
     * @throws IllegalArgumentException ako je naziv prazan String ili null.
     */
    public void setName(String name) {
        if(name!=null && !name.isEmpty()) {
            this.name = name;
        }
        else{
            throw new IllegalArgumentException("Ime ne moze biti prazno");
        }
    }
    /**
     * Vraca cenu dela.
     * @return price kao int.
     */
    public int getPrice() {
        return price;
    }
    /**
     * Postavlja cenu dela na zadatu vrednost.
     *
     * @param price cenu dela.
     * @throws IllegalArgumentException ako je cena prazan manja od 0.
     */
    public void setPrice(int price) {
        if(price>0) {
            this.price = price;
        }else{
            throw new IllegalArgumentException("Cena mora biti veca od 0");
        }
    }
    /**
     * Vraca marku automobila za koju je deo.
     * @return brand kao String.
     */
    public String getBrand() {
        return brand;
    }
    /**
     * Postavlja marku automobila za koju je deo na zadatu vrednost.
     *
     * @param brand  marka automobila za koju je deo.
     * @throws IllegalArgumentException ako je marka prazan String ili null.
     */
    public void setBrand(String brand) {
        if(brand!=null && !brand.isEmpty()) {
            this.brand = brand;
        }else {
            throw new IllegalArgumentException("Marka ne sme biti prazna");
        }
    }
    /**
     * Vraca model automobila za koji je deo.
     * @return model kao String.
     */
    public String getModel() {
        return model;
    }
    /**
     * Postavlja model automobila za koji je deo na zadatu vrednost.
     *
     * @param model model automobila za koji je deo.
     * @throws IllegalArgumentException ako je model prazan String ili null.
     */
    public void setModel(String model) {
        if(model!=null && !model.isEmpty()) {
            this.model = model;
        }else {
            throw new IllegalArgumentException("Model ne sme biti prazan");
        }
    }
    /**
     * Vraca dobabljaca od koga se nabavlja deo.
     * @return suplier kao Suplier.
     */
    public Suplier getSuplier() {
        return suplier;
    }

    /**
     * Postavlja dobavljaca na zadaty vrednost.
     *
     * @param suplier dobavljac dela
     * @throws IllegalArgumentException ako je dobavljac null.
     */
    public void setSuplier(Suplier suplier) {
            this.suplier = suplier;
    }
    /**
     * Poredi dva dela po ID-ju.
     * @param o koji predstavlja deo.
     * @return
     * <ul>
     *      <li> true  - ako je partId oba dela ista ili ako je unet isti objekat </li>
     *      <li> false - ako je unet null objekat ili ako nije klase Part</li>
     * </ul>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Part part = (Part) o;
        return partID == part.partID;
    }
    /**
     * Izracunava hash code na osnovu partId.
     *
     * @return hash code na osnovu partId.
     */
    @Override
    public int hashCode() {
        return Objects.hash(partID);
    }
    /**
     * toString metoda
     * @return vrednosti atributa dela kao String.
     */
    @Override
    public String toString() {
        return "Part{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}

