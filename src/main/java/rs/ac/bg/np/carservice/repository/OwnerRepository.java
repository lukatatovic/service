package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Owner;

import java.util.Optional;

@Repository
public interface OwnerRepository extends JpaRepository<Owner,Long> {
    Optional<Owner> findByPhoneNumber(String phoneNumber);

    //Owner saveOwner(Owner owner);


}
