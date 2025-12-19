
package com.example.carquotesystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.carquotesystem.model.Car;

public interface CarRepository extends JpaRepository<Car,Long>{
}
