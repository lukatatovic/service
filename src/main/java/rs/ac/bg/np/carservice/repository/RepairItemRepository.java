package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Part;
import rs.ac.bg.np.carservice.domain.RepaitItem;

import java.util.Optional;

@Repository
public interface RepairItemRepository extends JpaRepository<RepaitItem,Long> {
    Optional<RepaitItem> findByPart(Part part);
}
