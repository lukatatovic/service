package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Repair;

import java.util.List;
@Repository
public interface RepairRepository extends JpaRepository<Repair,Long> {
    //List<Repair> findAll();
}
