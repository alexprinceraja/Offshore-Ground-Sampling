package com.example.ogs.repository;

import com.example.ogs.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByName(String name);
}
