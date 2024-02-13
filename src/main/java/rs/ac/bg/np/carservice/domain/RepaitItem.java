package rs.ac.bg.np.carservice.domain;

import jakarta.persistence.*;

import java.util.Objects;
/**
 * Entitet u bazi podataka/domenska klasa stavki popravke
 *
 * Koristi se da predstavi vezu sa tabelom "repait_item" iz baze podataka i kao domenski objekat.
 * Pamti sve bitne informacije o stavci popravke to jest ID stavke popravke, popravku za koju je stavka i deo koji se popravlja.
 *
 * @author Luka
 */

@Entity
public class RepaitItem {
    /**
     * Automatski generisan identifikator stavke popravke koji je unikatan i identifikuje objekat u bazi podataka.
     */
    @Id
    @GeneratedValue
    private long repaiItemId;
    /**
     * Popravka koja sadrzi stavku popravke koja je tipa Repair
     */
    @ManyToOne
    private Repair repair;
    /**
     * Deo koji se popravlja koji je tipa Part
     */
    @OneToOne
    private Part part;
    /**
     * Bezparametarski konstruktor koji inicijalizuje objekat klase RepaitItem.
     */
    public RepaitItem() {
    }

    /**
     * Parametarski konstruktor koji inicijalizuje objekat klase RepaitItem sa zadatim vrednostima
     *
     * @param repaiItemId ID stavke popravke.
     * @param repair popravka koja sadrzi stavku popravke.
     * @param part deo koji se popravlja.
     */
    public RepaitItem(long repaiItemId, Repair repair, Part part) {
        setRepaiItemId(repaiItemId);
        setRepair(repair);
        setPart(part);
    }
    /**
     * Vraca ID stavke popravke.
     * @return repaiItemId kao long.
     */
    public long getRepaiItemId() {
        return repaiItemId;
    }

    /**
     * Postavlja repaiItemId na zadatu vrednost.
     * @param repaiItemId ID stavke popravke.
     * @throws IllegalArgumentException ako je repaiItemId manji od 1.
     */
    public void setRepaiItemId(long repaiItemId) {
        if(repaiItemId>0) {
            this.repaiItemId = repaiItemId;
        }else {
            throw new IllegalArgumentException("Id mora biti veci od 0");
        }
    }


    /**
     * Vraca popravku koja sadrzi stavku popravke.
     * @return repair kao Repair.
     */

    public Repair getRepair() {
        return repair;
    }

    /**
     * Postavlja popravku na zadatu vrednost.
     * @param repair popravka koja sadrzi stavku popravke.
     */
    public void setRepair(Repair repair) {
            this.repair = repair;
    }

    /**
     * Vraca deo koji se popravlja.
     * @return part kao Part.
     */
    public Part getPart() {
        return part;
    }

    /**
     * Postavlja deo na zadatu vrednost.
     * @param part deo koji se popravlja.
     * @throws IllegalArgumentException ako je deo null.
     */
    public void setPart(Part part) {
        if(part!=null) {
            this.part = part;
        }else {
            throw new IllegalArgumentException("Deo ne moze biti null");
        }
    }
    /**
     * Poredi dve stavke popravke po ID-ju.
     * @param o koji predstavlja stavku popravke.
     * @return
     * <ul>
     *      <li> true  - ako je repaiItemId obe stavke popravke isti ili ako je unet isti objekat </li>
     *      <li> false - ako je unet null objekat ili ako nije klase RepaitItem</li>
     * </ul>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepaitItem that = (RepaitItem) o;
        return repaiItemId == that.repaiItemId;
    }
    /**
     * Izracunava hash code na osnovu repaiItemId.
     *
     * @return hash code na osnovu repaiItemId.
     */
    @Override
    public int hashCode() {
        return Objects.hash(repaiItemId);
    }
}
