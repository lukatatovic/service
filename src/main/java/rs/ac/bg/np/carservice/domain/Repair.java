package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;
/**
 * Entitet u bazi podataka/domenska klasa popravke
 *
 * Koristi se da predstavi vezu sa tabelom "repair" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o popravci to jest repairID, serviser zaduzen za popravku, automobil koji se popravlja, datum, cena i skup stavki popravke.
 *
 * @author Luka
 */
@Entity
public class Repair {
    /**
     * Automatski generisan identifikator popravke koji je unikatan i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long repairID;
    /**
     * Serviser zaduzen za popravku tipa Servicer.
     */
    @ManyToOne
    private Servicer servicer;
    /**
     * Automobil koji se popravlja tipa Car.
     */
    @ManyToOne
    private Car car;
    /**
     * Datum popravke tipa Date.
     */
    private Date date;
    /**
     * Cena popravke tipa int.
     */
    private int price;
    /**
     * Skup stavki popravke.
     */
    @OneToMany
    @JsonIgnore
    private Set<RepaitItem> items;


    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase Repair.
     */
    public Repair() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase Repair sa zadatim vrednostima
     *
     * @param repairID ID popravke
     * @param servicer serviser zaduzen za popravku
     * @param car automobil koji se popravlja
     * @param date datum popravke
     * @param price cena popravke
     * @param items skup stavki popravke
     */
    public Repair(long repairID, Servicer servicer, Car car, Date date, int price,Set<RepaitItem> items) {
        setRepairID(repairID);
        setServicer(servicer);
        setCar(car);
        setDate(date);
        setPrice(price);
        setItems(items);
    }
    /**
     * Vraca ID popravke.
     * @return repairID kao long.
     */
    public long getRepairID() {
        return repairID;
    }
    /**
     * Postavlja repairID na zadatu vrednost.
     *
     * @param repairID ID servisa.
     * @throws IllegalArgumentException ako je repairID manji od 1.
     */
    public void setRepairID(long repairID) {
        if(repairID>0) {
            this.repairID = repairID;
        }else {
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }
    }

    /**
     * Vraca servisera koji je zaduzen za popravku.
     * @return servicer kao Servicer.
     */
    public Servicer getServicer() {
        return servicer;
    }
    /**
     * Postavlja servisera na zadatu vrednost.
     *
     * @param servicer serviser zaduzen za popravku.
     * @throws IllegalArgumentException ako je serviser null.
     */
    public void setServicer(Servicer servicer) {
        if(servicer!=null){
        this.servicer = servicer;
        }else {
            throw new IllegalArgumentException("Serviser ne sme biti null");
        }

    }
    /**
     * Vraca automobil koji se popravlja.
     * @return car kao Car.
     */
    public Car getCar() {
        return car;
    }
    /**
     * Postavlja automobil na zadatu vrednost.
     *
     * @param car automobil koji se popravlja.
     * @throws IllegalArgumentException ako je automobil null.
     */
    public void setCar(Car car) {
        if(car!=null){
            this.car = car;
        }else {
            throw new IllegalArgumentException("Automobil ne sme biti null");
        }
    }
    /**
     * Vraca datum popravke.
     * @return date kao Date.
     */
    public Date getDate() {
        return date;
    }
    /**
     * Postavlja datum na zadatu vrednost.
     *
     * @param date datum popravke.
     * @throws IllegalArgumentException ako je datum null.
     */
    public void setDate(Date date) {
        if(date!=null){
            this.date = date;
        }else {
            throw new IllegalArgumentException("Datum ne sme biti null");
        }
    }
    /**
     * Vraca cennu popravke.
     * @return price kao int.
     */
    public int getPrice() {
        return price;
    }
    /**
     * Postavlja cenu na zadatu vrednost.
     *
     * @param price cenu popravke.
     * @throws IllegalArgumentException ako je cena veca ili jednaka od 0.
     */
    public void setPrice(int price) {
        if(price>=0) {
            this.price = price;
        }else{
            throw new IllegalArgumentException("Cena mora biti pozitivna");
        }
    }
    /**
     * Vraca stavke popravke za datu popravku.
     *
     * @return items kao skup stavki popravke.
     */
    public Set<RepaitItem> getItems() {
        return items;
    }
    /**
     * Postavlja stavke popravke na zadatu vrednost.
     *
     * @param items kao skup stavki popravke.
     * @throws IllegalArgumentException ako su skup stavki popravke null.
     */
    public void setItems(Set<RepaitItem> items) {
        if(items!=null && !items.isEmpty()) {
            this.items = items;
        }else {
            throw new IllegalArgumentException("Mora postojati bar 1 stavka popravke");
        }
    }
}
