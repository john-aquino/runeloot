package com.johnaquino.runeloot.repository;



import com.johnaquino.runeloot.model.TemporossRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface TemporossRecordRepository extends JpaRepository<TemporossRecord, Long> {
    TemporossRecord findByUserId(String userId);
}