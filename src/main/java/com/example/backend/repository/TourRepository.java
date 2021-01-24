package com.example.backend.repository;




import com.example.backend.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TourRepository extends JpaRepository <Tour, Integer> {
    List<Tour> findByPlace_id(int place_id);
    boolean existsByPlace_id(int place_id);
    Optional<Tour> findByName(String name);
    boolean existsByName(String name);
}
