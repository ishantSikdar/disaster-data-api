package com.easc01.disasterdataapi.repository;

import com.easc01.disasterdataapi.model.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisasterRepository extends JpaRepository<Disaster, Long> {
}
