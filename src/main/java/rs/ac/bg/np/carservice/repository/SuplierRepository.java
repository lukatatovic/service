package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Suplier;

import java.util.List;
import java.util.Optional;
@Repository
public interface SuplierRepository extends JpaRepository<Suplier,Long> {
    //List<Suplier> getAll();

    Optional<Suplier> findByAdress(String adress);

   // Suplier save(Suplier suplier);

   // void deleteById(long suplierId);
}
