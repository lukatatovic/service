package rs.ac.bg.np.carservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.bg.np.carservice.domain.Car;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {

    Optional<Car> findCarByBrandAndModelAndHorsePower(String brand, String model, int horsePower);

    Optional<Car> findCarByLicensePlates(String licensePlates);
}
