package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;
@Entity
public class Car {
    @Id
    @GeneratedValue
    private long carID;
    private String brand;
    private String model;
    private String licensePlates;
    private String fuel;
    private int horsePower;
    @ManyToOne
    @JsonIgnore
    private Owner owner;

    @OneToMany
    @JsonIgnore
    private Set<Repair> repairs;

    public Car() {
    }

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

    public long getCarID() {
        return carID;
    }

    public void setCarID(long carID) {
        if(carID>0) {
            this.carID = carID;
        }else{
            throw new IllegalArgumentException("ID mora biti veci od 0");
        }
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if(!brand.isEmpty()&&brand!=null) {
            this.brand = brand;
        }else {
            throw new IllegalArgumentException("Marka ne sme biti prazna");
        }

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if(!model.isEmpty()&&model!=null) {
            this.model = model;
        }else {
            throw new IllegalArgumentException("Model ne sme biti prazan");
        }
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        if(!licensePlates.isEmpty()&&licensePlates!=null) {
            this.licensePlates = licensePlates;
        }else {
            throw new IllegalArgumentException("Tablice ne smeju biti prazne");
        }
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        if(!fuel.isEmpty()&&fuel!=null) {
            this.fuel = fuel;
        }else {
            throw new IllegalArgumentException("Gorivo ne sme biti prazno");
        }
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        if(horsePower>0) {
            this.horsePower = horsePower;
        }else {
            throw new IllegalArgumentException("Broj konjskih snaga mora biti veci od 0");
        }
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        if(owner!=null) {
            this.owner = owner;
        }else{
            throw new IllegalArgumentException("Vlasnik ne sme biti null");
        }
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
