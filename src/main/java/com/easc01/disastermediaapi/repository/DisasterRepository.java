package com.easc01.disastermediaapi.repository;

import com.easc01.disastermediaapi.model.Disaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface DisasterRepository extends JpaRepository<Disaster, Long> {

    @Query("SELECT d FROM Disaster d WHERE d.recordId = :recordId")
    Optional<Disaster> findByRecordId(@Param("recordId") String recordId);

    @Query("SELECT DISTINCT d FROM Disaster d " +
            "JOIN d.videos v " +
            "WHERE (:incidentType IS NULL OR LOWER(d.incidentType) = LOWER(:incidentType)) " +
            "AND (:incidentLocation IS NULL OR LOWER(d.incidentLocation) = LOWER(:incidentLocation)) " +
            "AND (:startDate IS NULL OR :endDate IS NULL OR v.publishedDate BETWEEN :startDate AND :endDate)")
    List<Disaster> findDisastersByCriteria(
            @Param("incidentType") String incidentType,
            @Param("incidentLocation") String incidentLocation,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate
    );
}
