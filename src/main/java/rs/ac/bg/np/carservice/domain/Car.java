package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
/**
 * Entitet u bazi podataka/domenska klasa automobila
 *
 * Koristi se da predstavi vezu sa tabelom "car" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o automobilu to jest carID, marka, model, registarske tablice, gorivo, vlasnik automobila i skup svih popravkih koje su uradjene na njemu.
 *
 * @author Luka
 */
@Entity
public class Car {
    /**
     * Automatski generisan identifikator automobila koji je unikatan i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long carID;
    /**
     * Model automobila koji je tipa String.
     */
    private String brand;
    /**
     * Marka automobila koja je tipa String.
     */
    private String model;
    /**
     * Registarske tablice automobila koje su tipa String.
     */
    private String licensePlates;
    /**
     * Tip goriva automobila koji je tipa String.
     */
    private String fuel;
    /**
     * Broj konjskih snaga automobila koje su tipa int.
     */
    private int horsePower;
    /**
     * Vlasnik automobila koji je tipa Owner.
     */
    @ManyToOne
    @JsonIgnore
    private Owner owner;
    /**
     * Skup svih popravkih automobila.
     */
    @OneToMany
    @JsonIgnore
    private Set<Repair> repairs;
    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase Car.
     */
    public Car() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Car sa zadatim vrednostima
     *
     * @param carID ID automobila
     * @param brand marka automobila
     * @param model model automobila
     * @param licensePlates registarske tablice automobila
     * @param fuel tip goriva automobila
     * @param horsePower broj konjskih snaga automobia
     * @param owner vlasnik automobila
     * @param repairs skup svih popravkih automobila
     */
    public Car(long carID, String brand, String model, String licensePlates, String fuel, int horsePower, Owner owner,Set<Repair> repairs) {
        setCarID(carID);
        setBrand(brand);
        setModel(model);
        setLicensePlates(licensePlates);
        setFuel(fuel);
        setHorsePower(horsePower);
        setOwner(owner);
        setRepairs(repairs);
    }
    /**
     * Vraca ID automobila.
     * @return carID kao long.
     */
    public long getCarID() {
        return carID;
    }

    /**
     * Postavlja carID na zadatu vrednost.
     *
     * @param carID ID automobila
     * @throws IllegalArgumentException ako je carID manji od 1
     */
    public void setCarID(long carID) {
        if(carID>0) {
            this.carID = carID;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }
    }

    /**
     * Vraca marku automobila.
     * @return brand kao String.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Postavlja marku na zadatu vrednost.
     *
     * @param brand marka automobila
     * @throws IllegalArgumentException ako je marka prazan String ili null.
     */
    public void setBrand(String brand) {
        if(!brand.isEmpty()&&brand!=null) {
            this.brand = brand;
        }else {
            throw new IllegalArgumentException("Marka ne sme biti prazna");
        }

    }
    /**
     * Vraca model automobila.
     * @return model kao String.
     */
    public String getModel() {
        return model;
    }

    /**
     * Postavlja model na zadatu vrednost.
     *
     * @param model model automobila.
     * @throws IllegalArgumentException ako je model prazan String ili null.
     */
    public void setModel(String model) {
        if(!model.isEmpty()&&model!=null) {
            this.model = model;
        }else {
            throw new IllegalArgumentException("Model ne sme biti prazan");
        }
    }
    /**
     * Vraca registarske tablice automobila.
     * @return licensePlates kao String.
     */
    public String getLicensePlates() {
        return licensePlates;
    }
    /**
     * Postavlja registarske tablice  na zadatu vrednost.
     *
     * @param licensePlates registarske tablice automobila.
     * @throws IllegalArgumentException ako su registarske tablice prazan String ili null.
     */
    public void setLicensePlates(String licensePlates) {
        if(!licensePlates.isEmpty()&&licensePlates!=null) {
            this.licensePlates = licensePlates;
        }else {
            throw new IllegalArgumentException("Tablice ne smeju biti prazne");
        }
    }
    /**
     * Vraca tip goriva automobila.
     * @return fuel kao String.
     */
    public String getFuel() {
        return fuel;
    }
    /**
     * Postavlja tip goriva na zadatu vrednost.
     *
     * @param fuel registarske tablice automobila.
     * @throws IllegalArgumentException ako je tip goriva prazan String ili null.
     */
    public void setFuel(String fuel) {
        if(!fuel.isEmpty()&&fuel!=null) {
            this.fuel = fuel;
        }else {
            throw new IllegalArgumentException("Gorivo ne sme biti prazno");
        }
    }
    /**
     * Vraca broj konjskih snaga automobila.
     * @return horsePower kao int.
     */
    public int getHorsePower() {
        return horsePower;
    }

    /**
     * Postavlja broj konjskih snaga na zadaty vrednost.
     *
     * @param horsePower broj konjskih snaga automobila.
     * @throws IllegalArgumentException ako je broj konjskih snaga manji od 1.
     */
    public void setHorsePower(int horsePower) {
        if(horsePower>0) {
            this.horsePower = horsePower;
        }else {
            throw new IllegalArgumentException("Broj konjskih snaga mora biti veci od 0");
        }
    }
    /**
     * Vraca vlasnika automobila.
     * @return owner kao Owner.
     */
    public Owner getOwner() {
        return owner;
    }

    /**
     * Postavlja vlasnika na zadatu vrednost.
     *
     * @param owner vlasnik automobila.
     * @throws IllegalArgumentException ako je vlasnik null.
     */
    public void setOwner(Owner owner) {
        if(owner!=null) {
            this.owner = owner;
        }else{
            throw new IllegalArgumentException("Vlasnik ne sme biti null");
        }
    }

    /**
     * Vraca skup svih popravkih automobila.
     *
     * @return repairs kao skup  popravkih automobila
     */
    public Set<Repair> getRepairs() {
        return repairs;
    }

    /**
     * Postavlja repairs na zadatu vrednost.
     *
     * @param repairs kao skup svih popravkih automobila.
     * @throws IllegalArgumentException ako je skup popravki null.
     */
    public void setRepairs(Set<Repair> repairs) {
        if(repairs!=null) {
            this.repairs = repairs;
        }else {
            throw new IllegalArgumentException("Popravke ne mogu biti null");
        }
    }
}
