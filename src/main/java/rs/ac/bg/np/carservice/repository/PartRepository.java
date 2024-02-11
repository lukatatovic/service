package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Part;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {




    Optional<Part> findByNameAndBrandAndModel(String name, String brand, String model);
    //List<Part> findAll();

   
}
