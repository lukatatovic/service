package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Service;

import java.util.Optional;
@Repository
public interface ServiceRepository extends JpaRepository<Service,Long> {
    Optional<Service> findByName(String name);

   // Optional<Service> findById(long serviceId);

   //Service saveService(Service service);


}
