package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
public class Part {
    @Id
    @GeneratedValue
    private long partID;
    private String name;
    private int price;
    private String brand;
    private String model;
    @OneToOne
    @JsonIgnore
    private Suplier suplier;

    public Part() {
    }

    public Part(long partID, String name, int price, String brand, String model, Suplier suplier) {
        setPartID(partID);
        setName(name);
        setPrice(price);
        setBrand(brand);
        setModel(model);
        setSuplier(suplier);
    }

    public long getPartID() {
        return partID;
    }

    public void setPartID(long partID) {
        if(partID>0) {
            this.partID = partID;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if(price>0) {
            this.price = price;
        }else{
            throw new IllegalArgumentException("Cena mora biti veca od 0");
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

    public Suplier getSuplier() {
        return suplier;
    }

    public void setSuplier(Suplier suplier) {
        if(suplier!=null) {
            this.suplier = suplier;
        }else {
            throw new IllegalArgumentException("Dobavljac ne sme biti null");
        }
    }
}
