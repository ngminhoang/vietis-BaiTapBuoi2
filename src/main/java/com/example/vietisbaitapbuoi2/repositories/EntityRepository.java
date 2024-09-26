package com.example.vietisbaitapbuoi2.repositories;

import com.example.vietisbaitapbuoi2.entities.Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepository extends JpaRepository<Entity, Long> {
}
