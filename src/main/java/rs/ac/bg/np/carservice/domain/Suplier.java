package rs.ac.bg.np.carservice.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
@Entity
public class Suplier {
    @Id
    @GeneratedValue
    private long suplierId;
    private String name;
    private String adress;
    private String phoneNumber;

    public Suplier() {
    }

    public Suplier(long suplierId, String name, String adress, String phoneNumber) {
        setSuplierId(suplierId);
        setName(name);
        setAdress(adress);
        setPhoneNumber(phoneNumber);
    }

    public long getSuplierId() {
        return suplierId;
    }

    public void setSuplierId(long suplierId) {
        if(suplierId>0) {
            this.suplierId = suplierId;
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

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        if(!adress.isEmpty() && adress!=null) {
            this.adress = adress;
        }
        else{
            throw new IllegalArgumentException("Adresa ne moze biti prazna");
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
}
