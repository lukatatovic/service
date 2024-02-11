package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Servicer;

import java.util.List;
import java.util.Optional;
@Repository
public interface ServicerRepository extends JpaRepository<Servicer,Long> {
    Optional<Servicer> findByNameAndSurname(String name, String surname);

   // Optional<Servicer> findById(long servicerID);

}
